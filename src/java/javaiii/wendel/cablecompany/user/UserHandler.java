/**
 *
 * @author Kaleb
 * @created on: 2/11/2015
 */
package javaiii.wendel.cablecompany.user;
import java.util.*;

public class UserHandler
{
    //HashMap used to identify role values.
    public HashMap<Integer, String> roleMap;
    //Array list to hold list of users.
    private ArrayList<User> userList;
    
    public UserHandler()
    {
        //Fills roleMap with roles as a substitution for a lookup table.
        roleMap = new HashMap();
        roleMap.put(1, "Administrator");
        roleMap.put(2, "Customer");
        //Fills list of users with hardcoded values.
        userList = new ArrayList();
        userList.add(new Administrator("admin","adpassword", 1, "Kaleb", "Wendel", "00001"));
        userList.add(new Customer("larry","howdy01", 2, "Test", "Customer", 100200, "123 Fake Avenue", "", "", "Cedar Rapids", "IA", "52403", "test@test.com", "3190000000"));
    }
    
    //Returns current list of users.
    public ArrayList<User> getUserList()
    {
        return userList;
    }

    //Verify if user exists.
    //Returns true or false if the username is found.
    public boolean exists(String username)
    {
        boolean exists = false;
        for(User user: userList)
        {
            if(user.getUsername().equals(username))
            {
                exists = true;
            }
        }
        return exists;
    }
    
    //Verify users credentials.
    //Returns the User object if username/password values match. 
    public User verifyCredentials(User loginUser)
    {
        for(User user: userList)
        {
            if(loginUser.getUsername().equals(user.getUsername()))
            {
                if(loginUser.getPassword().equals(user.getPassword()))
                {
                    return user;
                }
            }
        }
        return null;
    }
}
