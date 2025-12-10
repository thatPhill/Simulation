package actions;

import entities.Entity;
import entities.creature.Creature;
import pathfinders.BreadthFirstSearch;
import world.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class TurnActions implements Action {
    WorldMap map;
    BreadthFirstSearch bfs;

    public TurnActions(WorldMap map, BreadthFirstSearch bfs) {
        this.map = map;
        this.bfs = bfs;
    }

    @Override
    public void execute() {
        List<Creature> creatures = new ArrayList<>();
        for (Entity entity : map.getEntitiesMap().values()) {
            if (entity instanceof Creature) {
                creatures.add((Creature) entity);
            }
        }

        for (Creature creature : creatures) {
            if (map.getEntity(creature.getCoordinates()) == creature) {
                creature.makeMove(bfs, map);
            }
        }
    }
}
