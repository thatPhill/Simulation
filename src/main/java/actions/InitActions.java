package actions;

import world.WorldMap;


public class InitActions implements Action {
    WorldMap map;

    public InitActions(WorldMap map) {
        this.map = map;
    }


    @Override
    public void execute() {
        map.setupEntitiesPositions();
    }
}
