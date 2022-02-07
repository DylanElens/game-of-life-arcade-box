package model.patterns;

import model.Cell;
import model.Pattern;

public class glidersbythedozenPattern extends Pattern {
  private final int LENGTH = 3;
  private final int WIDTH = 5;

  public glidersbythedozenPattern() {
    this.pattern = new Cell[LENGTH][WIDTH];
    for (int row = 0; row < LENGTH; row++) {
      for (int col = 0; col < WIDTH; col++) {
        pattern[row][col] = new Cell(row, col);
      }
    }
      pattern[0][0].setValue(1);
      pattern[0][1].setValue(1);
      pattern[0][4].setValue(1);
      pattern[1][0].setValue(1);
      pattern[1][4].setValue(1);
      pattern[2][0].setValue(1);
      pattern[2][3].setValue(1);
      pattern[2][4].setValue(1);
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
