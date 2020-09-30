<%-- 
    Document   : logIn
    Created on : 12 sept. 2020, 10:53:28
    Author     : Jesus Cruz
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="com.mismarcasderuning.model.Management"%>
<%
    try {
        Management m = new Management();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String loginError = m.login(username, password);

        if (!loginError.equals("")) {
            session.setAttribute("loginError", loginError);
            response.sendRedirect("logInPage.jsp");
        } else {
            session.setAttribute("currentUser", m.getUser(username));
            response.sendRedirect("index.jsp");
        }
    } catch (SQLException e) {
        response.sendRedirect("error.jsp");
    } catch (NullPointerException e) {
        response.sendRedirect("error.jsp");
    } catch (ClassNotFoundException e) {
        response.sendRedirect("error.jsp");
    }

%>
