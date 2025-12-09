package entities.creature;

import entities.Entity;
import pathfinders.BreadthFirstSearch;
import world.Coordinates;
import world.WorldMap;

import java.util.List;

public class Predator extends Creature {

    private static final int DEFAULT_SPEED = 5;
    private static final int DEFAULT_HEALTH = 100;
    private int damage = 50;

    public Predator(Coordinates coordinates, String emoji, int speed, int health) {
        super(coordinates, emoji, speed, health);
    }

    public Predator(Coordinates coordinates, String emoji) {
        super(coordinates, emoji, DEFAULT_SPEED, DEFAULT_HEALTH);
    }

    @Override
    public void makeMove(BreadthFirstSearch pathfinder, WorldMap worldMap) {
        Coordinates start = getCoordinates();
        Coordinates herbivore = findNearestTarget(worldMap, pathfinder);
        List<Coordinates> path = pathfinder.findPath(start, herbivore);
        if (path == null || path.isEmpty()) return;

        Coordinates next = null;

        next = getNextStep(path, worldMap.getEntity(herbivore));

        attack(worldMap, next, herbivore);

        this.setHealth(this.getHealth() - getSpeed());

        if (this.getHealth() <= 0){
            worldMap.removeEntity(this.getCoordinates());
            return;
        }

        Entity targetCell = worldMap.getEntity(next);
        if (!(targetCell instanceof Herbivore)) {
            worldMap.removeEntity(start);
            setCoordinates(next);
            worldMap.setEntity(next, this);
        }


    }

    private void attack(WorldMap worldMap, Coordinates next, Coordinates herbivore) {
        if (next.equals(herbivore)){
            Herbivore target = (Herbivore) worldMap.getCreature(herbivore);
            target.setHealth(target.getHealth() - damage);

            if (target.getHealth() <= 0){
                worldMap.removeEntity(herbivore);
            }
        }
    }

    //Helper method for findNearestTarget
    @Override
    public boolean isTarget(Entity entity) {
        return entity instanceof Herbivore;
    }




}
