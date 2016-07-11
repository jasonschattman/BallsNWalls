
package ballsnwalls;

import static ballsnwalls.CollisionSimulator.randDouble;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;


public class WallDrawingCollisionSimulator extends CollisionSimulator {
    
    boolean lineBeingDrawn;
    int xWallStart, xWallEnd, yWallStart, yWallEnd;
    
    
    public WallDrawingCollisionSimulator(JPanel panel) {
        super( panel );
        lineBeingDrawn = false;
        this.backgroundColor = Color.black;
    }
    
     
 public void makeRandomDistribution(int numBalls, double radius, double maxSpeed ) {
     balls = new Ball[numBalls];
     
     double max = Math.min(maxSpeed, 20);
     double r = Math.min(radius, p.getWidth()/(2*numBalls));
     Color c;
      
     for (int i = 0; i < balls.length; i++) {
         
         double randX = randDouble(200,600);
         double randY = randDouble(200,600);
         double xSpeed = randDouble(-max, max);
         double ySpeed = randDouble(-max, max);
         
         if (i==0)
             c = Color.red;
         else
             c = Color.blue;
         balls[i] = new Ball(randX, randY, radius, xSpeed, ySpeed, c, 1);       
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
 
 public void makeTwoSidedCharge(int numBalls, int radius, double speed ) {
     
     balls = new Ball[numBalls];
     
     Random rand = new Random();
     
     int w = p.getWidth(), h = p.getHeight();
     
     for (int i = 0; i < balls.length/2; i++) {
         double x = randDouble( 10, .2*w );
         double y = randDouble( .15*h, .7*h );
         balls[i] = new Ball(x, y, radius, speed, 0, Color.yellow, 1);
     } 
     
     for (int i = balls.length/2; i < balls.length; i++) {
         double x = randDouble( .8*w, w - 10 );
         double y = randDouble( .2*h, .75*h );
        
         balls[i] = new Ball(x, y, radius, -speed, 0, Color.red, 1);                   
   }
 }

 public void makeFourSidedCharge(int numBalls, double radius, double speed) {
         
     balls = new Ball[numBalls];
     
     Random rand = new Random();
     
     for (int i = 0; i < balls.length/4; i++) {
         double x = rand.nextDouble() * p.getWidth()/5;
         double y = p.getHeight()/4 + rand.nextDouble() * p.getHeight()/2;
         balls[i] = new Ball(x, y, radius, speed, 0, Color.blue, 1);
     } 
     
     
     for (int i = balls.length/4; i < balls.length/2; i++) {
         double x = p.getWidth() - rand.nextDouble() * p.getWidth()/5;
         double y = p.getHeight()/4 + rand.nextDouble() * p.getHeight()/2;
         balls[i] = new Ball(x, y, radius, -speed, 0, Color.red, 1); 
                  
       }
     
     for (int i = balls.length/2; i < 3*balls.length/4; i++) {
         double x = p.getWidth()/5 + rand.nextDouble() * p.getWidth()/2;
         double y = rand.nextDouble() * p.getHeight()/5;
         balls[i] = new Ball(x, y, radius, 0, speed, Color.yellow, 1 );
     } 
     
     
     for (int i = 3*balls.length/4; i < balls.length; i++) {
         double x = p.getWidth()/5 + rand.nextDouble() * p.getWidth()/2;
         double y = p.getHeight() - rand.nextDouble() * p.getHeight()/5;
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
          
      if( lineBeingDrawn ) {
          G.setColor(Color.yellow);
          G.drawLine(xWallStart, yWallStart, xWallEnd, yWallEnd);
      }
          
      return bi;
   }
}
