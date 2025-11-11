package entities.stative;

import entities.Entity;
import world.Coordinates;

public abstract class Stative extends Entity {

    public Stative(Coordinates coordinates, String emoji) {
        super(coordinates, emoji);
    }
}
