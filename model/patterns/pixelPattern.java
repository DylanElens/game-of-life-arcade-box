package model.patterns;

import model.Cell;
import model.Pattern;

public class pixelPattern extends Pattern {
    private final int LENGTH = 1;
    private final int WIDTH = 1;

    public pixelPattern(){
        this.pattern =  new Cell[LENGTH][WIDTH];
        pattern[0][0] = new Cell(0, 0);
        pattern[0][0].setValue(1);
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
