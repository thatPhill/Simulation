package world;

import actions.*;
import pathfinders.BreadthFirstSearch;

import java.util.Scanner;

public class Simulation {
    public static int turnCount = 0;
    private WorldMap map;
    private BreadthFirstSearch bfs;
    private WorldConfig worldConfig;

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
        this.turnAction = new TurnActions(map, bfs, worldConfig);
        this.renderAction = new RenderAction(map);
        this.initAction = new InitActions(map);
        this.spawnAction = new SpawnActions(map, worldConfig);

        Thread inputThread = new Thread(this::handleInput);
        inputThread.start();
    }

    private void handleInput() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("P")) {
                    if (!isPaused) {
                        System.out.println("--------------------------SIMULATION PAUSED--------------------------");
                    } else {
                        System.out.println("--------------------------ALREADY PAUSED--------------------------");
                    }
                    isPaused = true;

                } else if (input.equalsIgnoreCase("S")) {
                    if (isPaused) {
                        System.out.println("--------------------------SIMULATION RESUMED--------------------------");
                    }  else {
                        System.out.println("--------------------------ALREADY RUNNING--------------------------");
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
