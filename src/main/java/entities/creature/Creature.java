package entities.creature;

import entities.Entity;
import pathfinders.BreadthFirstSearch;
import world.Coordinates;
import world.WorldConfig;
import world.WorldMap;

public abstract class Creature extends Entity {
    private int speed;
    private int health;


    public Creature(Coordinates coordinates, int speed, int health) {
        super(coordinates);
        this.speed = speed;
        this.health = health;
    }

    public abstract void makeMove(BreadthFirstSearch pathfinder, WorldMap worldMap, WorldConfig worldConfig);

    public abstract Class<? extends Entity> getTarget();


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
