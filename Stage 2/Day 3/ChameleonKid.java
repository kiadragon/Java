// @KIADRAGON
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.util.ArrayList;

public class ChameleonKid extends ChameleonCritter {
    // EXTENDS FROM CHAMELEONCRITTER
    public ArrayList<Actor> getActors() {

        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs = { Location.AHEAD, Location.FULL_CIRCLE };
        for (Location loc : getLocationsInDirections(dirs))
        {
            // GET WHAT IS AHEAD AND BEHIND
            Actor a = getGrid().get(loc);
            if (a != null) {
                actors.add(a);
            }
        }
        return actors;
    }

/** 
    * CREATE A METHOD getLocationInDirections(int [] )
    * pass a array of int
    * return all the location set as ArraryList<location>
**/ 
    public ArrayList<Location> getLocationsInDirections(int[] directions) {

        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
        
        for (int d : directions) {
            // ITERATE directions
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            
            if (gr.isValid(neighborLoc)) {
                // JUDGE IS THE LOCATION INSIDE THE GRID
                locs.add(neighborLoc);
            }
        }
        return locs;
    }
}
