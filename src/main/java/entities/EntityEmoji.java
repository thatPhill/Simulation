package entities;


    public enum EntityEmoji {
        ROCK("⛰️"),
        GRASS("🌿"),
        TREE("🌳"),
        HERBIVORE("🐇"),
        PREDATOR("🦊"),
        EMPTY("⬛");

        private final String emoji;

        EntityEmoji(String emoji) {
            this.emoji = emoji;
        }

        public String getEmoji() {
            return emoji;
        }
    }

