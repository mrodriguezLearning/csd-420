package com.circlestyles;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

/*
Marco Rodriguez
Assignment 7.2
9/18/2026
*/

public class App extends Application {
    
    /*
   * Override the start method in the Application class
   */
  @Override
  public void start(Stage primaryStage) {

    HBox hBox = new HBox(15);
    Scene scene = new Scene(hBox, 400, 250);   
    hBox.setAlignment(Pos.CENTER);
    hBox.setPadding(new Insets(0));
    /*
     * Load the stylesheet
     * Adds to the stylesheets property - 
     *    Loads the style sheet from the file mystyle.css
     * File location in the same directory as the .java file
     */     
    scene.getStylesheets().add("./mystyle.css");
    Pane pane1 = new Pane();
    Circle circle1 = new Circle(30,120,40);
    circle1.getStyleClass().add("plaincircle");

    Circle circle2 = new Circle(120,120,40);
    circle2.getStyleClass().add("plaincircle");
    circle2.setId("greencircle");

    Circle circle3 = new Circle(210,120,40);
    circle3.setId("plaincircle");
    circle3.setId("redcircle");

    
    pane1.getChildren().addAll(circle1, circle2, circle3);
    
    /*
     * Add a style class
     */
    circle1.getStyleClass().add("plaincircle");
    circle2.getStyleClass().add("plaincircle");
    /*
     * Add a style ID
     */
    circle3.setId("redcircle");
    
    Pane pane2 = new Pane();
    Circle circle4 = new Circle(50,120,40);
    
    /*
     * Sets Style class for circle4
     */
    circle4.getStyleClass().add("plaincircle"); 
    /*
     * Add a style ID
     */
    pane2.getChildren().add(circle4);
    pane2.getStyleClass().add("border");
    

    hBox.getChildren().addAll(pane2, pane1); 
    
    /*
     * Set the window title
     */
    primaryStage.setTitle("StyleSheetDemo");
    /*
     * Place the scene in the window
     */
    primaryStage.setScene(scene);
    /*
     * Display the window
     */
    primaryStage.show();
  }

    public static void main(String[] args) {
        launch(args);
    }
}