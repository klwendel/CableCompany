/**
 *
 * @author Kaleb
 * @dateModified 2/11/2015
 */
package javaiii.wendel.cablecompany.user;

public class Administrator extends User
{
    //Instance variables for Administrators.
    private String employeeId;
    
    //Constructors
    public Administrator()
    {
        
    }
    
    public Administrator(String username, String password, int role, String firstName, String lastName, String employeeId)
    {
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(role);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.employeeId = employeeId;
    }
    
    /**
     * @return the employeeId
     */
    public String getEmployeeId()
    {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }
    
    public String toString()
    {
        String result = "Username: " + this.getUsername() + "\nFirst Name: " + this.getFirstName() + "\nLast Name: " + this.getLastName() + "\nRole: " + this.getRole() + "\nEmployee Id: " + this.employeeId;
        return result;
    }
}
