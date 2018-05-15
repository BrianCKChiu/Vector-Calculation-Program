////////////////////////////////////////////////////////////////////////////////
// MainFormController.java
// ============
// 
// 
//
// AUTHOR: Brian Chiu
// CREATED: 2018-04-10
// UPDATED: 2018-02-20
////////////////////////////////////////////////////////////////////////////////
package chiubri;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class MainFormController implements Initializable
{
    // constants
    private static final int PADDING = 10;      // pixels
    private static final int UNIT_COUNT = 10;   // # of units only on positive side
    
    // member vars
    private int width;                  // width of drawing area
    private int height;                 // height of drawing area
    private int centerX;                // center X in screen space
    private int centerY;                // center y in screen space
    private double mouseX;              // screen coordinate x
    private double mouseY;              // screen coordinate y
    private double coordRatio;          // map screen coord to logical coord, s/l
    private double coordX;              // logical coordinate x
    private double coordY;              // logical coordinate y
    private Line[] hLines;              // horizontal grid lines
    private Line[] vLines;              // vertical grid lines
    private MainModel model;
    // JavaFX controls
    private Rectangle rectClip;         // clipping rectangle
    @FXML
    private Pane paneView;
    @FXML
    private Pane paneControl;
    @FXML
    private Label labelCoord;
    @FXML
    private Line line1a;
    @FXML
    private Line line1b;
    @FXML
    private Line line1c;
    @FXML
    private Line line2a;
    @FXML
    private Line line2b;
    @FXML
    private Line line2c;
    @FXML
    private Circle point1a;
    @FXML
    private Circle point1b;
    @FXML
    private Circle point2a;
    @FXML
    private Circle point2b;
    @FXML
    private Circle pointIntersect;
    @FXML
    private Slider sliderL1X1;
    @FXML
    private Label labelL1X1;
    @FXML
    private Slider sliderL1X2;
    @FXML
    private Slider sliderL1Y2;
    @FXML
    private Slider sliderL1Y1;
    @FXML
    private Label labelL1Y1;
    @FXML
    private Label labelL1X2;
    @FXML
    private Label labelL1Y2;
    @FXML
    private Slider sliderL2X1;
    @FXML
    private Slider sliderL2Y1;
    @FXML
    private Slider sliderL2X2;
    @FXML
    private Slider sliderL2Y2;
    @FXML
    private Label labelL2X1;
    @FXML
    private Label labelL2Y1;
    @FXML
    private Label labelL2X2;
    @FXML
    private Label labelL2Y2;
    @FXML
    private Label labelIntersect;
    @FXML
    private Button resetButton;
    @FXML
    private Label labelEquationL1;
    @FXML
    private Label labelEquationL2;


    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // init line arrays
        initGrid();

        // set clip region for drawing area
        rectClip = new Rectangle(500, 500);
        paneView.setClip(rectClip);

        // update width and height of drawing area
        ChangeListener resizeListener = (ov, oldV, newV) -> handleViewResized();
        paneView.widthProperty().addListener(resizeListener);
        paneView.heightProperty().addListener(resizeListener);
        
        //property binding for sliders
        
        ////////////////////////Line 1//////////////////////////////////////////
        labelL1X1.textProperty()
                .bind(sliderL1X1.valueProperty().asString("%.1f"));
        labelL1Y1.textProperty()
                .bind(sliderL1Y1.valueProperty().asString("%.1f"));
        labelL1X2.textProperty()
                .bind(sliderL1X2.valueProperty().asString("%.1f"));
        labelL1Y2.textProperty()
                .bind(sliderL1Y2.valueProperty().asString("%.1f"));
        
        
        
        
        
        //register ChangeListener for all sliders
        ChangeListener listener = (ov, oldValue, newValue) -> updateLines();
        sliderL1X1.valueProperty().addListener(listener);
        sliderL1Y1.valueProperty().addListener(listener);
        sliderL1X2.valueProperty().addListener(listener);
        sliderL1Y2.valueProperty().addListener(listener);
        
        
        ////////////////////////Line 2//////////////////////////////////////////
        labelL2X1.textProperty()
                .bind(sliderL2X1.valueProperty().asString("%.1f"));
        labelL2Y1.textProperty()
                .bind(sliderL2Y1.valueProperty().asString("%.1f"));
        labelL2X2.textProperty()
                .bind(sliderL2X2.valueProperty().asString("%.1f"));
        labelL2Y2.textProperty()
                .bind(sliderL2Y2.valueProperty().asString("%.1f"));
        
      
        
        
        
        //register ChangeListener for all sliders
        sliderL2X1.valueProperty().addListener(listener);
        sliderL2Y1.valueProperty().addListener(listener);
        sliderL2X2.valueProperty().addListener(listener);
        sliderL2Y2.valueProperty().addListener(listener);
        
        
     
        model = new MainModel();

        //end of initalize
    }



    ///////////////////////////////////////////////////////////////////////////
    @FXML
    private void handleMouseMoved(MouseEvent event)
    {
    }



    ///////////////////////////////////////////////////////////////////////////
    @FXML
    private void handleMouseDragged(MouseEvent event)
    {
    }



    ///////////////////////////////////////////////////////////////////////////
    @FXML
    private void handleMousePressed(MouseEvent event)
    {
    }



    ///////////////////////////////////////////////////////////////////////////
    @FXML
    private void handleMouseReleased(MouseEvent event)
    {
    }



    ///////////////////////////////////////////////////////////////////////////
    @FXML
    private void handleMouseExited(MouseEvent event)
    {
        labelCoord.setText("");
    }



    ///////////////////////////////////////////////////////////////////////////
    private void initGrid()
    {
        int lineCount = UNIT_COUNT * 2 + 1; // both side plus 1 at enter
        hLines = new Line[lineCount];
        vLines = new Line[lineCount];

        // create line objects
        for(int i = 0; i < lineCount; ++i)
        {
            hLines[i] = new Line();
            hLines[i].setStrokeWidth(0.2);
            hLines[i].setStroke(Color.GRAY);
            paneView.getChildren().add(hLines[i]);
            hLines[i].toBack();

            vLines[i] = new Line();
            vLines[i].setStrokeWidth(0.2);
            vLines[i].setStroke(Color.GRAY);
            paneView.getChildren().add(vLines[i]);
            vLines[i].toBack();
        }

        // for center line
        hLines[lineCount / 2].setStroke(Color.BLACK);
        hLines[lineCount / 2].setStrokeWidth(0.4);
        vLines[lineCount / 2].setStroke(Color.BLACK);
        vLines[lineCount / 2].setStrokeWidth(0.4);

        // layout grid lines
        updateGrid();
    }



    ///////////////////////////////////////////////////////////////////////////
    private void handleViewResized()
    {
        width = (int)paneView.getWidth();
        height = (int)paneView.getHeight();

        // compute the ratio of scrren to virtual = s / v
        double dim = Math.min(width, height) - (PADDING * 2);
        coordRatio = dim / (UNIT_COUNT * 2.0);

        centerX = (int)(width * 0.5 + 0.5);
        centerY = (int)(height * 0.5 + 0.5);
        //System.out.printf("center: (%d, %d)\n", centerX, centerY);

        // update clipping region
        rectClip.setWidth(width);
        rectClip.setHeight(height);

        updateGrid();
        updateLines();
    }



    ///////////////////////////////////////////////////////////////////////////
    private void updateGrid()
    {
        int dim;    // square dimension
        int xGap, yGap;

        if(width > height)
        {
            dim = height - (PADDING * 2);
            xGap = (int)((width - dim) * 0.5 + 0.5);
            yGap = PADDING;
        }
        else
        {
            dim = width - (PADDING * 2);
            xGap = PADDING;
            yGap = (int)((height - dim) * 0.5 + 0.5);
        }
        double step = dim / (UNIT_COUNT * 2.0);

        for(int i = 0; i < hLines.length; ++i)
        {
            hLines[i].setStartX(xGap);
            hLines[i].setStartY(yGap + (int)(step * i + 0.5));
            hLines[i].setEndX(width - xGap);
            hLines[i].setEndY(yGap + (int)(step * i + 0.5));

            vLines[i].setStartX(xGap + (int)(step * i + 0.5));
            vLines[i].setStartY(yGap);
            vLines[i].setEndX(xGap + (int)(step * i + 0.5));
            vLines[i].setEndY(height - yGap);
        }
        
    }
    



    ///////////////////////////////////////////////////////////////////////////
    private void updateLines()
    {
        //get logical coords of all points
        
        //line 1
        double l1x1= sliderL1X1.getValue();
        double l1y1= sliderL1Y1.getValue();
        
        double l1x2= sliderL1X2.getValue();
        double l1y2= sliderL1Y2.getValue();
        
        //line 2
        double l2x1= sliderL2X1.getValue();
        double l2y1= sliderL2Y1.getValue();
        
        double l2x2= sliderL2X2.getValue();
        double l2y2= sliderL2Y2.getValue();
        
      
        //convert logical coords to actual coords
        point1a.setCenterX(l1x1 * coordRatio + centerX);
        point1a.setCenterY(-l1y1 * coordRatio + centerY);
        point1b.setCenterX(l1x2 * coordRatio + centerX);
        point1b.setCenterY(-l1y2 * coordRatio + centerY);
       
        //for exended points
        Vector2 v = new Vector2();  //direction vector
        Vector2 p;                  //the extended point
        
        //draw lines
        line1a.setStartX(point1a.getCenterX());
        line1a.setStartY(point1a.getCenterY());
        line1a.setEndX(point1b.getCenterX());
        line1a.setEndY(point1b.getCenterY());
        
        //extension of lines
        v.set(l1x2 - l1x1, l1y2 - l1y1);
        v.normalize();
        p = v.clone().scale(50).add(new Vector2(l1x2, l1y2));
        
        line1b.setStartX(point1b.getCenterX());
        line1b.setStartY(point1b.getCenterY());
        line1b.setEndX(p.x * coordRatio + centerX);
        line1b.setEndY(-p.y * coordRatio + centerY);
        
        p = v.clone().scale(-50).add(new Vector2(l1x1, l1y1));
        line1c.setStartX(point1a.getCenterX());
        line1c.setStartY(point1a.getCenterY());
        line1c.setEndX(p.x * coordRatio + centerX);
        line1c.setEndY(-p.y * coordRatio + centerY);
        
        
        ////////////////////////Line 2//////////////////////////////////////////  
        Vector2 v2 = new Vector2();
        Vector2 q;  
        //convert logical coords to actual coords
        point2a.setCenterX(l2x1 * coordRatio + centerX);
        point2a.setCenterY(-l2y1 * coordRatio + centerY);
        point2b.setCenterX(l2x2 * coordRatio + centerX);
        point2b.setCenterY(-l2y2 * coordRatio + centerY);
        
        //draw lines
        line2a.setStartX(point2a.getCenterX());
        line2a.setStartY(point2a.getCenterY());
        line2a.setEndX(point2b.getCenterX());
        line2a.setEndY(point2b.getCenterY());
        
        //extension of lines
        v2.set(l2x2 - l2x1, l2y2 - l2y1);
        v2.normalize();
        q = v2.clone().scale(50).add(new Vector2(l2x2, l2y2));
        
        line2b.setStartX(point2b.getCenterX());
        line2b.setStartY(point2b.getCenterY());
        line2b.setEndX(q.x * coordRatio + centerX);
        line2b.setEndY(-q.y * coordRatio + centerY);
        
        q = v2.clone().scale(-50).add(new Vector2(l2x1, l2y1));
        line2c.setStartX(point2a.getCenterX());
        line2c.setStartY(point2a.getCenterY());
        line2c.setEndX(q.x * coordRatio + centerX);
        line2c.setEndY(-q.y * coordRatio + centerY);
        
        
        model.setLine1(l1x1, l1y1, l1x2, l1y2);
        model.setLine2(l2x1, l2y1, l2x2, l2y2);
        Vector2 intersectPoint = model.getIntersectPoint();

        //plot the intersect point and print
        labelIntersect
                .setText(String.format("(%.1f, %.1f)",
                        intersectPoint.x, intersectPoint.y));
        
        pointIntersect.setCenterX(intersectPoint.x * coordRatio + centerX);
        pointIntersect.setCenterY(-intersectPoint.y * coordRatio + centerY);
        
        //displaying slope-intersect equation of line 1  (y=mx+b)
        String equationL1;
        double slope1 = (l1y2 - l1y1) / (l1x2 - l1x1);
        double yIntersectL1 =  l1y1 - (slope1 * l1x1);
        if (yIntersectL1 != 0.0)
            labelEquationL1
                    .setText(String.format("y= %.1fx+%.1f",
                            slope1, yIntersectL1));
        else
            //when b-value is 0
            labelEquationL1.setText(String.format("y= %.1fx", slope1));
        
        //displaying slope-intersect equation of line 2  (y=mx+b)
        String equationL2;
        double slope2 = (l2y2 - l2y1) / (l2x2 - l2x1);
        double yIntersectL2 =  l2y1 - (slope2 * l2x1);
        if (yIntersectL2 != 0.0)
            labelEquationL2
                    .setText(String.format("y= %.1fx+%.1f",
                            slope2, yIntersectL2));
        else
            //when b-value is 0
            labelEquationL2.setText(String.format("y= %.1fx", slope2));
    }

    @FXML
    private void handleReset(ActionEvent event) {
        
        //defult values when reset button is clicked
        sliderL1X1.setValue(-5.0);
        sliderL1Y1.setValue(5.0);
        sliderL1X2.setValue(5.0);
        sliderL1Y2.setValue(-5.0);
        sliderL2X1.setValue(-5.0);
        sliderL2Y1.setValue(-5.0);
        sliderL2X2.setValue(5.0);
        sliderL2Y2.setValue(5.0);  
    }
}
