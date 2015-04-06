/**
 *
 * @author Kaleb
 * @created on: 2/11/2015
 */

/*
Change Log:
    Date:   4/6/2015  
    Desc:   Removed properties used to recover the user profile.
            Adjusted the class to become a JavaBean.
*/
package javaiii.wendel.cablecompany.user;
import java.io.Serializable;

public class User implements Serializable
{
    //General variables.
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int role;

    //Constructors.
    public User()
    {
        username = "Unknown";
        password = "Unknown";
        firstName = "Unknown";
        lastName = "Unknown";
        //Default value for an unkown role as -1 will not exist in the roles table.
        role = -1;
    }
    
    public User(String username, String password)
    {
        //Sets values of instance variables.
        this.username = username;
        this.password = password;
    }
        
    public User(String username, String password, int role)
    {
        //Sets values of instance variables.
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public User(String username, String password, String firstName, String lastName)
    {
        //Sets values of instance variables.
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
        
    public User(String username, String password, int role, String firstName, String lastName)
    {
        //Sets values of instance variables.
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return the role
     */
    public int getRole()
    {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(int role)
    {
        this.role = role;
    }
}
