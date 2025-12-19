package utils;

import actions.SpawnAction;
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
        System.out.println("За время симуляции было съедено: " + SpawnAction.countGrass + " " + EntityType.GRASS.getEmoji());
    }

    public static void printEatenHerbivores() {
        System.out.println("За время симуляции было съедено: " + SpawnAction.countHerbivores + " " + EntityType.HERBIVORE.getEmoji());
    }

    public static void printDiedPredators() {
        System.out.println("За время симуляции умерло: " + SpawnAction.countPredators + " " + EntityType.PREDATOR.getEmoji());
    }

    public static void printKeyForPause() {
        System.out.println("Для паузы введите букву - \"P\"");
    }

    public static void printKeyForResume() {
        System.out.println("Для возобновление симуляции введите букву - \"S\"");
    }


}
