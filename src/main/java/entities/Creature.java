package entities;

import pathfinders.BreadthFirstSearch;
import world.Coordinates;
import world.WorldMap;

public abstract class Creature extends Entity {
    private int speed;
    private int health;


    public Creature(Coordinates coordinates, String emoji, int speed, int health) {
        super(coordinates, emoji);
        this.speed = speed;
        this.health = health;
    }

    public abstract void makeMove(WorldMap worldMap, BreadthFirstSearch pathfinder);


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
