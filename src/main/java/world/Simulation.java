package world;

import actions.Action;
import actions.InitActions;
import actions.RenderAction;
import actions.TurnActions;
import pathfinders.BreadthFirstSearch;

public class Simulation {
    WorldMap map;
    BreadthFirstSearch bfs;

    Action turnAction;
    Action renderAction;
    Action initAction;

    public Simulation() {
        this.map = new WorldMap();

        this.bfs = new BreadthFirstSearch(map);

        this.turnAction = new TurnActions(map,bfs);
        this.renderAction = new RenderAction(map);
        this.initAction = new InitActions(map);

    }

    public void run(){
        while (true){
            initAction.execute();
            renderAction.execute();
            turnAction.execute();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
