package ballsnwalls;

import java.awt.*;
import java.util.ArrayList;

public class Ball {
    double xPos, yPos;
    Vector velocity;  //measured in pixels per frame
    double radius;
    double diameter;
    double speedLossRate;
    Color color;
    
    double timeToImpact;
    Wall nearestWall;
    double xContactPoint, yContactPoint; //the coordinates of the point of contact when the ball strikes a wall
    double xPosAtLanding, yPosAtLanding; //the centre of the ball when it strikes a wall
    boolean justHitWall;
    boolean willHitCorner;
    int numNearWalls = 0;

    //CONSTRUCTOR FOR A BALL OBJECT
    public Ball(double x, double y, double r, double xSpeed, double ySpeed, Color c, double slr) {
        this.radius = r;
        this.diameter = 2 * r;
        this.color = c;
        this.velocity = new Vector(xSpeed, ySpeed);
        this.speedLossRate = slr;
        this.timeToImpact = Double.POSITIVE_INFINITY;
        this.justHitWall = false;
        this.willHitCorner = false;
        this.nearestWall = null;
        this.xContactPoint = Double.POSITIVE_INFINITY;
        this.yContactPoint = Double.POSITIVE_INFINITY;

        this.setPosition(x, y);        
    }
    
    public String describe( ArrayList<Wall> wallList ) {
       return "Near walls: " + numNearWalls + ", Just hit wall: " + this.justHitWall; 
    }
    
    public boolean hasEscaped() {
        if (xPos > 700 || xPos < 100 || yPos > 700 || yPos < 100 )
            return true;
        else
            return false;
    }

    //USED FOR PLACING THE BALL AT AN ARBITRARY LOCATION
    public void setPosition(double x, double y) {
        this.xPos = x;
        this.yPos = y;
    }

    public void updatePositionUsingVelocity() {
        double newX = this.xPos + this.velocity.xComponent;
        double newY = this.yPos + this.velocity.yComponent;
        this.setPosition(newX, newY);
        reduceSpeedDueToFriction();
    }

    public void reduceSpeedDueToFriction() {
        double newXspeed = this.speedLossRate * this.velocity.xComponent;
        double newYspeed = this.speedLossRate * this.velocity.yComponent;
        this.setVelocity(newXspeed, newYspeed);
    }

    //RETURNS THE DISTANCE BETWEEN TWO POINTS
    public static double getDistance(double x1, double y1, double x2, double y2) {
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;
        return Math.hypot(deltaX, deltaY);
    }

    public boolean hasCollidedWith(Ball theOtherBall) {
        double dist = getDistance(this.xPos, this.yPos, theOtherBall.xPos, theOtherBall.yPos);

        if (dist <= this.radius + theOtherBall.radius) 
            return true;
         
        else 
            return false;        
    }

    public void adjustVelocityAfterCollisionWith(Ball theOtherBall) {
        double deltaX = this.xPos - theOtherBall.xPos;
        double deltaY = this.yPos - theOtherBall.yPos;

        if (deltaX == 0) {
            deltaX = -0.001;
        }

        double tan = deltaY / deltaX;
        double tanSq = tan * tan;
        double secSq = 1 + tanSq;

        double Vax = this.velocity.xComponent;
        double Vay = this.velocity.yComponent;
        double Vbx = theOtherBall.velocity.xComponent;
        double Vby = theOtherBall.velocity.yComponent;

        double Uax = (-Vay * tan + tanSq * Vax + Vbx + tan * Vby) / secSq;
        double Uay = -(-tan * Vbx - tanSq * Vby + tan * Vax - Vay) / secSq;
        double Ubx = (Vax + Vbx * tanSq + Vay * tan - tan * Vby) / secSq;
        double Uby = (Vby + Vay * tanSq - tan * Vbx + tan * Vax) / secSq;

        if (this.justHitWall == false) {
            this.setVelocity(Uax, Uay);
        }

        if (theOtherBall.justHitWall == false) {
            theOtherBall.setVelocity(Ubx, Uby);
        }

        realignToSurface(theOtherBall, tan, tanSq);
    }

    public void realignToSurface(Ball theOtherBall, double tan, double tanSq) {
        double xAdjust = 2 * this.radius / Math.sqrt(1 + tanSq);
        double newXb, newYb;

        if (this.xPos > theOtherBall.xPos) {
            newXb = -xAdjust + this.xPos;
        } else {
            newXb = xAdjust + this.xPos;
        }

        newYb = tan * (newXb - this.xPos) + this.yPos;
        theOtherBall.setPosition(newXb, newYb);
    }

