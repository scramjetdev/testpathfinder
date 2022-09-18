package net.scramjet;

import net.scramjet.pathfinder.Pathfinder;
import net.scramjet.renderer.Renderer;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(16);
        new Renderer(grid, new Pathfinder(grid));
    }
}
