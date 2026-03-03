package com.jdbc.demo;

import java.sql.*;
import java.util.Scanner;

/*
===========================================================
JDBC DAILY PRACTICE – COMPLETE BASICS
===========================================================

Topics Covered:
1. JDBC Setup
2. Database Connection
3. DriverManager
4. PreparedStatement
5. INSERT Operation
6. UPDATE Operation
7. DELETE Operation

Interview Quick Revision Points:
- JDBC = Java Database Connectivity
- DriverManager establishes DB connection
- Connection represents session with DB
- PreparedStatement prevents SQL Injection
- executeUpdate() → INSERT, UPDATE, DELETE
- executeQuery() → SELECT
===========================================================
*/

public class Main {

    // Database configuration
    static final String URL = "jdbc:mysql://localhost:3306/testdb";
    static final String USERNAME = "root";
    static final String PASSWORD = "yourpassword";

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            // STEP 1: Establish Connection
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database Connected Successfully");

            /*
            ===================================================
            INSERT OPERATION
            ===================================================
            SQL: INSERT INTO students (name, age) VALUES (?, ?)
            */

            String insertSQL = "INSERT INTO students (name, age) VALUES (?, ?)";
            pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, "Ramu Kaka");
            pstmt.setInt(2, 78);

            int insertRows = pstmt.executeUpdate();
            System.out.println("Inserted Rows: " + insertRows);


            /*
            ===================================================
            UPDATE OPERATION
            ===================================================
            SQL: UPDATE students SET age = ? WHERE name = ?
            */

            String updateSQL = "UPDATE students SET age = ? WHERE name = ?";
            pstmt = conn.prepareStatement(updateSQL);
            pstmt.setInt(1, 24);
            pstmt.setString(2, "Ramu Kaka");

            int updateRows = pstmt.executeUpdate();
            System.out.println("Updated Rows: " + updateRows);


            /*
            ===================================================
            DELETE OPERATION
            ===================================================
            SQL: DELETE FROM students WHERE name = ?
            */

            String deleteSQL = "DELETE FROM students WHERE name = ?";
            pstmt = conn.prepareStatement(deleteSQL);
            pstmt.setString(1, "Ramu Kaka");

            int deleteRows = pstmt.executeUpdate();
            System.out.println("Deleted Rows: " + deleteRows);


        } catch (SQLException e) {

            System.out.println("SQL Exception Occurred");
            e.printStackTrace();

        } finally {

            // STEP 2: Close Resources (Best Practice)

            try {
                if (pstmt != null)
                    pstmt.close();

                if (conn != null)
                    conn.close();

                System.out.println("Connection Closed");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
