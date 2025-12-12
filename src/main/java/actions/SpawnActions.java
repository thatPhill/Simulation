package actions;

import entities.EntityFactory;
import entities.EntityType;
import world.Coordinates;
import world.WorldConfig;
import world.WorldMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SpawnActions implements Action{
    private final WorldMap map;
    WorldConfig worldConfig;
    private int grassCount;
    private int predatorCount;
    private int herbivoreCount;

    public SpawnActions(WorldMap map,  WorldConfig worldConfig) {
        this.map = map;
        this.worldConfig = worldConfig;
        grassCount = worldConfig.getGrass();
        predatorCount = worldConfig.getPredators();
        herbivoreCount = worldConfig.getHerbivores();
    }

    @Override
    public void execute() {
        List<Coordinates> emptyPositions = findEmptyPositions(map);

       Iterator<Coordinates> iterator = emptyPositions.iterator();

        while (grassCount != worldConfig.getGrass() && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            map.setEntity(pos, EntityFactory.createEntity(EntityType.GRASS, pos));
            System.out.println("grass ЗАСПАУНИЛСЯ НА " + pos.toString());
            worldConfig.setGrass(worldConfig.getGrass() + 1);
        }
        while (predatorCount != worldConfig.getPredators() && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            map.setEntity(pos, EntityFactory.createEntity(EntityType.PREDATOR, pos));
            System.out.println("PREDATOR ЗАСПАУНИЛСЯ НА " + pos.toString());
            worldConfig.setPredators(worldConfig.getPredators() + 1);
        }
        while (herbivoreCount != worldConfig.getHerbivores() && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            map.setEntity(pos, EntityFactory.createEntity(EntityType.HERBIVORE, pos));
            System.out.println("herbivore ЗАСПАУНИЛСЯ НА " + pos.toString());
            worldConfig.setHerbivores(worldConfig.getHerbivores() + 1);
        }

    }


private List<Coordinates> findEmptyPositions(WorldMap map){
        List<Coordinates> emptyPositions = new ArrayList<>();
        for(int x = 0; x < map.getSize(); x++){
            for (int y = 0; y < map.getSize(); y++){
                Coordinates pos = new Coordinates(x, y);
                if(map.getEntity(pos) == null){
                    emptyPositions.add(pos);
                }
            }
        }
        Collections.shuffle(emptyPositions);
        return emptyPositions;
}




    public int getHerbivoreCount() {
        return herbivoreCount;
    }

    public void setHerbivoreCount(int herbivoreCount) {
        this.herbivoreCount = herbivoreCount;
    }

    public int getPredatorCount() {
        return predatorCount;
    }

    public void setPredatorCount(int predatorCount) {
        this.predatorCount = predatorCount;
    }

    public int getGrassCount() {
        return grassCount;
    }

    public void setGrassCount(int grassCount) {
        this.grassCount = grassCount;
    }
}
