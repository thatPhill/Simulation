package world;

import entities.Entity;
import entities.creature.Creature;
import entities.creature.Herbivore;
import entities.creature.Predator;
import entities.resource.Grass;

import java.util.*;

public class WorldMap {
    private int size;


    public WorldMap(int size) {
        this.size = size;
    }

    private final HashMap<Coordinates, Entity> entitiesMap = new HashMap<>();

    public void setEntity(Coordinates coordinates, Entity entity) {
        if (isValide(coordinates)) {
            entity.setCoordinates(coordinates);
            entitiesMap.put(coordinates, entity);
        } else {
            throw new IllegalArgumentException("Coordinates out of bounds: " + coordinates);
        }
    }

    public void removeEntity(Coordinates coordinates) {
        if (isValide(coordinates)) {
            entitiesMap.remove(coordinates);
        } else {
            throw new IllegalArgumentException("Coordinates out of bounds: " + coordinates);
        }
    }

    private boolean isValide(Coordinates coordinates) {
        return coordinates.x() >= 0 &&
                coordinates.x() < size &&
                coordinates.y() >= 0 &&
                coordinates.y() < size;
    }


    public boolean isCellWalkable(int x, int y, Creature mover) {
        Entity entity = getEntity(new Coordinates(x, y));

        if (entity == null) {
            return true;
        }
        if (entity instanceof Grass && mover instanceof Herbivore) {
            return true;
        }
        if (entity instanceof Herbivore && mover instanceof Predator) {
            return true;
        }
        return false;
    }

    public Entity getEntity(Coordinates coordinates) {
        return entitiesMap.get(coordinates);
    }



    public int getSize() {
        return size;
    }


    public HashMap<Coordinates, Entity> getEntitiesMap() {
        return entitiesMap;
    }
}

