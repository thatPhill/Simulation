package entities.creature;

import entities.resource.Grass;
import pathfinders.BreadthFirstSearch;
import world.Coordinates;
import world.WorldMap;

import java.util.List;

public class Herbivore extends Creature {

    private static final int DEFAULT_SPEED = 1;
    private static final int DEFAULT_HEALTH = 5;

    public Herbivore(Coordinates coordinates, String emoji, int speed, int health) {
        super(coordinates, emoji, speed, health);
    }

    public Herbivore(Coordinates coordinates, String emoji) {
        super(coordinates, emoji, DEFAULT_SPEED, DEFAULT_HEALTH);
    }

    @Override
    public Coordinates makeMove(BreadthFirstSearch pathfinder, WorldMap worldMap) {
        Coordinates start = getCoordinates();
        Coordinates grass = findNearestGrass(worldMap, pathfinder);
        List<Coordinates> path = pathfinder.findPath(start, grass);
        if (path == null || path.isEmpty()) return start;

        Coordinates next = null;

        if (path.size() <= getSpeed()){
            next = path.getLast();
        } else if (getSpeed() < path.size()) {
            next = path.get(getSpeed());
        }

        worldMap.removeEntity(start);
        setCoordinates(next);
        worldMap.setEntity(next, this);
        return next;
    }


    public Coordinates findNearestGrass(WorldMap worldMap, BreadthFirstSearch pathfinder) {
        Coordinates herbivoreCoordinates = getCoordinates();
        int minimalSizePath = Integer.MAX_VALUE;
        Coordinates nearestGrassCoordinates = null;
        for (Coordinates mapCoordinates : worldMap.getEntitiesMap().keySet()) {
            if (worldMap.getEntitiesMap().get(mapCoordinates) instanceof Grass) {
                int currentSize = pathfinder.findPath(herbivoreCoordinates, mapCoordinates).size();
                if (currentSize < minimalSizePath) {
                    minimalSizePath = currentSize;
                    nearestGrassCoordinates = mapCoordinates;
                }
            }
        }
        return nearestGrassCoordinates;
    }

}
