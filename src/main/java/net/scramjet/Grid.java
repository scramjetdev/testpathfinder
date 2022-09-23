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
        return getNeighbors(coords.getX(), coords.getY());
    }
    public List<Coords> getNeighbors(int x, int y) {
        List<Coords> neighbors = new ArrayList<>();
        /*
        C = Center
        N = neighbor
        | = Blocks that needs to be false
        */

        /*
         *  *  *
         *  C  N
         *  *  *
         */
        if(!get(x + 1, y)) neighbors.add(new Coords(x + 1, y));

        /*
         *  *  *
         *  C  |
         *  |  N

         |1 = (1,0)
         |2 = (0,-1)
         */
        if (!get(x + 1, y - 1) && (!get(x + 1, y) || !get(x, y - 1))) neighbors.add(new Coords(x + 1, y - 1));

        /*
         *  *  *
         *  C  *
         *  N  *
         */
        if(!get(x, y - 1)) neighbors.add(new Coords(x, y - 1));

        /*
         *  *  *
         |  C  *
         N  |  *

         |1 = (-1,0)
         |2 = (0,-1)
         */
        if(!get(x - 1, y - 1) && (!get(x - 1, y) || !get(x, y - 1))) neighbors.add(new Coords(x - 1, y - 1));

         /*
         *  *  *
         N  C  *
         *  *  *
         */
        if(!get(x - 1, y)) neighbors.add(new Coords(x - 1, y));

        /*
         N  |  *
         |  C  *
         *  *  *

         |1 = (-1,0)
         |2 = (0,1)
         */
        if(!get(x - 1, y + 1) && (!get(x - 1, y) || !get(x, y + 1))) neighbors.add(new Coords(x - 1, y + 1));

        /*
         *  N  *
         *  C  *
         *  *  *
         */
        if(!get(x, y + 1)) neighbors.add(new Coords(x, y + 1));

        /*
         *  |  N
         *  C  |
         *  *  *

         |1 = (0,1)
         |2 = (1,0)
         */
        if(!get(x + 1, y + 1) && (!get(x, y + 1) || !get(x + 1, y))) neighbors.add(new Coords(x + 1, y + 1));

       return neighbors;
    }

    public float cost(Coords origin, Coords neighbor) {
        if (Math.abs(neighbor.getX() - origin.getX()) == 1 && Math.abs(neighbor.getY() - origin.getY()) == 1) {
            return 0.9F;
        }
        return 0.8F;
    }
}
