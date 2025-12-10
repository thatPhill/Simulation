import actions.Action;
import entities.creature.Herbivore;
import entities.creature.Predator;
import entities.resource.Grass;
import entities.stative.Tree;
import pathfinders.BreadthFirstSearch;
import render.RenderConsoleMap;
import world.Coordinates;
import world.Simulation;
import world.WorldMap;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation();
        simulation.run();



    }


}
