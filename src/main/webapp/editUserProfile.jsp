<%@ page import="lv.javaguru.java2.domain.UserProfile" %><%--
  Created by IntelliJ IDEA.
  User: Camille
  Date: 13.04.2016
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User Profile</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Main Page">
    <meta name="author" content="<MyTi Team">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/resources/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="${pageContext.request.contextPath}/resources/dist/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/resources/dist/css/myTi.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${pageContext.request.contextPath}/resources/bower_components/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<div id="wrapper">
    <jsp:include page="/navbar.jsp"/>
    <div id="page-wrapper">
<h1>Here you can edit your profile.</h1>

<form method="post" action="editUserProfile">
    <p><% UserProfile userProfile = (UserProfile)request.getAttribute("data");%></p>

    <p>First Name: </p><input type="text" name="firstName" value="<%=userProfile.getFirstName()%>"></br>
    <p>Last Name: </p><input type="text" name="lastName" value="<%=userProfile.getLastName()%>"></br>
    <p>E-mail: </p><input id="email" type="text" name="email" value="<%=userProfile.getEmail()%>"></br>
    <input type="submit" value="Save">
</form>

       <!-- <script type="text/javascript">

            function validateEmail(email) {
            // http://stackoverflow.com/a/46181/11236

            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
            }

            function validate(){
            $("#result").text("");
            var email = $("#email").val();
            if (validateEmail(email)) {
                $("#result").text(email + "is not valid :(");
                $("#result").css("color", "red");
            }
            return false;
            }

            $("form").bind("submit", validate);
            </script>-->
    </div>
</div>
</body>
</html>
