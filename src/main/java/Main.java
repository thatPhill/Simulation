import render.ConsoleMap;
import world.Map;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        ConsoleMap cm = new ConsoleMap();
        map.setupEntitiesPositions();
        cm.render(map);
    }
}
