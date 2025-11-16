import entities.EntityEmoji;
import entities.creature.Herbivore;
import entities.resource.Grass;
import entities.stative.Tree;
import pathfinders.BreadthFirstSearch;
import render.RenderConsoleMap;
import world.Coordinates;
import world.WorldMap;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {



        WorldMap map = new WorldMap(10);
        BreadthFirstSearch bfs = new BreadthFirstSearch(map);
        Coordinates start = new Coordinates(0, 0);
        Coordinates end = new Coordinates(4, 4);
        Herbivore herbivore = new Herbivore(start, EntityEmoji.PREDATOR.getEmoji(),2,2);
        Herbivore herbivore1 = new Herbivore(new Coordinates(1,1), EntityEmoji.HERBIVORE.getEmoji());
        Grass grass = new Grass(end, EntityEmoji.GRASS.getEmoji());
        Grass grass1 = new Grass(new Coordinates(2,2), EntityEmoji.GRASS.getEmoji());
        Grass grass2 = new Grass(new Coordinates(4,0), EntityEmoji.GRASS.getEmoji());
        Grass grass3 = new Grass(new Coordinates(7,8), EntityEmoji.GRASS.getEmoji());
        Tree tree = new Tree(new Coordinates(1,1),EntityEmoji.TREE.getEmoji());
        map.setEntity(new Coordinates(3,3), tree);
        map.setEntity(grass2.getCoordinates(), grass2);
        map.setEntity(grass3.getCoordinates(), grass3);
        map.setEntity(start, herbivore);
        map.setEntity(herbivore1.getCoordinates(), herbivore1);
        map.setEntity(end, grass);
        map.setEntity(new Coordinates(2,2), grass1);
        RenderConsoleMap renderConsoleMap = new RenderConsoleMap();
        Scanner input = new Scanner(System.in);



        while (true) {
            renderConsoleMap.render(map);
            herbivore1.makeMove(bfs, map);
            System.out.println(herbivore.makeMove(bfs, map));
            Thread.sleep(1000);

        }



    }


}
