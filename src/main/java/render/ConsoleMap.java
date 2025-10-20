package render;

import entities.Entity;
import entities.EntityEmoji;
import world.Coordinates;
import world.Map;

public class ConsoleMap {
    private final String EMPTY = EntityEmoji.EMPTY.getEmoji();


    public void render (Map map){
        for (int vertical = 0; vertical < map.getSize(); vertical++) {
            for (int horizontal = 0; horizontal < map.getSize(); horizontal++) {
                Entity entity = map.getEntity(new Coordinates(horizontal, vertical));
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
