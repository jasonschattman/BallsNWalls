package ballsnwalls;

import java.awt.*;
import javax.swing.JFrame;
import java.util.Random;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JPanel;

public class BallsNWalls implements Runnable  { //extends JFrame implements MouseListener, MouseMotionListener {

 int winHeight=800, winWidth=800;
 Ball[] balls;
 VerticalWall[] vWalls;
 HorizontalWall[] hWalls;
 ArrayList<Wall> walls;
 int radius;
 double maxSpeed;
 Color backgroundColor = Color.black;
 BallsNWalls bnw;
 boolean collisionsOn = true;
 boolean mouseDown = false, lineBeingDrawn = false;
 int xMouse, yMouse, xWallStart, xWallEnd, yWallStart, yWallEnd;
 int millisecondsBetweenFrames = 20;
 double initialKineticEnergy;
 double totalKineticEnergy;
 JPanel p;
 Thread animator;
 
 public BallsNWalls( JPanel panel ) {
     this.p = panel;
 }
 
 public static int randDouble(double min, double max) {
     Random r = new Random();
     
     return (int) (min + r.nextDouble()*(max-min));
 }
 
 public void mouseMoved(MouseEvent e) {}
 
 public void mouseEntered( MouseEvent e) {}
 
 public void mouseExited( MouseEvent e) {}
 
 public void mouseClicked( MouseEvent e) {}
 
