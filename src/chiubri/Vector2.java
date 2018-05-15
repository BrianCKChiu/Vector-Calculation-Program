////////////////////////////////////////////////////////////////////////////////
// Vector2.java
// ============
// a general purpose class to define 2D vector
//
// AUTHOR: Brian Chiu
// CREATED: 2018-01-25
// UPDATED: 2018-02-12
////////////////////////////////////////////////////////////////////////////////

package chiubri;


public class Vector2 {
    
    /* 
    - replace value type from double to float for faster a faster calculation 
     and will take up less space
    
    - using public makes it easier to access and no protection is needed 
    */
    public double x;
    public double y;
    
    public Vector2() {
     
    }
    public Vector2(double x, double y) {
        set(x, y);
    
    }
    public void set(double x, double y) {
        
        this.x = x;
        this.y = y;
    }
    public void set(Vector2 v2) {
       x = v2.x;
       y = v2.y;
    }
    public String toString() {
        return String.format("Vector2 (%.2f, %.2f", x, y);
    }
    public Vector2 clone() {
        
        return new Vector2(x, y);
    }
    
    //simple operations for vectors
    public Vector2 add(Vector2 v2) {
        
        x += v2.x;
        y += v2.y;
        return this;
    }    
    public Vector2 subtract(Vector2 v2) {
        x -= v2.x;
        y -= v2.y;
        return this;
    }
    public Vector2 scale(double s) {
        x *= s;
        y *= s;
        return this;
    }
    
    public double dot(Vector2 v2) {
    
    return x * v2.x + y * v2.y;
    }
    public double getLength() {
        
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }    
    // if it returns a 1, it is identical
    // if it returns a 0, there is zero relationship
    public Vector2 normalize() {
        double length = getLength();
        if(length != 0.0)
        {
            x /= length;
            y /= length;
        }
        return this;
    }    
    //checks the x and y cords are equal to each other
    public boolean equals(Vector2 v2) {
        
        if (x == v2.x && y == v2.y) 
            return true;
        else
            return false;
    }
}
