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
        //Creates a connection and sets it to null.
        Connection connection = null;
        
        // This should be deprecated, but it may still be needed for some configurations of Tomcat/Java/Operating Systems/etc.
        try 
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR: There was an error getting Class.forName in DatabaseConnection.getDatabaseConnection:\n" + ex.getMessage());
        }
        
        //Get the connection object.
        try
        {
            String databaseUrl = "jdbc:mysql://localhost:3306/cellular_division";
            String user = "celluser";
            String password = "letmein";
            connection = DriverManager.getConnection(databaseUrl, user, password);
        }
        catch(SQLException ex)
        {
            System.out.println("ERROR: There was an error in DatabaseConnection.getDatabaseConnection:\n" + ex.getMessage());
        }
        return connection;
    }
}
