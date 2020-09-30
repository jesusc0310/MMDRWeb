<%-- 
    Document   : logInPage
    Created on : 1 sept. 2020, 20:46:40
    Author     : Jesus Cruz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio de Sesion | Mis Marcas de Running</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
        <link rel="stylesheet" href="assets/css/main.css">
    </head>
    <body class="is-preload">
        <div id="page-wrapper">

            <!-- Header -->
            <header id="header">
                <h1><a href="index.jsp">MIS MARCAR DE RUNNING</a></h1>
                <nav id="nav">
                    <ul>
                        <li><a href="index.jsp">Inicio</a></li>
                        <li><a href="signUpPage.jsp" class="button primary">Registrarse</a></li>
                    </ul>
                </nav>
            </header>

            <!-- Main -->
            <section id="main" class="container small">
                <section class="box special">
                    <span class="image featured">
                        <img src="https://cdn.discordapp.com/attachments/641698383228305418/685024884744912904/banner.png" alt="MIS MARCAS DE RUNNING" />
                    </span>
                    <div class="col-12 col-12-mobilep">
                        <h2 class="major">INICIA SESIÓN</h2>
                    </div>

                    <!-- Login -->
                    <form method="post" action="logIn.jsp">
                        <div class="row gtr-50 gtr-uniform">
                            <div class="col-12 col-12-mobilep">
                                <input type="text" name="username" placeholder="Usuario">
                            </div>
                            <div class="col-12 col-12-mobilep">
                                <input type="password" name="password" placeholder="Contraseña">
                            </div>
                        </div>
                        <div class="col-12">
                            <%
                                if (session.getAttribute("loginError") != null) {
                                    out.print("<p class=\"red\">" + session.getAttribute("loginError") + "</p>");
                                    session.removeAttribute("loginError");
                                }
                            %>
                            <p>¿Aún no tienes cuenta? <a href="signUpPage.jsp">Registrate</a></p>
                            <ul class="actions fit">
                                <li><input type="submit" value="Entrar"></li>
                            </ul>
                            <p><a href="remindPassPage.jsp">He olvidado mi contraseña</a></p>
                        </div>
                    </form>


                </section>
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

    </body>
</html>
