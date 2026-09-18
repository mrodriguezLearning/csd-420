package com.marcothreethreads;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Random;

/**
 * 
 * Marco Rodriguez
 * Assignment 8.2
 * 9/18/2026
 */

public class App extends Application {
    // Text area to display the interleaved characters
    private TextArea textArea;

    /**
     * The start method initializes the JavaFX user interface and launches
     * the three background threads.
     *
     */
    @Override
    public void start(Stage primaryStage) {
        
        // Initialize the text area properties
        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setEditable(false);

        // Set up the scene and layout
        StackPane root = new StackPane(textArea);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Marco Three Threads");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create the three runnable tasks
        Thread letterThread = new Thread(new LetterTask());
        Thread numberThread = new Thread(new NumberTask());
        Thread specialThread = new Thread(new SpecialTask());

        // Start all three threads concurrently
        letterThread.start();
        numberThread.start();
        specialThread.start();
    }

    /**
     * LetterTask is a Runnable that generates 10,000 random lowercase letters.
     */
    class LetterTask implements Runnable {
        @Override
        public void run() {
            Random rand = new Random();
            for (int i = 0; i < 10000; i++) {
                
                // Generate a random lowercase letter from 'a' to 'z'
                char c = (char) ('a' + rand.nextInt(26));
                appendCharacter(c);
                pause();
            }
        }
    }

    /**
     * NumberTask is a Runnable that generates 10,000 random numeric digits.
     */
    class NumberTask implements Runnable {
        @Override
        public void run() {
            Random rand = new Random();
            for (int i = 0; i < 10000; i++) {
                
                // Generate a random digit from '0' to '9'
                char c = (char) ('0' + rand.nextInt(10));
                appendCharacter(c);
                pause();
            }
        }
    }

    /**
     * SpecialTask is a Runnable that generates 10,000 random special characters.
     */
    class SpecialTask implements Runnable {
        @Override
        public void run() {
            Random rand = new Random();
            
            // Array of allowed special characters
            char[] specials = {'!', '@', '#', '$', '%', '&', '*'};
            
            for (int i = 0; i < 10000; i++) {
                
                // Pick a random special character from the array
                char c = specials[rand.nextInt(specials.length)];
                appendCharacter(c);
                pause();
            }
        }
    }

    /**
     * Safely appends a character to the JavaFX text area from a background thread.
     * Platform.runLater queues the update on the JavaFX Application Thread.
     *
     * @param c The character to append
     */
    private void appendCharacter(char c) {
        Platform.runLater(() -> textArea.appendText(String.valueOf(c)));
    }

    /**
     * Pauses the current thread for 1 millisecond.
     * This forces the CPU to switch between threads, ensuring the output
     * is heavily interleaved rather than printed all at once by a single thread.
     */
    private void pause() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * The main method serves as the entry point and test code for the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}