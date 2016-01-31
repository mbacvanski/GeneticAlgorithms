package Agriculture;

import java.util.ArrayList;

/**
 * Created by marc on 160130.
 */
public class Map {

    private Tile start;
    private Tile end;

    private ArrayList<ArrayList<Tile>> tiles;

    public Map(ArrayList<ArrayList<Tile>> tiles, Tile start, Tile end) {
        this.tiles = tiles;

    }

    public Tile getStart() {
        return start;
    }

    public Tile getEnd() {
        return end;
    }

    public ArrayList<ArrayList<Tile>> getTiles() {
        return tiles;
    }
}
