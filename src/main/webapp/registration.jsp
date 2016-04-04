<%--
  Created by IntelliJ IDEA.
  User: Kemran
  Date: 02/04/2016
  Time: 6:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reistration | MyTi</title>
</head>
<body>

    <div class="main">
        <div class="header" >
            <h1>Create a Free Account!</h1>
        </div>
        <form method = "post" action="registration">
            <ul class="reg-form">
                <h2>New Account:</h2>
                <li>
                    <input type="text"   placeholder="batman777" name="login" required/>
                    <div class="clear"> </div>
                </li>
                <li>
                    <input type="text"   placeholder="Bruce" name="firstname" required/>
                    <div class="clear"> </div>
                </li>
                <li>
                    <input type="text"   placeholder="Wayne" name="lastname" required/>
                    <div class="clear"> </div>
                </li>
                <li>
                    <input type="text"   placeholder="darkknight@gotham.com" name="email" required/>
                    <div class="clear"> </div>
                </li>
                <li>
                    <input type="password"   placeholder="password" name="password" required/>
                    <div class="clear"> </div>
                </li>
                    <input type="submit" onclick="myFunction()" value="Create Account">
                    <div class="clear"> </div>
            </ul>

        </form>
    </div>

    <div class="copy-right">
        <p>MyTi</p>
    </div>

</body>
</html>
