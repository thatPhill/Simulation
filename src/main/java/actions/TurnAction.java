package actions;

import entities.Entity;
import entities.creature.Creature;
import pathfinders.BreadthFirstSearch;
import world.WorldConfig;
import world.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class TurnAction implements Action {
    WorldMap map;
    BreadthFirstSearch bfs;
    WorldConfig worldConfig;

    public TurnAction(WorldMap map, BreadthFirstSearch bfs, WorldConfig worldConfig) {
        this.map = map;
        this.bfs = bfs;
        this.worldConfig = worldConfig;
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
                creature.makeMove(bfs, map, worldConfig);
            }
        }
    }
}
