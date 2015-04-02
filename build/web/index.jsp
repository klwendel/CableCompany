<%-- 
    Document   : index.jsp
    Created on : Feb 11, 2015, 1:17:32 PM
    Author     : Kaleb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="javaiii.wendel.cablecompany.user.*, java.io.*, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <%@ include file="includes/headerWithLogin.html" %>
    <div id="navigationMenu">
        <ul id="navigation">
            <li><a href="/WendelCableCompany/loggedAdministrator/AddUser.jsp">Create New User</a></li>
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
            
            //Used to read from the .ser file.
            //Need to convert the relative path to absolute.
            String relativeSerPath = "/dataStorage/users.ser";
            String absoluteSerPath = this.getServletContext().getRealPath(relativeSerPath);
            log("INFO: index.jsp | absolute file patht to users.ser: " + absoluteSerPath);
            
            //Streams to read data.
            FileInputStream fileInput = null;
            ObjectInputStream objectInput = null;
            
            //Array to hold list of user objects.
            ArrayList<User> userList = new ArrayList();
            
            //Read data from the file.
            if(new File(absoluteSerPath).exists())
            {
                log("INFO: index.jsp | users.ser file exists.");
                //Clear userList.
                userList.clear();
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
                            log("INFO: User array list read from file.");
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
            
            //Display users.
            if(userList.size() > 0)
            {
        %>
        <h2>Stored users:</h2>
        <%
                for(User user: userList)
                {
        %>
            <label class="label">Username:</label>
            <label><%= user.getUsername() %></label><br>
            <label class="label">First Name:</label>
            <label><%= user.getFirstName() %></label><br>
            <label class="label">Last Name:</label>
            <label><%= user.getLastName() %></label><br><br>
        <%
                }
            }
            else
            {
        %>
        <h2>There are currently no users stored in the application.</h2>
        <%
            }// end of if(userList.size() > 0)
        %>
    </div><%-- end of content div --%>
    <%@ include file="includes/footer.html" %>