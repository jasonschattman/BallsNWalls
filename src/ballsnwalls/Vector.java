
package ballsnwalls;

import java.awt.Graphics2D;

public class Vector {
    double xComponent;
    double yComponent;
    double magnitude;
    double angle;

    public Vector(double xc, double yc) {
        setComponents( xc, yc );   
    }
    
    public void setComponents( double xc, double yc ) {
        this.xComponent = xc;
        this.yComponent = yc;
        this.magnitude = Math.hypot(xc, yc);
        this.angle = Math.atan2( this.yComponent, this.xComponent);
    }
    
    public void setAngle( double theta ) {
        this.angle = theta;
        this.xComponent = this.magnitude * Math.cos(theta);
        this.yComponent = this.magnitude * Math.sin(theta);
    }
    
    public void addTo( Vector v2 ) {
        setComponents( this.xComponent + v2.xComponent, this.yComponent + v2.yComponent);
    }

    
    public Vector add( Vector v2 ) {
        return new Vector( this.xComponent + v2.xComponent, this.yComponent + v2.yComponent);
    }
    
    public Vector subtract( Vector v2 ) {
        return new Vector( this.xComponent - v2.xComponent, this.yComponent - v2.yComponent);
    }
    
    public Vector scalarMultiply( double s ) {
        return new Vector( this.xComponent * s, this.yComponent * s);        
    }
    
    public Vector getUnitVector() {
        return this.scalarMultiply( 1/ this.magnitude );
    }
    
    public double dotProduct( Vector v2 ) {
        return this.xComponent * v2.xComponent + this.yComponent * v2.yComponent;
    }
    
    public Vector projOntoWall( Wall w ) {
        double magnitudeOfProjection = this.dotProduct(w.lineVector) / w.magnitudeSquared;
        return w.lineVector.scalarMultiply(magnitudeOfProjection);
    }
    
    public void draw( Ball b, Graphics2D g ) {
        g.drawLine((int)b.xPos, (int)b.yPos, (int)this.xComponent, (int)this.yComponent);
    }
    
    
}
