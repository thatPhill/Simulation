package actions;

import world.WorldMap;


public class InitAction implements Action {
    WorldMap map;

    public InitAction(WorldMap map) {
        this.map = map;
    }


    @Override
    public void execute() {
        map.setupEntitiesPositions();
    }
}