    public double getTimeToWallCollision(Wall w) {
        double A = w.A, B = w.B, C = w.C;
        
        //THE RELATIVE VELOCITY BETWEEN THE BALL AND THE WALL
        double Vx = this.velocity.xComponent - w.velocity.xComponent;
        double Vy = this.velocity.yComponent - w.velocity.yComponent;
        
        double vDotN = Vx * A + Vy * B;
        double lineFunction = w.lineFunction(xPos, yPos);

        if (lineFunction == 0) //IF THE CENTRE OF THE BALL IS EXACTLY ON THE WALL
            return 0;

        else if (Math.signum(vDotN) * Math.signum(lineFunction) == -1) { //IF THE BALL IS MOVING TOWARDS THE WALL
            return (Math.abs(lineFunction) - radius * w.magnitudeOfNormal) / Math.abs(vDotN);
        }
         
        else 
            return Double.POSITIVE_INFINITY; 
    }

    public void setFirstWallEncountered( ArrayList<Wall> walls ) {
        double tMin = Double.POSITIVE_INFINITY;
        double t;
        short numWallsWithinStrikingDistance = 0;

        Wall firstWallEncountered = null;

        for (int i = 0; i < walls.size(); i++) {
            Wall w = walls.get(i);
            t = getTimeToWallCollision(w);

            if (t <= 2) {
                this.setContactPointOnWall(w, t);
                if (this.isWithinWallBounds(w)) {
                    numWallsWithinStrikingDistance++; 
                }
            }

            if (numWallsWithinStrikingDistance >= 2) {
                //System.out.println("WILL HIT CORNER");
                this.willHitCorner = true;
            }
            
            if (t < tMin) {
                tMin = t;
                this.timeToImpact = tMin;
                firstWallEncountered = w;
            }
        }

        this.nearestWall = firstWallEncountered;
    }

    public void setContactPointOnWall( Wall w, double t ) {
 
        //the coordinates of the centre of the ball when the ball is tangent to the nearest wall
        this.xPosAtLanding = this.xPos + this.velocity.xComponent * t;
        this.yPosAtLanding = this.yPos + this.velocity.yComponent * t;
        
        if ( w.lineFunction(this.xPosAtLanding, this.yPosAtLanding) > 0 ) {
            this.xContactPoint = this.xPosAtLanding - radius * w.unitNormal.xComponent;
            this.yContactPoint = this.yPosAtLanding - radius * w.unitNormal.yComponent;
        }
        
        else {
            this.xContactPoint = this.xPosAtLanding + radius * w.unitNormal.xComponent;
            this.yContactPoint = this.yPosAtLanding + radius * w.unitNormal.yComponent;
        }
    }
    
