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


</head>
<body>
<div id="datetimepicker" class="input-append date">
    <input type="text" id = "date"></input>
      <span class="add-on">
        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
      </span>
</div>

<script type="text/javascript"
        src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js">
</script>

<script type="text/javascript"
        src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js">
</script>
<script type="text/javascript">
    $('#datetimepicker').datetimepicker({
        format: 'dd/MM/yyyy hh:mm:ss',
        language: 'en'
    });
</script>

</body>
<html>
