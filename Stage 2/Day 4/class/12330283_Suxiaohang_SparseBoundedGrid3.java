// @KIADRAGON
// SparseBoundedGrid similate SparseBoundedGrid2
import java.util.ArrayList;
import java.util.*;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
/**
 * An <code>SparseBoundedGrid3</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid3<E> extends AbstractGrid<E>
{
    private Map<Location, E> occupantMap;
    private int row, col;

    /**
     * Constructs an empty unbounded grid.
     */
    public SparseBoundedGrid3(int rows, int cols)
    {
        // judge if rows and cols is not valid
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        row = rows;
        col = cols;
        occupantMap = new TreeMap<Location, E>();
    }

    // get row
    public int getNumRows()
    {
        return row;
    }
    // get col
    public int getNumCols()
    {
        return col;
    }

    // JUDGE IS THE LOCATION IS VALID
    public boolean isValid(Location loc)
    {
        return loc.getCol() >= 0 && loc.getCol() < col
        && loc.getRow() >= 0 && loc.getRow() < row;
    }

    // RETURN THE GET OCCUPIED LOCATION
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        // FOR KEY SET
        for (Location loc : occupantMap.keySet()) {
            a.add(loc);
        }
        return a;
    }

    // GET OBJECT ON LOCATION LOC
    public E get(Location loc)
    {

        // JUDGE TO SEE IS LOCATION IS VALID
        if (!isValid(loc)) {
            throw new IllegalArgumentException("loc == null");
        }
        // THEN RETURN
        return occupantMap.get(loc);
    }

    // PUT AN OBJECT ON LOCATION LOC
    public E put(Location loc, E obj)
    {
        // IF THE LOC IS NULL
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        // IF THE LOCATION IS NOT VALID
        if (!isValid(loc)) {
            throw new IllegalStateException("The location is not in the Grid!");
        }
        // IF THE OBJECT IS NULL
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }
        return occupantMap.put(loc, obj);
    }

    // REMOVE OBJECT FROM LOCATION
    public E remove(Location loc)
    {
        // JUDGE IS THE LOCATION IS NULL
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        // JUDGE THE LOCATION IS VALID
        if (!isValid(loc)) {
            throw new IllegalStateException("The location is not in the Grid!");
        }
        // RETURN THE OBJECT REMOVED
        return occupantMap.remove(loc);
    }
}
