package model;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class World implements Runnable {
    private final int WORLD_MAX_SIZE = 104;
    private final int BORDER = 20;
    private final int SCREEN_MAX_SIZE = WORLD_MAX_SIZE - BORDER;
    private final int SPEED_MAX = 100;
    private final int SPEED_MIN = 2000;
    private int speed = 1000;
    private Cell[][] grid = new Cell[WORLD_MAX_SIZE][WORLD_MAX_SIZE];
    private Cursor cursor = new Cursor();
    private Timer timer = new Timer();
    private int cursorPosX = cursor.getX();
    private int cursorPosY = cursor.getY();
    private static final World instance = new World();

    private World() {
        makeCleanGrid();
    }

    public void makeCleanGrid(){
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                grid[row][col] = new Cell(row, col);
            }
        }
  }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = BORDER; row < SCREEN_MAX_SIZE; row++) {
            for (int col = BORDER; col < SCREEN_MAX_SIZE; col++) {
                countLiveNeighbours(grid[row][col]);
                if (grid[row][col].getValue() == 1 && !(row == cursorPosY && cursorPosX == col)) {
                    sb.append('*');
                }
                if (row == cursorPosY && cursorPosX == col) {
                    sb.append('X');
                }
                if (grid[row][col].getValue() == 0 && !(row == cursorPosY && cursorPosX == col)) {
                    sb.append('.');
                }
                sb.append("|");
                if (grid[row][col].getAmountOfLiveNeighbours() > 0) {
                    System.out.println(grid[row][col].getAmountOfLiveNeighbours());
                }
            }
            sb.append("\n");
            for (int col = BORDER; col < SCREEN_MAX_SIZE; col++) {
                sb.append("--");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void countLiveNeighbours(Cell cell) {
        int counter = 0;
        int startPosX = cell.getCol() - 1 < 0 ? cell.getCol() : cell.getCol() - 1;
        int startPosY = cell.getRow() - 1 < 0 ? cell.getRow() : cell.getRow() - 1;
        int endPosX = cell.getCol() + 1 > 83 ? cell.getCol() : cell.getCol() + 1;
        int endPosY = cell.getRow() + 1 > 83 ? cell.getRow() : cell.getRow() + 1;

        for (int row = startPosY; row <= endPosY; row++) {
            for (int col = startPosX; col <= endPosX; col++) {
                if (grid[row][col].getValue() == 1)
                    counter++;
            }
        }

        cell.setAmountOfLiveNeighbours(cell.getValue() == 1 ? counter - 1 : counter);
    }

    /*
     * Any live cell with two or three live neighbours survives.
     * Any dead cell with three live neighbours becomes a live cell.
     * All other live cells die in the next generation. Similarly, all other dead
     * cells stay dead.
     */

    private void setCellState(Cell cell) {
        if (cell.getValue() == 1 && (cell.getAmountOfLiveNeighbours() == 2 || cell.getAmountOfLiveNeighbours() == 3)) {
            cell.setValue(1);
        } else if (cell.getValue() == 0 && cell.getAmountOfLiveNeighbours() == 3) {
            cell.setValue(1);
        } else {
            cell.setValue(0);
        }
    }

    public void next() {
        for (int row = 0; row < WORLD_MAX_SIZE; row++) {
            for (int col = 0; col < WORLD_MAX_SIZE; col++) {
                countLiveNeighbours(grid[row][col]);
            }
        }
        for (int row = 0; row < WORLD_MAX_SIZE; row++) {
            for (int col = 0; col < WORLD_MAX_SIZE; col++) {
                setCellState(grid[row][col]);
            }
        }
    }

    public void placePattern() {
        int counterY = 0;
        int counterX = 0;
        for (int row = cursor.getY(); row < cursor.getY() + cursor.getSelectedPattern().getLength(); row++) {
            for (int col = cursor.getX(); col < cursor.getX() + cursor.getSelectedPattern().getWidth(); col++) {
                if (grid[cursor.getY() + counterY][cursor.getX() + counterX].getValue() == 0) {
                    grid[cursor.getY() + counterY][cursor.getX() + counterX]
                            .setValue(cursor.getSelectedPattern().getPattern()[counterY][counterX].getValue());
                }
                counterX++;
            }
            counterX = 0;
            counterY++;
        }
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                if (cursorPosY - 1 > BORDER)
                    cursorPosY--;
                break;
            case DOWN:
                if (cursorPosY + 1 < SCREEN_MAX_SIZE)
                    cursorPosY++;
                break;
            case LEFT:
                if (cursorPosX - 1 > BORDER)
                    cursorPosX--;
                break;
            case RIGHT:
                if (cursorPosX + 1 < SCREEN_MAX_SIZE)
                    cursorPosX++;
                break;
        }
        cursor.updateCursorLocation(cursorPosX, cursorPosY);
    }

    public String getCursorPosition() {
        return String.valueOf(cursor.getX()) + ", " + String.valueOf(cursor.getY());
    }

    public void setSelectedPattern(int index) {
        cursor.setSelectedPattern(index);
    }

    public void incrementPattern() {
        cursor.incrementPattern();
    }

    public void decrementPattern() {
        cursor.decrementPattern();
    }

    public void run() {
        timer.scheduleAtFixedRate(new Clock(), 0, speed);
    }

    public static World getInstance() {
        return instance;
    }

    public void toFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int row = BORDER; row < SCREEN_MAX_SIZE; row++) {
            for (int col = BORDER; col < SCREEN_MAX_SIZE; col++) {
                if (row == cursorPosY && cursorPosX == col) {
                    sb.append(2);
                }else{
                    sb.append(grid[row][col].getValue());
                }
            }
            sb.append("\n");
            for (int col = BORDER; col < SCREEN_MAX_SIZE; col++) {
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("matrix.txt"));
        writer.write(sb.toString());
        writer.close();
    }

    public void pauseGrid() {
       timer.cancel();  
       timer.purge();
    }

    public void incrementSpeed() {
        if (this.speed - 100 >= SPEED_MAX) {
            this.speed -= 100;
            System.out.println(this.speed);
            timer.cancel();
            timer.purge();
            timer = new Timer();
            timer.scheduleAtFixedRate(new Clock(), 0, speed);
        }else{
            timer.cancel();
            timer.purge();
            timer = new Timer();
            timer.scheduleAtFixedRate(new Clock(), 0, speed);
        }
    }

    public void decrementSpeed() {
        if (this.speed + 100 <= SPEED_MIN) {
            this.speed += 100;
            System.out.println(this.speed);
            timer.cancel();
            timer.purge();
            timer = new Timer();
            timer.scheduleAtFixedRate(new Clock(), 0, speed);
        }else{
            timer.cancel();
            timer.purge();
            timer = new Timer();
            timer.scheduleAtFixedRate(new Clock(), 0, speed);
        }
    }
}
