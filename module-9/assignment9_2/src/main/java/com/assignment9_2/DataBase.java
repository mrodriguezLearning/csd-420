package com.assignment9_2;

/**
 * Marco Rodriguez
 * Assignment 9.2
 * 9/25/2026
 * 
 * Consolidating all Classes Files to a DataBase class with methods.
 * This class establishes a connection to a MySQL database, resets and creates
 * a table named address33, inserts address records, queries and prints
 * the stored results, and safely closes the database resources.
 */
import java.sql.*;

public class DataBase {
    // Database connection and SQL statement fields
    Connection con;
    Statement stmt;

    /**
     * Constructor for DataBase.
     * Loads the MySQL JDBC driver, establishes a connection using database credentials,
     * and initializes the Statement object used for running SQL queries.
     */
    public DataBase() {
        try {
            // Load the MySQL JDBC driver into runtime memory
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection parameters
            String url = "jdbc:mysql://localhost:3306/databasedb";
            String user = "student1";
            String password = "pass";
            
            // Connect to the database and initialize the SQL statement runner
            this.con = DriverManager.getConnection(url, user, password);
            this.stmt = this.con.createStatement();

            System.out.println("Connection established");

        } catch (java.lang.Exception ex) {
            // Print error details if driver loading or connection fails
            ex.printStackTrace();
        }
    }

    /**
     * Drops the existing address33 table if present, then creates
     * a new address33 table with structured address columns and a primary key.
     */
    public void CreateTable() {
        // Attempt to drop the table if it already exists from a prior run
        try {
            this.stmt.executeUpdate("DROP TABLE address33");
            System.out.println("Table address Dropped");
        } catch (SQLException e) {
            System.out.println("Table address does not exist");
        }

        // Create the fresh address33 table schema
        try {
            this.stmt.executeUpdate("CREATE TABLE address33(ID int PRIMARY KEY, LASTNAME varchar(40), " +
                    "FIRSTNAME varchar(40), STREET varchar(40), CITY varchar(40), STATE varchar(40), " +
                    "ZIP varchar(40))");
            System.out.println("Table address Created");
        } catch (SQLException e) {
            System.out.println("Table address Creation failed: " + e.getMessage());
        }
    }

    /**
     * Inserts sample address records into the address33 table.
     * Outputs the count of updated rows for each insert operation.
     */
    public void InserData() {
        try {
            // Execute batch inserts for each sample address record
            System.out.println(this.stmt.executeUpdate(
                    "INSERT INTO address33 VALUES(55,'Larry','Rich','1111 Redwing Circle888','Bellevue','NE','68123')") + " row updated");

            System.out.println(this.stmt.executeUpdate(
                    "INSERT INTO address33 VALUES(1,'Fine','Ruth','1111 Redwing Circle','Bellevue','NE','68123')") + " row updated");

            System.out.println(this.stmt.executeUpdate(
                    "INSERT INTO address33 VALUES(2,'Howard','Curly','1000 Galvin Road South','Bellevue','NE','68005')") + " row updated");

            System.out.println(this.stmt.executeUpdate(
                    "INSERT INTO address33 VALUES(3,'Howard','Will','2919 Redwing Circle','Bellevue','NE','68123')") + " row updated");

            System.out.println(this.stmt.executeUpdate(
                    "INSERT INTO address33 VALUES(4,'Wilson','Larry','1121 Redwing Circle','Bellevue','NE','68124')") + " row updated");

            System.out.println(this.stmt.executeUpdate(
                    "INSERT INTO address33 VALUES(5,'Johnson','George','1300 Galvin Road South','Bellevue','NE','68006')") + " row updated");

            System.out.println(this.stmt.executeUpdate(
                    "INSERT INTO address33 VALUES(6,'Long','Matthew','2419 Redwing Circle','Bellevue','NE','68127')") + " row updated");

            System.out.println(this.stmt.executeUpdate(
                    "INSERT INTO address33 VALUES(44,'Tom','Matthew','1999 Redwing Circle','Bellevue','NE','68123')") + " row updated");

            System.out.println("Data Inserted");
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Insert Data Failed");
        }
    }

    /**
     * Executes a SELECT query to retrieve all rows from the address33 table
     * and prints each column value to the console line by line.
     *
     */
    public void SelectData() throws SQLException {
        ResultSet rs = this.stmt.executeQuery("SELECT * FROM address33");

        System.out.println("Received Results:");

        // Retrieve dynamic column count from table metadata
        int columnCount = rs.getMetaData().getColumnCount();

        // Iterate through each row in the ResultSet cursor
        while (rs.next()) {
            for (int x = 1; x <= columnCount; ++x) {
                System.out.print(rs.getString(x) + " ");
            }
            System.out.println();
        }
    }

    /**
     * Closes the active Statement and Connection instances to prevent resource leaks.
     */
    public void closeDB() {
        try {
            // Close statement resource if initialized
            if (this.stmt != null) {
                this.stmt.close();
            }
            // Close connection resource if initialized
            if (this.con != null) {
                this.con.close();
                System.out.println("Database closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DataBase db = new DataBase();

        // Ensure connection was successfully made before running database operations
        if (db.con != null) {
            db.CreateTable();
            db.InserData();

            try {
                db.SelectData();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Ensure connections close regardless of whether queries pass or fail
                db.closeDB();
            }
        }
    }
}