/**
 *
 * @author Kaleb
 * @created on: 2/11/2015
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
    //Used to recover username.
    private String securityQuestion;
    private String securityAnser;

    //Constructors.
    public User()
    {

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
     * @return the securityQuestion
     */
    public String getSecurityQuestion()
    {
        return securityQuestion;
    }

    /**
     * @param securityQuestion the securityQuestion to set
     */
    public void setSecurityQuestion(String securityQuestion)
    {
        this.securityQuestion = securityQuestion;
    }

    /**
     * @return the securityAnser
     */
    public String getSecurityAnser()
    {
        return securityAnser;
    }

    /**
     * @param securityAnser the securityAnser to set
     */
    public void setSecurityAnser(String securityAnser)
    {
        this.securityAnser = securityAnser;
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
