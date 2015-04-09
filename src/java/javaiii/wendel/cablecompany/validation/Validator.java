/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
Change Log:
    Date: 4/8/2015 - Kaleb Wendel
    Desc: Added isValidInteger method.
*/
package javaiii.wendel.cablecompany.validation;

/**
 *
 * @author Kaleb
 */
public class Validator
{
    public static boolean isNullOrEmpty(String value)
    {
        boolean valid = false;
        if(value != null && !value.isEmpty())
        {
            valid = true;
        }
        return valid;
    }
    
    public static boolean isValidLength(String value, int minLength, int maxLength)
    {
        boolean valid = false;
        if(value.length() <= maxLength && value.length() >= minLength)
        {
            valid = true;
        }
        return valid;
    }
    
    public static boolean isValidInteger(String value)
    {
        boolean valid = false;
        try
        {
            Integer.parseInt(value);
            valid = true;
        }
        catch(NumberFormatException ex)
        {
            System.out.println("There was an error parsing the userId to an integer value.");
            ex.printStackTrace();
        }
        return valid;
    }
}
