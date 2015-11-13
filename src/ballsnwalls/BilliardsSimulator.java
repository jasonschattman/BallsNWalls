package ballsnwalls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class BilliardsSimulator extends CollisionSimulator {

    static final int PLACING_CUEBALL = 1, AIMING_CUEBALL = 2, ANIMATING = 3;
    int status; //one of the three above
    
    int xCueBall, yCueBall;
    int x1, x2, x3, w, h, b;
    
    Color tableBorder = new Color(87,45,9); //brown
    Color tableSurface = Color.green;
    
    double speedLossRate = 0.99; //simulates friction;
    int ballRadius;
    

    static Color[] colors = {Color.LIGHT_GRAY, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED,
        Color.YELLOW, Color.BLACK, new Color(0, 128, 200), Color.darkGray, new Color(200, 128, 0),
        Color.ORANGE, new Color(60, 128, 30), new Color(200, 10, 200)};

    public BilliardsSimulator(JPanel panel) {
        super(panel);
        this.backgroundColor = Color.black;
        
        b = 24;
        h = p.getHeight();
        w = p.getWidth();
        x1 = w/2 - (h+2*b)/4;
        x2 = x1 + b;
        x3 = w/2 + (h-2*b)/4;
        ballRadius = (int) (0.45*b);
        status = PLACING_CUEBALL;
    }

    public void makeBilliardBalls(double xCueBall, double yCueBall, double xRackStart, double yRackStart, double cueBallxSpeed, double cueBallySpeed) {

        balls = new Ball[16];

        balls[0] = new Ball(xCueBall, yCueBall, ballRadius, cueBallxSpeed, cueBallySpeed, Color.white, speedLossRate);

        double xStart = xRackStart;
        double y = yRackStart;
        double deltaY = Math.sqrt(3) * ballRadius;
        double x;
        int ballCount = 1;

        for (int row = 1; row <= 5; row++) {

            x = xStart;

            for (int col = 1; col <= row; col++) {
                balls[ballCount] = new Ball(x, y, ballRadius, 0, 0, colors[ballCount - 1], speedLossRate);
                x = x + 2 * ballRadius;
                ballCount++;
            }

            y = y + deltaY;
            xStart = xStart - ballRadius;
        }
    }

    public void drawScreen() {
        Image img = createImage();
        Graphics g = p.getGraphics();
        g.drawImage(img, 0, 0, p);
    }

    private Image createImage() {
        BufferedImage bi = new BufferedImage(p.getWidth(), p.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D G = (Graphics2D) bi.getGraphics();

        G.setColor(this.backgroundColor);
        G.fillRect(0, 0, p.getWidth(), p.getHeight());       
            
        //Table border
        G.setColor( tableBorder );
        G.fillRect(x1, 0, (h+2*b)/2, h);
        
        //Playing surface
        G.setColor( tableSurface );
        G.fillRect( x2, b, (h-2*b)/2, h-2*b );
        
        //Pockets
           //upper corner pockets
        G.setColor(Color.black);     
        G.fillArc(x1, 0, 2*b,2*b, 0, -90);
        G.fillArc(x3-b, 0, 2*b,2*b, 180, 90);
           //lower corner pockets
        G.fillArc(x1, h-2*b, 2*b,2*b, 0, 90);
        G.fillArc(x3-b, h-2*b, 2*b,2*b, 90, 90);
          //side pockets
        G.fillArc(x1, h/2-b, 2*b, 2*b, -90, 180);
        G.fillArc(x3-b, h/2-b, 2*b, 2*b, 90, 180);
        
        //Walls
        if (walls != null) 
            for (int i = 0; i < walls.size(); i++) 
                walls.get(i).draw(G); 

         
        if (balls != null) 
            for (int i = 0; i < balls.length; i++) 
                balls[i].draw(G);
 
        return bi;
    }

    public void setWalls(JPanel p) { 
        walls = new ArrayList();

        walls.add(new Wall(x2+b, b, x3-b, b, 0, tableBorder, 3, "NORTH"));
        walls.add(new Wall(x2+b, h-b, x3-b, h-b, 0, tableBorder, 3, "SOUTH"));
        
        walls.add(new Wall(x2, 2*b, x2, h/2-b, 0, tableBorder, 3, "NORTHWEST"));
        walls.add(new Wall(x2, h/2+b, x2, h-2*b, 0, tableBorder, 3, "SOUTHWEST"));
        
        walls.add(new Wall(x3, 2*b, x3, h/2-b, 0, tableBorder, 3, "NORTHEAST "));
        walls.add(new Wall(x3, h/2+b, x3, h-2*b, 0, tableBorder, 3, "SOUTHEAST "));
    }

}
