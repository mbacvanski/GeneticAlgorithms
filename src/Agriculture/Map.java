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

        this.start = start;
        this.end = end;
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

    public void setTile(Tile tile, int x, int y) {
        tiles.get(y).set(x, tile);
    }
}
