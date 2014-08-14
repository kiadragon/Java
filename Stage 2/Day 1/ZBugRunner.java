/** 
    * Author: Kiadragon
    * Email: sxhdragon@gmail.com
    * QQ: 709916025
    * Time 2014.8.7
**/
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

// ZBugRunner
public final class ZBugRunner
{
    private ZBugRunner(){}

    public static void main(String[] args)
    {   

        ActorWorld world = new ActorWorld();
        // ZBug new
        ZBug alice = new ZBug(4);
        world.add(new Location(1, 1), alice);
        world.show();
    }
}