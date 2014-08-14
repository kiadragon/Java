// @KIADRAGON
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;

public class KingCrab extends CrabCritter
{
    // EXTENDS FROM KINGCRAB
    public void processActors(ArrayList<Actor> actors) {
        Grid gr = getGrid();
        for (Actor a : actors) {
            if (a instanceof Actor) {
                // mvoeCol moveRow STORE  THE DIFFERENCE BETWEEN KINGCRAB AND ACTOR
                int moveCol = a.getLocation().getCol() - getLocation().getCol(),
                moveRow = a.getLocation().getRow() - getLocation().getRow();
                // newLoc STORE NEW LOCATION FOR ACTOR
                Location newLoc = new Location(a.getLocation().getRow()
                    + moveRow, a.getLocation().getCol() + moveCol);
                if (gr.isValid(newLoc) && gr.get(newLoc) == null){
                    // JUDGE THE NEW LOCATION IS WHETHER INSIDE THE GRID AND NOTHING ON IT
                    a.moveTo(newLoc);
                } else {
                    // IF CAN MOVE , REMOVE THE ACTOR
                    a.removeSelfFromGrid();
                }
            }
        }
    }
}
