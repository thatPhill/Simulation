package world;

import entities.*;

import java.util.*;

public class Map {
    private final int size;
    private static final int DEFAULT_SIZE = 10;
    private int grass = 10;
    private int rocks = 10;
    private int tree = 10;
    private int herbivore = 5;
    private int predator = 3;


    public Map(int size) {
        if (size < 10 || size > 50) {
            throw new IllegalArgumentException("Size must be between 10 and 50");
        }
        this.size = size;
        grass = this.size;
        rocks = this.size;
        tree = this.size;
        herbivore = this.size / 2;
        predator = this.size / 3;
    }

    public Map() {
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

        // Заполняем список всеми доступными координатами
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                availablePositions.add(new Coordinates(x, y));
            }
        }

        // Перемешиваем, чтобы распределение было случайным
        Collections.shuffle(availablePositions);

        // Итератор для извлечения координат
        Iterator<Coordinates> iterator = availablePositions.iterator();

        while (grass > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, new Grass(pos, EntityEmoji.GRASS.getEmoji()));
            grass--;
        }

        while (rocks > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, new Rock(pos, EntityEmoji.ROCK.getEmoji()));
            rocks--;
        }

        while (tree > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, new Tree(pos, EntityEmoji.TREE.getEmoji()));
            tree--;
        }

        while (herbivore > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, new Herbivore(pos, EntityEmoji.HERBIVORE.getEmoji()));
            herbivore--;
        }

        while (predator > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, new Predator(pos, EntityEmoji.PREDATOR.getEmoji()));
            predator--;
        }
    }

    public int getSize() {
        return size;
    }

    public Entity getEntity(Coordinates coordinates) {
        return entitiesMap.get(coordinates);
    }

}
