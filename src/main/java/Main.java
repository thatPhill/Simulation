import pathfinders.BreadthFirstSearch;
import render.ConsoleMap;
import world.WorldMap;

public class Main {
    public static void main(String[] args) {

        WorldMap worldMap = new WorldMap();
        BreadthFirstSearch bfs = new BreadthFirstSearch(worldMap);
        ConsoleMap cm = new ConsoleMap();
        worldMap.setupEntitiesPositions();
        cm.render(worldMap);
    }
}
