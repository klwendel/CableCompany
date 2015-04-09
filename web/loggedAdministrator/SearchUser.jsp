<%-- 
    Document   : SearchUser
    Created on : Apr 8, 2015, 9:01:39 PM
    Author     : Kaleb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="javaiii.wendel.cablecompany.user.*, java.util.*"%>
<!DOCTYPE html>
<html>
    <%
        log("INFO: SearchUser.jsp requested.");
        //Used to identify where the page is in the web application.
        log("INFO: SearchUser.jsp contextPath: " + request.getContextPath());
        log("INFO: SearchUser.jsp servletPath: " + request.getServletPath());
        log("INFO: SearchUser.jsp requestURI: " + request.getRequestURI());
        log("INFO: SearchUser.jsp requestURL: " + request.getRequestURL());
        
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
            <li><a href="/WendelCableCompany/loggedAdministrator/AddUser.jsp">Add User</a></li>
        </ul>
    </div><%-- end of navigation div --%>
    <div id="content">
        <%
            //Tests to determine if the entry was valid.
            if(errorMap.containsKey("valid"))
            {
        %>
        <h2>${errorMap["valid"]}</h2>
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
        <form id="searchUser" name="searchUser" action ="/WendelCableCompany/SearchUser" method="get">
            <fieldset>
                <legend>Find User By User Id:</legend>
                <label class="label" for="userId">User Id:</label>
                <input id="userId" name="userId" type="text" value="${param.userId}" />
                <label class="error" for="userId">${errorMap.containsKey("userId") ? errorMap["userId"] : ""}</label><br>
                <input type="submit" value="Submit">
        <%
            }//end of if(errorMap.containsKey("valid"))
        %>
            </fieldset>
        </form>
    </div><%-- end of content div --%>
    <%@ include file="../includes/footer.html" %>