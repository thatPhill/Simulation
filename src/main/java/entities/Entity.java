package entities;

import mapetc.Coordinates;

public abstract class Entity {
private Coordinates coordinates;
private final String emoji;

    public Entity(Coordinates coordinates, String emoji) {
        this.coordinates = coordinates;
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }


}
