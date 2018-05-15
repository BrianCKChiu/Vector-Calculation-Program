////////////////////////////////////////////////////////////////////////////////
// MainModel.java
// ============
// 
// 
//
// AUTHOR: Brian Chiu
// CREATED: 2018-04-10
// UPDATED: 2018-02-20
////////////////////////////////////////////////////////////////////////////////
package chiubri;

public class MainModel {
    
    Line line1; // line 1 
    Line line2; //line2
    
    public void MainModel (){}
    
    public void setLine1 (double x1, double y1, double x2, double y2) {
        line1 = new Line (x1, y1, x2, y2);
        
    }
    
    public void setLine2 (double x1, double y1, double x2, double y2) {
        line2 = new Line (x1, y1, x2, y2);
        
    }
    
    //returns intersection point of both lines
    public Vector2 getIntersectPoint () {
        
        return line1.intersect(line2);
    }
    
}
