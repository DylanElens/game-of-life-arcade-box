package model.patterns;

import model.Cell;
import model.Pattern;


public class pulsarPattern extends Pattern{
	
    private final int LENGTH = 13;
    private final int WIDTH = 13;

    public pulsarPattern(){
        this.pattern =  new Cell[LENGTH][WIDTH];

        for (int row = 0; row < LENGTH; row++) {
            for (int col = 0; col < WIDTH; col++) {
                pattern[row][col] = new Cell(row, col);
            }
        }
		pattern[0][2].setValue(1);
		pattern[0][3].setValue(1);
		pattern[0][4].setValue(1);
		pattern[0][8].setValue(1);
		pattern[0][9].setValue(1);
    pattern[0][10].setValue(1);

		pattern[2][0].setValue(1);
		pattern[2][5].setValue(1);
		pattern[2][7].setValue(1);
		pattern[2][12].setValue(1);

    pattern[3][0].setValue(1);
		pattern[3][5].setValue(1);
		pattern[3][7].setValue(1);
		pattern[3][12].setValue(1);

    pattern[4][0].setValue(1);
		pattern[4][5].setValue(1);
		pattern[4][7].setValue(1);
		pattern[4][12].setValue(1);

    pattern[5][2].setValue(1);
		pattern[5][3].setValue(1);
		pattern[5][4].setValue(1);
		pattern[5][8].setValue(1);
		pattern[5][9].setValue(1);
    pattern[5][10].setValue(1);

    pattern[7][2].setValue(1);
		pattern[7][3].setValue(1);
		pattern[7][4].setValue(1);
		pattern[7][8].setValue(1);
		pattern[7][9].setValue(1);
    pattern[7][10].setValue(1);

    pattern[8][0].setValue(1);
		pattern[8][5].setValue(1);
		pattern[8][7].setValue(1);
		pattern[8][12].setValue(1);

    pattern[9][0].setValue(1);
		pattern[9][5].setValue(1);
		pattern[9][7].setValue(1);
		pattern[9][12].setValue(1);

    pattern[10][0].setValue(1);
		pattern[10][5].setValue(1);
		pattern[10][7].setValue(1);
		pattern[10][12].setValue(1);

    pattern[12][2].setValue(1);
		pattern[12][3].setValue(1);
		pattern[12][4].setValue(1);
		pattern[12][8].setValue(1);
		pattern[12][9].setValue(1);
    pattern[12][10].setValue(1);
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

