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

    public Herbivore(Coordinates coordinates, int speed, int health) {
        super(coordinates, speed, health);
    }

    public Herbivore(Coordinates coordinates, int speed) {
        super(coordinates, speed, DEFAULT_HEALTH);
    }

    public Herbivore(Coordinates coordinates) {
        super(coordinates, DEFAULT_SPEED, DEFAULT_HEALTH);
    }

    @Override
    public void makeMove(BreadthFirstSearch pathfinder, WorldMap worldMap, WorldConfig worldConfig) {
        pathfinder.setMover(this);
        Coordinates start = getCoordinates();
        List<Coordinates> path = pathfinder.find(worldMap, this.getCoordinates(), Grass.class);
        if (path == null || path.isEmpty()) {
            return;
        }

        Coordinates next = pathfinder.getNextStep(worldMap, path);

        this.setHealth(this.getHealth() - 1);


        if (next.equals(path.getLast())) {
            this.setHealth(this.getHealth() + 5);
            worldConfig.setGrass(worldConfig.getGrass() - 1);
        }

        if (this.getHealth() <= 0) {
            worldConfig.setHerbivores(worldConfig.getHerbivores() - 1);
            worldMap.removeEntity(this.getCoordinates());
        } else {
            worldMap.removeEntity(start);
            setCoordinates(next);
            worldMap.setEntity(next, this);
        }


    }


    @Override
    public boolean isTarget(Entity entity) {
        return entity instanceof Grass;
    }


}
