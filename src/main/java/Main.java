import entities.EntityEmoji;
import entities.creature.Herbivore;
import entities.resource.Grass;
import entities.stative.Tree;
import pathfinders.BreadthFirstSearch;
import render.RenderConsoleMap;
import world.Coordinates;
import world.WorldMap;

public class Main {
    public static void main(String[] args) {
        WorldMap map = new WorldMap(5);
        BreadthFirstSearch bfs = new BreadthFirstSearch(map);
        Coordinates start = new Coordinates(0, 0);
        Coordinates end = new Coordinates(4, 4);
        Herbivore herbivore = new Herbivore(start, EntityEmoji.HERBIVORE.getEmoji(), 2,2);
        Grass grass = new Grass(end, EntityEmoji.GRASS.getEmoji());
        Tree tree = new Tree(new Coordinates(1,1),EntityEmoji.TREE.getEmoji());
        map.setEntity(new Coordinates(1,1), tree);
        map.setEntity(start, herbivore);
        map.setEntity(end, grass);
        RenderConsoleMap renderConsoleMap = new RenderConsoleMap();

        System.out.println(bfs.findPath(start, end));

        renderConsoleMap.render(map);

    }


}
