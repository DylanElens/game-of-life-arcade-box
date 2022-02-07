package model;

public abstract class Pattern {
    protected Cell[][] pattern;

    public Cell[][] getPattern(){
        return this.pattern;
    }

    public abstract int  getLength();
    public abstract int getWidth();

}
