package world;

import entities.Entity;
import entities.EntityFactory;
import entities.EntityType;
import entities.creature.Creature;
import entities.creature.Herbivore;
import entities.creature.Predator;
import entities.resource.Grass;

import java.util.*;

public class WorldMap {
    private final WorldConfig config;


    public WorldMap(WorldConfig config) {
        this.config = config;
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
        int grassCount = config.getGrass();
        int rockCount = config.getRocks();
        int herbivoreCount = config.getHerbivores();
        int predatorCount = config.getPredators();
        int treeCount = config.getTrees();
        List<Coordinates> availablePositions = new ArrayList<>();

        for (int x = 0; x < config.getSize(); x++) {
            for (int y = 0; y < config.getSize(); y++) {
                availablePositions.add(new Coordinates(x, y));
            }
        }

        Collections.shuffle(availablePositions);

        Iterator<Coordinates> iterator = availablePositions.iterator();

        while (grassCount > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, EntityFactory.createEntity(EntityType.GRASS, pos));
            grassCount--;
        }

        while (rockCount > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, EntityFactory.createEntity(EntityType.ROCK, pos));
            rockCount--;
        }

        while (treeCount > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, EntityFactory.createEntity(EntityType.TREE, pos));
            treeCount--;
        }

        while (herbivoreCount > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, EntityFactory.createEntity(EntityType.HERBIVORE, pos));
            herbivoreCount--;
        }

        while (predatorCount > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, EntityFactory.createEntity(EntityType.PREDATOR, pos));
            predatorCount--;
        }


    }


    public boolean isCellWalkable(int x, int y, Creature mover) {
        Entity entity = getEntity(new Coordinates(x, y));

        if (entity == null){
            return true;
        }
        if (entity instanceof Grass && mover instanceof Herbivore){
            return true;
        }
        if (entity instanceof Herbivore && mover instanceof Predator){
            return true;
        }
        return false;
    }

    public Entity getEntity(Coordinates coordinates) {
        return entitiesMap.get(coordinates);
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
            int newX = coord.x() + direction[0];
            int newY = coord.y() + direction[1];

            if (newX >= 0 && newX < getSize() && newY >= 0 && newY < getSize()) {
                if (isCellWalkable(newX, newY, mover)) {
                    neighbours.add(new Coordinates(newX, newY));
                }
            }
        }
        return neighbours;
    }

    public int getSize() {
        return config.getSize();
    }

    public void setSize(int size) {
        config.setSize(size);
    }

    public HashMap<Coordinates, Entity> getEntitiesMap() {
        return entitiesMap;
    }
}

