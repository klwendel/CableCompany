/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
Change Log:
    Date:   4/6/2015
    Desc:   Commented out code regarding writing to a serialized file.
            Moved validation methods to the UserHandler class.
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
import javaiii.wendel.cablecompany.validation.*;

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
            //Retrieves values entered by the user.
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            
            //Data validation.
            if(!Validator.isNullOrEmpty(username))
            {
                errorMap.put("username", "Username is required");
                valid = false;
            }
            else if(!UserHandler.isValidUsername(username))
            {
                errorMap.put("username", "Username must be at least one character and less than 256");
                valid = false;
            }
            else if(!UserHandler.isValidUsernameCharacters(username))
            {
                errorMap.put("username", "Username must consist of only letters, numbers and/or underscores");
                valid = false;
            }
            
            if(!Validator.isNullOrEmpty(password))
            {
                errorMap.put("password", "Password is required");
                valid = false;
            }
            else if(!UserHandler.isValidPassword(password))
            {
                errorMap.put("password", "Password must be at least 8 characters and less than 60.");
                valid = false;
            }
            else if(!UserHandler.isValidPasswordCharacters(password))
            {
                errorMap.put("password", "Password must consist of only letters, numbers and/or underscores");
                valid = false;                
            }
            else if (!confirmPassword.equals(password))
            {
                errorMap.put("password", "Passwords do not match");
                valid = false;
            }
            
            if(!Validator.isNullOrEmpty(firstName))
            {
                errorMap.put("firstName", "First name is required");
                valid = false;
            }
            else if(!UserHandler.isValidFirstName(firstName))
            {
                errorMap.put("firstName", "First name must be at least 1 character and less than 128.");
                valid = false;
            }            
            else if(!UserHandler.isValidFirstNameCharacters(firstName))
            {
                errorMap.put("firstName", "First name must consist of only letters");
                valid = false;
            }
            
            if(!Validator.isNullOrEmpty(lastName))
            {
                errorMap.put("lastName", "Last name is required");
                valid = false;
            }
            else if(!UserHandler.isValidLastName(lastName))
            {
                errorMap.put("lastName", "Last name must be at least 1 character and less than 128.");
                valid = false;
            }
            else if(!UserHandler.isValidLastNameCharacters(lastName))
            {
                errorMap.put("lastName", "Last name must consist of only letters");
                valid = false;
            }
            
            if(valid)
            {
                //Create user object.
                newUser = new User(username, password, firstName, lastName);
                int userId = UserHandler.addUser(newUser);
                if(userId == -1)
                {
                    errorMap.put("error", "There was an error adding the user.\nPlease try again.");
                }
                else if(userId == -2)
                {
                    errorMap.put("error", "That username has already been used.\nPlease try again with a different username.");
                }
                else if(userId > 0)
                {
                    errorMap.put("valid","Successfully created user!");
                    newUser.setUserId(userId);
                }
            }//End of if(valid)
            
            //Adding attributes to the request object.
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