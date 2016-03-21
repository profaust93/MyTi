<%--
  Created by IntelliJ IDEA.
  User: Kemran
  Date: 21/03/2016
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login or Registration</title>
    <meta charset="utf-8">
</head>
<body>
<div class="main">
    <div class="header" >
        <h1>Login or Create a Free Account!</h1>
    </div>
    <p>Plan your time effectively and have Fun.</p>
    <form>
        <ul class="left-form">
            <h2>New Account:</h2>
            <li>
                <input type="text"   placeholder="batman777" required/>
                <a href="#" class="icon ticker"> </a>
                <div class="clear"> </div>
            </li>
            <li>
                <input type="text"   placeholder="Bruce" required/>
                <a href="#" class="icon ticker"> </a>
                <div class="clear"> </div>
            </li>
            <li>
                <input type="text"   placeholder="Wayne" required/>
                <a href="#" class="icon ticker"> </a>
                <div class="clear"> </div>
            </li>
            <li>
                <input type="text"   placeholder="darkknight@gotham.com" required/>
                <a href="#" class="icon ticker"> </a>
                <div class="clear"> </div>
            </li>
            <li>
                <input type="password"   placeholder="password" required/>
                <a href="#" class="icon into"> </a>
                <div class="clear"> </div>
            </li>
            <li>
                <input type="password"   placeholder="repeat password" required/>
                <a href="#" class="icon into"> </a>
                <div class="clear"> </div>
            </li>
            <input type="submit" onclick="myFunction()" value="Create Account">
            <div class="clear"> </div>
        </ul>
        <ul class="right-form">
            <h3>Login:</h3>
            <div>
                <li><input type="text"  placeholder="Username" required/></li>
                <li> <input type="password"  placeholder="Password" required/></li>
                <a href="#"><h4>I forgot my Password!</h4></a>
                <input type="submit" onclick="myFunction()" value="Login" >
            </div>
            <div class="clear"> </div>
        </ul>
        <div class="clear"> </div>

    </form>

</div>
<!-----start-copyright---->
<div class="copy-right">
    <p>© MyTi 2016</p>
</div>
<!-----//end-copyright---->


</body>
</html>