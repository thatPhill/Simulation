package pathfinders;

import world.Coordinates;

import java.util.List;

public interface PathFinder {
    List<Coordinates> find(Coordinates start, Coordinates goal);
}
