<%@ page import="java.util.Map" %>
<%@ page import="lv.javaguru.java2.domain.UserProfileList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.awt.*" %>
<%@ page import="javax.swing.*" %><%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 2016.04.23.
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile List</title>
    <style>
        /* Style the timeLapsList */
        ul.tab {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
        }

        /* Float the timeLapsList items side by side */
        ul.tab li {float: left;}

        /* Style the links inside the timeLapsList items */
        ul.tab li a {
            display: inline-block;
            color: black;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            transition: 0.3s;
            font-size: 17px;
        }

        /* Change background color of links on hover */
        ul.tab li a:hover {background-color: #ddd;}

        /* Create an active/current tablink class */
        ul.tab li a:focus, .active {background-color: #ccc;}

        /* Style the tab content */
        .tabcontent {
            display: none;
            padding: 6px 12px;
            border: 1px solid #ccc;
            border-top: none;
        }
        button.accordion {
            background-color: #eee;
            color: #444;
            cursor: pointer;
            padding: 0px;
            width: 100%;
            border: none;
            text-align: left;
            outline: none;
            font-size: 15px;
            transition: 0.4s;
        }

        button.accordion.active, button.accordion:hover {
            background-color: #ddd;
        }

        div.panel {
            padding: 0 18px;
            background-color: white;
            max-height: 0;
            width: 90%;
            word-break: break-all;
            overflow: hidden;
            transition: 0.6s ease-in-out;
            opacity: 0;
        }

        div.panel.show {
            opacity: 1;
            max-height: 500px;
        }
    </style>
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
<%List<UserProfileList> userProfileList = (List<UserProfileList>) request.getAttribute("data");%>


<div id="wrapper">
    <jsp:include page="/navbar.jsp"/>
    <div id="page-wrapper">

        <% for(int i = 0; i < userProfileList.size() ; i++) { %>
<button class="accordion"><p>Name:<%=userProfileList.get(i).getFirstName()%></p>
    <p>Surname:<%=userProfileList.get(i).getLastName()%></p>
    <p>ID:<%=userProfileList.get(i).getUserId()%></p>
</button>
<div class="panel">
    <div>
        <p><b>Name:</b><%=userProfileList.get(i).getFirstName()%></p>
    </div>
    <div>
        <p><b>Surname:</b><%=userProfileList.get(i).getLastName()%></p>
    </div>
    <div>

        <h4>E-mail:</h4><p><%=userProfileList.get(i).getEmail()%></p>
    </div>
    <div>
        <img src="<%=userProfileList.get(i).getProfilePicture()%>">
    </div>
    <div>
        <form method="get" action="addChallenge" name="recipientId">
            <button type="submit" value="<%=userProfileList.get(i).getUserId()%>" name = "recipientId">Challenge!</button>
        </form>
    </div>
</div>
<%}%>
        <script>
            var acc = document.getElementsByClassName("accordion");
            var i;

            for (i = 0; i < acc.length; i++) {
                acc[i].onclick = function(){
                    this.classList.toggle("active");
                    this.nextElementSibling.classList.toggle("show");
                }
            }
        </script>
        <script>
            function openCategory(evt, category) {
                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("tabcontent");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablinks");
                for (i = 0; i < tabcontent.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" active", "");
                }
                document.getElementById(category).style.display = "block";
                evt.currentTarget.className += " active";
            }
        </script>
    </div>
</div>
</body>
</html>
