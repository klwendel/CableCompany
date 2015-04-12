/**
 *
 * @author Kaleb
 * @created on: 2/11/2015
 */
/*
Change Log:
    Date: 4/8/15
    Desc: Adjusted the class to include validation and data access methods for adding a user.

    Date: 4/8/15 - Kaleb Wendel
    Desc: Adjusted class to include data access method for searching for a user by id.

    Date: 4/12/15 - Kaleb
    Desc: Adjusted class to include data access method for user login.
*/
package javaiii.wendel.cablecompany.user;
import java.sql.*;
import javaiii.wendel.cablecompany.data.DatabaseConnection;
import javaiii.wendel.cablecompany.validation.*;

public class UserHandler
{
   public static boolean isValidUsername(String value)
   {
       int minValue = 1;
       int maxValue = 256;
       return Validator.isValidLength(value, minValue, maxValue);
   }
   
   public static boolean isValidUsernameCharacters(String value)
   {
        //Regex expression test.
        //String.matches() internally uses Pattern and Matcher.
        //The pattern below matches letters, numbers and underscores
        //(^ = start of line | [\\w] = letters, numbers and underscore | {1,256} = min,max | $ = end of line | [] = beginning/end of character group)
        return value.matches("^[\\w]{1,256}$");
   }
   public static boolean isValidFirstName(String value)
   {
        int minLength = 1;
        int maxLenght = 128;
        return Validator.isValidLength(value, minLength, maxLenght);
   }
   
   public static boolean isValidFirstNameCharacters(String value)
   {
        //(^ = start of line | [\\p{Alpha] = letters | {1,128} = min,max | $ = end of line | [] = beginning/end of character group)
        return value.matches("^[\\p{Alpha}]{1,128}$");
   }
   public static boolean isValidLastName(String value)
   {
        int minLength = 1;
        int maxLenght = 128;
        return Validator.isValidLength(value, minLength, maxLenght);
   }
   
   public static boolean isValidLastNameCharacters(String value)
   {
        //(^ = start of line | [\\p{Alpha] = letters | {1,128} = min,max | $ = end of line | [] = beginning/end of character group)
        return value.matches("^[\\p{Alpha}]{1,128}$");
   }
   
   public static boolean isValidPassword(String value)
   {
        int minValue = 8;
        int maxValue = 40;
        return Validator.isValidLength(value, minValue, maxValue);
   }
   
   public static boolean isValidPasswordCharacters(String value)
   {
        //(^ = start of line | [\\w] = letters, numbers and underscore | {8,60} = min,max | $ = end of line | [] = beginning/end of character group)
        return value.matches("^[\\w]{8,60}$");
   }
   
   public static int addUser(User user)
   {
        //Default value used for when the upate fails.
        int result = -1;
        Connection connection = DatabaseConnection.getDatabaseConnection();
        try
        {
            String preparedSql = "CALL sp_add_companyuser(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(preparedSql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPassword());
            
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                //LAST_INSERT_ID is the new product id returned from the add_product procedure.
                result = rs.getInt("LAST_INSERT_ID()");
            }
        }
        catch(SQLException ex)
        {
            System.out.println("ERROR: There was an error writing the user to the database:\n\t" + ex.getMessage() + "\n\tSQL State: " + ex.getSQLState());
            ex.printStackTrace();
            if(ex.getSQLState().equals("HY000"))
            {
                //Sets return value to -2 so we can know if the username already exists.
                result = -2;
            }
        }   
        finally
        {
            try
            {
                connection.close();
            }
            catch(Exception ex)
            {
                System.out.println("ERROR: There was an error closing the Database Connection Stream.");
                ex.printStackTrace();
            }
        }
        return result;
   }
   
    /**
     *
     * @param userId
     * @return
     */
    public static User searchUser(int userId)
    {
        User user = null;
        Connection connection = DatabaseConnection.getDatabaseConnection();
        try
        {
            String preparedSql = "CALL sp_get_user_by_userid(?)";
            PreparedStatement statement = connection.prepareStatement(preparedSql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setFirstName(rs.getString(3));
                user.setLastName(rs.getString(4));
            }
        }
        catch(SQLException ex)
        {
            System.out.println("ERROR: There was an error retreiving the user details:\n\t" + ex.getMessage() + "\n\tSQL State: " + ex.getSQLState());
            ex.printStackTrace();
        }   
        finally
        {
            try
            {
                connection.close();
            }
            catch(Exception ex)
            {
                System.out.println("ERROR: There was an error closing the Database Connection Stream.");
                ex.printStackTrace();
            }
        }
        return user;
    }
    
    public static User validateUser(String username, String password)
    {
        User user = null;
        Connection connection = DatabaseConnection.getDatabaseConnection();
        try
        {
            String preparedSql = "CALL sp_get_user_by_name_password(?,?)";
            PreparedStatement statement = connection.prepareStatement(preparedSql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setFirstName(rs.getString(3));
                user.setLastName(rs.getString(4));
            }
        }
        catch(SQLException ex)
        {
            System.out.println("ERROR: There was an error retreiving the user details during the login procedure:\n\t" + ex.getMessage() + "\n\tSQL State: " + ex.getSQLState());
            ex.printStackTrace();
        }   
        finally
        {
            try
            {
                connection.close();
            }
            catch(Exception ex)
            {
                System.out.println("ERROR: There was an error closing the Database Connection Stream.");
                ex.printStackTrace();
            }
        }
        return user;
    }
}