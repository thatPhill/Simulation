package entities.creature;

import entities.Entity;
import pathfinders.BreadthFirstSearch;
import world.Coordinates;
import world.WorldConfig;
import world.WorldMap;

import java.util.List;

public class Predator extends Creature {

    private static final int DEFAULT_SPEED = 2;
    private static final int DEFAULT_HEALTH = 100;
    private int damage;

    public Predator(Coordinates coordinates, String emoji, int speed, int health, int damage) {
        super(coordinates, emoji, speed, health);
        this.damage = damage;
    }

    public Predator(Coordinates coordinates, String emoji) {
        super(coordinates, emoji, DEFAULT_SPEED, DEFAULT_HEALTH);
    }

    @Override
    public void makeMove(BreadthFirstSearch pathfinder, WorldMap worldMap, WorldConfig worldConfig) {
        Coordinates start = getCoordinates();
        Coordinates herbivore = findNearestTarget(worldMap, pathfinder);
        List<Coordinates> path = pathfinder.findPath(start, herbivore);
        if (path == null || path.isEmpty()) return;

        Coordinates next = null;

        next = getNextStep(path, worldMap.getEntity(herbivore));

        attack(worldMap, herbivore, worldConfig);

        this.setHealth(this.getHealth() - getSpeed());

        if (this.getHealth() <= 0){
            worldMap.removeEntity(this.getCoordinates());
            worldConfig.setPredators(worldConfig.getPredators() - 1);
            return;
        }

        Entity targetCell = worldMap.getEntity(next);
        if (!(targetCell instanceof Herbivore)) {
            worldMap.removeEntity(start);
            setCoordinates(next);
            worldMap.setEntity(next, this);
        }


    }

    private void attack(WorldMap worldMap, Coordinates herbivore, WorldConfig worldConfig) {
        List<Coordinates> neighbours = worldMap.getNeighbours(this.getCoordinates(), this);
        for (Coordinates neighbour : neighbours) {
            Creature target = worldMap.getCreature(neighbour);
            if (target instanceof Herbivore) {
                setDamage(10);
                target.setHealth(target.getHealth() - getDamage());
                if (target.getHealth() <= 0){
                    worldConfig.setHerbivores(worldConfig.getHerbivores() - 1);
                    worldMap.removeEntity(herbivore);
                }
            }
        }
    }

    //Helper method for findNearestTarget
    @Override
    public boolean isTarget(Entity entity) {
        return entity instanceof Herbivore;
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
