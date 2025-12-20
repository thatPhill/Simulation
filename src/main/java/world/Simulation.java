package world;

import actions.*;
import pathfinders.BreadthFirstSearch;
import render.RenderConsoleMap;
import utils.MessagePrinter;

import java.util.Scanner;

public class Simulation {
    private int turnCount = 0;
    private final WorldMap map;
    private final BreadthFirstSearch bfs;
    private final WorldConfig worldConfig;
    public final static String PAUSE_KEY = "P";
    public final static String RESUME_KEY = "S";
    private final RenderConsoleMap render = new RenderConsoleMap();


    private final Action initAction;
    private final Action turnAction;
    private final Action spawnAction;

    private volatile boolean isPaused = false;
    private volatile boolean isRunning = true;
    private final Object pauseLock = new Object(); // Объект для синхронизации паузы

    public Simulation(WorldConfig worldConfig) {
        this.map = new WorldMap(worldConfig.getSize());
        this.worldConfig = worldConfig;
        this.bfs = new BreadthFirstSearch(map);
        this.turnAction = new TurnAction(map, bfs, worldConfig);
        this.initAction = new InitAction(map, worldConfig);
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

    private void nextTurn(){
        render.render(map,this);
        MessagePrinter.printKeyForPause();
        turnAction.execute();
        spawnAction.execute();
        setTurnCount(getTurnCount() + 1);
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
            nextTurn();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }
}
