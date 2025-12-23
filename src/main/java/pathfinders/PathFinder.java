package pathfinders;

import entities.Entity;
import world.Coordinates;
import world.WorldMap;

import java.util.List;

public interface PathFinder {
    List<Coordinates> find(WorldMap worldMap, Coordinates start, Class<? extends Entity> target);
}
