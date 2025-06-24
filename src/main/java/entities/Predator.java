package entities;

import mapetc.Coordinates;

public class Predator extends Creature{
    private int defaultSpeed = 2;
    private int defaultHealth = 10;

    public Predator(Coordinates coordinates, String emoji, int speed, int health) {
        super(coordinates, emoji, speed, health);
    }


    @Override
    void makeMove() {

    }


}
