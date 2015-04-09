<%-- 
    Document   : createUser
    Created on : Feb 11, 2015, 10:33:39 PM
    Author     : Kaleb
--%>

<%--
Change Log:
    Date: 4/6/2015 - Kaleb Wendel
    Desc: Adjusted page to implement Java EL to make the code more concise.
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
        //If we have not reached the servlet the objects are instantiated to prevent null pointers.
        HashMap<String, String> errorMap = (HashMap<String, String>)request.getAttribute("errorMap");
        if(errorMap == null)
        {
            errorMap = new HashMap();
        }
        
        User user = (User)request.getAttribute("user");
        if(user == null)
        {
            user = new User();
        }
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
            if(errorMap.containsKey("valid"))
            {
        %>
        <h2>${errorMap["valid"]}</h2>
        <label class="label">User Id:</label>
        <label>${user.userId}</label><br>
        <label class="label">Username:</label>
        <label>${user.username}</label><br>
        <label class="label">First Name:</label>
        <label>${user.firstName}</label><br>
        <label class="label">Last Name:</label>
        <label>${user.lastName}</label><br>
        <%        
            }
            else
            {
        %>
        <h2 class="error">${errorMap.containsKey("error") ? errorMap["error"] : ""}</h2>
        <form id="addUser" name="addUser" action ="/WendelCableCompany/AddUser" method="get">
            <fieldset>
                <legend>New User Entry</legend>
                <label class="label" for="username">Username:</label>
                <input id="username" name="username" type="text" value="${param.username}" />
                <label class="error" for="username">${errorMap.containsKey("username") ? errorMap["username"] : ""}</label><br>
                
                <label class="label" for="password">Password:</label>
                <input id="password" name="password" type="password" value="" />
                <label class="error" for="password">${errorMap.containsKey("password") ? errorMap["password"] : ""}</label><br>
                
                <label class="label" for="confirmPassword">Confirm Password:</label>
                <input id="confirmPassword" name="confirmPassword" type="password" value="" />
                <label class="error" for="confirmPassword">${errorMap.containsKey("password") ? errorMap["password"] : ""}</label><br>
                
                <label class="label" for="firstName">First Name:</label>
                <input id="firstName" name="firstName" type="text" value="${param.firstName}" />
                <label class="error" for="firstName">${errorMap.containsKey("firstName") ? errorMap["firstName"] : ""}</label><br>
                
                <label class="label" for="lastName">Last Name:</label>
                <input id="lastName" name="lastName" type="text" value="${param.lastName}" />
                <label class="error" for="lastName">${errorMap.containsKey("lastName") ? errorMap["lastName"] : ""}</label><br>
                <input type="submit" value="Submit">
                <%
            }//end of if(errorMap.containsKey("valid"))
                %>
            </fieldset>
        </form>
    </div><%-- end of content div --%>
    <%@ include file="../includes/footer.html" %>