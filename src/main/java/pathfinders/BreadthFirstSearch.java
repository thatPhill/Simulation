package pathfinders;

import entities.Entity;
import entities.creature.Creature;
import world.Coordinates;
import world.WorldMap;

import java.util.*;

public class BreadthFirstSearch implements PathFinder {

    private final WorldMap worldMap;
    private Creature mover;


    public BreadthFirstSearch(WorldMap worldMap) {
        this.worldMap = worldMap;
    }


    private static final List<Coordinates> DIRECTIONS = List.of(
            new Coordinates(0, 1),   // right
            new Coordinates(1, 0),   // down
            new Coordinates(0, -1),  // left
            new Coordinates(-1, 0),  // up
            new Coordinates(-1, -1), // up-left
            new Coordinates(-1, 1),  // up-right
            new Coordinates(1, 1),   // down-right
            new Coordinates(1, -1)   // down-left
    );

    public List<Coordinates> getNeighbours(Coordinates coord, Creature mover) {
        List<Coordinates> neighbours = new ArrayList<>();
        for (Coordinates direction : DIRECTIONS) {
            Coordinates neighbour = coord.sum(direction);
            if (neighbour.x() >= 0 && neighbour.x() < worldMap.getSize()&&
                    neighbour.y() >= 0 && neighbour.y() < worldMap.getSize()) {
                if (worldMap.isCellWalkable(neighbour.x(), neighbour.y(), mover)) {
                    neighbours.add(neighbour);
                }
            }
        }

        return neighbours;
    }

    @Override
    public List<Coordinates> find(Coordinates start, Coordinates goal) {
        Queue<Coordinates> queue = new LinkedList<>();
        Map<Coordinates, Coordinates> parentMap = new HashMap<>();
        Set<Coordinates> visited = new HashSet<>();


           queue.add(start);
           visited.add(start);
           parentMap.put(start, null);


           while (!queue.isEmpty()) {
               Coordinates current = queue.poll();
               if (current.equals(goal)) {
                   List<Coordinates> path = new ArrayList<>();
                   while (current != null) {
                       path.add(current);
                       current = parentMap.get(current);
                   }

                   Collections.reverse(path);
                   return path;
               }
               List<Coordinates> neighbours = getNeighbours(current, mover);

               for (Coordinates neighbour : neighbours) {
                   if (!visited.contains(neighbour)) {
                       queue.add(neighbour);
                       parentMap.put(neighbour, current);
                       visited.add(neighbour);
                   }
               }
           }
        return Collections.emptyList();

    }



    public void setMover(Creature mover) {
        this.mover = mover;
    }
}
