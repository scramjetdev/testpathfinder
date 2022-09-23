package net.scramjet.renderer;

import net.scramjet.Coords;
import net.scramjet.Grid;
import net.scramjet.pathfinder.Pathfinder;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Renderer extends JFrame {
    private final Grid grid;
    private final Pathfinder pathfinder;
    private Coords startPoint = new Coords(8, 8);
    private Coords endPoint = new Coords(10, 13);
    private List<Coords> path;

    public Renderer(Grid grid, Pathfinder pathfinder) {
        super("A*→algo");
        this.grid = grid;
        this.pathfinder = pathfinder;
        //
        setSize(720,720);
        setVisible(true);
        //
        new Timer(50, event -> {
            repaint();
        }).start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                int windowX = getWidth();
                int windowY = getHeight();

                int gridXSize = windowX / grid.getGridSize();
                int gridYSize = windowY / grid.getGridSize();

                int gridX = mouseX / gridXSize;
                int gridY = mouseY / gridYSize;
                System.out.println(gridX + " " + gridY);

                if(e.getButton() == MouseEvent.BUTTON1) {
                    grid.set(gridX, gridY, !grid.get(gridX, gridY));
                } else if(e.getButton() == MouseEvent.BUTTON3) {
                    if(e.isAltDown()) {
                        startPoint = new Coords(gridX, gridY);
                    } else {
                        endPoint = new Coords(gridX, gridY);
                    }
                }

            }
        });
    }

    @Override
    public void paint(Graphics graphics) {
        path = pathfinder.getPath(startPoint, endPoint);
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(new Color(0, 0, 0));
        int windowX = getWidth();
        int windowY = getHeight();

        int gridXSize = windowX / grid.getGridSize();
        int gridYSize = windowY / grid.getGridSize();

        for (int x = 0; x < windowX; x += gridXSize) {
            graphics.drawLine(x, 0, x, getHeight());
        }

        for (int y = 0; y < windowY; y += gridYSize) {
            graphics.drawLine(0, y, getWidth(), y);
        }

        for (int x = 0; x < grid.getGridSize(); x++) {
            for (int y = 0; y < grid.getGridSize(); y++) {
                boolean value = grid.get(x, y);

                if(value) {
                    graphics.setColor(new Color(0, 0, 0));
                } else {
                    graphics.setColor(new Color(255, 255, 255));
                }

                graphics.fillRect(x * gridXSize + 1, y * gridYSize + 1, gridXSize - 1, gridYSize - 1);
            }

        }

        graphics.setColor(new Color(0, 255, 0));
        graphics.fillRect(startPoint.getX() * gridXSize + 1, startPoint.getY() * gridYSize + 1, gridXSize - 1, gridYSize -1);

        graphics.setColor(new Color(255, 0, 0));
        graphics.fillRect(endPoint.getX() * gridXSize + 1, endPoint.getY() * gridYSize + 1, gridXSize - 1, gridYSize -1);

        graphics.setColor(new Color(255, 115, 0));

        if(path == null || path.isEmpty()) return;
        path.remove(0);
        for (Coords coords : path) {
            graphics.fillRect(coords.getX() * gridXSize + 1, coords.getY() * gridYSize + 1, gridXSize - 1, gridYSize - 1);
        }
    }
}