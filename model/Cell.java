package model;

public class Cell {
    private int row;
    private int col;
    private int value;
	private int amountOfLiveNeighbours;

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        this.value = 0;
    }

    public int getValue() {
        return value;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setValue(int value) {
        this.value = value;
    }
	public int getAmountOfLiveNeighbours() {
		return amountOfLiveNeighbours;
	}
	public void setAmountOfLiveNeighbours(int amount){
		this.amountOfLiveNeighbours = amount;
	}
}
