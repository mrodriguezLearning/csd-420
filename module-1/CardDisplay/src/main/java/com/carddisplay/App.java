package com.carddisplay;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.Collections;

/*
*
*   Marco Rodriguez
*   Cads Assignment
*   8/13/2026
*
 */

public class App extends Application {

    private HBox cardBox;
    private ArrayList<Integer> deck;

    @Override
    public void start(Stage primaryStage) {
        /* Arreay for 52 card deck */
        deck = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }
        // Adding HBox
        cardBox = new HBox(15);
        cardBox.setAlignment(Pos.CENTER);
        cardBox.setPadding(new Insets(20, 20, 20, 20));
        // Adding a Button
        Button refreshButton = new Button("Refresh");
        
        refreshButton.setOnAction(event -> displayCards());
        //Call diaplayCards Function
        displayCards();

        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(cardBox, refreshButton);

        Scene scene = new Scene(root, 600, 300);
        primaryStage.setTitle("Random Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayCards() {
        cardBox.getChildren().clear();
        // Shuffle Array
        Collections.shuffle(deck);
        //Adding 4 images for the suffled deck
        for (int i = 0; i < 4; i++) {
            String imagePath = "file:cards/" + deck.get(i) + ".png";
            Image cardImage = new Image(imagePath);
            ImageView imageView = new ImageView(cardImage);
            
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
            
            cardBox.getChildren().add(imageView);
            
        }
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}