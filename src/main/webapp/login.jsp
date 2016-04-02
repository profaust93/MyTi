<%--
  Created by IntelliJ IDEA.
  User: Kemran
  Date: 02/04/2016
  Time: 6:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login | MyTi</title>
</head>
<body>

    <div class="main">
        <div class="header" >
            <h1>Login or Create a Free Account!</h1>
        </div>
        <p>Plan your time effectively and have Fun.</p>
        <form method = "post" action="login">

            <ul class="log-form">
                <h3>Login:</h3>
                <div>
                    <li><input type="text" name="login" placeholder="Username" required/></li>
                    <li> <input type="password" name="password" placeholder="Password" required/></li>
                    <a href="#"><h4>Don't have an account? Create one now.</h4></a>
                    <input type="submit" onclick="myFunction()" value="Login">
                </div>
                <div class="clear"> </div>
            </ul>

            <div class="clear"> </div>
        </form>
    </div>


    <div class="copy-right">
        <p>MyTi</p>
    </div>


</body>
</html>
