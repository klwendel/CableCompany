/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaiii.wendel.cablecompany.data;
import java.sql.*;
/**
 *
 * @author Kaleb
 * @creation 4/6/2015
 */
public class DatabaseConnection
{
    public static Connection getDatabaseConnection()
    {
        Connection connection = null;
        
        // This should be deprecated, but it may still be needed for some
        // configurations of Tomcat/Java/Operating Systems/etc.
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR: getting Class.forName: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        // Get the connection object 
        try
        {
            String databaseUrl = "jdbc:mysql://127.0.0.1:3306/cellular_division";
            String username = "celluser";
            String password = "letmein";
            connection = DriverManager.getConnection(databaseUrl, username, password);
        }
        catch(SQLException ex)
        {
            System.out.println("In method getDbConnection(): " + ex.getMessage());
            ex.printStackTrace();
        }
        return connection;
    }
}
