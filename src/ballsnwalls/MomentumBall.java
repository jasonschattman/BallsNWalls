
package ballsnwalls;

import java.awt.Color;
import java.awt.Graphics2D;


public class MomentumBall extends Ball {
    
    boolean hasCollided = false;
    double xCollision, yCollision;
    double xStart, yStart;
    
    public MomentumBall(double x, double y, double r, double xSpeed, double ySpeed, Color col) {
        super( x, y, r, xSpeed, ySpeed, col, 1);
        this.xStart = x;
        this.yStart = y;
    }
    
    
    public void adjustVelocityAfterCollisionWith( Ball theOtherBall ) {
        
        
        super.adjustVelocityAfterCollisionWith(  theOtherBall );
        
        MomentumBall m = (MomentumBall) theOtherBall;
        
        this.hasCollided = true;
        m.hasCollided = true;
        
        this.xCollision = this.xPos;
        this.yCollision = this.yPos;
        m.xCollision = theOtherBall.xPos;
        m.yCollision = theOtherBall.yPos;
    }
    
    public void draw( Graphics2D g) {
        super.draw(g);
        
        g.setColor(Color.white); 
        
        if( this.hasCollided ) {
            g.drawLine((int)this.xStart, (int)this.yStart, (int)this.xCollision, (int)this.yCollision);
            g.drawLine((int)this.xCollision, (int)this.yCollision, (int)this.xPos, (int)this.yPos); 
        }
        
        else
            g.drawLine((int)this.xStart, (int)this.yStart, (int)this.xPos, (int)this.yPos); 
    }
    
}
