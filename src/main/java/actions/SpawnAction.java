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

public class SpawnAction implements Action{
    private final WorldMap map;
    WorldConfig worldConfig;
    private int totalGrass;
    private int totalPredators;
    private int totalHerbivores;

    public static int countGrass = 0;
    public static int countPredators = 0;
    public static int countHerbivores = 0;

    public SpawnAction(WorldMap map, WorldConfig worldConfig) {
        this.map = map;
        this.worldConfig = worldConfig;
        totalGrass = worldConfig.getGrass();
        totalPredators = worldConfig.getPredators();
        totalHerbivores = worldConfig.getHerbivores();
    }

    @Override
    public void execute() {
        List<Coordinates> emptyPositions = findEmptyPositions(map);

       Iterator<Coordinates> iterator = emptyPositions.iterator();

        while (totalGrass != worldConfig.getGrass() && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            map.setEntity(pos, EntityFactory.createEntity(EntityType.GRASS, pos));
            worldConfig.setGrass(worldConfig.getGrass() + 1);
            countGrass++;
        }
        while (totalPredators != worldConfig.getPredators() && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            map.setEntity(pos, EntityFactory.createEntity(EntityType.PREDATOR, pos));
            worldConfig.setPredators(worldConfig.getPredators() + 1);
            countPredators++;
        }
        while (totalHerbivores != worldConfig.getHerbivores() && iterator.hasNext()) {
            Coordinates pos = iterator.next();
            map.setEntity(pos, EntityFactory.createEntity(EntityType.HERBIVORE, pos));
            worldConfig.setHerbivores(worldConfig.getHerbivores() + 1);
            countHerbivores++;
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




    public int getTotalHerbivores() {
        return totalHerbivores;
    }

    public void setTotalHerbivores(int totalHerbivores) {
        this.totalHerbivores = totalHerbivores;
    }

    public int getPredatorCount() {
        return totalPredators;
    }

    public void setPredatorCount(int predatorCount) {
        this.totalPredators = predatorCount;
    }

    public int getTotalGrass() {
        return totalGrass;
    }

    public void setTotalGrass(int totalGrass) {
        this.totalGrass = totalGrass;
    }
}
