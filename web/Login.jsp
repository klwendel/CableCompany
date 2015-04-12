<%-- 
    Document   : userLogin
    Created on : Feb 15, 2015, 2:18:06 PM
    Author     : Kaleb
--%>
<%--
Change Log:
    Date: 4/12/15 - Kaleb
    Desc: Adjusted class to adhere to MVC design patter.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="javaiii.wendel.cablecompany.user.*, java.util.*"%>
<!DOCTYPE html>
<html>
       <%
        log("INFO: Login.jsp requested.");  
        //Used to identify where the page is in the web application.
        log("INFO: Login.jsp contextPath: " + request.getContextPath());
        log("INFO: Login.jsp servletPath: " + request.getServletPath());
        log("INFO: Login.jsp requestURI: " + request.getRequestURI());
        log("INFO: Login.jsp requestURL: " + request.getRequestURL());
        
        //Getting request attributes added in Login.java servlet processing.
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
            <li><a href="/WendelCableCompany/loggedAdministrator/AddUser.jsp">Create New User</a></li>
            <li><a href="/WendelCableCompany/loggedAdministrator/SearchUser.jsp">Find User</a></li>
        </ul>
    </div><%-- end of navigation div --%>
    <div id="content">
        <%
            //Tests to determine if the entry was valid.
            if(errorMap.containsKey("valid"))
            {
        %>
        <h3>Welcome, ${user.firstName} ${user.lastName}!</h3>
        <h3>${errorMap["valid"]}</h3>
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
        <h3 class="error">${errorMap.containsKey("error") ? errorMap["error"] : ""}</h3>
        <form id="login2" name="login2" action ="/WendelCableCompany/Login" method="get">
            <fieldset>
                <legend>User Login:</legend>
                <label class="label" for="username">Username:</label>
                <input id="username" name="username" type="text" value="${param.username}" />
                <label class="error" for="username">${errorMap.containsKey("username") ? errorMap["username"] : ""}</label><br>
                
                <label class="label" for="password">Password:</label>
                <input id="password" name="password" type="password" value="" />
                <label class="error" for="password">${errorMap.containsKey("password") ? errorMap["password"] : ""}</label><br>
                <input type="submit" value="Login">
                <%
            }//end of if(errorMap.containsKey("valid"))
                %>
            </fieldset>
        </form>
    </div><%-- end of content div --%>
    <%@ include file="includes/footer.html" %>