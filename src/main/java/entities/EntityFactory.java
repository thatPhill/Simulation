package entities;

import entities.creature.Herbivore;
import entities.creature.Predator;
import entities.resource.Grass;
import entities.stative.Rock;
import entities.stative.Tree;
import world.Coordinates;

public class EntityFactory {

    public static Entity createEntity(EntityType type, Coordinates pos) {
        return switch (type) {
            case HERBIVORE -> new Herbivore(pos);
            case PREDATOR -> new Predator(pos);
            case GRASS -> new Grass(pos);
            case ROCK -> new Rock(pos);
            case TREE -> new Tree(pos);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

}
