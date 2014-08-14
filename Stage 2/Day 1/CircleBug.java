/** 
    * Author: Kiadragon
    * Email: sxhdragon@gmail.com
    * QQ: 709916025
    * Time 2014.8.7
**/

import info.gridworld.actor.Bug;
public class CircleBug extends Bug
{
    private int steps;
    private int sideLength;
    public CircleBug(int length)
    {
        // Set Steps
        // As BoxBug
        steps = 0;
        sideLength = length;
    }
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            // TURN ONCE A TIME TO RUN CIRCLE
            turn();
            steps = 0;
            // SET STEP = 0
        }
    }
}
