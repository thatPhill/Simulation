package world;

import actions.*;
import pathfinders.BreadthFirstSearch;

public class Simulation {

    private WorldMap map;
    private BreadthFirstSearch bfs;
    private WorldConfig worldConfig;

    Action turnAction;
    Action renderAction;
    Action initAction;
    Action spawnAction;

    public Simulation(WorldConfig worldConfig) {
        this.map = new WorldMap(worldConfig);
        this.worldConfig = worldConfig;
        this.bfs = new BreadthFirstSearch(map);

        this.turnAction = new TurnActions(map,bfs, worldConfig);
        this.renderAction = new RenderAction(map);
        this.initAction = new InitActions(map);
        this.spawnAction = new SpawnActions(map, worldConfig);

    }

    public void run(){
            initAction.execute();
        while (true){
            renderAction.execute();
            turnAction.execute();
            spawnAction.execute();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }






}
