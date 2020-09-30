<%-- 
    Document   : singUp.jsp
    Created on : 14 sept. 2020, 12:11:15
    Author     : Jesus Cruz
--%>

<%@page import="com.mismarcasderuning.model.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.mismarcasderuning.model.Management"%>
<%
    session.removeAttribute("username");
    session.removeAttribute("name");
    session.removeAttribute("surname1");
    session.removeAttribute("surname2");
    session.removeAttribute("email");
    session.removeAttribute("birthDate");

    try {
        Management m = new Management();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPass = request.getParameter("repeatPass");
        String name = request.getParameter("name");
        String surname1 = request.getParameter("surname1");
        String surname2 = request.getParameter("surname2");
        String surnames = surname1.concat(" ").concat(surname2);
        String email = request.getParameter("email");

        Calendar birthDate = Calendar.getInstance();
        SimpleDateFormat dMy = new SimpleDateFormat("dd/MM/yyyy");
        birthDate.setTime(dMy.parse(request.getParameter("birthDate")));

        boolean error = false;

        if (m.getUser(username) != null) {
            error = true;
            session.setAttribute("usernameError", "Este usuario ya existe");
        }

        if (m.getUserByEmail(email) != null) {
            error = true;
            session.setAttribute("emailError", "Este correo ya está en uso");
        }

        if (User.validatePassword(password) && User.validatePassword(repeatPass)) {
            if (!password.equals(repeatPass)) {
                error = true;
                session.setAttribute("passError", "Las contraseñas no coinciden");
            }
        } else {
            error = true;
            session.setAttribute("passError", "La contraseña debe tener 8 caracteres con al menos una mayuscula, una minuscula y un número.");
        }

        if (!error) {
            User user = m.regist(username, password, email, name, surnames, birthDate);
            
            if (user != null) {
                session.setAttribute("currentUser", user);
                response.sendRedirect("index.jsp");
            }
        } else {
            session.setAttribute("username", username);
            session.setAttribute("name", name);
            session.setAttribute("surname1", surname1);
            session.setAttribute("surname2", surname2);
            session.setAttribute("email", email);
            session.setAttribute("birthDate", dMy.format(birthDate.getTime()));

            response.sendRedirect("signUpPage.jsp");
        }
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("error.html");
    }
%>
