import mapetc.Map;
import render.MapConsoleRender;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        map.setupEntitiesPositions();
        MapConsoleRender mapConsoleRender = new MapConsoleRender();
        mapConsoleRender.render(map);
        int a = 123;
    }
}
