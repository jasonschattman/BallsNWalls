package ballsnwalls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class BilliardsSimulator extends CollisionSimulator {

    BallsNWallsForm form; 
    final int PLACING_CUEBALL = 1, AIMING_CUEBALL = 2, ANIMATING = 3;
    int mode; //one of the three above
    
    int xCueBall, yCueBall;
    int xRack, yRack; //the apex of the triangular rack at the start
    int xAim, yAim; 
    Vector velocityCueBall;
    double initialKineticEnergy;
    
    int x1, x2, x3, w, h, b; //used to calculate the dimensions of the table
    
    Color tableBorder = new Color( 87, 45, 9 ); //brown
    Color tableSurface = Color.green;
    
    double speedLossRate; //simulates friction;
    int ballRadius;    

    static Color[] colors = {Color.LIGHT_GRAY, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, 
                             Color.PINK, Color.RED, Color.YELLOW, Color.BLACK, new Color(0, 128, 200), 
                             Color.darkGray, new Color(200, 128, 0), Color.ORANGE, new Color(60, 128, 30), new Color(200, 10, 200)};

    public BilliardsSimulator( BallsNWallsForm bnwf ) {
   
        super( bnwf.drawingPanel );
        this.form = bnwf;
        this.backgroundColor = Color.black;
        
        b = 24;
        h = p.getHeight();
        w = p.getWidth();
        
        x1 = w/2 - (h+2*b)/4;
        x2 = x1 + b;
        x3 = w/2 + (h-2*b)/4;
        
        ballRadius = (int) (0.45*b);
        speedLossRate = 0.99;
        xCueBall = w/2;
        yCueBall = h/3;
        xRack = w/2;
        yRack = 2*h/3;
        mode = PLACING_CUEBALL;
    }

    public void makeBilliardBalls() {

        balls = new Ball[16];
        
        //The cue ball
        balls[0] = new Ball(xCueBall, yCueBall, ballRadius, 1, 30, Color.white, speedLossRate);
        initialKineticEnergy = balls[0].getKineticEnergy();
      
        //The triangular rack of 15
        double xStart = xRack;
        double y = yRack;
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
    
    public void setCueBallSpeed() {
        
        Vector aimingVector = new Vector( xAim - xCueBall, yAim - yCueBall );
        
        //Dragging an aim line of 100 pixels yields a shooting speed of 20 pixels per frame
        velocityCueBall = aimingVector.scalarMultiply( 0.20 );
    }
    
    public boolean allBallStoppedRolling() {
        if (getTotalKineticEnergy() <= .001 * initialKineticEnergy ) 
            return true;
        else
            return false;
    }

    public void drawScreen() {
        Image img = createImage();
        Graphics g = p.getGraphics();
        g.drawImage(img, 0, 0, p);
    }

    private Image createImage() {
        BufferedImage bi = new BufferedImage(p.getWidth(), p.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D G = (Graphics2D) bi.getGraphics();
        
        //Black background
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

    public void run() {
               
        while( Thread.currentThread() == animator ) { //FOR EACH FRAME...
           
           if ( allBallStoppedRolling() )  {
               animator = null;
               form.shootButton.setEnabled(true);
               mode = AIMING_CUEBALL;
           }
               
           for (int i = 0; i < balls.length; i++) { //FOR EACH Ball i...
                
                balls[i].checkForWallCollisions2( walls );
                              
                if ( collisionsOn ) {
                
                    for (int j = i+1; j < balls.length; j++)   //CHECK FOR COLLISIONS BETWEEN i AND EACH BALL FROM i+1 TO THE END
                        
                        if ( balls[i].hasCollidedWith( balls[j] ) ) {                            
                            balls[i].adjustVelocityAfterCollisionWith( balls[j] );                             
                            balls[i].checkForWallCollisions( walls );
                            balls[j].checkForWallCollisions( walls );                         
                        }
                }                 
               
               if( balls[i].justHitWall == false ) 
                    balls[i].updatePositionUsingVelocity();  //UPDATE THE POSITION OF THAT BALL USING ITS OWN VELOCITY VECTOR                              
           }
           
           for (int i = 0; i < walls.size(); i++ )  //MOVE THE WALLS IF THEY HAVE NON-ZERO SPEED
               walls.get(i).updatePositionUsingVelocity();
           
           drawScreen();
           sleep( millisecondsBetweenFrames ); 
        }
    }
}
