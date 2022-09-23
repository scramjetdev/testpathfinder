package net.scramjet;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private final boolean[][] grid;

    public Grid(int size) {
        this.grid = new boolean[size][size];
    }

    public boolean get(int x, int y) {
        if (x >= grid.length || y >= grid.length || x < 0 || y < 0) return true;
        return grid[x][y];
    }

    public void set(int x, int y, boolean value) {
        grid[x][y] = value;
    }

    public int getGridSize() {
        return grid.length;
    }

    public List<Coords> getNeighbors(Coords coords) {
        List<Coords> toReturn = new ArrayList<>();

        if (!get(coords.getX() + 1, coords.getY())) toReturn.add(new Coords(coords.getX() + 1, coords.getY()));
        if (!get(coords.getX() - 1, coords.getY())) toReturn.add(new Coords(coords.getX() - 1, coords.getY()));
        if (!get(coords.getX() , coords.getY() +1)) toReturn.add(new Coords(coords.getX(), coords.getY() + 1));
        if (!get(coords.getX() , coords.getY() -1)) toReturn.add(new Coords(coords.getX(), coords.getY()- 1));

       return toReturn;
    }
}
