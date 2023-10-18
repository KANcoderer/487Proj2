package com.example.moviedatabaseweb.bootstrap;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class Database {
    private static Connection conn;
    // Connect to the database
    public static void connect() {
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            System.out.println("Connection Success");
        }catch(Exception e){
            System.out.println("Connection Fail");
        }
    }

    // Close the connection to the database
    public static void close() throws SQLException {
        conn.close();
    }

    // Add a movie time to the database
    public static void addMovie(String name, String description, String image) {
        try {

            String sql = "INSERT INTO movies (name, description, image) VALUES (?, ?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setString(3, image);
            pstmt.executeUpdate();

        }catch (SQLException sqle){
            System.err.println("movie was not added");
        }
    }
    public static void editMovie(int id, String name, String description, String image) {
        try {

            String sql = "UPDATE movies SET name=?, description = ?, image=? WHERE Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setString(3, image);
            pstmt.setInt(4,id);
            pstmt.executeUpdate();

        }catch (SQLException sqle){
            System.err.println("movie was not edited");
        }
    }
    public static ResultSet GetMaxID() throws SQLException {
        try {
            String sql = "Select max(Id) from movies";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            return null;
        }
    }
    public static ResultSet SearchMovieById(int id) throws SQLException {
        String sql = "Select * from movies where Id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        return pstmt.executeQuery();
    }

    public static void editMovieName(int id, String name) throws SQLException {
        String sql = "UPDATE movies SET name = ? WHERE Id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public static void editMovieDescription(int id, String description) throws SQLException {
        String sql = "UPDATE movies SET description = ? WHERE Id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, description);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }

    public static void editMovieImage(int id, String image) throws SQLException {
        String sql = "UPDATE movies SET image = ? WHERE Id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, image);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    // Remove a movie from the database
    public static void removeMovie(int id) throws SQLException {
        String sql = "DELETE FROM movies WHERE Id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
    public static ResultSet browseMovies() {
        try{
            String sql = "Select * from movies";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            return null;
        }

    }
}