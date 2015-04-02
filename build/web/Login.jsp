<%-- 
    Document   : userLogin
    Created on : Feb 15, 2015, 2:18:06 PM
    Author     : Kaleb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="javaiii.wendel.cablecompany.user.*"%>
<!DOCTYPE html>
<html>
    <%@ include file="../includes/header.html" %>
    <div id="navigationMenu">
        <ul id="navigation">
            <li><a href="/WendelCableCompany/index.jsp">Home</a></li>
            <li><a href="/WendelCableCompany/loggedAdministrator/AddUser.jsp">Create New User</a></li>
        </ul>
    </div><%-- end of navigation div --%>
    <div id="content">
        <%
            //Instantiate UserHandler.
            UserHandler userHandler = new UserHandler();
            //Store request parameters.
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            //Variable used to display additional information during development.
            boolean devStatus = false;
            //Display list of current users.
            if(devStatus == true)
            {
        %>
        <p>***************DEV VARIABLES***************</p>
        <%
                for(User user: userHandler.getUserList())
                {
        %>
        <p><%= user.getUsername() %></p>
        <p><%= user.getFirstName() %></p>
        <p><%= user.getLastName() %></p>
        <p><%= user.getRole() %></p>
        <p>----------End of User----------</p>
        <%
                }
        %>
        <p>*************END DEV VARIABLES*************</p>        
        <%
            }
            //Verify if user exists.
            User user = userHandler.verifyCredentials(new User(username, password));
            if(user != null)
            {
                if(user instanceof Administrator)
                {
        %>
        <h2>Welcome back, <%= user.getUsername() %>!</h2>
        <h3>Below is information in your user record:</h3>
        <label class="label">Username:</label>
        <label><%= ((Administrator)user).getUsername() %></label><br>
        <label class="label">First Name:</label>
        <label><%= ((Administrator)user).getFirstName() %></label><br>
        <label class="label">Last Name:</label>
        <label><%= ((Administrator)user).getLastName() %></label><br>
        <label class="label">Role:</label>
        <label><%= userHandler.roleMap.get(((Administrator)user).getRole()) %></label><br>
        <label class="label">Employee Id:</label>
        <label><%= ((Administrator)user).getEmployeeId() %></label><br>
        <%
                }
                else if(user instanceof Customer )
                {
        %>
        <h2>Welcome back, <%= user.getUsername() %>!</h2>
        <h3>Below is information in your user record:</h3>
        <label class="label">Username:</label>
        <label><%= ((Customer)user).getUsername() %></label><br>
        <label class="label">First Name:</label>
        <label><%= ((Customer)user).getFirstName() %></label><br>
        <label class="label">Last Name:</label>
        <label><%= ((Customer)user).getLastName() %></label><br>
        <label class="label">Account Number:</label
        <label><%= ((Customer)user).getAccountNumber() %></label><br>
        <label class="label">Role:</label>
        <label><%= userHandler.roleMap.get(((Customer)user).getRole()) %></label><br>
        <label class="label">Address1:</label>
        <label><%= ((Customer)user).getAddress1() %></label><br>
        <label class="label">Address2:</label>
        <label><%= ((Customer)user).getAddress2() %></label><br>
        <label class="label">Address3:</label>
        <label><%= ((Customer)user).getAddress3() %></label><br>
        <label class="label">City:</label>
        <label><%= ((Customer)user).getCity() %></label><br>
        <label class="label">State:</label>
        <label><%= ((Customer)user).getState() %></label><br>
        <label class="label">Zip:</label>
        <label><%= ((Customer)user).getZip() %></label><br>
        <label class="label">Email:</label>
        <label><%= ((Customer)user).getEmailAddress() %></label><br>
        <label class="label">Contact Phone:</label>
        <label><%= ((Customer)user).getContactPhone() %></label><br>
        <%                            
                }
            }
            else
            {
        %>
            <h3 class="error">Invalid username and/or password. Please verify you are using the correct username and password.</h3>
            <form id="login2" name="login" action ="" method="post">
                <fieldset>
                    <legend>User Login</legend>
                    <label class="login" for="username">Username:</label>
                    <input class="login" type="text" id="username" name="username"></input><br>
                    <label class="login" for="password">Password:</label>
                    <input class="login" type="password" id="password" name="password"></input><br>
                    <input type="submit" value="Login">
                    <a class="login2" href="RecoverUser.jsp" title="Reset Password">Forgot Password?</a>
                </fieldset>
            </form>
        <%            
            }
        %>
    </div><%-- end of content div --%>
    <%@ include file="includes/footer.html" %>