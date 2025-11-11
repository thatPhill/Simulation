package entities;

import world.Coordinates;

public abstract class Entity {
    private Coordinates coordinates;
    private final String emoji;

    public Entity(Coordinates coordinates, String emoji) {
        this.emoji = emoji;
        this.coordinates = coordinates;
    }

    public Coordinates getCoorditanes() {
        return coordinates;
    }

    public void setCoorditanes(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getEmoji() {
        return emoji;
    }
}
