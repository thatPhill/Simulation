package world;

public class WorldConfig {
    private int size = 20;
    private int grassCount = 10;
    private int rocksCount = 10;
    private int treesCount = 10;
    private int herbivoresCount = 5;
    private int predatorsCount = 3;

    public int getSize() {
        return size;
    }

    public int getGrassCount() {
        return grassCount;
    }

    public int getRocksCount() {
        return rocksCount;
    }

    public int getTreesCount() {
        return treesCount;
    }

    public int getHerbivoresCount() {
        return herbivoresCount;
    }

    public int getPredatorsCount() {
        return predatorsCount;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setGrassCount(int grassCount) {
        this.grassCount = grassCount;
    }

    public void setRocksCount(int rocksCount) {
        this.rocksCount = rocksCount;
    }

    public void setTreesCount(int treesCount) {
        this.treesCount = treesCount;
    }

    public void setHerbivoresCount(int herbivoresCount) {
        this.herbivoresCount = herbivoresCount;
    }

    public void setPredatorsCount(int predatorsCount) {
        this.predatorsCount = predatorsCount;
    }
}
