package pathfinders;

import world.Coordinates;
import world.WorldMap;

import java.util.*;

public class BreadthFirstSearch implements PathFinder {
    Queue<Coordinates> queue = new LinkedList<>();
    Map<Coordinates, Coordinates> parentMap = new HashMap<>();
    Set<Coordinates> visited = new HashSet<>();
    private final WorldMap worldMap;

    public BreadthFirstSearch(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    @Override
    public List<Coordinates> findPath(Coordinates start, Coordinates end) {
           queue.add(start);
           visited.add(start);
           parentMap.put(start, null);

           while (!queue.isEmpty()) {
               Coordinates current = queue.poll();
               if (current.equals(end)) {
                   List<Coordinates> path = new ArrayList<>();
                   while (current != null) {
                       path.add(current);
                       current = parentMap.get(current);
                   }

                   Collections.reverse(path);
                   return path;
               }

               for (Coordinates neighbour : worldMap.getNeighbours(current)) {
                   if (!visited.contains(neighbour)) {
                       queue.add(neighbour);
                       parentMap.put(neighbour, current);
                       visited.add(neighbour);
                   }
               }
           }
        return Collections.emptyList();

    }
}
