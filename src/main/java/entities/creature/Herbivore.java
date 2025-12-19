package entities.creature;

import entities.Entity;
import entities.resource.Grass;
import pathfinders.BreadthFirstSearch;
import world.Coordinates;
import world.WorldConfig;
import world.WorldMap;

import java.util.List;

public class Herbivore extends Creature {

    private static final int DEFAULT_SPEED = 1;
    private static final int DEFAULT_HEALTH = 10;

    public Herbivore(Coordinates coordinates, String emoji, int speed, int health) {
        super(coordinates, emoji, speed, health);
    }

    public Herbivore(Coordinates coordinates, String emoji, int speed) {
        super(coordinates, emoji, speed, DEFAULT_HEALTH);
    }

    public Herbivore(Coordinates coordinates, String emoji) {
        super(coordinates, emoji, DEFAULT_SPEED, DEFAULT_HEALTH);
    }

    @Override
    public void makeMove(BreadthFirstSearch pathfinder, WorldMap worldMap, WorldConfig worldConfig) {
        Coordinates start = getCoordinates();
        Coordinates grass = findNearestTarget(worldMap, pathfinder);
        List<Coordinates> path = pathfinder.find(start, grass);
        if (path == null || path.isEmpty()) return;

        Coordinates next = null;
        this.setHealth(this.getHealth() - 1);

        next = getNextStep(path, worldMap.getEntity(grass));

        if (next.equals(grass)){
            this.setHealth(this.getHealth() + 5);
            worldConfig.setGrass(worldConfig.getGrass() - 1);
        }

        if (this.getHealth() <= 0){
            worldConfig.setHerbivores(worldConfig.getHerbivores() - 1);
            worldMap.removeEntity(this.getCoordinates());
        } else {
            worldMap.removeEntity(start);
            setCoordinates(next);
            worldMap.setEntity(next, this);
        }


    }


    //Helper method for findNearestTarget
    @Override
    public boolean isTarget(Entity entity) {
        return entity instanceof Grass;
    }


}
