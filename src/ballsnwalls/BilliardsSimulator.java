package ballsnwalls;

import java.awt.BasicStroke;
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
    int xAim, yAim, xAimEnd, yAimEnd; 
    Vector velocityCueBall, lineOfSightVector;
    double initialKineticEnergy; //initial energy imparted to the cueball when the user shoots
    
    int x1, x2, x3, w, h, b; //used to calculate the dimensions of the table
    int pr; //pocket radius
    int pd; //pocket diameter
    int pl; //pocket length (used for the side pockets, which are drawn as ovals rather than perfect circles)
    
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
        
        reset();
    }
    
    public void reset() {
        b = 24; //the width of the brown table border
        pr = (int) (1.5*b);
        pd = 2*pr;
        pl = pr/2;
        h = p.getHeight();
        w = p.getWidth();
        
        x1 = w/2 - (h+2*b)/4; //left edge of the table
        x2 = x1 + b; //left edge of the playing surface
        x3 = w/2 + (h-2*b)/4; //right edge of the playing surface
        
        ballRadius = (int) (0.45*b);
        speedLossRate = 0.98;
        
        xCueBall = w/2; //the initial placement of the cueball
        yCueBall = h/3;
        xRack = w/2; //the initial placement of the rack
        yRack = 2*h/3;
        mode = PLACING_CUEBALL; //we start the game by placing the cueball before the breakshot
        
        makeBilliardBalls();
    }

    public void makeBilliardBalls() {

        balls = new Ball[16];
        
        //Creates the cue ball
        balls[0] = new Ball( xCueBall, yCueBall, ballRadius, 1, 30, Color.white, speedLossRate);
        
        initialKineticEnergy = balls[0].getKineticEnergy();
      
        //Creates the 15 target balls, setting their initial x,y position so that they form a triangular rack
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
        
        if ( mode == this.AIMING_CUEBALL) {
            
            Vector aimingVector = new Vector( xAim - balls[0].xPos, yAim - balls[0].yPos );
            velocityCueBall = aimingVector.scalarMultiply( 0.20 );
            balls[0].setVelocity( velocityCueBall.xComponent, velocityCueBall.yComponent );
            
            lineOfSightVector = aimingVector.scalarMultiply(10);
            xAimEnd = (int) (balls[0].xPos + lineOfSightVector.xComponent);
            yAimEnd = (int) (balls[0].yPos + lineOfSightVector.yComponent);
            
        }
        
        else
            System.out.println("ERROR! setCueBallSpeed called in wrong mode!");
    }
    
    //returns true when all balls have slowed to practically 0 speed, i.e. when
    //the total kinetic energy of all balls is less than .0001 times the initial KE of the cueball when it was struck
    public boolean allBallStoppedRolling() {
        
        if ( getTotalKineticEnergy() <= .0001 * initialKineticEnergy ) 
            return true;
        
        else
            return false;
    }

    //called in every repetition of the animation loop
    public void drawScreen() {
        Image img = createImage();
        Graphics g = p.getGraphics();
        g.drawImage(img, 0, 0, p);
    }

    //Draws the scene for the current frame of the animation
    private Image createImage() {
        BufferedImage bi = new BufferedImage(p.getWidth(), p.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D G = (Graphics2D) bi.getGraphics();
        
        //Draws the black background
        G.setColor(this.backgroundColor);
        G.fillRect(0, 0, p.getWidth(), p.getHeight());       
            
        //Draws the table border
        G.setColor( tableBorder );
        G.fillRect(x1, 0, (h+2*b)/2, h);
        
        //Draws the green playing surface
        G.setColor( tableSurface );
        G.fillRect( x2, b, (h-2*b)/2, h-2*b );
        
        //Draws the pockets
           //upper corner pockets
        G.setColor(Color.black);  
        G.fillArc(x2-pr, b-pr, pd, pd, 0, -90);
        G.fillArc(x3-pr, b-pr, pd, pd, 180, 90);
           //lower corner pockets
        G.fillArc(x2-pr, h-b-pr, pd,pd, 0, 90);
        G.fillArc(x3-pr, h-b-pr, pd,pd, 90, 90);
          //side pockets
        G.fillArc(x2-pl, h/2-pr, 2*pl, pd, -90, 180);
        G.fillArc(x3-pl, h/2-pr, 2*pl, pd, 90, 180);
        
        //Draws the walls
        if (walls != null) 
            for (int i = 0; i < walls.size(); i++) 
                walls.get(i).draw(G); 
        
        //Draws the balls
        if (balls != null) 
            for (int i = 0; i < balls.length; i++) 
                balls[i].draw(G);
        
        //Draws the blue aiming line and the red speed line
        if ( mode == AIMING_CUEBALL && form.mouseDown == true ) {
            G.setColor(Color.red);
            BasicStroke s = new BasicStroke(4);
            G.setStroke(s);
            G.drawLine((int)balls[0].xPos, (int)balls[0].yPos, xAim, yAim );
            
            G.setColor(Color.blue);
            BasicStroke s2 = new BasicStroke(1);           
            G.setStroke(s2);
            G.drawLine((int)balls[0].xPos, (int)balls[0].yPos, xAimEnd, yAimEnd );
        }
 
        return bi;
    }

    public void setWalls(JPanel p) { 
        walls = new ArrayList();

        walls.add(new Wall(x2+pr, b, x3-pr, b, 0, tableBorder, 3, "NORTH"));
        walls.add(new Wall(x2+pr, h-b, x3-pr, h-b, 0, tableBorder, 3, "SOUTH"));
        
        walls.add(new Wall(x2, b+pr, x2, h/2-pr, 0, tableBorder, 3, "NORTHWEST"));
        walls.add(new Wall(x2, h/2+pr, x2, h-b-pr, 0, tableBorder, 3, "SOUTHWEST"));
        
        walls.add(new Wall(x3, b+pr, x3, h/2-pr, 0, tableBorder, 3, "NORTHEAST "));
        walls.add(new Wall(x3, h/2+pr, x3, h-b-pr, 0, tableBorder, 3, "SOUTHEAST "));
    }

    public void run() {
               
        while( Thread.currentThread() == animator ) { //FOR EACH FRAME...
           
           if ( allBallStoppedRolling() )  {
               animator = null;
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
