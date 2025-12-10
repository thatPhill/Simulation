package actions;

import render.RenderConsoleMap;
import world.WorldMap;

public class RenderAction implements Action {
    private final WorldMap map;
    private final RenderConsoleMap render;

    public RenderAction(WorldMap map) {
        this.map = map;
        this.render = new RenderConsoleMap();
    }

    @Override
    public void execute() {
        render.render(map);
        System.out.println("        ");
    }
}
