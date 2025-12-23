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
    private static final int DEFAULT_DAMAGE = 10;
    private int damage;

    public Predator(Coordinates coordinates, int speed, int health, int damage) {
        super(coordinates, speed, health);
        this.damage = damage;
    }

    public Predator(Coordinates coordinates) {
        super(coordinates, DEFAULT_SPEED, DEFAULT_HEALTH);
        this.damage = DEFAULT_DAMAGE;
    }

    @Override
    public void makeMove(BreadthFirstSearch pathfinder, WorldMap worldMap, WorldConfig worldConfig) {
        pathfinder.setMover(this);
        Coordinates start = getCoordinates();
        List<Coordinates> path = pathfinder.find(worldMap, this.getCoordinates(), Herbivore.class);

        if (path == null || path.isEmpty()) {
            return;
        }

        Coordinates next = pathfinder.getNextStep(worldMap, path);
        Entity target = worldMap.getEntity(path.getLast());

        if (next == null) {
            if (target instanceof Herbivore) {
                System.out.println("hp herbivore before attck - " + ((Herbivore) target).getHealth());
                System.out.println("Predator on cell" + this.getCoordinates() + "attack herbivore on " + target.getCoordinates() + "hp herbivore after this " + ((Herbivore) target).getHealth());
                attack(worldMap, path.getLast(), worldConfig, pathfinder);
            }
            return;
        }

        if (path.get(path.size() - 2).equals(next)) {
            attack(worldMap, path.getLast(), worldConfig, pathfinder);
        }

        this.setHealth(this.getHealth() - getSpeed());

        if (this.getHealth() <= 0) {
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

    private void attack(WorldMap worldMap, Coordinates targetCell, WorldConfig worldConfig, BreadthFirstSearch bfs) {
        List<Coordinates> neighbours = bfs.getNeighbours(this.getCoordinates(), this);
        for (Coordinates neighbour : neighbours) {
            Creature target = (Creature) worldMap.getEntity(neighbour);
            if (target instanceof Herbivore) {
                target.setHealth(target.getHealth() - getDamage());
                if (target.getHealth() <= 0) {
                    worldConfig.setHerbivores(worldConfig.getHerbivores() - 1);
                    worldMap.removeEntity(targetCell);
                }
            }
        }
    }

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
