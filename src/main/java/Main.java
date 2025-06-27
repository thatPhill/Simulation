import mapetc.WorldMap;
import render.MapConsoleRender;

public class Main {
    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap(8);
        worldMap.setupEntitiesPositions();
        MapConsoleRender mapConsoleRender = new MapConsoleRender();
        mapConsoleRender.render(worldMap);
        int a = 123;
    }
}
