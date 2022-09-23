package net.scramjet.pathfinder;

import net.scramjet.Coords;
import net.scramjet.Grid;

import java.util.*;

public class Frontier {
    private final Grid grid;

    private final PriorityQueue<Node> frontier = new PriorityQueue<>();
    private final Map<Coords, Coords> cameFrom = new HashMap<>();
    private final Map<Coords, Float> costSoFar = new HashMap<>();

    public Frontier(Grid grid) {
        this.grid = grid;
    }


    public Map<Coords, Coords> generate(Coords startPoint, Coords endPoint) {
        frontier.clear();
        cameFrom.clear();
        costSoFar.clear();
        //
        frontier.add(new Node(startPoint, 0.0F));
        cameFrom.put(startPoint, null);
        costSoFar.put(startPoint, 0F);
        //
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if(current.getCoords() == endPoint) break;
            for (Coords next : grid.getNeighbors(current.getCoords())) {
                float newCost = costSoFar.get(current.getCoords()) + grid.cost(current.getCoords(), next);
                if (!costSoFar.containsKey(next) || newCost < costSoFar.get(next)) {
                    float priority = newCost + heuristic(endPoint, next);
                    frontier.add(new Node(next, priority));
                    cameFrom.put(next, current.getCoords());
                    costSoFar.put(next, newCost);
                }
            }
        }

        return cameFrom;
    }

    private float heuristic (Coords a, Coords b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getX());
    }
}
