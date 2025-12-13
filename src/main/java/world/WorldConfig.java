package world;

public class WorldConfig {

    private int size;
    private int grass;
    private int rocks;
    private int trees;
    private int herbivores;
    private int predators;

    public WorldConfig(int size, int grass, int rocks, int trees, int herbivores, int predators) {
        if (size >= 10 && size <= 50) {
            if (grass > (size / 2)) {
                throw new IllegalArgumentException("Too much grass.");
            }
            if (rocks > (size / 2)) {
                throw new IllegalArgumentException("Too much rock.");
            }
            if (trees > (size / 2)) {
                throw new IllegalArgumentException("Too much trees.");
            }
            if (herbivores > (size / 3)) {
                throw new IllegalArgumentException("Too much herbivores.");
            }
            if (predators > (size / 3)) {
                throw new IllegalArgumentException("Too much predators.");
            }
            this.size = size;
            this.grass = grass;
            this.rocks = rocks;
            this.trees = trees;
            this.herbivores = herbivores;
            this.predators = predators;
        } else {
            throw new IllegalArgumentException("Size must be between 10 and 50.");
        }
    }

    public int getSize() {
        return size;
    }

    public int getGrass() {
        return grass;
    }

    public int getRocks() {
        return rocks;
    }

    public int getTrees() {
        return trees;
    }

    public int getHerbivores() {
        return herbivores;
    }

    public int getPredators() {
        return predators;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setGrass(int grass) {
        this.grass = grass;
    }

    public void setRocks(int rocks) {
        this.rocks = rocks;
    }

    public void setTrees(int trees) {
        this.trees = trees;
    }

    public void setHerbivores(int herbivores) {
        this.herbivores = herbivores;
    }

    public void setPredators(int predators) {
        this.predators = predators;
    }
}
