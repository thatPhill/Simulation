package actions;

import entities.Entity;
import entities.EntityFactory;
import entities.EntityType;
import world.Coordinates;
import world.WorldConfig;
import world.WorldMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


public class InitAction implements Action {
    WorldMap map;
    WorldConfig config;


    public InitAction(WorldMap map, WorldConfig config) {
        this.map = map;
        this.config = config;
    }


    @Override
    public void execute() {
        setupEntitiesPositions();
    }

   private void spawn(Iterator<Coordinates> iterator, Function<Coordinates, Entity> mapper, int count) {
        while (0 < count && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            Entity entity = mapper.apply(pos);
            map.setEntity(pos, entity);
            count--;
        }
    }

    private void setupEntitiesPositions() {
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

        spawn(iterator, pos -> EntityFactory.createEntity(EntityType.GRASS, pos), grassCount);
        spawn(iterator, pos -> EntityFactory.createEntity(EntityType.ROCK, pos), rockCount);
        spawn(iterator, pos -> EntityFactory.createEntity(EntityType.TREE, pos), treeCount);
        spawn(iterator, pos -> EntityFactory.createEntity(EntityType.HERBIVORE, pos), herbivoreCount);
        spawn(iterator, pos -> EntityFactory.createEntity(EntityType.PREDATOR, pos), predatorCount);


    }
}
