/** 
    * Author: Kiadragon
    * Email: sxhdragon@gmail.com
    * QQ: 709916025
    * Time 2014.8.7
**/
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
// ZBUG
public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private boolean moveFlag = false;
    public ZBug(int length)
    {
        setDirection(Location.EAST);
        // Set Steps
        // As BoxBug
        steps = 0;
        sideLength = length;
    }
    public void act()
    {
        // Using three condition judge how many turn it is need
        if ((steps % sideLength != 0) && canMove() || steps == 0 || moveFlag)
        {
            move();
            steps++;
            moveFlag = false;
        }
        else
        {
            // First turn 3 Times
            if (steps == sideLength){
                turn();
                turn();
                turn();
                moveFlag = true;
            } else if (steps == sideLength*2){
                // Second turn 5 Times
                turn();
                turn();
                turn();
                turn();
                turn();
                moveFlag = true;
            }
        }
    }
}
