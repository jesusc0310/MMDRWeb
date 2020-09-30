<%-- 
    Document   : profile
    Created on : 29 sept. 2020, 14:19:29
    Author     : Jesus Cruz
--%>

<%@page import="com.mismarcasderuning.model.Run"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mismarcasderuning.model.User"%>
<%@page import="com.mismarcasderuning.model.Management"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Perfil | Mis Marcas de Running</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
        <link rel="stylesheet" href="assets/css/main.css">
    </head>
    <body class="landing is-preload">

        <%
            try {
                Management m = new Management();
                User user = (User) session.getAttribute("currentUser");
        %>

        <div id="page-wrapper">

            <!-- Header -->
            <header id="header">
                <h1><a href="index.jsp" >MIS MARCAS DE RUNNING</a> | &nbsp;
                    <%=m.getNUsers() + " usuarios."%>
                </h1>
                <nav id="nav">
                    <ul>
                        <li><a href="index.jsp">Inicio</a></li>
                        <li>
                            <a class="icon solid fa-angle-down">Opciones</a>
                            <ul>
                                <li><a href="generic.html">Buscar usuarios</a></li>
                                <li><a href="contact.html">Ver carreras</a>
                                    <ul>
                                        <%
                                            ArrayList<Run> runs = m.getRuns();

                                            for (int i = 0; i < 5; i++) {

                                                out.println("<li><a href=\"#\">" + runs.get(i).getName() + "</a></li>");
                                            }

                                        %>
                                    </ul>
                                </li>
                                <li><a href="generic.html">Ver marcas publicas</a></li>
                                <li><a href="getMarks.jsp">Ver mis marcas</a></li>
                                <li><a href="addMarkPage.jsp">Insertar una marca</a></li>
                                    <%=(user.getId() == 0
                                            ? "<li><a href=\"addRun.jsp\">Añadir una carrera</a></li>" : "")%>

                            </ul>
                        </li>
                        <%
                            if (user.getId() == -1) {
                                out.print("<li><a href=\"signUpPage.jsp\" class=\"button primary\">Resgistrarse</a></li>"
                                        + "<li><a href=\"logInPage.jsp\" class=\"button\">Iniciar sesión</a></li>");
                            } else {
                                out.print("<li><a href=\"profile.jsp\">"
                                        + user.getUsername() + "</a>"
                                        + "<ul>"
                                        + "<li><a href=\"profile.jsp\">Mi perfil</a></li>"
                                        + "<li><a href=\"modifyData.jsp\">Cambiar mis datos</a></li>"
                                        + "<li><a href=\"logOut.jsp\">Cerrar Sesión</a></li>"
                                        + "</ul>"
                                        + "</li>");
                            }
                        %>
                    </ul>
                </nav>
            </header>

            <!-- Banner -->
            <section id="banner">
            </section>

            <!-- Main -->
            <section id="main" class="container">
                <section class="box">
                    <h2 class="special">Mi perfil</h2>
                </section>
            </section>
            
            <footer id="footer">
                <ul class="icons">
                    <li><a href="https://twitter.com/Yiisus0310" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
                    <li><a href="https://www.facebook.com/jesus.cruzmunoz.12" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
                    <li><a href="https://www.instagram.com/jesusc.dev/" class="icon brands fa-instagram"><span class="label">Instagram</span></a></li>
                    <li><a href="https://github.com/jesusc0310/" class="icon brands fa-github"><span class="label">Github</span></a></li>
                </ul>
                <ul class="copyright">
                    <li>&copy; Mis Marcas de Running. All rights reserved.</li><li>Design: <a href="https://github.com/jesusc0310/">JesusC0310</a></li>
                </ul>
            </footer>
        </div>
        <%
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.html");
            } catch (Error er) {
                er.printStackTrace();
            }
        %>
    </body>
</html>
