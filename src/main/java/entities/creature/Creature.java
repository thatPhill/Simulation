package entities.creature;

import entities.Entity;
import entities.resource.Grass;
import pathfinders.BreadthFirstSearch;
import world.Coordinates;
import world.WorldMap;

import java.util.List;

public abstract class Creature extends Entity {
    private int speed;
    private int health;


    public Creature(Coordinates coordinates, String emoji, int speed, int health) {
        super(coordinates, emoji);
        this.speed = speed;
        this.health = health;
    }

    public abstract Coordinates makeMove(BreadthFirstSearch pathfinder, WorldMap worldMap);

    public abstract boolean isTarget(Entity entity);

    public Coordinates findNearestTarget(WorldMap worldMap, BreadthFirstSearch pathfinder) {
        Coordinates creatureCoordinates = getCoordinates();
        int minimalSizePath = Integer.MAX_VALUE;
        Coordinates nearestTargetCoordinates = null;
        for (Coordinates mapCoordinates : worldMap.getEntitiesMap().keySet()) {
            if (isTarget(worldMap.getEntitiesMap().get(mapCoordinates))) {
                pathfinder.setMover(this);
                int currentSize = pathfinder.findPath(creatureCoordinates, mapCoordinates).size();
                if (currentSize < minimalSizePath) {
                    minimalSizePath = currentSize;
                    nearestTargetCoordinates = mapCoordinates;
                }
            }
        }
        return nearestTargetCoordinates;
    }

     Coordinates getNextStep(List<Coordinates> path, Coordinates next, Entity target) {
        if (path.size() <= getSpeed() && target instanceof Grass) {
            next = path.getLast();
        } else if (path.size() <= getSpeed() && target instanceof Herbivore) {
            next = path.get(path.size() - 2);
        }
        else if (getSpeed() < path.size()) {
            next = path.get(getSpeed());
        }
        return next;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
