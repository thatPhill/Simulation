package mapetc;

import entities.*;

import java.util.HashMap;
import java.util.Random;

public class Map {
    int grass = 10;
    int rocks = 10;
    int tree = 8;
    int herbivore = 5;
    int predator = 3;

    Random rand = new Random();
    public int sizeMap = 10;

   private HashMap<Coordinates, Entity> entitiesMap = new HashMap<>();

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        entitiesMap.put(coordinates, entity);
    }


    public void setupEntitiesPositions() {
        while (grass > 0 || rocks > 0 || tree > 0 || herbivore > 0 || predator > 0) {
            for (int vertical = 0; vertical < sizeMap; vertical++) {
                for (int horizontal = 0; horizontal < sizeMap; horizontal++) {
                    switch (rand.nextInt(15)) {
                        case 1 -> {
                            if (grass > 0) {
                                grass--;
                                setEntity(new Coordinates(horizontal, vertical), new Grass(new Coordinates(horizontal, vertical), EntityEmoji.GRASS.getEmoji()));
                            }
                        }
                        case 2 -> {
                            if (rocks > 0) {
                            rocks--;
                            setEntity(new Coordinates(horizontal, vertical), new Rock(new Coordinates(horizontal, vertical), EntityEmoji.ROCK.getEmoji()));
                            }
                        }
                        case 3 -> {
                            if (tree > 0) {
                                tree--;
                                setEntity(new Coordinates(horizontal, vertical), new Tree(new Coordinates(horizontal, vertical), EntityEmoji.TREE.getEmoji()));
                            }
                        }
                        case 4 -> {
                            if (herbivore > 0) {
                                herbivore--;
                                setEntity(new Coordinates(horizontal, vertical), new Herbivore(new Coordinates(horizontal, vertical), EntityEmoji.HERBIVORE.getEmoji()));
                            }
                        }
                        case 5 -> {
                            if (predator > 0) {
                                predator--;
                                setEntity(new Coordinates(horizontal, vertical), new Predator(new Coordinates(horizontal, vertical), EntityEmoji.PREDATOR.getEmoji()));
                            }
                        }
                    }
                }
            }
        }
    }

    private void initEntities() {

    }


    public HashMap<Coordinates, Entity> getEntitiesMap() {
        return entitiesMap;
    }
}
