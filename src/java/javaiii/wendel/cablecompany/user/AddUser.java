/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaiii.wendel.cablecompany.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.RequestDispatcher;
import java.io.*;

/**
 *
 * @author Kaleb
 */
@WebServlet(name = "AddUser", urlPatterns =
{
    "/AddUser"
})
public class AddUser extends HttpServlet
{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            log("INFO: AddUser.java requested.");
            //Used to identify where the page is in the web application.
            log("INFO: AddUser.java contextPath: " + request.getContextPath());
            log("INFO: AddUser.java servletPath: " + request.getServletPath());
            log("INFO: AddUser.java requestURI: " + request.getRequestURI());
            log("INFO: AddUser.java requestURL: " + request.getRequestURL());
            //Used to determine if the data is valid.
            Boolean valid = true;
            
            //HashMap used to store error messages.
            HashMap<String, String> errorMap = new HashMap<>();
            
            //User object to be passed in the request.
            User newUser = null;
            
            //Used to write the user to the .ser file.       
            //Need to conver the relative path to absolute.
            String relativeSerPath = "/dataStorage/users.ser";
            String absoluteSerPath = this.getServletContext().getRealPath(relativeSerPath);
            
            //Streams used to read data.
            FileInputStream fileInput = null;
            ObjectInputStream objectInput = null;
            
            //Streams used to write data.
            FileOutputStream fileOutput = null;
            ObjectOutputStream objectOutput = null;
            
            //Array to hold list of user objects.
            ArrayList<User> userList = new ArrayList();
            
            //Retrieves values entered by the user.
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
          
            //Data validation.
            
            if(username.equals("") || username == null)
            {
                errorMap.put("username", "Username is required");
                valid = false;
            }
            else if(username.length() < 2 || username.length() > 60)
            {
                errorMap.put("username", "Username must be greater than 2 characters but less than 60");
                valid = false;
            }
            //Regex expression test.
            //String.matches() internally uses Pattern and Matcher.
            //The pattern below matches letters, numbers and underscores
            //(^ = start of line | [\\p{Alnum}] = letters and numbers | {2,60} = min,max | $ = end of line | [] = beginning/end of character group)
            else if(!username.matches("^[\\p{Alnum}]{2,60}$"))
            {
                errorMap.put("username", "Username must consist of only letters and/or numbers");
                valid = false;
            }
            
            if(password.equals("") || password == null)
            {
                errorMap.put("password", "Password is required");
                errorMap.put("confirmPassword", "Password is required");
                valid = false;
            }
            else if(password.length() < 6 || password.length() > 60)
            {
                errorMap.put("password", "Password must be greater than 6 characters but less than 60");
                errorMap.put("confirmPassword", "Password must be greater than 6 characters but less than 60");
                valid = false;
            }
            else if (!confirmPassword.equals(password))
            {
                errorMap.put("password", "Password does not match");
                errorMap.put("confirmPassword", "Password does not match");
                valid = false;
            }
            //(^ = start of line | [\\w] = letters, numbers and underscore | {6,60} = min,max | $ = end of line | [] = beginning/end of character group)
            else if(!password.matches("^[\\w]{6,60}$"))
            {
                errorMap.put("password", "Password must consist of only letters, numbers and/or underscores");
                errorMap.put("confirmPassword", "Password must consist of only letters, numbers and/or underscores");
                valid = false;                
            }
            
            if(firstName.equals("") ||  firstName == null)
            {
                errorMap.put("firstName", "First name is required");
                valid = false;
            }
            else if(firstName.length() < 2 || firstName.length() > 60)
            {
                errorMap.put("firstName", "First name must be greater than 2 characters but less than 60");
                valid = false;
            }            
            //(^ = start of line | [\\p{Alpha] = letters | {2,60} = min,max | $ = end of line | [] = beginning/end of character group)
            else if(!firstName.matches("^[\\p{Alpha}]{2,60}$"))
            {
                errorMap.put("firstName", "First name must consist of only letters");
                valid = false;
            }
            
            if(lastName.equals("")|| lastName == null)
            {
                errorMap.put("lastName", "Last name is required");
                valid = false;
            }
            else if(lastName.length() < 2 || lastName.length() > 60)
            {
                errorMap.put("lastName", "Last name must be greater than 2 characters but less than 60");
                valid = false;
            }
            //(^ = start of line | [\\p{Alpha] = letters | {2,60} = min,max | $ = end of line | [] = beginning/end of character group)
            else if(!lastName.matches("^[\\p{Alpha}]{2,60}$"))
            {
                errorMap.put("lastName", "Last name must consist of only letters");
                valid = false;
            }
            
            if(valid)
            {
                //Create user object.
                newUser = new User(username, password, firstName, lastName);
                errorMap.put("valid","Successfully created user!");

                //Clear userList.
                userList.clear();
                
                //Read data from the file.
                if(new File(absoluteSerPath).exists())
                {
                //Read data from the file and populate user array list.
                    try
                    {
                        //Create streams to read from .ser file.
                        fileInput = new FileInputStream(absoluteSerPath);
                        objectInput = new ObjectInputStream(fileInput);

                        try
                        {
                            //Read data from .ser file and place within the array list.
                            //Reads until EOFException.
                            while(true)
                            {
                                userList = (ArrayList<User>)objectInput.readObject();
                                log("INFO: Array list read from file.");
                            }
                        }
                        catch (ClassNotFoundException ex) 
                        {
                            log("ERROR: The class [User] was not found and the data was not read.");
                        }
                        catch (EOFException ex) 
                        {
                            log("INFO: The end of the file [users.ser] was met.");
                        }
                    }
                    catch(FileNotFoundException ex)
                    {
                        log("ERROR: The input file [users.ser] was not found.");
                    }
                    catch(IOException ex)
                    {
                     log("ERROR: There was an error reading data from the file [users.ser]." + ex.hashCode());
                    }
                    finally
                    {
                        try
                        {
                            objectInput.close();
                        }
                        catch(IOException ex)
                        {
                            log("ERROR: There was an error closing the ObjectInputStream [objectInput].");
                        }
                        catch(NullPointerException ex)
                        {
                            log("ERROR: The ObjectInputStream [objectInput] was null.");
                        }
                    }
                }//End of if(new File(absoluteSerPath).exists())
                //Write data to the file.
                try
                {
                    //Create streams to output data to serialized file.
                    fileOutput = new FileOutputStream(absoluteSerPath);
                    objectOutput = new ObjectOutputStream(fileOutput);

                    //Add new user to array list.
                    userList.add(newUser);
                    log("INFO: Username added to userList: " + newUser.getUsername());
                    log("INFO: Objects in userList: " + userList.size());

                    //Write array list to the file.
                    objectOutput.writeObject(userList);
                    log("INFO: userList written to the file.");
                }
                catch(IOException ex)
                {
                    log("ERROR: There was an error writing the new User to the disk.");
                }
                finally
                {
                    try
                    {
                        objectOutput.close();
                    }
                    catch(IOException ex)
                    {
                        log("ERROR: There was an error closing the ObjectOutputStream[objectOutput].");
                    }  
                }
            }//End of if(valid)
            
            //Adding attributes to the request object.
            request.setAttribute("valid", valid);
            request.setAttribute("errorMap", errorMap);
            request.setAttribute("user", newUser);
            
            //Passes request back to AddUser.jsp
            String relativePath = "/loggedAdministrator/AddUser.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(relativePath);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>
}