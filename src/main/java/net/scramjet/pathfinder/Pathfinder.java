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
        /*
        current = goal path>


path = []
while current != start:
   path.append(current)
   current = came_from[current]
path.append(start) # optional
path.reverse() # optional
         */
        Map<Coords, Coords> cameFrom = frontier.generate(from);
        System.out.println(cameFrom);
        List<Coords> path = new ArrayList<>();
        Coords current = to;
        System.out.println(current);

        while (!current.equals(from)) {
            path.add(current);
            current = cameFrom.get(current);
            if (current == null) break;
        }
        Collections.reverse(path);
        return path;
    }
}
