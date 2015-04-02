/**
 *
 * @author Kaleb
 * @dateModified 2/11/2015
 */
package javaiii.wendel.cablecompany.user;

public class Customer extends User
{
    //Instance variables for Customers.
    private int accountNumber;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private String zip;
    private String emailAddress;
    private String contactPhone;

    //Constructor
    public Customer()
    {
        
    }
    
    public Customer(String username, String password, int role, String firstName, String lastName, int accountNumber, String address1, String address2, String address3, String city, String state, String zip, String email, String contactPhone)
    {
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(role);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.accountNumber = accountNumber;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.emailAddress = email;
        this.contactPhone = contactPhone;
    }
    
    /**
     * @return the accountNumber
     */
    public int getAccountNumber()
    {
        return accountNumber;
    }
    
    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(int accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the address1
     */
    public String getAddress1()
    {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1)
    {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2()
    {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2)
    {
        this.address2 = address2;
    }

    /**
     * @return the address3
     */
    public String getAddress3()
    {
        return address3;
    }

    /**
     * @param address3 the address3 to set
     */
    public void setAddress3(String address3)
    {
        this.address3 = address3;
    }

    /**
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState()
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public String getZip()
    {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip)
    {
        this.zip = zip;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress()
    {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the contactPhone
     */
    public String getContactPhone()
    {
        return contactPhone;
    }

    /**
     * @param contactPhone the contactPhone to set
     */
    public void setContactPhone(String contactPhone)
    {
        this.contactPhone = contactPhone;
    }
}
