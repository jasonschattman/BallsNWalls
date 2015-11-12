
package ballsnwalls;

import java.awt.Color;


public class HorizontalWall extends Wall {
    
    double yLevel;
    
    public HorizontalWall(double yLevel, double xStart, double xEnd, double speed, Color c, int lineThickness, String name ) {
        super(xStart, yLevel, xEnd, yLevel, speed, c, lineThickness, name);
        this.yLevel = yLevel;
        this.orientation="horizontal";
    }
}