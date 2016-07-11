
package ballsnwalls;

import java.awt.Color;


public class EpidemicRolePlayer extends Ball {
    
    static Color healer = Color.blue;
    static Color carrier = Color.red;
    static Color healthyPerson = Color.green;
    
    public EpidemicRolePlayer(double x, double y, double r, double xSpeed, double ySpeed, Color c) {
        super(x, y, r, xSpeed, ySpeed, c, 1);
    }
    
    public void adjustVelocityAfterCollisionWith( Ball theOtherBall ) { 
        
        super.adjustVelocityAfterCollisionWith( theOtherBall ); 
        
        Color me = this.color;
        Color you = theOtherBall.color;
           
        if( me == healer && you == carrier ) 
            theOtherBall.color = healthyPerson;
        
        else if ( me == carrier && you == healer ) 
            this.color = healthyPerson;
        
        else if( me == carrier && you == healthyPerson )
            theOtherBall.color = carrier;
        
        else if( you == carrier && me == healthyPerson )
            this.color = carrier;
 
     }
}
