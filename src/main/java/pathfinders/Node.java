package pathfinders;

import world.Coordinates;


public record Node(Coordinates coordinates, Node parent) {
}
