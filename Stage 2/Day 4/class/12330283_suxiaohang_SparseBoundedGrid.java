// @KIADRAGON
// class SparseBoundedGrid
import java.util.ArrayList;
import java.util.LinkedList;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
/**
 * <code>SparseBoundedGrid</code> contains the methods that are common to grid
 * implementations. <br />
 */
public class SparseBoundedGrid<E> implements Grid<E>
{
    private ArrayList<LinkedList> occupantArray;
    private int row, col;

    // judge the row and cols are valid
    public SparseBoundedGrid(int rows, int cols) {
        // JUDGE THE LOCATION
        // IF IT IS NOT VALID
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }

        row = rows;
        col = cols;
        occupantArray = new ArrayList<LinkedList>();
        for (int i =0; i < row; i++) {
            occupantArray.add(new LinkedList<SparseGridNode>());
        }
    }

    // get row
    public int getNumRows() {
        return row;
    }

    // get col
    public int getNumCols() {
        return col;
    }

    // judge locaiton is valid
    public boolean isValid(Location loc) {
        return loc.getCol() >= 0 && loc.getCol() < col
        && loc.getRow() >= 0 && loc.getRow() < row;
    }

    // get OccupiedLocations
    public ArrayList<Location> getOccupiedLocations() {
        // new occupiedLocation stores Location
        ArrayList<Location> occupiedLocation = new ArrayList<Location>();
        // record the row
        int rows;
        rows = 0;
        for (LinkedList a : occupantArray) {
            // get col object list
            LinkedList<SparseGridNode> b = a;
            if (a != null) {
                // judge it is nul or not
                for (SparseGridNode temp : b) {
                    if (temp != null) {
                        occupiedLocation.add(new Location(rows, temp.getCol()));
                    }
                }
            }
            // row add
            rows++;
        }
        return occupiedLocation;
    }
    
    public E get(Location loc) {
        // if is not valid throw an IllegalArgumentException
        if(!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        LinkedList<SparseGridNode> colNode = occupantArray.get(loc.getRow());
        // judge colNode is null or not
        if (colNode == null) {
            return null;
        }
        // get col node
        for (SparseGridNode i : colNode) {
            if (i.getCol() == loc.getCol()) {
                return (E)i.get();
            }
        }
        return null;
    }

    // put Object obj on Location obj
    // and return old Object
    public E put(Location loc, E obj) {
        // judge Location is valid
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        // judge obj is not null
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }
        // store old occupant
        E oldOccupant = get(loc);
        LinkedList<SparseGridNode> colNode = occupantArray.get(loc.getRow());
        // set object in SparseGridNode
        for (SparseGridNode temp : colNode) {
            if (temp.getCol() == loc.getCol()) {
                temp.setObj(obj);
                return oldOccupant;
            }
        }
        colNode.add(new SparseGridNode(obj, loc.getCol()));
        return oldOccupant;
    }

    // remove Object on Location loc
    public E remove(Location loc) {
        // judge it is valid or not
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        LinkedList<SparseGridNode> colNode = occupantArray.get(loc.getRow());
        // store the index of removing index
        for (int c = 0; c < colNode.size(); c++) {
            if (colNode.get(c).getCol() == loc.getCol()) {
                colNode.remove(c);
            }
        }
        return get(loc);
    }

    //  getNeighbors METHOD
    // FROM ABSTRACTGRID
    public ArrayList<E> getNeighbors(Location loc)
    {
        ArrayList<E> neighbors = new ArrayList<E>();
        for (Location neighborLoc : getOccupiedAdjacentLocations(loc)) {
            neighbors.add(get(neighborLoc));
        }
        return neighbors;
    }

    // getValidAdjacentLocations METHOD
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

    // getEmptyAdjacentLocations METHOD
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

    // getOccupiedAdjacentLocations METHOD
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

    /**
     * Creates a string that describes this grid.
     * @return a string with descriptions of all objects in this grid (not
     * necessarily in any particular order), in the format {loc=obj, loc=obj,
     * ...}
     */
    //METHOD TOSTRING
    //REWRITE DOWN THE LOCATION
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


    public class SparseGridNode<E> {
        private Object occupant;
        private int col;
        // constructor of SparseGridNode
        public SparseGridNode(E obj, int cols) {
            occupant = obj;
            col = cols;
        }
        // get return the Object
        public Object get() {
            return occupant;
        }
        // getCol() return col
        public int getCol() {
            return col;
        }
        //set Object in SparseGridNode
        public void setObj(E obj) {
            occupant = obj;
        }
    }
}