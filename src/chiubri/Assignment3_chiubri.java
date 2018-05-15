////////////////////////////////////////////////////////////////////////////////
// Assignment3_chiubri.java
// ============
// 
// 
//
// AUTHOR: Brian Chiu
// CREATED: 2018-04-10
// UPDATED: 2018-02-20
////////////////////////////////////////////////////////////////////////////////
package chiubri;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Assignment3_chiubri extends Application
{
    ///////////////////////////////////////////////////////////////////////////
    // entry-point of JavaFX application
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Assignment_3");
        stage.show();
    }



    ///////////////////////////////////////////////////////////////////////////
    public static void main(String[] args)
    {
        launch(args);
    }
}