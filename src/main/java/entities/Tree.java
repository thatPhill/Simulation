package entities;

import mapetc.Coordinates;

public class Tree extends Entity implements StaticEntity {
    public Tree(Coordinates coordinates, String emoji) {
        super(coordinates, emoji);
    }
}
