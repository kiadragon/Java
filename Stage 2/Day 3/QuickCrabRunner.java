// @KIADRAGON
// SIMULATE WITH CHAMELEONREUNNER.JAVA
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

public final class QuickCrabRunner
{
    private QuickCrabRunner(){}
    // protect from instance
    private static final int ONE = 1,
    TWO = 2,
    THREE = 3,
    FOUR = 4,
    FIVE = 5, SEVEN = 7, EIGHT = 8;
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(SEVEN, EIGHT), new Rock());
        world.add(new Location(THREE, THREE), new Rock());
        world.add(new Location(TWO, EIGHT), new Rock(Color.BLUE));
        world.add(new Location(FIVE, FIVE), new Rock(Color.PINK));
        world.add(new Location(ONE, FIVE), new Rock(Color.RED));
        world.add(new Location(SEVEN, TWO), new Rock(Color.YELLOW));
        // CREATE NEW QuickCrab()
        world.add(new Location(FOUR, FOUR), new QuickCrab());
        world.add(new Location(FIVE, EIGHT), new QuickCrab());
        world.show();
    }
}