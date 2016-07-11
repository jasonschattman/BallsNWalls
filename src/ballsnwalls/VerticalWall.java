
package ballsnwalls;

import java.awt.Color;


public class VerticalWall extends Wall {
    
    double xLevel;
    
    public VerticalWall(double xLevel, double yStart, double yEnd, double speed, Color c, int lineThickness, String name ) {
        super(xLevel, yStart, xLevel, yEnd, speed, c, lineThickness, name);
        this.xLevel = xLevel;
        this.orientation = "vertical";
    }
}
