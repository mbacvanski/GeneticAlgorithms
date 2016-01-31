package Agriculture;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by marc on 160130.
 */
public class MapReader {
    private final int TILELISTROWS = 20;
    private final int TILELISTCOLS = 20;

    private Map map;
    private Tile start;
    private Tile end;
    private ArrayList<ArrayList<Tile>> tilelist = new ArrayList<>();
    private File mapFile = new File("src/Agriculture/map.txt");

    public MapReader() {

        for (int row = 0; row < TILELISTROWS; row++) {
            tilelist.add(new ArrayList<Tile>());
            for (int col = 0; col < TILELISTCOLS; col++) {
                tilelist.get(row).add(new Tile(' ', 0, 0));
            }
        }

    }

    public void readMap() {
        try {
            Scanner in = new Scanner(mapFile);
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
            map = new Map(tilelist, start, end);
        }
    }

    private void parseStartEnd(char[] tiles, int lineNumber) {
        int columnNumber = 0;
        for (char thisChar : tiles) {
            if (thisChar == 'A') {
                start = new Tile(thisChar, lineNumber, columnNumber);
            } else if (thisChar == 'B') {
                end = new Tile(thisChar, lineNumber, columnNumber);
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

    public Map getMap() {
        return map;
    }
}
