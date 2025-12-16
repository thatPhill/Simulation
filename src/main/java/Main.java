import world.Simulation;
import world.WorldConfig;

public class Main {
    public static void main(String[] args) {
        WorldConfig worldConfig = new WorldConfig(20,10,10,10,5,3);
        Simulation simulation = new Simulation(worldConfig);
        simulation.start();


    }


}
