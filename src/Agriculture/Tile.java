package Agriculture;

/**
 * Created by marc on 160130.
 */
public class Tile {

    private char thisChar;

    private int x;
    private int y;
    private int lineNumber;
    private int columnNumber;

    public Tile(char thisChar, int lineNumber, int columnNumber) {
        this.thisChar = thisChar;

        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public boolean isNotWall() {
        return thisChar != '1';
    }


    public char getChar() {
        return thisChar;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
