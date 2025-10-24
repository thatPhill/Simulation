package render;

import entities.Entity;
import entities.EntityEmoji;
import world.Coordinates;
import world.WorldMap;

public class ConsoleMap {
    private final String EMPTY = EntityEmoji.EMPTY.getEmoji();


    public void render (WorldMap worldMap){
        for (int vertical = 0; vertical < worldMap.getSize(); vertical++) {
            for (int horizontal = 0; horizontal < worldMap.getSize(); horizontal++) {
                Entity entity = worldMap.getEntity(new Coordinates(horizontal, vertical));
                if  (entity == null) {
                    System.out.print(EMPTY);
                } else {
                    System.out.print(entity.getEmoji());
                }
            }
            System.out.println();
        }
    }
}
