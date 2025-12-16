package utils;

import actions.SpawnActions;
import entities.EntityType;

public class MessagePrinter {


    public static void printPause() {
        System.out.println("--------------------------SIMULATION PAUSED--------------------------");
    }

    public static void printAlreadyPause() {
        System.out.println("--------------------------ALREADY PAUSED--------------------------");
    }

    public static void printSimulationResumed() {
        System.out.println("--------------------------SIMULATION RESUMED--------------------------");
    }

    public static void printSimulationAlreadyResumed() {
        System.out.println("--------------------------ALREADY RUNNING--------------------------");
    }

    public static void printEatenGrasses() {
        System.out.println("За время симуляции было съедено: " + SpawnActions.countGrass + " " + EntityType.GRASS.getEmoji());
    }

    public static void printEatenHerbivores() {
        System.out.println("За время симуляции было съедено: " + SpawnActions.countHerbivores + " " + EntityType.HERBIVORE.getEmoji());
    }

    public static void printDiedPredators() {
        System.out.println("За время симуляции умерло: " + SpawnActions.countPredators + " " + EntityType.PREDATOR.getEmoji());
    }

}
