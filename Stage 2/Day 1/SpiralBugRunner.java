/** 
    * Author: Kiadragon
    * Email: sxhdragon@gmail.com
    * QQ: 709916025
    * Time 2014.8.7
**/
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

// SpiralBugRunner
public final class SpiralBugRunner
{
    private SpiralBugRunner(){}

    public static void main(String[] args)
    {   
        ActorWorld world = new ActorWorld(new UnboundedGrid());
        // SpiralBug
        SpiralBug alice = new SpiralBug(4);
        world.add(new Location(1, 1), alice);
        world.show();
    }
}