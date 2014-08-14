/** 
    * Author: Kiadragon
    * Email: sxhdragon@gmail.com
    * QQ: 709916025
    * Time 2014.8.7
**/
import info.gridworld.actor.Bug;
public class SpiralBug extends Bug
{
    private int steps;
    private int sideLength;
    public SpiralBug(int length)
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
            turn();
            turn();
            // turn two time
            steps = 0;
            sideLength++;
            // key point to add sideLength 1 twice turn
        }
    }
}
