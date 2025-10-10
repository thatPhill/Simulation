package entities;

import world.Coordinates;

public abstract class Creature extends Entity {
    private int speed;
    private int health;


    public Creature(Coordinates coordinates, String emoji, int speed, int health) {
        super(coordinates, emoji);
        this.speed = speed;
        this.health = health;
    }

    abstract void makeMove();


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
