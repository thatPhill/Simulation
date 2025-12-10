package render;

import entities.Entity;
import entities.EntityType;
import entities.creature.Herbivore;
import world.Coordinates;
import world.WorldMap;

public class RenderConsoleMap {
    private final String EMPTY = EntityType.EMPTY.getEmoji();


    public void render (WorldMap worldMap){
        for (int x = 0; x < worldMap.getSize(); x++) {
            for (int y = 0; y < worldMap.getSize(); y++) {
                Entity entity = worldMap.getEntity(new Coordinates(x, y));
                if  (entity == null) {
                    System.out.print(EMPTY + " ");
                } else {
                    if (entity instanceof Herbivore){
                        System.out.print(((Herbivore) entity).getHealth());
                    } else {
                        System.out.print(entity.getEmoji() + " ");
                    }

                }
            }
            System.out.println();
        }
    }
}
