<%-- 
    Document   : createUser
    Created on : Feb 11, 2015, 10:33:39 PM
    Author     : Kaleb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="javaiii.wendel.cablecompany.user.*, java.util.*"%>
<!DOCTYPE html>
<html>
    <%
        log("INFO: AddUser.jsp requested.");
        //Used to identify where the page is in the web application.
        log("INFO: AddUser.jsp contextPath: " + request.getContextPath());
        log("INFO: AddUser.jsp servletPath: " + request.getServletPath());
        log("INFO: AddUser.jsp requestURI: " + request.getRequestURI());
        log("INFO: AddUser.jsp requestURL: " + request.getRequestURL());
        
        //Getting request attributes added in AddUser.java servlet processing.
        HashMap<String, String> errorMap = (HashMap<String, String>)request.getAttribute("errorMap");
        User user = (User)request.getAttribute("user");
        Boolean valid = (Boolean)request.getAttribute("valid");
        
        //Used to display data entered by the user. 
        String username = "";
        String password = "";
        String confirmPassword = "";
        String firstName = "";
        String lastName = "";
        
        if(user == null)
        {
            if(request.getParameter("username") != null)
            {
                username = request.getParameter("username");
            }//end of if(request.getParameter("username") != null)
            
            if(request.getParameter("firstName") != null)
            {
                firstName = request.getParameter("firstName");
            }//end of if(request.getParameter("firstName") != null)
            
            if(request.getParameter("lastName") != null)
            {
                lastName = request.getParameter("lastName");                
            }//end of if(request.getParameter("lastName") != null)

            if(errorMap != null && !errorMap.containsKey("password"))
            {
                password = request.getParameter("password");
                confirmPassword = request.getParameter("confirmPassword");
            }//end of if(errorMap != null && !errorMap.containsKey("password"))
        }
        else
        {
            username = user.getUsername();
            password = user.getPassword();
            confirmPassword = user.getPassword();
            firstName = user.getFirstName();
            lastName = user.getLastName();
        }//end of if(user == null) 
    %>
    <%@ include file="../includes/header.html" %>
    <div id="navigationMenu">
        <ul id="navigation">
            <li><a href="/WendelCableCompany/index.jsp">Home</a></li>
        </ul>
    </div><%-- end of navigation div --%>
    <div id="content">
        <%
            //Tests to determine if the entry was valid.
            if(errorMap != null && errorMap.containsKey("valid"))
            {
        %>
        <h2><%= errorMap.get("valid") %></h2>
        <label class="label">Username:</label>
        <label><%= username %></label><br>
        <label class="label">First Name:</label>
        <label><%= firstName %></label><br>
        <label class="label">Last Name:</label>
        <label><%= lastName %></label><br>
        <%        
            }
            else
            {
        %>
        <form id="addUser" name="addUser" action ="/WendelCableCompany/AddUser" method="get">
            <fieldset>
                <legend>New User Entry</legend>
                <label class="label" for="username">Username:</label>
                <input id="username" name="username" type="text" value="<%= username %>" />
                <%
                    //Add's error label if the data was not valid.
                    if(errorMap != null && errorMap.containsKey("username"))
                    {
                %>
                <label class="error" for="username"><%= errorMap.get("username")%></label><br>
                <%
                    }
                    else
                    {
                %>
                <br>
                <%
                    }//end of if(errorMap != null && errorMap.containsKey("username")) 
                %>
                <label class="label" for="password">Password:</label>
                <input id="password" name="password" type="password" value="<%= password %>" />
                <%
                    //Add's error label if the data was not valid.
                    if(errorMap != null && (errorMap.containsKey("password") || errorMap.containsKey("confirmPassword")))
                    {
                %>
                <label class="error" for="password"><%= errorMap.get("password")%></label><br>
                <%
                    }
                    else
                    {
                %>
                <br>
                <%
                    }//end of if(errorMap != null && errorMap.containsKey("password"))
                %>
                <label class="label" for="confirmPassword">Confirm Password:</label>
                <input id="confirmPassword" name="confirmPassword" type="password" value="<%= confirmPassword %>" />
                <%
                    //Add's error label if the data was not valid.
                    if(errorMap != null && (errorMap.containsKey("confirmPassword") || errorMap.containsKey("password")))
                    {
                %>
                <label class="error" for="confirmPassword"><%= errorMap.get("confirmPassword")%></label><br>
                <%
                    }
                    else
                    {
                %>
                <br>
                <%
                    }//end of if(errorMap != null && errorMap.containsKey("confirmPassword"))
                %>
                <label class="label" for="firstName">First Name:</label>
                <input id="firstName" name="firstName" type="text" value="<%= firstName %>" />
                <%
                    //Add's error label if the data was not valid.
                    if(errorMap != null && errorMap.containsKey("firstName"))
                    {
                %>
                <label class="error" for="firstName"><%= errorMap.get("firstName")%></label><br>
                <%
                    }
                    else
                    {
                %>
                <br>
                <%
                    }//end of if(errorMap != null && errorMap.containsKey("firstName"))
                %>
                <label class="label" for="lastName">Last Name:</label>
                <input id="lastName" name="lastName" type="text" value="<%= lastName %>" />
                <%
                    //Add's error label if the data was not valid.
                    if(errorMap != null && errorMap.containsKey("lastName"))
                    {
                %>
                <label class="error" for="lastName"><%= errorMap.get("lastName")%></label><br>
                <%
                    }
                    else
                    {
                %>
                <br>
                <%
                    }//end of if(errorMap != null && errorMap.containsKey("lastName"))
                %>
                <input type="submit" value="Submit">
                <%
            }//end of if(errorMap.containsKey("valid"))
                %>
            </fieldset>
        </form>
    </div><%-- end of content div --%>
    <%@ include file="../includes/footer.html" %>