package model.patterns;

import model.Cell;
import model.Pattern;

public class blockPattern extends Pattern {
    private final int LENGTH = 2;
    private final int WIDTH = 2;

    public blockPattern(){
        this.pattern =  new Cell[LENGTH][WIDTH];
        for (int row = 0; row < LENGTH; row++) {
            for (int col = 0; col < WIDTH; col++) {
                pattern[row][col] = new Cell(row, col);
                pattern[row][col].setValue(1);
            }
        }
    }

    @Override
    public int getLength() {
        return LENGTH;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }
}
