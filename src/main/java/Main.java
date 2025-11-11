import render.RenderConsoleMap;
import world.WorldMap;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        WorldMap map = new WorldMap();
        map.setupEntitiesPositions();
        RenderConsoleMap renderConsoleMap = new RenderConsoleMap();
        renderConsoleMap.render(map);

    }
}
