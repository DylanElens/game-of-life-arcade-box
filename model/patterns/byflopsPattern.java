package model.patterns;

import model.Cell;
import model.Pattern;

public class byflopsPattern extends Pattern {
    private final int LENGTH = 7;
    private final int WIDTH = 6;

    public byflopsPattern(){
        this.pattern =  new Cell[LENGTH][WIDTH];
        for (int row = 0; row < LENGTH; row++) {
            for (int col = 0; col < WIDTH; col++) {
                pattern[row][col] = new Cell(row, col);
            }
        }
        pattern[0][3].setValue(1);

		pattern[1][1].setValue(1);
        pattern[1][3].setValue(1);

        pattern[2][5].setValue(1);

        pattern[3][0].setValue(1);
        pattern[3][1].setValue(1);
        pattern[3][2].setValue(1);
        pattern[3][3].setValue(1);
        pattern[3][4].setValue(1);

        pattern[4][5].setValue(1);

        pattern[5][1].setValue(1);
        pattern[5][3].setValue(1);

        pattern[6][3].setValue(1);
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
