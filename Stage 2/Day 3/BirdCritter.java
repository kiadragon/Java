// @KIADRAGON

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class BirdCritter extends Critter
{
    private static final int ZERO = 0;
    public BirdCritter()
    {
        setColor(Color.RED);
    }
    // CREATE A METHOD CAN DEFINE THE COLOR OF BRID
    public BirdCritter(Color color) {
        setColor(color);
    }

    // IT CAN AUTOMATICLY EAT CRAB
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.EAST, Location.NORTH, Location.NORTHEAST,
            Location.NORTHWEST, Location.SOUTH,
            Location.SOUTHWEST, Location.SOUTHEAST,
            Location.WEST  };
            // EAT CRAB IN ALL DIRECTION
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null) {
                actors.add(a);
            }
        }
        return actors;
    }

    public void processActors(ArrayList<Actor> actors) {
        for (Actor a : actors) {
            if (a instanceof Critter) {
                // EAT CRAB
                a.removeSelfFromGrid();
            }
        }
    }
    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs =
            { Location.EAST, Location.NORTH, Location.NORTHEAST,
            Location.NORTHWEST, Location.SOUTH,
            Location.SOUTHWEST, Location.SOUTHEAST,
            Location.WEST  };
        for (Location loc : getLocationsInDirections(dirs)) {
            if (getGrid().get(loc) == null) {
                locs.add(loc);
            }
        }
        return locs;
    }

    public Actor getNearestCrab(ArrayList<Actor> crabOnGrid) {
        Actor nearestCrab = (Actor)(crabOnGrid.get(ZERO));
        for (Actor a : crabOnGrid) {
            // Find nearest Crab
            if (Math.abs(nearestCrab.getLocation().getRow() - getLocation().getRow())
                + Math.abs(nearestCrab.getLocation().getCol() - getLocation().getCol())
                > Math.abs(a.getLocation().getRow() - getLocation().getRow())
                + Math.abs(a.getLocation().getCol() - getLocation().getCol())) {
                nearestCrab = (Actor)a;
            }
        }
            return nearestCrab;
    }

    // CREATE A METHOD GETNEARESTLOCATION()
    // NEED ACTOR AND GRID AS PARAMETER
    // RETURN NEAREST LOCATION
    public Location getNearestLocation(Actor nearestCrab, Grid gr) {
        int moveCol = 0, moveRow = 0;
        // GET DIRECTION TOWARD CRAB
        moveCol = getDifferenceCol(nearestCrab);
        moveRow = getDifferenceRow(nearestCrab);
        // Judge location VALID or NOT NULL
        if (gr.isValid(new Location(getLocation().getRow() + moveRow,
            getLocation().getCol() + moveCol))) {
                if (gr.get(new Location(getLocation().getRow() + moveRow,
            getLocation().getCol() + moveCol)) == null) {
                    return new Location(getLocation().getRow() + moveRow,
            getLocation().getCol() + moveCol);
            }
        }
            // IF THIS IS CANT MOVE
            // TRY Move Up or Down
        else if (gr.isValid(new Location(getLocation().getRow(),
            getLocation().getCol() + moveCol))) {
                if (gr.get(new Location(getLocation().getRow(),
            getLocation().getCol() + moveCol)) == null) {
                    return new Location(getLocation().getRow(),
            getLocation().getCol() + moveCol);
            }
        }
        // IF CANT MOVE LIKE ABOVE
        // MOVE LEFT OR RIGHT
        else if (gr.isValid(new Location(getLocation().getRow() + moveRow,
            getLocation().getCol()))) {
                if (gr.get(new Location(getLocation().getRow() + moveRow,
            getLocation().getCol())) == null) {
                    return new Location(getLocation().getRow() + moveRow,
            getLocation().getCol());
            }
        }
        return null;
    }

    public int getDifferenceRow(Actor nearestCrab) {
        if (nearestCrab.getLocation().getRow() - getLocation().getRow() != ZERO) {
            return (nearestCrab.getLocation().getRow() - getLocation().getRow() > ZERO)?1:-1;
        } else {
            return ZERO;
        }
    }

    public int getDifferenceCol(Actor nearestCrab) {
        if (nearestCrab.getLocation().getCol() - getLocation().getCol() != ZERO) {
            return (nearestCrab.getLocation().getCol() - getLocation().getCol() > ZERO)?1:-1;
        } else {
            return ZERO;
        }
    }

    public Location selectMoveLocation(ArrayList<Location> loc) {
        // GET CRABS on GRID
        Grid gr = getGrid();
        ArrayList<Actor> crabOnGrid = new ArrayList<Actor> ();
        ArrayList<Location> locList = gr.getOccupiedLocations();
        for (Location a : locList) {
            // FIND CRAB
            // Critter != this
            if (gr.get(a) instanceof Critter && gr.get(a) != this) {
                Actor crab = (Actor)(gr.get(a));
                crabOnGrid.add(crab);
            }
        }

        // Init nearestCrab
        if (crabOnGrid.size() == 0) {
            return super.selectMoveLocation(loc);
        }

        Actor nearestCrab = getNearestCrab(crabOnGrid);
        
        // SET DIRECTION TOWARD CRAB
        setDirection(getLocation().getDirectionToward(
            nearestCrab.getLocation()));

        // SELECT MOVE TO NEARESTCRAB DIRECTION
        Location targetLoc =getNearestLocation(nearestCrab, gr);
        // IF TARGET LOCATION IS NULL
        // THEN MOVE LIKE CRITTER
        if (targetLoc == null) {
            return super.selectMoveLocation(loc);
        } else {
            return targetLoc;
        }
    }

    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc)) {
                locs.add(neighborLoc);
            }
        }
        return locs;
    }

}
