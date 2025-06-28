package pathfiders;

import mapetc.Coordinates;

import java.util.List;

public interface PathFinder {
    List<Coordinates> findPath(Coordinates start, Coordinates goal);
}
