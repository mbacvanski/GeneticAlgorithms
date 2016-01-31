package Agriculture;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Paran on 160130.
 */
public class MapReader {
    private final int TILELISTROWS = 20;
    private final int TILELISTCOLS = 20;

    private Map map;
    private Tile start;
    private Tile end;
    private ArrayList<ArrayList<Tile>> tilelist = new ArrayList<>();

    private ArrayList<Integer> xWall = new ArrayList<>();
    private ArrayList<Integer> yWall = new ArrayList<>();

    private File mapFile = new File("src/Agriculture/map.txt");

    public MapReader() {

        //Filling in empty
        for (int row = 0; row < TILELISTROWS; row++) {
            tilelist.add(new ArrayList<>());
            for (int col = 0; col < TILELISTCOLS; col++) {
                tilelist.get(row).add(new Tile(' ', 0, 0));
            }
        }

    }

    public void readMap(File toRead) {
        try {
            System.out.println("MapReader.readMap");
            Scanner in = new Scanner(toRead);
            int lineNumber = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                char[] lineArray = line.toCharArray();
                parseStartEnd(lineArray, lineNumber);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            for (ArrayList<Tile> tilesHere : tilelist) {
                for (Tile tileHere : tilesHere) {
                    System.out.print(tileHere.getChar() + " ");
                }
                System.out.println();
            }
            map = new Map(tilelist, start, end);
        }
    }

    private void parseStartEnd(char[] tiles, int lineNumber) {
        int columnNumber = 0;
        for (int i = 0, tilesLength = tiles.length; i < tilesLength; i++) {
            char thisChar = tiles[i];
            if (thisChar == 'A') {
                start = new Tile(thisChar, lineNumber, columnNumber);
            } else if (thisChar == 'B') {
                end = new Tile(thisChar, lineNumber, columnNumber);
            } else if (thisChar == '1') {
                xWall.add(columnNumber);
                yWall.add(lineNumber);
            }
            tilelist.get(lineNumber).add(new Tile(thisChar, lineNumber, columnNumber++));
        }
    }

    public Tile getStart() {
        return start;
    }

    public Tile getEnd() {
        return end;
    }

    public ArrayList<ArrayList<Tile>> getTiles() {
        return tilelist;
    }

    public ArrayList<Integer> getyWall() {
        return yWall;
    }

    public ArrayList<Integer> getxWall() {
        return xWall;
    }

    public Map getMap() {
        return map;
    }
}
