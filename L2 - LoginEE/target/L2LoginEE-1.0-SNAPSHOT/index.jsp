<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Prog Academy</title>
  </head>
  <body>
    <% String user_login = (String)session.getAttribute("user_login"); %>
    <% String age = (String)session.getAttribute("age"); %>
    <% String pass = (String)session.getAttribute("pass"); %>
    <% String login = (String)session.getAttribute("login"); %>

    <% if (user_login == null || "".equals(user_login)) { %>
        <form action="/login" method="POST">
            Login: <input type="text" name="login"><br>
            Password: <input type="password" name="password"><br>
            Age: <input type="text" name="age"><br>
            <input type="submit" />
        </form>
    <% if (age != null) { %>
        <% if (age.equals("young")) { %>
            <br><b>You are under 18</b>
        <% }%>
        <% if (age.equals("empty")) { %>
           <br><b>Age is empty</b>
        <% }%>
        <% if (age.equals("error")) { %>
            <br><b>Age is error</b>
        <% }%>
    <% }%>

        <% if (login != null) { %>
            <% if (login.equals("empty")) { %>
                <br><b>Login is empty</b>
            <% }%>
            <% if (login.equals("error")) { %>
                <br><b>Wrong login</b>
            <% }%>
        <% }%>
        <% if (pass != null ) { %>
            <% if (pass.equals("empty")) { %>
                <br><b>Pass is empty</b>
            <% }%>
            <% if (pass.equals("error")) { %>
                <br><b>Wrong pass</b>
            <% }%>
            <% if (pass.equals("short")) { %>
                <br><b>Pass is short</b>
            <% }%>
        <% }%>



    <% } else { %>
        <h1>You are logged in as: <%= user_login %></h1>
        <br>Click this link to <a href="/login?a=exit">logout</a>
        <% if (pass != null && pass.equals("short")) { %>
            <br><b>Pass is short</b>
        <% }%>

    <% } %>
  </body>
</html>
