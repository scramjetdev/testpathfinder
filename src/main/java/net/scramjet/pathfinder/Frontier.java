package net.scramjet.pathfinder;

import net.scramjet.Coords;
import net.scramjet.Grid;

import java.util.*;
/*
frontier.put(start )
reached = set()
reached.add(start)

while not frontier.empty():
   current = frontier.get()
   for next in graph.neighbors(current):
      if next not in reached:
         frontier.put(next)
         reached.add(next)
 */

public class Frontier {
    private final Grid grid;

    private final List<Coords> frontier = new ArrayList<>();
    private final Map<Coords, Coords> cameFrom = new HashMap<>();

    public Frontier(Grid grid) {
        this.grid = grid;
    }


    public Map<Coords, Coords> generate(Coords startPoint) {
        frontier.clear();
        cameFrom.clear();
        frontier.add(startPoint);
        cameFrom.put(startPoint, null);
        /*
        while not frontier.empty():
           current = frontier.get()
   for next in graph.neighbors(current):
      if next not in came_from:
         frontier.put(next)
         came_from[next] = current
         */
        while (!frontier.isEmpty()) {
            Coords current = frontier.remove(0);
            for (Coords next : grid.getNeighbors(current)) {
                System.out.println(next);
                if (!cameFrom.containsKey(next)) {
                    frontier.add(next);
                    cameFrom.put(next, current);
                }
            }
        }

        return cameFrom;
    }
}
