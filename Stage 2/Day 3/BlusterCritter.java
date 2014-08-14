// @KIADRAGON
// EXTEND FROM CRITTER
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;

public class BlusterCritter extends Critter
{
    private int courage;
    private static final int STEP_SIZE = 2;
    // SET STEP_SIZE 
    // consider how many need to taking into count
    private static final int DARKENING_FACTOR = 10;
    private static final int MAX_COLOR_VALUE = 255;
    // SET DARKENING_FACTOR
    // CONSTANT that use lighten and darkening
    public BlusterCritter(int c) {
        courage = c;
    }
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Grid gr = getGrid();
        Location loc = getLocation();
        int col = loc.getCol(),
        // get BLUSTCRITTER COL
        row = loc.getRow();
        // GET BLUSTCRITTER ROW
        for(int i = -STEP_SIZE; i < STEP_SIZE; i++) {

            for (int j = -STEP_SIZE; j < STEP_SIZE; j++) {
                // CONSIDER ((2 * STEP_SIZE) + 1) * ((2 * STEP_SIZE) + 1)
                if (gr.isValid(new Location(row + j, col + i))) {
                    // JUDGE IS IN THE GRID
                    Actor a = (Actor)gr.get(new Location(row + j, col +i));
                    if (a != this && 
                        a instanceof Critter) {
                        // JUDGE IF NOT IS ITSELF OR OTHER CRITTERS
                        actors.add(a);
                    }
                }
            }
        }
        return actors;
    }

    public void processActors(ArrayList<Actor> actors) {
        int n = actors.size();
        if (n >= courage) {
            // LIGHTENING PROCESS
            Color c = getColor();
            int red = (c.getRed() - DARKENING_FACTOR)<0?0:c.getRed() - DARKENING_FACTOR;
            int blue= (c.getBlue() - DARKENING_FACTOR)<0?0:c.getBlue() - DARKENING_FACTOR;
            int green = (c.getGreen() - DARKENING_FACTOR)<0?0:c.getGreen() - DARKENING_FACTOR;
            setColor(new Color(red, green, blue));
            return;
        } else {
            // DARKENING PROCESS
            Color c = getColor();
            int red = (c.getRed() + DARKENING_FACTOR)>MAX_COLOR_VALUE?MAX_COLOR_VALUE:c.getRed() + DARKENING_FACTOR;
            int blue= (c.getBlue() + DARKENING_FACTOR)>MAX_COLOR_VALUE?MAX_COLOR_VALUE:c.getBlue() + DARKENING_FACTOR;
            int green = (c.getGreen() + DARKENING_FACTOR)>MAX_COLOR_VALUE?MAX_COLOR_VALUE:c.getGreen() + DARKENING_FACTOR;
            setColor(new Color(red, green, blue));
            return;
        }
    }

}
