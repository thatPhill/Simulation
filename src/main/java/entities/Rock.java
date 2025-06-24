package entities;

import mapetc.Coordinates;

public class Rock extends Entity implements StaticEntity {
    public Rock(Coordinates coordinates, String emoji) {
        super(coordinates, emoji);
    }
}
