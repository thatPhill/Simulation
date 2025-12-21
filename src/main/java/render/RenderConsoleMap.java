package render;

import entities.Entity;
import entities.EntityType;
import entities.creature.Herbivore;
import entities.creature.Predator;
import entities.resource.Grass;
import entities.stative.Rock;
import entities.stative.Tree;
import world.Coordinates;
import world.Simulation;
import world.WorldMap;

public class RenderConsoleMap {
    private final String EMPTY = EntityType.EMPTY.getEmoji();
    private final String PREDATOR = EntityType.PREDATOR.getEmoji();
    private final String HERBIVORE = EntityType.HERBIVORE.getEmoji();
    private final String ROCK = EntityType.ROCK.getEmoji();
    private final String TREE = EntityType.TREE.getEmoji();
    private final String GRASS = EntityType.GRASS.getEmoji();


    public void render (WorldMap worldMap, Simulation simulation) {
        for (int x = 0; x < worldMap.getSize(); x++) {
            for (int y = 0; y < worldMap.getSize(); y++) {
                Entity entity = worldMap.getEntity(new Coordinates(x, y));
                switch (entity) {
                    case null -> System.out.print(EMPTY + " ");
                    case Herbivore herbivore -> System.out.print(HERBIVORE + " ");
                    case Rock rock -> System.out.print(ROCK + " ");
                    case Tree tree -> System.out.print(TREE + " ");
                    case Grass grass -> System.out.print(GRASS + " ");
                    case Predator predator -> System.out.print(PREDATOR + " ");
                    default -> {
                    }
                }
            }
            System.out.println();
        }
        System.out.println("Turn - " + simulation.getTurnCount());
    }
}
