package mapetc;

import entities.*;

import javax.swing.text.Position;
import java.util.*;

public class WorldMap {
    int grass = 10;
    int rocks = 10;
    int tree = 10;
    int herbivore = 5;
    int predator = 3;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    public WorldMap(int size) {
        this.size = size;
        grass = this.size;
        rocks = this.size;
        tree = this.size;
        herbivore = this.size / 2;
        predator = this.size / 3;
    }


    public WorldMap() {
        this.size = DEFAULT_SIZE;
    }

    private final HashMap<Coordinates, Entity> entitiesMap = new HashMap<>();

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        entitiesMap.put(coordinates, entity);
    }

    public boolean isCellWalkable(int x, int y) {
        Entity entity = getEntity(new Coordinates(x, y));
        if (entity == null) return true;
        if (entity instanceof Grass) return true;
        return false;
    }

    public List<Coordinates> getNeighbours(Coordinates coord) {
        List<Coordinates> neighbours = new ArrayList<>();
        int[][] directions = {
                {0, 1},   // вверх
                {1, 0},   // вправо
                {0, -1},  // вниз
                {-1, 0}   // влево
        };

        for (int[] direction : directions) {
            int newHorizontal = coord.getHorizontal() + direction[0];
            int newVertical = coord.getVertical() + direction[1];

            if (newHorizontal >= 0 && newHorizontal < size && newVertical >= 0 && newVertical < size) {
                if (isCellWalkable(newHorizontal, newVertical)) {
                    neighbours.add(new Coordinates(newHorizontal, newVertical));
                }
            }
        }

        return neighbours;
    }

    public void setupEntitiesPositionsTest(){
        Coordinates start = new Coordinates(0, 0);
        Coordinates goal = new Coordinates(2, 5);
        setEntity(start, new Herbivore(start,EntityEmoji.HERBIVORE.getEmoji()));
        setEntity(goal, new Grass(goal,EntityEmoji.GRASS.getEmoji()));
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

    public Entity getEntity(Coordinates coordinates) {
        return entitiesMap.get(coordinates);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
