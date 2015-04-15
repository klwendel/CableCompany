/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaiii.wendel.cablecompany.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javaiii.wendel.cablecompany.validation.Validator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kaleb
 */
@WebServlet(name = "Login", urlPatterns =
{
    "/Login"
})
public class Login extends HttpServlet
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
            log("INFO: Login.java requested.");
            //Used to identify where the page is in the web application.
            log("INFO: Login.java contextPath: " + request.getContextPath());
            log("INFO: Login.java servletPath: " + request.getServletPath());
            log("INFO: Login.java requestURI: " + request.getRequestURI());
            log("INFO: Login.java requestURL: " + request.getRequestURL());
            //Used to determine if the data is valid.
            Boolean valid = true;
            //HashMap used to store error messages.
            HashMap<String, String> errorMap = new HashMap<>();
            //User object to be pssed in the request.
            User user = null;
            //Retrieves values entered by the user.
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            //Data validation.
            if(!Validator.isNullOrEmpty(username))
            {
                errorMap.put("username", "Username is required.");
                valid = false;
            }
            else if(!UserHandler.isValidUsername(username))
            {
                errorMap.put("username", "Username must be at least one character and less than 256");
                valid = false;
            }
            
            if(!Validator.isNullOrEmpty(password))
            {
                errorMap.put("password", "Password is required.");
                valid = false;
            }
            else if(!UserHandler.isValidPassword(password))
            {
                errorMap.put("password", "Password must be at least 8 characters and less than 40.");
                valid = false;
            }
            
            if(valid)
            {
                user = UserHandler.validateUser(username, password);
                if(user == null)
                {
                    errorMap.put("error", "Invalid username or password.\nPlease try again.");
                }
                else
                {
                    errorMap.put("valid","User details:");
                }
            }//End of if(valid)
            
            //Adding attributes to the request object.
            request.setAttribute("errorMap", errorMap);
            request.setAttribute("user", user);
            
            //Passes request back to AddUser.jsp
            String relativePath = "/Login.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(relativePath);
            rd.forward(request, response);
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
