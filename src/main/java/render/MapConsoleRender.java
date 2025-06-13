package render;

import entities.Entity;
import entities.EntityEmoji;
import mapetc.Coordinates;
import mapetc.Map;

public class MapConsoleRender {
   private final String EMPTY = EntityEmoji.EMPTY.getEmoji();


    public void render(Map map ) {
        for (int vertical = 0; vertical < map.sizeMap; vertical++) {
            for (int horizontal = 0; horizontal < map.sizeMap; horizontal++) {
                Entity entity = map.getEntitiesMap().get(new Coordinates(horizontal, vertical));
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
