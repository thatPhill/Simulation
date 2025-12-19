package world;

import actions.*;
import pathfinders.BreadthFirstSearch;
import utils.MessagePrinter;

import java.util.Scanner;

public class Simulation {
    public static int turnCount = 0;
    private WorldMap map;
    private BreadthFirstSearch bfs;
    private WorldConfig worldConfig;
    public final static String PAUSE_KEY = "P";
    public final static String RESUME_KEY = "S";


    Action turnAction;
    Action renderAction;
    Action initAction;
    Action spawnAction;

    private volatile boolean isPaused = false;
    private volatile boolean isRunning = true;
    private final Object pauseLock = new Object(); // Объект для синхронизации паузы

    public Simulation(WorldConfig worldConfig) {
        this.map = new WorldMap(worldConfig);
        this.worldConfig = worldConfig;
        this.bfs = new BreadthFirstSearch(map);
        this.turnAction = new TurnAction(map, bfs, worldConfig);
        this.renderAction = new RenderAction(map);
        this.initAction = new InitAction(map,worldConfig);
        this.spawnAction = new SpawnAction(map, worldConfig);

        Thread inputThread = new Thread(this::handleInput);
        inputThread.start();
    }

    private void handleInput() {
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase(PAUSE_KEY)) {
                    if (!isPaused) {
                        MessagePrinter.printPause();
                        MessagePrinter.printEatenGrasses();
                        MessagePrinter.printEatenHerbivores();
                        MessagePrinter.printDiedPredators();
                        MessagePrinter.printKeyForResume();
                    } else {
                        MessagePrinter.printAlreadyPause();
                    }
                    isPaused = true;

                } else if (input.equalsIgnoreCase(RESUME_KEY)) {
                    if (isPaused) {
                        MessagePrinter.printSimulationResumed();
                    } else {
                        MessagePrinter.printSimulationAlreadyResumed();
                    }
                    isPaused = false;
                    // Разбудить основной поток, если он ждет
                    synchronized (pauseLock) {
                        pauseLock.notifyAll();
                    }
                }
            }

        }
        scanner.close();
    }

    public void start() {
        initAction.execute();
        while (isRunning) {
            synchronized (pauseLock) {
                while (isPaused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
            renderAction.execute();
            MessagePrinter.printKeyForPause();
            turnAction.execute();
            spawnAction.execute();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