    public void checkForWallCollisions2(ArrayList<Wall> walls) {
        if ( this.justHitWall ) {
            this.updatePositionUsingVelocity();
        }

        ArrayList<Wall> nearWalls = new ArrayList<Wall>();
        Wall w;
        double timeToWall;
        
        for (int i = 0; i < walls.size(); i++) {
            
            w = walls.get(i);
            timeToWall = this.getTimeToWallCollision( w );
            
            if ( timeToWall <= 1 ) {
                this.setContactPointOnWall( w, timeToWall );
                
                if ( this.isWithinWallBounds(w) ) 
                    nearWalls.add( w );
                
                else if (this.isWithinOneRadiusOfWallEndpoint(w)) {
                    this.reverseDirection();
                    this.justHitWall = true;
                }
            } 
        }
        
        this.numNearWalls = nearWalls.size();
        
        if ( numNearWalls > 1 ) {
            this.setPosition( this.xPosAtLanding, this.yPosAtLanding );
            this.reverseDirection();
            this.justHitWall = true;
        }
        
        else if (numNearWalls == 1) {
            this.setPosition( this.xPosAtLanding, this.yPosAtLanding );
            w = nearWalls.get(0);
            this.reflectOffWall( w );
            this.justHitWall = true;
        }
        
        else {
            this.justHitWall = false;
        }
    }
    
    
    public void checkForWallCollisions(ArrayList<Wall> walls) {

        if (this.justHitWall) {
            this.updatePositionUsingVelocity();
        }

        setFirstWallEncountered( walls );

        Wall w = this.nearestWall;

        if (w != null) {

            double t = this.timeToImpact;

            if (t <= 1) { // IF THE BALL WILL CONTACT WALL w IN SOME WAY DURING THIS FRAME
                
                this.setContactPointOnWall( w, t );
                
                if ( this.isWithinWallBounds( w ) ) { //IF THE BALL WILL ACTUALLY HIT THE WALL
                    this.setPosition( this.xPosAtLanding, this.yPosAtLanding );

                    if ( this.willHitCorner ) {
                        this.reverseDirection();
                        this.willHitCorner = false;
                    } 
                    
                    else 
                        this.reflectOffWall(w);  
                    
                    this.justHitWall = true;
                }

                else if ( this.isWithinOneRadiusOfWallEndpoint( w ) ) {  //IF THE BALL WILL HIT ONE OF THE ENDPOINTS OF THE WALL
                    this.reverseDirection(); 
                    this.justHitWall = true;
                }
                
                
                else { //THE BALL IS PASSING THROUGH A GAP IN THE WALL
                    if ( this.willHitCorner ) {
                        this.reverseDirection();
                        this.willHitCorner = false;
                    } 
                    this.justHitWall = false;
                }
            }
            
            else { //TIME TO IMPACT IS STILL MORE THAN 1 FRAME AWAY, SO DON'T INTERACT WITH THE WALL YET
                this.justHitWall = false;
            }
        }

    }

    
    public void reflectOffWall(Wall w) {

        //IF THE BALL IS MOVING TOWARDS THE WALL
        if (this.velocity.dotProduct(w.velocity) <= 0 && this.velocity.magnitude > 0) {
            Vector vDiff = this.velocity.subtract(w.velocity);
            double thetaIn = vDiff.angle;
            double beta = w.angleOfNormal;
            double thetaOut = 2 * beta - thetaIn + Math.PI;
            this.velocity.setAngle(thetaOut);
        } 
        
        else { //IF THE BALL IS MOVING AWAY FROM THE WALL BUT THE WALL IS CHASING IT AND WILL EVENTUALLY CATCH UP TO IT
            Vector velComponentParallelToWall = this.velocity.projOntoWall(w);
            Vector magnifiedWallVelocity = (Vector) w.velocity.scalarMultiply(1.5);
            Vector vFinal = velComponentParallelToWall.add(magnifiedWallVelocity);
            this.setVelocity(vFinal.xComponent, vFinal.yComponent);
        }
    }
    
    
    //RETURNS TRUE IF THE BALL'S CONTACT POINT IS BETWEEN THE TWO ENDPOINTS OF WALL w
    public boolean isWithinWallBounds( Wall w ) { 
        double xContact = this.xContactPoint;
        double yContact = this.yContactPoint;
        
        if (w.xStart < w.xEnd ) { //FOR DIAGONAL WALLS
            if ( xContact >= w.xStart && xContact <= w.xEnd)
                return true;
            else
                return false;
        }
        
        else if (w.xStart > w.xEnd ) {//FOR DIAGONAL WALLS
            if ( xContact >= w.xEnd && xContact <= w.xStart)
                return true;
            else
                return false;
        }
        
        else { //FOR VERTICAL WALLS
            if (w.yStart < w.yEnd ) {
                if ( yContact >= w.yStart && yContact <= w.yEnd)
                    return true;
                else
                    return false;
            }
            
            else {
                if ( yContact >= w.yEnd && yContact <= w.yStart)
                    return true;
                else
                    return false;
            }
        }
    }
    
    
    public boolean isWithinOneRadiusOfWallEndpoint( Wall w ) {
        double d1 = getDistance(this.xPos, this.yPos, w.xStart, w.yStart);
        double d2 = getDistance(this.xPos, this.yPos, w.xEnd, w.yEnd);
        
        if( d1 <= this.radius || d2 <= this.radius )
            return true;
        else
            return false;
    }
    

    //USED WHENEVER THE VELOCITY NEEDS TO BE CHANGED
    public void setVelocity(double xSpeed, double ySpeed) {
        this.velocity.setComponents(xSpeed, ySpeed);
    }

    //USED WHEN BOUNCING OFF A HORIZONTAL WALL
    public void reverseYDirection() {
        this.setVelocity(this.velocity.xComponent, -this.velocity.yComponent);
    }

    //USED WHEN BOUNCING OFF A VERTICAL WALL
    public void reverseXDirection() {
        this.setVelocity(-this.velocity.xComponent, this.velocity.yComponent);
    }

    public void reverseDirection() {
        this.setVelocity(-this.velocity.xComponent, -this.velocity.yComponent);
    }

    //DRAWS THE BALL CENTERED AT ITS CURRENT LOCATION 
    public void draw(Graphics2D g) {
        g.setColor(this.color);
        g.fillOval((int) (this.xPos - this.radius), (int) (this.yPos - this.radius), (int) this.diameter, (int) this.diameter);
    }
}
