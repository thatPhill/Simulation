package entities;

import mapetc.Coordinates;

public class Herbivore extends Creature{

    public Herbivore(Coordinates coordinates, String emoji, int speed, int health) {
        super(coordinates, emoji, speed, health);
    }

    @Override
    void makeMove() {

    }
}
