package ballsnwalls;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

public class BilliardsSimulator extends CollisionSimulator {
    
    int xCueBall, yCueBall;
    
    static Color[] colors = {Color.LIGHT_GRAY, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED,
        Color.YELLOW, Color.BLACK, new Color(0, 128, 200), Color.darkGray, new Color(200, 128, 0),
        Color.ORANGE, new Color(60, 128, 30), new Color(200, 10, 200)};

    public BilliardsSimulator(JPanel panel) {
        super( panel );
        this.backgroundColor = Color.green;
    }

    public void makeBilliardBalls(double xCueBall, double yCueBall, double xRackStart, double yRackStart, double cueBallxSpeed, double cueBallySpeed, double speedLossRate) {

        this.radius = 12;

        balls = new Ball[16];

        balls[0] = new Ball(xCueBall, yCueBall, radius, cueBallxSpeed, cueBallySpeed, Color.white, speedLossRate);

        double xStart = xRackStart;
        double y = yRackStart;
        double deltaY = Math.sqrt(3) * radius;
        double x;
        int ballCount = 1;

        for (int row = 1; row <= 5; row++) {

            x = xStart;

            for (int col = 1; col <= row; col++) {
                balls[ballCount] = new Ball(x, y, radius, 0, 0, colors[ballCount - 1], speedLossRate);
                x = x + 2 * radius;
                ballCount++;
            }

            y = y + deltaY;
            xStart = xStart - radius;
        }
    }

    public void setWalls(JPanel p) {
        walls = new ArrayList();
        
        int h = p.getHeight(), w = p.getWidth();
        int tableWidth = h/2;
        int leftOverHorizontalDistance = w - tableWidth;
        int x1 = leftOverHorizontalDistance/2, x2 = x1 + tableWidth;

        walls.add(new Wall( 0, 0, w, 0, 0, Color.white, 3, "NORTH"));
        walls.add(new Wall( 0, h, w, h, 0, Color.white, 3, "SOUTH"));
        walls.add(new Wall( x1, 0, x1, h, 0, Color.white, 3, "WEST"));
        walls.add(new Wall( x2, 0, x2, h, 0, Color.white, 3, "EAST "));
    }

}
