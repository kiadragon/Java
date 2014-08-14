// @KIADRAGON
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class QuickCrab extends CrabCritter
{
    // EXTENDS FROM CRABCRITTER
    public ArrayList<Location> getMoveLocations() {
        ArrayList<Location> firstLoc = new ArrayList<Location>();
        ArrayList<Location> secondLoc = new ArrayList<Location>();
        // FIRSTLOC IS STORED THE CRABCRITTER WALKING LOCITON
        // SECONDLOC STORES THE FURTHER LOCATION
        firstLoc = super.getMoveLocations();
        for (Location loc : firstLoc) {
            // USING ADDALL TO ADD FURTHER LOCAITON
            secondLoc.addAll(getLocationsInDirectionsNew(loc));
        }
        if (secondLoc.size() != 0) {
            // IF THE SECONDLOC IS NULL THAT WILL NOT RETURN ANYTHING
            return secondLoc;
       } else {
        return firstLoc;
       }
    }

/**
    * CREATE A NEW METHOD GETLOCAITONSINDIRECTIONNEW
    * NEED A LOCTION PARAMETER
    * RETURN FURTHER LOCITON SET FORM THIS LOCATION
**/
    public ArrayList<Location> getLocationsInDirectionsNew(Location locParameter){
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = new Location(locParameter.getRow(),
            locParameter.getCol());
        // PROTECT DATA
         int[] directions = 
         { Location.LEFT, Location.RIGHT };
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc) && gr.get(neighborLoc) == null){
                    // JUDGE LOCATION IS VALID IN THE GRID
                    // JUDGE LOCAITON IS NULL THAT CAN WALK IN
                    locs.add(neighborLoc);
            }
        }
        return locs;
    }

}
