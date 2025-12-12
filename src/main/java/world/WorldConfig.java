package world;

public class WorldConfig {
    private int size = 20;
    private int grass = 10;
    private int rocks = 10;
    private int trees = 10;
    private int herbivores = 5;
    private int predators = 3;

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
