/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package java3labassignment2;

import View.ViewManager;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author emad
 */
public class Java3LabAssignment2 extends Application{

    /**
     * @param args the command line arguments
     */
   @Override
    public void start(Stage primaryStage) throws IOException {
       ViewManager.openAdminPage();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
