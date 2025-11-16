package entities.creature;

import pathfinders.BreadthFirstSearch;
import world.Coordinates;
import world.WorldMap;

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
    public Coordinates makeMove(BreadthFirstSearch pathfinder, WorldMap worldMap) {

        return null;
    }

    //Helper method for findNearestTarget
    @Override
    public boolean isTarget(Object entity) {
        return entity instanceof Herbivore;
    }


    public int attack(){
        return 5;
    };


}
