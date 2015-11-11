
package ballsnwalls;

import java.awt.*;

public class Wall {
    double xStart, yStart, xEnd, yEnd;
    double A, B, C;
    Vector normal, unitNormal;
    Vector velocity;
    Vector lineVector;
    double length;
    double magnitudeOfNormal;
    double magnitudeSquared;
    String name;
  
    int thickness;
    String orientation;
    double angleOfNormal;
    BasicStroke stroke;
    Color color;
    
    public Wall( double xstart, double ystart, double xend, double yend, double speed, Color c, int linethickness, String n) {
        this.setPosition( xstart, ystart, xend, yend );
        
        if (A == 0) {
            this.angleOfNormal = Math.PI/2;
            this.orientation = "horizontal";
        }
        
        else {
            this.angleOfNormal = Math.atan2(B, A);
            
            if (B == 0) {
                this.orientation = "vertical";
            } 
        
            else {
                this.orientation = "diagonal";
            }            
        }
                        
        this.magnitudeSquared = A*A + B*B;
        this.magnitudeOfNormal = Math.sqrt(this.magnitudeSquared);
        this.normal = new Vector( A, B );
        this.unitNormal = this.normal.scalarMultiply( 1 / this.magnitudeOfNormal );
        this.velocity = this.unitNormal.scalarMultiply( speed );
        this.lineVector = new Vector(B, -A);
        this.color = c; 
        this.stroke = new BasicStroke( linethickness );
        this.name = n;
    }
    
    
    public void setPosition(double xstart, double ystart, double xend, double yend) {
        this.xStart = xstart;
        this.yStart = ystart;
        this.xEnd = xend;
        this.yEnd = yend;
        
        if( xStart == xEnd ) {
            this.A = 1;
            this.B = 0;
            this.C = -xStart;
        }
        
        else {
            double slope = (yStart - yend) / (xStart - xEnd);
            this.A = -slope;
            this.B = 1;
            this.C = -yStart + slope * xStart;            
        }
        
        this.length = Math.hypot(xEnd-xStart, yEnd-yStart);
    }
    
    
    public void updatePositionUsingVelocity() {
        double x2 = this.xStart + this.velocity.xComponent;
        double y2 = this.yStart + this.velocity.yComponent;
        double x3 = this.xEnd + this.velocity.xComponent;
        double y3 = this.yEnd + this.velocity.yComponent;
        this.setPosition(x2,y2,x3,y3);
    }
    
    
    public double lineFunction(double x0, double y0) { //Returns the value of Ax0 + Bx0 + C
        return this.A * x0 + this.B * y0 + this.C;
    }
    
    
 
    public void draw( Graphics2D g) {
        g.setColor( this.color );
        g.setStroke( this.stroke ); //SETS THE THICKNESS OF THE LINE TO BE DRAWN
        g.drawLine( (int) this.xStart, (int) this.yStart, (int) this.xEnd, (int) this.yEnd );
    }  
}
