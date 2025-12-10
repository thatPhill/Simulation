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
            case HERBIVORE -> new Herbivore(pos, type.getEmoji());
            case PREDATOR -> new Predator(pos, type.getEmoji());
            case GRASS -> new Grass(pos, type.getEmoji());
            case ROCK -> new Rock(pos, type.getEmoji());
            case TREE -> new Tree(pos, type.getEmoji());
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

}
