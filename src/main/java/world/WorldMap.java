package world;

import entities.Entity;
import entities.EntityFactory;
import entities.EntityType;
import entities.creature.Creature;
import entities.creature.Herbivore;
import entities.creature.Predator;
import entities.resource.Grass;
import entities.stative.Rock;
import entities.stative.Tree;

import java.util.*;

public class WorldMap {
    private final int size;
    private static final int DEFAULT_SIZE = 20;
    private int grass = 10;
    private int rocks = 10;
    private int tree = 10;
    private int herbivore = 5;
    private int predator = 3;

    public WorldMap(int size) {
        if (size < 10 || size > 50) {
            throw new IllegalArgumentException("Map size must be between 10 and 50");
        }
        this.size = size;
        grass = this.size/2;
        rocks = this.size/2;
        tree = this.size/2;
        herbivore = this.size / 3;
        predator = this.size / 4;
    }

    public WorldMap() {
        this.size = DEFAULT_SIZE;
    }

    private final HashMap<Coordinates, Entity> entitiesMap = new HashMap<>();

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        entitiesMap.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        entitiesMap.remove(coordinates);
    }

    public void setupEntitiesPositions() {
        List<Coordinates> availablePositions = new ArrayList<>();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                availablePositions.add(new Coordinates(x, y));
            }
        }

        Collections.shuffle(availablePositions);

        Iterator<Coordinates> iterator = availablePositions.iterator();

        while (grass > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, EntityFactory.createEntity(EntityType.GRASS, pos));
            grass--;
        }

        while (rocks > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, EntityFactory.createEntity(EntityType.ROCK, pos));
            rocks--;
        }

        while (tree > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, EntityFactory.createEntity(EntityType.TREE, pos));
            tree--;
        }

        while (herbivore > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, EntityFactory.createEntity(EntityType.HERBIVORE, pos));
            herbivore--;
        }

        while (predator > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, EntityFactory.createEntity(EntityType.PREDATOR, pos));
            predator--;
        }


    }


    public boolean isCellWalkable(int x, int y, Creature mover) {
        Entity entity = getEntity(new Coordinates(x, y));
        if (entity == null) return true;
        if (entity instanceof Grass && mover instanceof Herbivore) return true;
        if (entity instanceof Herbivore && mover instanceof Predator) return true;
        return false;
    }

    public Entity getEntity(Coordinates coordinates) {
        return entitiesMap.get(coordinates);
    }

    public Creature getCreature(Coordinates coordinates) {
        return (Creature) entitiesMap.get(coordinates);
    }

    public List<Coordinates> getNeighbours(Coordinates coord, Creature mover) {
        List<Coordinates> neighbours = new ArrayList<>();
        int[][] directions = {
                {0, 1},   // right
                {1, 0},   // down
                {0, -1},  // left
                {-1, 0},  // up
                {-1, -1},// up-left
                {-1, 1}, // up-right
                {1, 1}, // down-right
                {1, -1} //down-left
        };

        for (int[] direction : directions) {
            int newX = coord.getX() + direction[0];
            int newY = coord.getY() + direction[1];

            if (newX >= 0 && newX < size && newY >= 0 && newY < size) {
                if (isCellWalkable(newX, newY, mover)) {
                    neighbours.add(new Coordinates(newX, newY));
                }
            }
        }
        return neighbours;
    }

    public int getSize() {
        return size;
    }

    public HashMap<Coordinates, Entity> getEntitiesMap() {
        return entitiesMap;
    }
}

