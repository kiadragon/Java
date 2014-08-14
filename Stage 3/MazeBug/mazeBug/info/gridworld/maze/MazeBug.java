package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
    private int north = 1, south = 1, west = 1, east = 1;
    private Location next;
    private Location last;
    private boolean isEnd = false;
    private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
    private Integer stepCount = 0;
    private boolean hasShown = false;

    // final message has been shown

    /**
     * Constructs a box bug that traces a square of a given side length
     * 
     * @param length
     *            the side length
     */
    public MazeBug() {
        setColor(Color.GREEN);
        last = new Location(0, 0);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        boolean willMove = canMove();
        getValid(getLocation());
        if (isEnd) {
            // to show step count when reach the goal
            if (!hasShown) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } else if (willMove) {
            move();
            // increase step count when move
            stepCount++;
        }
    }

    /**
     * Find all positions that can be move to.
     * 
     * @param loc
     *            the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return null;
        }
        ArrayList<Location> valid = new ArrayList<Location>();
        // get empty adjacent locations and return to valid
        valid = getEmptyAdjacentLocations(gr, getLocation());
        if (valid.size() != 0) {
            // set next position
            next = getNext(valid);
            last = getLocation();
            valid.add(last);
            crossLocation.push(valid);
        } else {
            // get last ArrayList
            ArrayList<Location> nextList = crossLocation.pop();
            decreaseDirectionChange();
            // return to last position
            next = nextList.get(nextList.size() - 1);
        }
        // what save in last index is bug's previous position
        return valid;
    }

    /**
     * getEmptyAdjacentLocations
     * 
     * @param loc
     * @return valid adjacentLocation
     */
    public ArrayList<Location> getEmptyAdjacentLocations(Grid<Actor> gr,
            Location loc) {
        ArrayList<Location> valid = gr.getEmptyAdjacentLocations(loc);
        // store array index need to remove
        Stack<Integer> removeIndex = new Stack<Integer>();
        int index = 0;
        for (Location lo : valid) {
            int direction = getLocation().getDirectionToward(lo);
            if (direction != Location.SOUTH && direction != Location.NORTH
                    && direction != Location.WEST && direction != Location.EAST) {
                removeIndex.push(index);
            }
            index++;
        }
        while (!removeIndex.isEmpty()) {
            valid.remove((int) removeIndex.pop());
        }
        return valid;
    }

    /**
     * getNext return next location
     * 
     * @param valid
     * @return next location
     */
    public Location getNext(ArrayList<Location> valid) {
        Location loc;
        int direction = caculateDirection(valid);
        loc = getLocation().getAdjacentLocation(direction);
        // return
        return loc;
    }

    /**
     * caculateDirection came out randomly
     * 
     * @param null
     * @return Direction
     */
    public int caculateDirection(ArrayList<Location> valid) {
        // Random new
        Random randomNumber = new Random();
        int southTemp = 0, northTemp = 0, westTemp = 0, eastTemp = 0;
        boolean changeDirection = false;
        for (Location lo : valid) {
            // north
            int direction = getLocation().getDirectionToward(lo);
            if (getDirection() != direction) {
                changeDirection = true;
            }
            if (direction == Location.NORTH) {
                northTemp = north;
            }
            // south
            if (direction == Location.SOUTH) {
                southTemp = south;
            }
            // west
            if (direction == Location.WEST) {
                westTemp = west;
            }
            // east
            if (direction == Location.EAST) {
                eastTemp = east;
            }
        }
        // get Random from nextInt
        int r = randomNumber.nextInt(northTemp + southTemp + westTemp
                + eastTemp + 1);
        // north
        return directionDefined(r, northTemp, southTemp, westTemp, eastTemp, changeDirection);
    }

    /**
     * function return define which direction it go
     * 
     * @param r
     * @param northTemp
     * @param southTemp
     * @param westTemp
     * @param easTemp
     * @return direction it go
     */
    public int directionDefined(int r, int northTemp, int southTemp,
            int westTemp, int eastTemp, boolean changeDirection) {
        if (r <= northTemp && northTemp != 0) {
            north++;
            return Location.NORTH;
        }
        // south
        if (northTemp <= r && r <= northTemp + southTemp && southTemp != 0) {
            south++;
            return Location.SOUTH;
        }
        // west
        if (northTemp + southTemp <= r && r <= northTemp + southTemp + westTemp
                && westTemp != 0) {
            west++;
            return Location.WEST;
        }
        if (northTemp + southTemp + westTemp <= r
                && r <= northTemp + southTemp + westTemp + eastTemp
                && eastTemp != 0) {
            east++;
            return Location.EAST;
        } else {
            // throw an exception
            throw new IllegalArgumentException("South" + southTemp + "North"
                    + northTemp + "West" + westTemp + "East" + eastTemp);
        }
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * 
     * @return true if this bug can move.
     */
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        // judge is reached end
        ArrayList<Actor> neighbors = gr.getNeighbors(getLocation());
        for (Actor neigh : neighbors) {
            // judge neighbor is red rock or not
            int direction = getLocation().getDirectionToward(
                    neigh.getLocation());
            if (direction == Location.SOUTH || direction == Location.NORTH
                    || direction == Location.WEST || direction == Location.EAST) {
                if (neigh.getColor().equals(Color.RED)) {
                    isEnd = true;
                    return false;
                }
            }
        }
        // judge is there anyway can g
        return true;
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        if (gr.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            moveTo(next);
            last = loc;
            System.out.print("--North"
                    + north
                    + "  --South"
                    + south
                    + "   --West" + west + "   --East " + east + "\n");
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }

    /**
     * decrease the directionchange
     */
    public void decreaseDirectionChange() {
        int direction = last.getDirectionToward(getLocation());
        // north
        if ((direction == 0 || direction == 360) && north != 1) {
            north--;
        }
        // east
        else if (direction == 90 && east != 1) {
            east--;
        }
        // south
        else if (direction == 180 && south != 1) {
            south--;
        }
        // west
        else if (direction == 240 && west != 1) {
            west--;
        }
    }
}
