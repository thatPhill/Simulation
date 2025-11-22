package entities.creature;

import entities.Entity;
import pathfinders.BreadthFirstSearch;
import world.Coordinates;
import world.WorldMap;

import java.util.List;

public class Predator extends Creature {

    private static final int DEFAULT_SPEED = 2;
    private static final int DEFAULT_HEALTH = 10;

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

        if (next.equals(herbivore)) worldMap.getCreature(herbivore).setHealth(getHealth() - attack());


        worldMap.clearCell(start);
        setCoordinates(next);
        worldMap.setEntity(next, this);


    }

    //Helper method for findNearestTarget
    @Override
    public boolean isTarget(Entity entity) {
        return entity instanceof Herbivore;
    }


    public int attack(){
        return 5;
    };


}
