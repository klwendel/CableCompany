/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
}