 public void drawScreen() {
      Image img = createImage();
      Graphics g = p.getGraphics();
      g.drawImage(img, 0, 0, p);
}

private Image createImage() {
      BufferedImage bufferedImage = new BufferedImage(winWidth, winHeight, BufferedImage.TYPE_INT_RGB);

      Graphics2D G = (Graphics2D) bufferedImage.getGraphics();
      
      G.setColor( this.backgroundColor );
      G.fillRect(0, 0, winWidth, winHeight);

      if (vWalls != null )
        for (int i = 0; i < vWalls.length; i++)
           vWalls[i].draw(G);
      
      if (hWalls != null )
        for (int i = 0; i < hWalls.length; i++) 
           hWalls[i].draw(G);  
      
      if (walls != null )
        for (int i = 0; i < walls.size(); i++) 
           walls.get(i).draw(G);   
          
      if (balls != null )
        for (int i = 0; i < balls.length; i++) 
           balls[i].draw(G); 
      
      if( lineBeingDrawn ) {
          G.setColor(Color.white);
          G.drawLine(xWallStart, yWallStart, xWallEnd, yWallEnd);
      }
          
      return bufferedImage;
   }

 
 public static void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
        }
}
 
 
 public void makeRandomDistribution(int numBalls, double radius, double maxSpeed ) {
     balls = new Ball[numBalls];
     
     for (int i = 0; i < balls.length; i++) {
         
         double randX = randDouble(200,600);
         double randY = randDouble(200,600);
         double xSpeed = randDouble(-maxSpeed, maxSpeed);
         double ySpeed = randDouble(-maxSpeed, maxSpeed);
         
         balls[i] = new Ball(randX, randY, radius, xSpeed, ySpeed, Color.blue, 1);       
     }
 }
 
 public void makeEpidemicSimulation(int numHealers, int numHealthy, int numCarriers, double maxSpeed, double radius ) {
      
     int numIndividuals = numHealers + numHealthy + numCarriers;
     
     balls = new Ball[ numIndividuals ];
     
     int personCount = 0;
     
     for (int i = 0; i < numHealers; i++) {
         
         double randX = randDouble(200,600);
         double randY = randDouble(200,600);;
         double xSpeed = randDouble(-maxSpeed, maxSpeed);
         double ySpeed = randDouble(-maxSpeed, maxSpeed);
          
         balls[personCount++] = new EpidemicRolePlayer(randX, randY, radius, xSpeed, ySpeed, Color.blue); 
     }
     
     for (int i = 0; i < numHealthy; i++) {
         
         double randX = randDouble(200,600);
         double randY = randDouble(200,600);;
         double xSpeed = randDouble(-maxSpeed, maxSpeed);
         double ySpeed = randDouble(-maxSpeed, maxSpeed);
          
         balls[personCount++] = new EpidemicRolePlayer(randX, randY, radius, xSpeed, ySpeed, Color.green); 
     }
     
     for (int i = 0; i < numCarriers; i++) {
         
         double randX = randDouble(200,600);
         double randY = randDouble(200,600);;
         double xSpeed = randDouble(-maxSpeed, maxSpeed);
         double ySpeed = randDouble(-maxSpeed, maxSpeed);
          
         balls[personCount++] = new EpidemicRolePlayer(randX, randY, radius, xSpeed, ySpeed, Color.red); 
      }
 }
 
 
 public void makeTwoSidedCharge(int numBalls, double radius, double speed ) {
     
     balls = new Ball[numBalls];
     
     Random rand = new Random();
     
     int w = p.getWidth(), h = p.getHeight();
     
     for (int i = 0; i < balls.length/2; i++) {
         double x = randDouble( 10, .2*w );
         double y = randDouble( .2*h, .8*h );
         balls[i] = new Ball(x, y, radius, speed, 0, Color.yellow, 1);
     } 
     
     for (int i = balls.length/2; i < balls.length; i++) {
         double x = randDouble( .8*w, w - 10 );
         double y = randDouble( .2*h, .8*h );
        
         balls[i] = new Ball(x, y, radius, -speed, 0, Color.red, 1);                   
   }
 }
 
 
 public void makeFourSidedCharge(int numBalls, double radius, double speed) {
         
     balls = new Ball[numBalls];
     
     Random rand = new Random();
     
     for (int i = 0; i < balls.length/4; i++) {
         double x = rand.nextDouble() * winWidth/5;
         double y = winHeight/4 + rand.nextDouble() * winHeight/2;
         balls[i] = new Ball(x, y, radius, speed, 0, Color.blue, 1);
     } 
     
     
     for (int i = balls.length/4; i < balls.length/2; i++) {
         double x = winWidth - rand.nextDouble() * winWidth/5;
         double y = winHeight/4 + rand.nextDouble() * winHeight/2;
         balls[i] = new Ball(x, y, radius, -speed, 0, Color.red, 1); 
                  
       }
     
     for (int i = balls.length/2; i < 3*balls.length/4; i++) {
         double x = winWidth/5 + rand.nextDouble() * winWidth/2;
         double y = rand.nextDouble() * winHeight/5;
         balls[i] = new Ball(x, y, radius, 0, speed, Color.yellow, 1 );
     } 
     
     
     for (int i = 3*balls.length/4; i < balls.length; i++) {
         double x = winWidth/5 + rand.nextDouble() * winWidth/2;
         double y = winHeight - rand.nextDouble() * winHeight/5;
         balls[i] = new Ball(x, y, radius, 0, -speed, Color.green, 1); 
                  
   }
 }
 
 
 public void makePinball(int numBalls, double radius, double initSpeed, double speedLossRate) {
     
     balls = new Ball[numBalls];
     
     balls[0] = new Ball(100,100, radius, initSpeed/2, initSpeed/2, Color.yellow, speedLossRate );
     
     Random rand = new Random();
     
     for (int i = 1; i < balls.length; i++) {
        
         double randX = randDouble(400,600);
         double randY = randDouble(400,600);      
         
         balls[i] = new Ball(randX, randY, radius, 0, 0, Color.blue, speedLossRate );          
     }
 }
 
 
 public void makeBilliardsRack(double xCueBall, double yCueBall, double xRackStart, double yRackStart, double cueBallxSpeed, double cueBallySpeed, double speedLossRate ) {
     
     this.radius = 12;

     balls = new Ball[16];
     
     balls[0] = new Ball(xCueBall,yCueBall, radius, cueBallxSpeed, cueBallySpeed, Color.white, speedLossRate );
     
     Color[] colors = {Color.cyan, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED,
                        Color.YELLOW, Color.BLACK, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.BLUE};
     
     double xStart = xRackStart;
     double y = yRackStart;
     double deltaY = Math.sqrt(3)*radius;
     double x;
     int ballCount = 1;
     
     for (int row = 1; row <= 5; row++) {
         
         x = xStart;
        
         for( int col = 1; col <= row; col++) {
             balls[ballCount] = new Ball(x, y, radius, 0, 0, colors[ballCount-1], speedLossRate);
             x = x + 2*radius;
             ballCount++;
         }
       
         y = y + deltaY;
         xStart = xStart - radius;               
     }
  }
 
 public void conservationOfMomentumTester(double radius, double xSpeed1, double xSpeed2, double verticalOffsetRatio) {
     balls = new Ball[2];
     
     double yStationary = 400;
     double yMoving = yStationary + verticalOffsetRatio * 2 * radius;
     
     balls[0] = new MomentumBall( 100, yMoving,     radius, xSpeed1, 0, Color.red );     
     balls[1] = new MomentumBall( 500, yStationary, radius, xSpeed2, 0, Color.blue );

 }
 
 public double getTotalKineticEnergy() {
     double KE = 0;
     
     for (int i = 0; i < balls.length; i++) 
         KE += Math.pow( balls[i].velocity.magnitude, 2);
     
     return KE;
 }
 
 public void adjustKineticEnergies() {
     this.totalKineticEnergy = getTotalKineticEnergy();
     
     double energyLossRatio = this.initialKineticEnergy/this.totalKineticEnergy;
     
     if (energyLossRatio <= 0.95) {
         double correctionMultiplier = Math.sqrt(energyLossRatio);
         
         for (int i = 0; i < balls.length; i++) 
             balls[i].velocity.scalarMultiply(correctionMultiplier);         
     }   
 }
 
 
 public void setWalls( JPanel p ) {
        walls = new ArrayList();
        
        int w = p.getWidth(), h = p.getHeight();
        
        walls.add (new Wall( 0, 0, w, 0, 0, Color.yellow, 3, "NORTH"));
        walls.add (new Wall( 0, h, w, h, 0, Color.yellow, 3, "SOUTH"));
        walls.add (new Wall( w, 0, w, h, 0, Color.yellow, 3, "EAST "));
        walls.add (new Wall( 0, 0, 0, h, 0, Color.yellow, 3, "WEST"));        
}
 
 
 public void run() {
               
        while( Thread.currentThread() == animator ) {
                        
           for (int i = 0; i < balls.length; i++) { //FOR EACH Ball i...
                
                balls[i].checkForWallCollisions2( walls );
                              
                if ( collisionsOn) {
                
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
        

