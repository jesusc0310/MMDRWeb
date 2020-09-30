<%-- 
    Document   : index
    Created on : 10 ago. 2020, 23:42:37
    Author     : Jesus Cruz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.io.IOException"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.util.Properties"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.mismarcasderuning.model.User"%>
<%@page import="com.mismarcasderuning.model.Run"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mismarcasderuning.model.Management"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Inicio | Mis Marcas de Running</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
        <link rel="stylesheet" href="assets/css/main.css">
    </head>
    <body class="landing is-preload">
        <%

            try {
                Management m = new Management();

                User user = new User();

                if (session.isNew()) {
                    session.setAttribute("currentUser", user);
                } else {
                    user = (User) session.getAttribute("currentUser");
                }

                if (user.getId() != -1 && !user.isVerified()) {
                    response.sendRedirect("verificationPage.jsp");
                }
        %>

        <div id="page-wrapper">
            <!-- Header -->
            <header id="header" class="alt">
                <h1><a href="#" >MIS MARCAS DE RUNNING</a> | &nbsp;
                    <%=m.getNUsers() + " usuarios."%>
                </h1>
                <nav id="nav">
                    <ul>
                        <li><a href="#">Inicio</a></li>
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
                                    <%                                        
                                        if (user.getId() != -1) {
                                            out.print("<li><a href=\"getMarks.jsp\">Ver mis marcas</a></li>");
                                            out.print("<li><a href=\".jsp\">Insertar una marca</a></li>");

                                            if (user.getId() == 0) {
                                                out.print("<li><a href=\"addRun.jsp\">Añadir una carrera</a></li>");
                                            }
                                        }
                                    %>
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
                <h2>Mis marcas de running</h2>
                <p>El lugar donde guardar tus marcas como todo un corredor profesional.</p>

                <%
                    if (user.getId() == -1) {
                %>

                <ul class="actions special">
                    <li><a href="logInPage.jsp" class="button primary">Iniciar sesión</a></li>
                    <li><a href="signUpPage.jsp" class="button">Registrarse</a></li>
                </ul>

                <%} else {%>

                <ul class="actions special">
                    <li>
                        <form method="post" action="findUserPage.jsp">
                            <div class="row gtr-50 gtr-uniform">
                                <div class="col-8 col-7-mobilep">
                                    <input type="query" name="user" id="query" placeholder="Encuentra usuarios" />
                                </div>
                                <div class="col-2 col-2-mobilep">
                                    <span class="actions">
                                    <input type="submit" class="button special" value="&#128269;">
                                    </span>
                                </div>

                            </div>
                        </form>
                    </li>
                </ul>

                <%}%>


            </section>

            <!-- Main -->
            <section id="main" class="container">

                <section class="box special">
                    <header class="major">
                        <h2>Bienvenido al lugar donde guardar tus<br>
                            marcas como si fueras un corredor profesional</h2>
                        <p>Gracias a mis marcas de running puedes llevar todos 
                            tus tiempos en diferentes carreras sin ninguna preocupación, y así podrás seguir mejorando tus tiempos.</p>
                    </header>
                    <span class="image featured">
                        <img src="https://cdn.discordapp.com/attachments/641698383228305418/685024884744912904/banner.png" alt="MIS MARCAS DE RUNNING" />
                    </span>
                </section>

                <section class="box special features">
                    <div class="features-row">
                        <section>
                            <span class="icon solid major fa-bolt accent2"></span>
                            <h3>Mejora tus marcas</h3>
                            <p>Integer volutpat ante et accumsan commophasellus sed aliquam feugiat lorem aliquet ut enim rutrum phasellus iaculis accumsan dolore magna aliquam veroeros.</p>
                        </section>
                        <section>
                            <span class="icon solid major fa-chart-area accent3"></span>
                            <h3>Guarda tus clasificaciones</h3>
                            <p>Integer volutpat ante et accumsan commophasellus sed aliquam feugiat lorem aliquet ut enim rutrum phasellus iaculis accumsan dolore magna aliquam veroeros.</p>
                        </section>
                    </div>
                    <div class="features-row">
                        <section>
                            <span class="icon solid major fa-cloud accent4"></span>
                            <h3>Comparte marcas con los demas</h3>
                            <p>Integer volutpat ante et accumsan commophasellus sed aliquam feugiat lorem aliquet ut enim rutrum phasellus iaculis accumsan dolore magna aliquam veroeros.</p>
                        </section>
                        <section>
                            <span class="icon solid major fa-lock accent5"></span>
                            <h3>Manten ocultas tus marcas</h3>
                            <p>Integer volutpat ante et accumsan commophasellus sed aliquam feugiat lorem aliquet ut enim rutrum phasellus iaculis accumsan dolore magna aliquam veroeros.</p>
                        </section>
                    </div>
                </section>

                <div class="row">
                    <div class="col-6 col-12-narrower">

                        <%
                            Run run1 = m.getRun((int) (Math.random() * (0 + m.getRuns().size())));
                        %>
                        <section class="box special">
                            <span class="image featured"><img src="<%=run1.getPhoto()%>" alt="<%=run1.getName()%>" /></span>
                            <h3><b><%=run1.getName()%></b></h3>

                            <ul class="actions special">
                                <li><a href="
                                       <%
                                           if (user.getId() == -1) {
                                               out.print("logInPage.jsp");
                                           } else {
                                               session.setAttribute("selectedRun", run1.getId());
                                               out.print("newMarkPage.jsp");
                                           }

                                       %>
                                       " class="button">Guardar marca</a></li>
                            </ul>
                        </section>

                    </div>
                    <div class="col-6 col-12-narrower">
                        <%                            Run run2;
                            do {
                                run2 = m.getRun((int) (Math.random() * (0 + m.getRuns().size())));
                            } while (run2.getId() == run1.getId());
                        %>
                        <section class="box special">
                            <span class="image featured"><img src="<%=run2.getPhoto()%>" alt="<%=run2.getName()%>" /></span>
                            <h3><b><%=run2.getName()%></b></h3>
                            <ul class="actions special">
                                <li><a href="
                                       <%
                                           if (user.getId() == -1) {
                                               out.print("logInPage.jsp");
                                           } else {
                                               session.setAttribute("selectedRun", run2.getId());
                                               out.print("newMarkPage.jsp");
                                           }

                                       %>
                                       " class="button">Guardar marca</a></li>
                            </ul>
                        </section>

                    </div>
                </div>

            </section>

            <!-- Footer -->
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

        <!-- Scripts -->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/jquery.dropotron.min.js"></script>
        <script src="assets/js/jquery.scrollex.min.js"></script>
        <script src="assets/js/browser.min.js"></script>
        <script src="assets/js/breakpoints .min.js"></script>
        <script src="assets/js/util.js"></script>
        <script src="assets/js/main.js"></script>

        <%            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.html");
            } catch (Error er) {
                er.printStackTrace();
                response.sendRedirect("error.html");
            }
        %>

    </body>
</html>