package com.assignment9_2;

import java.sql.SQLException;

/**
 *
 * Marco Rodriguez
 * Assignment 9.2
 * 9/25/2026
 * 
 */
public class Assignment9_2 {

    public static void main(String[] args) throws SQLException {
        DataBase DB = new DataBase();
        
        DB.CreateTable();
        DB.InserData();
        DB.SelectData();
        DB.closeDB();
        
    }
}
