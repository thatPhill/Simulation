package mapetc;

import entities.*;

import java.util.*;

public class Map {
    int grass = 10;
    int rocks = 10;
    int tree = 8;
    int herbivore = 5;
    int predator = 3;
    Random rand = new Random();
    private int sizeMap = 10;

   private final HashMap<Coordinates, Entity> entitiesMap = new HashMap<>();

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        entitiesMap.put(coordinates, entity);
    }


    public void setupEntitiesPositions() {
        List<Coordinates> availablePositions = new ArrayList<>();

        // Заполняем список всеми доступными координатами
        for (int y = 0; y < sizeMap; y++) {
            for (int x = 0; x < sizeMap; x++) {
                availablePositions.add(new Coordinates(x, y));
            }
        }

        // Перемешиваем, чтобы распределение было случайным
        Collections.shuffle(availablePositions);

        // Итератор для извлечения координат
        Iterator<Coordinates> iterator = availablePositions.iterator();

        // Расставляем траву
        while (grass > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, new Grass(pos, EntityEmoji.GRASS.getEmoji()));
            grass--;
        }

        // Расставляем камни
        while (rocks > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, new Rock(pos, EntityEmoji.ROCK.getEmoji()));
            rocks--;
        }

        // Расставляем деревья
        while (tree > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, new Tree(pos, EntityEmoji.TREE.getEmoji()));
            tree--;
        }

        // Расставляем травоядных
        while (herbivore > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, new Herbivore(pos, EntityEmoji.HERBIVORE.getEmoji()));
            herbivore--;
        }

        // Расставляем хищников
        while (predator > 0 && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            setEntity(pos, new Predator(pos, EntityEmoji.PREDATOR.getEmoji()));
            predator--;
        }
    }


    public HashMap<Coordinates, Entity> getEntitiesMap() {
        return entitiesMap;
    }

    public int getSizeMap() {
        return sizeMap;
    }

    public void setSizeMap(int sizeMap) {
        this.sizeMap = sizeMap;
    }
}
