package Agriculture;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by marc on 160130.
 */
public class PathFinder implements DirectionSender, GridOwner {

    private final int numberOfAIs = 50;
    public AI fittestOne;
    private ArrayList<Direction> directions;
    private Iterator<Direction> directionIterator;
    private Map map;
    private boolean solved = false;
    private Tile startTile;
    private Tile endTile;

    private int x;
    private int y;

    private ArrayList<AI> aiList;

//    private MapReader mapReader;

    public PathFinder(Map map) {
//        mapReader = new MapReader();
//        mapReader.readMap();
//
//        map = mapReader.getMap();
//
//        startTile = mapReader.getStart();
//        endTile = mapReader.getEnd();

        startTile = map.getStart();
        endTile = map.getEnd();

        this.map = map;

        aiList = new ArrayList<>();
        for (int i = 0; i < numberOfAIs; i++) {
            aiList.add(new AI(startTile, endTile, this));
        }
    }

    public void startGuidance() {
        System.out.println("PathFinder.startGuidance");
        while (!solved) {
            double fittest = 0;
            double tester = 0;
            System.out.println("Not solved yet!");
            for (int i = 0; i < aiList.size(); i++) {
                tester = aiList.get(i).getFitness();
                aiList.get(i).run();
                System.out.println("aiList.get(i).getFitness() = " + aiList.get(i).getFitness());
                if (tester > fittest) {
                    solved = true;
                    fittestOne = aiList.get(i);
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

    public AI getFittest() {
        return fittestOne;
    }

    public enum Direction {UP, DOWN, LEFT, RIGHT}

}
