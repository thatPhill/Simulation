package entities.creature;

import entities.resource.Grass;
import pathfinders.BreadthFirstSearch;
import world.Coordinates;
import world.WorldMap;

import java.util.List;

public class Herbivore extends Creature {

    private static final int DEFAULT_SPEED = 1;
    private static final int DEFAULT_HEALTH = 3;

    public Herbivore(Coordinates coordinates, String emoji, int speed, int health) {
        super(coordinates, emoji, speed, health);
    }

    public Herbivore(Coordinates coordinates, String emoji, int speed) {
        super(coordinates, emoji, speed, DEFAULT_HEALTH);
    }

    public Herbivore(Coordinates coordinates, String emoji) {
        super(coordinates, emoji, DEFAULT_SPEED, DEFAULT_HEALTH);
    }

    @Override
    public Coordinates makeMove(BreadthFirstSearch pathfinder, WorldMap worldMap) {
        Coordinates start = getCoordinates();
        Coordinates grass = findNearestTarget(worldMap, pathfinder);
        List<Coordinates> path = pathfinder.findPath(start, grass);
        if (path == null || path.isEmpty()) return start;

        Coordinates next = null;
        this.setHealth(this.getHealth() - 1);
        next = getNextStep(path, next);

        if (next.equals(grass)) this.setHealth(this.getHealth() + 5);


        worldMap.removeEntity(start);
        setCoordinates(next);
        worldMap.setEntity(next, this);

        if (this.getHealth() <= 0) worldMap.removeEntity(this.getCoordinates());

        return next;
    }



    //Helper method for findNearestTarget
    @Override
    public boolean isTarget(Object entity) {
        return entity instanceof Grass;
    }


}
