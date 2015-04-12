<%-- 
    Document    : index.jsp
    Created on  : Feb 11, 2015, 1:17:32 PM
    Author      : Kaleb
--%>
<%--
    Change log  :
        Date: 4/6/2015
        Desc: Deleted previous work regarding reading/writing users to a serialized file.

        Date: 4/8/2015 - Kaleb Wendel
        Desc: Adjusted the navigation bar to include the SearchUser form.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="javaiii.wendel.cablecompany.user.*, java.io.*, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <%@ include file="includes/headerWithLogin.html" %>
    <div id="navigationMenu">
        <ul id="navigation">
            <li><a href="/WendelCableCompany/loggedAdministrator/AddUser.jsp">Create New User</a></li>
            <li><a href="/WendelCableCompany/loggedAdministrator/SearchUser.jsp">Find User</a></li>
        </ul>
    </div><%-- end of navigation div --%>
    <div id="content">
        <%
            log("INFO: index.jsp requested.");
            //Used to identify where the page is in the web application.
            log("INFO: index.jsp contextPath: " + request.getContextPath());
            log("INFO: index.jsp servletPath: " + request.getServletPath());
            log("INFO: index.jsp requestURI: " + request.getRequestURI());
            log("INFO: index.jsp requestURL: " + request.getRequestURL());
        %>
    </div><%-- end of content div --%>
    <%@ include file="includes/footer.html" %>