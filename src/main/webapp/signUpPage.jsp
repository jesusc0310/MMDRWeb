<%-- 
    Document   : singUpPage
    Created on : 10 sept. 2020, 19:34:30
    Author     : Jesus Cruz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registro | Mis Marcas de Running</title>
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
                        <li><a href="logInPage.jsp" class="button primary">Inicia Sesion</a></li>
                    </ul>
                </nav>
            </header>

            <!-- Main -->
            <section id="main" class="container">
                <section class="box special">
                    <span class="image featured">
                        <img src="https://cdn.discordapp.com/attachments/641698383228305418/685024884744912904/banner.png" alt="MIS MARCAS DE RUNNING" />
                    </span>
                    <div class="col-12-mobilep">
                        <h2 class="major">REGISTRATE</h2>
                    </div>

                    <!-- Registro -->
                    <form method="post" action="signUp.jsp">
                        <div class="row gtr-25 gtr-uniform">

                            <!-- PRIMERA FILA -->
                            <div class="col-5 col-6-mobilep">
                                <input type="text" id="user" name="username" required placeholder="Nombre de usuario"
                                       <%=(session.getAttribute("username") == null
                                               ? "" : "value=\"" + session.getAttribute("username") + "\"")%> >
                            </div>
                            
                            <% if (session.getAttribute("usernameError") != null) {
                                    out.print("<div class=\"col-12 col-12-mobilep\">"
                                            + "<p class=\"red\">"
                                            + session.getAttribute("usernameError")
                                            + "</p>"
                                            + "</div>");
                                } else {
                            %>
                            <div class="col-7 col-6-mobilep">
                                <!-- Este div sirve para rellenar las columnas restantes y que así todo se quede cuadrado -->
                            </div>
                            <%}%>

                            <!-- SEGUNDA FILA -->
                            <div class="col-5 col-6-mobilep">
                                <input type="password" name="password" required placeholder="Contraseña">
                                <%=(session.getAttribute("passError") == null
                                        ? "" : "<p class=\"red\">" + session.getAttribute("passError") + "</p>")%>
                            </div>
                            <div class="col-5 col-6-mobilep">
                                <input type="password" name="repeatPass" required placeholder="Repite la contraseña">
                            </div>

                            <!-- SEPARADOR -->
                            <div class="col-12">
                                <!-- Este div sirve para dar un poco de espacio entre los datos de usuario y la información propia del usuario -->
                            </div>


                            <!-- TERCERA FILA -->
                            <div class="col-5 col-6-mobilep">
                                <input type="text" id="name" name="name" placeholder="Nombre" required
                                       <%=(session.getAttribute("name") == null
                                               ? "" : "value=\"" + session.getAttribute("name") + "\"")%> >
                            </div>
                            <div class="col-7 col-6-mobilep">
                                <!-- Este div sirve para rellenar las columnas restantes y que así todo se quede cuadrado -->
                            </div>

                            <!-- CUARTA FILA -->
                            <div class="col-5 col-6-mobilep">
                                <input type="text" name="surname1" placeholder="Primer apellido" required
                                       <%=(session.getAttribute("surname1") == null
                                               ? "" : "value=\"" + session.getAttribute("surname1") + "\"")%> >
                            </div>
                            <div class="col-5 col-6-mobilep">
                                <input type="text" name="surname2" placeholder="Segundo apellido (Opcional)" 
                                       <%=(session.getAttribute("surname2") == null
                                               ? "" : "value=\"" + session.getAttribute("surname2") + "\"")%> >
                            </div>

                            <!-- SEPARADOR -->
                            <div class="col-12">
                                <!-- Este div sirve para dar un poco de espacio entre los datos de usuario y la información propia del usuario -->
                            </div>

                            <!-- QUINTA FILA -->
                            <div class="col-5 col-6-mobilep">
                                <input type="email" name="email" required autocomplete placeholder="Email"
                                       <%=(session.getAttribute("email") == null
                                               && session.getAttribute("emailError") == null
                                               ? "" : "value=\"" + session.getAttribute("email") + "\"")%>>

                                <%=(session.getAttribute("emailError") == null
                                        ? "" : "<p class=\"red\">" + session.getAttribute("emailError") + "</p>")%>
                            </div>
                            <div class="col-5 col-6-mobilep">
                                <input type="date" name="birthDate" min="1900-01-01" required placeholder="Fecha de nacimiento (dd/mm/aaaa)"
                                       <%=(session.getAttribute("birthDate") == null
                                               ? "" : "value=\"" + session.getAttribute("birthDate") + "\"")%> >
                            </div>

                            <!-- ENVIO DEL FORMULARIO -->
                            <div class="col-12">
                                Al pulsar botón acepta los <a href="https://www.boe.es/legislacion/codigos/codigo.php?id=55&modo=1&nota=0&tab=2">terminos y condiciones</a> de la empresa.
                                <br>
                                <ul class="actions special">
                                    <li><input type="submit" value="Registrarse"></li>
                                </ul>
                                <p>¿Ya tienes cuenta? <a href="logInPage.jsp">Inicia Sesión</a></p>
                            </div>
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
        <%
            session.removeAttribute("usernameError");
            session.removeAttribute("emailError");
            session.removeAttribute("passError");
        %>
    </body>
</html>