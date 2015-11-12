package ballsnwalls;

import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class CollisionSimulator implements Runnable  {

 Ball[] balls;
 ArrayList<Wall> walls;
 
 int radius;
 double maxSpeed;
 Color backgroundColor;
 boolean collisionsOn = true;
 
 int millisecondsBetweenFrames = 20;
 double initialKineticEnergy;
 double totalKineticEnergy;
 JPanel p;
 Thread animator;
 
 public CollisionSimulator( JPanel panel ) {
     this.p = panel;
 }
 
 public static int randDouble(double min, double max) {
     Random r = new Random();
     
     return (int) (min + r.nextDouble()*(max-min));
 }
 
 public void drawScreen() {
      Image img = createImage();
      Graphics g = p.getGraphics();
      g.drawImage(img, 0, 0, p);
}
 
private Image createImage() {
      BufferedImage bi = new BufferedImage(p.getWidth(), p.getHeight(), BufferedImage.TYPE_INT_RGB);

      Graphics2D G = (Graphics2D) bi.getGraphics();
      
      G.setColor( this.backgroundColor );
      G.fillRect(0, 0, p.getWidth(), p.getHeight());

      if (balls != null )
        for (int i = 0; i < balls.length; i++) 
           balls[i].draw(G); 
      
      if (walls != null )
        for (int i = 0; i < walls.size(); i++) 
           walls.get(i).draw(G); 
      
      return bi;
   }

 
 public static void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
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
               
        while( Thread.currentThread() == animator ) { //FOR EACH FRAME...
                        
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
        

