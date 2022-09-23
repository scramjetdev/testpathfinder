package net.scramjet.pathfinder;

import net.scramjet.Coords;
import net.scramjet.Grid;

import java.util.*;

public class Pathfinder {
    private final Frontier frontier;

    public Pathfinder(Grid grid) {
        this.frontier = new Frontier(grid);
    }

    public List<Coords> getPath(Coords from, Coords to) {
        Map<Coords, Coords> cameFrom = frontier.generate(from);
        List<Coords> path = new ArrayList<>();
        Coords current = to;

        while (!current.equals(from)) {
            path.add(current);
            current = cameFrom.get(current);
            if (current == null) break;
        }
        //Collections.reverse(path);
        return path;
    }
}
