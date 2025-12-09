package actions;

import entities.Entity;
import entities.creature.Creature;
import pathfinders.BreadthFirstSearch;
import render.RenderConsoleMap;
import world.Coordinates;
import world.WorldMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Action {
    WorldMap map = new WorldMap(10);
    BreadthFirstSearch bfs = new BreadthFirstSearch(map);
    RenderConsoleMap render = new RenderConsoleMap();

    public void initActions() throws InterruptedException {
        map.setupEntitiesPositions();
        List<Creature> creatures = new ArrayList<>();
        for (Entity entity : map.getEntitiesMap().values()) {
            if (entity instanceof Creature) {
                creatures.add((Creature) entity);
            }
        }
        while (true) {
            for (Creature creature : creatures) {
                if (map.getEntity(creature.getCoordinates()) == creature) {
                    creature.makeMove(bfs, map);
                }
            }

            render.render(map);
            System.out.println("        ");
            Thread.sleep(1000);
        }
    }
}
