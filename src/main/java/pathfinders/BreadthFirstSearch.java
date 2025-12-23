package pathfinders;

import entities.Entity;
import entities.creature.Attackable;
import entities.creature.Creature;
import entities.resource.Eatable;
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
            if (neighbour.x() >= 0 && neighbour.x() < worldMap.getSize() &&
                    neighbour.y() >= 0 && neighbour.y() < worldMap.getSize()) {
                if (worldMap.isCellWalkable(neighbour.x(), neighbour.y(), mover)) {
                    neighbours.add(neighbour);
                }
            }
        }

        return neighbours;
    }

    @Override
    public List<Coordinates> find(WorldMap worldMap, Coordinates start, Class<? extends Entity> target) {
        Queue<Node> queue = new LinkedList<>();
        Set<Coordinates> visited = new HashSet<>();

        Node startNode = new Node(start, null);
        queue.add(startNode);
        visited.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            Entity entity = worldMap.getEntity(current.coordinates());
            if (target.isInstance(entity)) {
                List<Coordinates> path = new ArrayList<>();
                Node node = current;
                while (node != null) {
                    path.add(node.coordinates());
                    node = node.parent();
                }
                Collections.reverse(path);
                return path;
            }

            List<Coordinates> neighbours = getNeighbours(current.coordinates(), mover);
            for (Coordinates neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    Node neighbourNode = new Node(neighbour, current);
                    queue.add(neighbourNode);
                    visited.add(neighbour);
                }
            }
        }
        return Collections.emptyList();
    }


    public void setMover(Creature mover) {
        this.mover = mover;
    }


    public Coordinates getNextStep(WorldMap worldMap, List<Coordinates> path) {

        Entity target = worldMap.getEntity(path.getLast());

        if (path.size() <= mover.getSpeed() && target instanceof Eatable) {
            return path.getLast();
        }else if (path.size() <= mover.getSpeed() && target instanceof Attackable) {
            return path.get(path.size() - 2);
        } else if (mover.getSpeed() < path.size()) {
           return path.get(mover.getSpeed());
        }

        return mover.getCoordinates();
    }
}
