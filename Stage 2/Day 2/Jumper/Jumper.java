import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

public class Jumper extends Bug
{
    public void act()
    {
        if (canJump()) {
            Jump();
        }
        else {
            super.act();
        }
    }


    public void Jump(){
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else
            turn();
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }

    public boolean canJump(){
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Flower);
    }
}
