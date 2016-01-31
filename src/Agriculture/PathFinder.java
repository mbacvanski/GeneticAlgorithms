package Agriculture;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by marc on 160130.
 */
public class PathFinder implements DirectionSender, GridOwner {

    private final int numberOfAIs = 50;
    public AI fittest;
    private ArrayList<Direction> directions;
    private Iterator<Direction> directionIterator;
    private Map map;
    private boolean solved = false;
    private Tile startTile;
    private Tile endTile;

    private int x;
    private int y;

    private ArrayList<AI> aiList;

    private MapReader mapReader;

    public PathFinder() {
        mapReader = new MapReader();
        mapReader.readMap();

        map = mapReader.getMap();

        startTile = mapReader.getStart();
        endTile = mapReader.getEnd();

        aiList = new ArrayList<>();
        for (int i = 0; i < numberOfAIs; i++) {
            aiList.add(new AI(startTile, endTile, this));
        }
    }

    public void startGuidance() {
        System.out.println("PathFinder.startGuidance");
        while (!solved) {
            for (int i = 0; i < aiList.size(); i++) {
                aiList.get(i).run();
                if (aiList.get(i).getFitness() == 1) {
                    solved = true;
                    fittest = aiList.get(i);
                }
            }
        }
    }

    @Override
    public Direction getNextDirection() {
        if (directionIterator.hasNext()) {
            return directionIterator.next();
        }
        return null;
    }

    @Override
    public Map getMap() {
        return map;
    }

    public enum Direction {UP, DOWN, LEFT, RIGHT}

}
