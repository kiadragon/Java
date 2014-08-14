import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

public class JumperRunner
{
    public static void main(String[] args)
    {   
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(1, 1), alice);
        world.show();
    }
}