package render;

import entities.Entity;
import entities.EntityEmoji;
import world.Coordinates;
import world.WorldMap;

public class RenderConsoleMap {
    private final String EMPTY = EntityEmoji.EMPTY.getEmoji();


    public void render (WorldMap worldMap){
        for (int x = 0; x < worldMap.getSize(); x++) {
            for (int y = 0; y < worldMap.getSize(); y++) {
                Entity entity = worldMap.getEntity(new Coordinates(x, y));
                if  (entity == null) {
                    System.out.print(EMPTY + " ");
                } else {
                    System.out.print(entity.getEmoji() + " ");
                }
            }
            System.out.println();
        }
    }
}
