////////////////////////////////////////////////////////////////////////////////
// Line.java
// ============
// a method that creates a line with 2 vectors and sees if other lines 
// intercept it
//
// AUTHOR: Brian Chiu
// CREATED: 2018-01-25
// UPDATED: 2018-02-12
////////////////////////////////////////////////////////////////////////////////
package chiubri;

public class Line {
    
    //properties of a line 
    private Vector2 point;
    private Vector2 direction; // direction where the line is going
                              
    //ctors
    public Line() {
        point = new Vector2(0, 0);
        direction = new Vector2 (0, 0);
    }
    public Line(Vector2 point, Vector2 dir) {
        
       this.point.set(point);
       this.direction.set(direction);
    }
    public Line (double slope, double intercept) {
        
        point = new Vector2(0, intercept); //y intercept
        direction = new Vector2(1, slope); //slope of the line
    }
    public Line(double x1, double y1, double x2, double y2) {
        
       point = new Vector2(x1, y1);
       direction = new Vector2(x2 - x1, y2 - y1); 
    }
    
    //setters and getters
    public void set(Vector2 point, Vector2 direction) {
       
        //reprevents pointing to the original object, instead creates a new 
        //object
       this.point.set(point);
       this.direction.set(direction);
    }
    //translating slope intercept (y = mx + b) form to 2 coordinates 
    public void set(double slope, double intercept) {
        
        point = new Vector2(0, intercept); //y intercept
        direction = new Vector2(1, slope); //slope of the line
    }
    public void set(double x1, double y1, double x2, double y2) {
       
       point = new Vector2(x1, y1);
       direction = new Vector2(x2 - x1, y2 - y1); 
    }
    public void setPoint(Vector2 point) {
        
        this.point = point;
    }
    public Vector2 getPoint() {
        
        return point.clone();
    }
    public void setDirection(Vector2 dir) {
        
        direction = dir;
    }
    public Vector2 getDirection() {
        
        return direction.clone();
    }
    //returns out the line details
    public String toString() {
       
        return String.format("Line\n====\n    Point: (%.2f,%.2f)\nDirection:" + 
                " (%.2f, %.2f)", point.x, point.y, direction.x, direction.y);
    }
    public Vector2 intersect(Line line) {
        
        //ax + by = c
        //line one
        double a = direction.y;
        double b = -direction.x;
        double c = direction.y * point.x - direction.x * point.y; 
        
        //line two
        double d = line.direction.y;
        double e = -line.direction.x;
        double f = line.direction.y * line.point.x - line.direction.x *
                line.point.y;      

        //determinant = ae - bd 
        double det = a * e - b * d;
        
        //will return these values if the lines dont intercept
        Vector2 intersectPoint = new Vector2 (Float.NaN, Float.NaN);
        
        //if the line intercepts 
        if (det != 0) {
        // finds x and y intercept
            intersectPoint.x = (c * e - b * f)/ det; //X cord
            intersectPoint.y = (a * f - c * d)/det;  //Y cord
        }
        
        //returns the line intersection
        return intersectPoint;
    }
    public boolean isIntersected(Line line) {
        
        //line 1
        double a = direction.y;
        double b = -direction.x;
        
        //line two
        double d = line.direction.y;
        double e = -line.direction.x;
        
        //returns false if the line doesnt intercept
        return((a * e - b * d) != 0);
            
    } 
    //returns true if both vector points are equal to each other
    public boolean equals(Line l) {
        
        if (point.equals(l.point) == true && 
                direction.equals(l.direction) == true)
            return true;
        else
            return false;
        
    }
    
}
