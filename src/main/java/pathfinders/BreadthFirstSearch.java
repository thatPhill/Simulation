package pathfinders;

import world.Coordinates;
import world.WorldMap;

import java.util.*;

public class BreadthFirstSearch {
    Queue<Coordinates> queue = new LinkedList<>();
    java.util.Map<Coordinates, Coordinates> parentMap = new HashMap<>();
    Set<Coordinates> visited = new HashSet<>();

    private final WorldMap worldMap;


    public BreadthFirstSearch(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public List<Coordinates> findPath(Coordinates start, Coordinates goal) {
        if (start.equals(goal)) {
            List<Coordinates> path = new ArrayList<>();
            path.add(start);
            return path;
        }


        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();

            if (current.equals(goal)) break;

            for (Coordinates neighbor : worldMap.getNeighbours(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (parentMap.containsKey(goal)) {
            return restorePath(parentMap, goal);
        } else {
            return Collections.emptyList();
        }
    }

    private List<Coordinates> restorePath(Map<Coordinates, Coordinates> parentMap, Coordinates goal) {
        List<Coordinates> path = new ArrayList<>();
        Coordinates current = goal;

        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }

        Collections.reverse(path);
        return path;
    }
}

