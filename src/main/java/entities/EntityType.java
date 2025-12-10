package entities;

public enum EntityType {
    HERBIVORE("🐇"),
    PREDATOR("🦊"),
    GRASS("🌿"),
    ROCK("⛰️"),
    TREE("🌳"),
    EMPTY("⬛");

    private final String emoji;

    EntityType(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }
}

