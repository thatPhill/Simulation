package render;

import entities.Entity;
import entities.EntityEmoji;
import mapetc.Coordinates;
import mapetc.WorldMap;

public class MapConsoleRender {
   private final String EMPTY = EntityEmoji.EMPTY.getEmoji();


    public void render(WorldMap worldMap) {
        for (int vertical = 0; vertical < worldMap.getSize(); vertical++) {
            for (int horizontal = 0; horizontal < worldMap.getSize(); horizontal++) {
                Entity entity = worldMap.getEntity(new Coordinates(horizontal, vertical));
                if (entity != null) {
                    System.out.print(entity.getEmoji());
                }else {
                    System.out.print(EMPTY);
                }
            }
            System.out.println();
        }
    }
}
