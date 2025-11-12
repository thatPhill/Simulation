package pathfinders;

import world.Coordinates;

import java.util.List;

public interface PathFinder {
    List<Coordinates> findPath(Coordinates start, Coordinates goal);
}
