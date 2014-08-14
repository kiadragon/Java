import info.gridworld.actor.Bug;
import java.util.Arrays;
public class DancingBug extends Bug
{
    private int [] array;
    private int arrayIndex;
    public DancingBug(int arrays[])
    {
        // array copy using copyOf method
        array = Arrays.copyOf(arrays, arrays.length);
        arrayIndex = 0;
    }
    public void act()
    {
        if (canMove())
            {
                move();
            } else {
                // using Array to turn n time
                if (arrayIndex == array.length){
                    arrayIndex = 0;
                } else {
                    for (int i = 0; i < array[arrayIndex]; i++) {
                        turn();
                        // loop turn
                    }
                    arrayIndex ++;
                }

            }
        }
}
