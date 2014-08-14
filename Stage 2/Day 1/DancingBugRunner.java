/** 
    * Author: Kiadragon
    * Email: sxhdragon@gmail.com
    * QQ: 709916025
    * Time 2014.8.7
**/
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.grid.Location;

// DancingBugRunner
public final class DancingBugRunner
{
    private DancingBugRunner(){}

    public static void main(String[] args)
    {   
        int [] array = {1, 2, 3, 4, 5, 6, 7};
        ActorWorld world = new ActorWorld(new UnboundedGrid());
        DancingBug alice = new DancingBug(array);
        world.add(new Location(1, 1), alice);
        world.show();
    }
}