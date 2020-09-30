<%-- 
    Document   : logOut
    Created on : 14 sept. 2020, 12:23:58
    Author     : Jesus Cruz
--%>

<%@page import="com.mismarcasderuning.model.User"%>
<%
    session.setAttribute("currentUser", new User());
    response.sendRedirect("index.jsp");
    





























%>
