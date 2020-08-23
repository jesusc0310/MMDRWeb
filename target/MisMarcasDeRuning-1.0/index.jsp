<%-- 
    Document   : index
    Created on : 10 ago. 2020, 23:42:37
    Author     : Jesus Cruz
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="com.mismarcasderuning.model.Run"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mismarcasderuning.model.Management"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
           Management m = new Management();
            
            ArrayList<Run> runs = m.getRuns();
            
            for (Run r : runs) {
                System.out.println("<h1>" + r.getId() + "</h1>");
            }
            
            m.close();
        %>
    </body>
</html>
