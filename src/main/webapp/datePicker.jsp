<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 16.31.3
  Time: 09:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"      "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/lib/datetimepicker/jquery.datetimepicker.css"/>
    <script src="/resources/lib/datetimepicker/jquery.js"></script>
    <script src="/resources/lib/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
    <script type="text/javascript">
        jQuery(function() {
            jQuery('#datetimepicker').datetimepicker();
        });
    </script>
    <title>Test</title>
</head>
<body>

Date: <input id="datetimepicker" type="text" >
</body>
</html>
