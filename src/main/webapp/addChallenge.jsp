<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 16.18.4
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Challenge</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Main Page">
    <meta name="author" content="<MyTi Team">

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

    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/resources/lib/datetimepicker-master/jquery.datetimepicker.css">

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
        <form method = "post" action="addChallenge">
            <h3>Name:</h3>
            <input type="text" name="name">
            <h3>From:</h3>
            <input type = "text" name="fromUserId">
            <h3>To:</h3>
            <input type="text" name="toUserId">
            <h3>End date:</h3>
            <input id="datetimepicker" type="text" name="date" >

            <script type="text/javascript"
                    src="${pageContext.request.contextPath}/resources/lib/datetimepicker-master/build/jquery.datetimepicker.min.js">
            </script>
            <script type="text/javascript"
                    src="${pageContext.request.contextPath}/resources/lib/datetimepicker-master/build/jquery.datetimepicker.full.min.js">
            </script>
            <script type="text/javascript"
                    src="${pageContext.request.contextPath}/resources/lib/datetimepicker-master/build/jquery.datetimepicker.full.js">
            </script>

            <script type="text/javascript">
                jQuery('#datetimepicker').datetimepicker();
            </script>
            <h3>Description</h3>
            <textarea rows="2" cols="50" type="text" name="description" autofocus maxlength="1000"></textarea>
            <input type="submit" value="submit">
        </form>
    </div>
</div>
</body>
</html>
