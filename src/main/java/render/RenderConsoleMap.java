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
    private final String empty = EntityType.EMPTY.getEmoji();
    private final String predator = EntityType.PREDATOR.getEmoji();
    private final String herbivore = EntityType.HERBIVORE.getEmoji();
    private final String rock = EntityType.ROCK.getEmoji();
    private final String tree = EntityType.TREE.getEmoji();
    private final String grass = EntityType.GRASS.getEmoji();


    public void render (WorldMap worldMap, Simulation simulation) {
        for (int x = 0; x < worldMap.getSize(); x++) {
            for (int y = 0; y < worldMap.getSize(); y++) {
                Entity entity = worldMap.getEntity(new Coordinates(x, y));
                switch (entity) {
                    case null -> System.out.print(empty + " ");
                    case Herbivore herbivore -> System.out.print(this.herbivore + " ");
                    case Rock rock -> System.out.print(this.rock + " ");
                    case Tree tree -> System.out.print(this.tree + " ");
                    case Grass grass -> System.out.print(this.grass + " ");
                    case Predator predator -> System.out.print(this.predator + " ");
                    default -> {
                    }
                }
            }
            System.out.println();
        }
        System.out.println("Turn - " + simulation.getTurnCount());
    }
}
