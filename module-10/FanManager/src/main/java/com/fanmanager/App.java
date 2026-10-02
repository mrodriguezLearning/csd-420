package com.fanmanager;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Marco Rodriguez
 * 10/2/2026
 * Assignment 10.2
 * 
 * The App class provides a JavaFX graphical user interface for managing 
 * sports fan records. It connects to a local MySQL database to retrieve 
 * and update records in the fans table based on a unique ID.
 * Table fans needs to be already created and populated, 
 * this app doesn't create fan table.
 */
public class App extends Application {

    // User interface input fields for fan data
    private TextField tfId = new TextField();
    private TextField tfFirstName = new TextField();
    private TextField tfLastName = new TextField();
    private TextField tfFavoriteTeam = new TextField();
    
    // Status label to provide operation feedback to the user
    private Label lblStatus = new Label("Enter an ID to display or update a record.");

    // Database connection constants
    private final String DB_URL = "jdbc:mysql://localhost:3306/databasedb";
    private final String DB_USER = "student1";
    private final String DB_PASS = "pass";
    
    /**
     * The start method initializes the layout, adds UI components to the scene,
     * and binds the button click events to their respective database methods.
     * 
     */
    @Override
    public void start(Stage primaryStage) {
        
        // Set up a grid pane to align labels and text fields neatly
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Add ID label and text field
        gridPane.add(new Label("ID:"), 0, 0);
        gridPane.add(tfId, 1, 0);
        
        // Add First Name label and text field
        gridPane.add(new Label("First Name:"), 0, 1);
        gridPane.add(tfFirstName, 1, 1);
        
        // Add Last Name label and text field
        gridPane.add(new Label("Last Name:"), 0, 2);
        gridPane.add(tfLastName, 1, 2);
        
        // Add Favorite Team label and text field
        gridPane.add(new Label("Favorite Team:"), 0, 3);
        gridPane.add(tfFavoriteTeam, 1, 3);

        // Create action buttons for database operations
        Button btnDisplay = new Button("Display");
        Button btnUpdate = new Button("Update");

        // Place buttons side by side in a horizontal box
        HBox hBoxButtons = new HBox(15, btnDisplay, btnUpdate);
        hBoxButtons.setAlignment(Pos.CENTER);

        // Group the grid, buttons, and status label into a vertical root container
        VBox root = new VBox(20, gridPane, hBoxButtons, lblStatus);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Bind button click events to the appropriate methods
        btnDisplay.setOnAction(e -> displayRecord());
        btnUpdate.setOnAction(e -> updateRecord());

        // Configure and display the main application window
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Fan Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Connects to the database and retrieves a record matching the provided ID.
     * If a match is found, it populates the text fields. If not, it clears them.
     */
    private void displayRecord() {
        String query = "SELECT firstname, lastname, favoriteteam FROM fans WHERE ID = ?";
        
        // Use try with resources to automatically manage the database connection
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement pstmt = con.prepareStatement(query)) {
             
            // Parse the ID from the text field and inject it into the SQL query
            pstmt.setInt(1, Integer.parseInt(tfId.getText().trim()));
            ResultSet rs = pstmt.executeQuery();
            
            // Check if the query returned any results
            if (rs.next()) {
                tfFirstName.setText(rs.getString("firstname"));
                tfLastName.setText(rs.getString("lastname"));
                tfFavoriteTeam.setText(rs.getString("favoriteteam"));
                lblStatus.setText("Record found and displayed.");
            } else {
                clearFields();
                lblStatus.setText("No record found for ID: " + tfId.getText());
            }
            
        } catch (NumberFormatException e) {
            lblStatus.setText("Error: ID must be a valid integer.");
        } catch (SQLException e) {
            lblStatus.setText("Database error: " + e.getMessage());
        }
    }

    /**
     * Connects to the database and updates the record corresponding to the provided ID
     * using the current text field values.
     */
    private void updateRecord() {
        String query = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?";
        
        // Use try with resources to securely execute the update query
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement pstmt = con.prepareStatement(query)) {
             
            // Map the text field values to the corresponding SQL query parameters
            pstmt.setString(1, tfFirstName.getText().trim());
            pstmt.setString(2, tfLastName.getText().trim());
            pstmt.setString(3, tfFavoriteTeam.getText().trim());
            pstmt.setInt(4, Integer.parseInt(tfId.getText().trim()));
            
            // Execute the update and check how many rows were modified
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                lblStatus.setText("Record updated successfully.");
            } else {
                lblStatus.setText("Update failed. Ensure the ID exists.");
            }
            
        } catch (NumberFormatException e) {
            lblStatus.setText("Error: ID must be a valid integer.");
        } catch (SQLException e) {
            lblStatus.setText("Database error: " + e.getMessage());
        }
    }

    /**
     * Clears the input fields for first name, last name, and favorite team.
     * This prevents leftover data from remaining on screen when an ID is not found.
     */
    private void clearFields() {
        tfFirstName.clear();
        tfLastName.clear();
        tfFavoriteTeam.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}