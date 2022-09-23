package net.scramjet.pathfinder;

import net.scramjet.Coords;

public class Node implements Comparable<Node>{
    private final Coords coords;
    private final float cost;

    public Node(Coords coords, float cost) {
        this.coords = coords;
        this.cost = cost;
    }

    public Coords getCoords() {
        return coords;
    }

    public float getCost() {
        return cost;
    }

    @Override
    public int compareTo(Node node) {
        return Float.compare(cost, node.cost);
    }
}
