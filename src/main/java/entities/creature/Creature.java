package entities.creature;

import entities.Entity;
import entities.resource.Grass;
import pathfinders.BreadthFirstSearch;
import world.Coordinates;
import world.WorldConfig;
import world.WorldMap;

import java.util.List;

public abstract class Creature extends Entity {
    private int speed;
    private int health;


    public Creature(Coordinates coordinates, int speed, int health) {
        super(coordinates);
        this.speed = speed;
        this.health = health;
    }

    public abstract void makeMove(BreadthFirstSearch pathfinder, WorldMap worldMap, WorldConfig worldConfig);

    public abstract boolean isTarget(Entity entity);


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
