// @KIADRAGON
// UnboundedGrid22 extend from Grid
import java.util.ArrayList;
import info.gridworld.grid.Grid;
import java.util.*;
import info.gridworld.grid.Location;

/**
 * An <code>UnboundedGrid2</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> implements Grid<E>
{
    private static final int SIZE = 16;
    private Object[][] occupantArray;
    private int size = SIZE;
    /**
     * Constructs an empty unbounded grid.
     **/
    public UnboundedGrid2()
    {
        occupantArray = new Object[size][size];
    }

    //  RETURN -1 
    public int getNumRows()
    {
        return -1;
    }

    // RETURN -1
    public int getNumCols()
    {
        return -1;
    }

    // JUDGE IS VALID
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getCol() && 0 <= loc.getRow();
    }

    // GET OCCUPIED LOCATION
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        // ITERATE
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    // ADD LOCATION
                    a.add(loc);
                }
            }
        }
        // RETURN
        return a;
    }

    // GET OBJECT FROM LOCATION
    public E get(Location loc)
    {
        // IS THE LOCATION IS VALID ?
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        // RETURN
        if (loc == null) {
            return null;
        }
        doubleSize(loc);
        return (E)occupantArray[loc.getRow()][loc.getCol()];
    }

    // PUT OBJ IN LOC
    public E put(Location loc, E obj)
    {
        // JUDGE LOC IS NULL
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        // IF OBJ IS NULL
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }
        // JUGDE RUN DOUBLESIZE OR NOT
        doubleSize(loc);
        // RETURN OLDOBJECT
        E oldObject = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldObject;
    }

    // REMOVE OBJECT
    public E remove(Location loc)
    {
        // IF LOC IS NULL
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        // IF THE LOCATION IS INVALID
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }

    public void doubleSize(Location loc) {
        int newSize = size;
        if (loc == null) {
            return;
        }
        while (loc.getCol() >= newSize  || loc.getRow() >= newSize) {
            // EXTEND THE SIZE
            newSize = size * 2;
        }

        // DOUBLE ARRAY
        Object[][] tempOccupantArray = new Object[newSize][newSize];
        for (int i = 0; i < size; i++) {
            tempOccupantArray[i] = Arrays.copyOf(occupantArray[i], newSize);
        }
        occupantArray = tempOccupantArray;
        size = newSize;

    }

    // METHOD getNeighbors
    // FROM ABSTRACTGRID
    public ArrayList<E> getNeighbors(Location loc)
    {
        ArrayList<E> neighbors = new ArrayList<E>();
        for (Location neighborLoc : getOccupiedAdjacentLocations(loc)) {
            neighbors.add(get(neighborLoc));
        }
        return neighbors;
    }

    // METHOD getValidAdjacentLocations
    // FROM ABSTRACTGRID
    public ArrayList<Location> getValidAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();

        int d = Location.NORTH;
        for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
        {
            Location neighborLoc = loc.getAdjacentLocation(d);
            if (isValid(neighborLoc)) {
                locs.add(neighborLoc);
            }
            d = d + Location.HALF_RIGHT;
        }
        return locs;
    }

    // METHOD getEmptyAdjacentLocations
    // FROM ABSTRACTGRID
    public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location neighborLoc : getValidAdjacentLocations(loc))
        {
            if (get(neighborLoc) == null) {
                locs.add(neighborLoc);
            }
        }
        return locs;
    }

    // METHOD getOccupiedAdjacentLocations
    // FROM ABSTRACTGRID
    public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location neighborLoc : getValidAdjacentLocations(loc))
        {
            if (get(neighborLoc) != null) {
                locs.add(neighborLoc);
            }
        }
        return locs;
    }

    // METHOD toString
    // FROM ABSTRACTGRID
    /**
     * Creates a string that describes this grid.
     * @return a string with descriptions of all objects in this grid (not
     * necessarily in any particular order), in the format {loc=obj, loc=obj,
     * ...}
     */
        public String toString()
        {
            String s = "{";
            for (Location loc : getOccupiedLocations())
            {
                if (s.length() > 1) {
                    s += ", ";
                }
                s += loc + "=" + get(loc);
            }
            return s + "}";
        }

}
