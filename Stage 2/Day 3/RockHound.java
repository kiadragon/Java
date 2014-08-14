// @KIADRAGON
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;

import java.util.ArrayList;

public class RockHound extends Critter
{
    // EXTENDS FROM CRITTER
    public void processActors(ArrayList<Actor> actors)  {
        for (Actor a : actors) {
            
            if (!(a instanceof Critter)) {
                // CONSIDER IT IS ROCK OR FLOWER OR NOT CRIITTER, THEN REMOVE IT
                a.removeSelfFromGrid();
            }

        }
    }
}
