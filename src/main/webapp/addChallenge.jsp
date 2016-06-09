<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 16.18.4
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="/header.jsp"/>
<body>
<%Map<String,String> dataMap = (Map<String,String>) request.getAttribute("data");%>
<div id="wrapper">
    <jsp:include page="/navbar.jsp"/>
    <div id="page-wrapper">
        <form method = "post" action="addChallenge">
            <h3>Challenge name:</h3>
            <input type="text" name="name">
            <h3>From:</h3>
            <input name="senderId" value="<%=dataMap.get("senderId")%>" readonly="true">
            <h3>To:</h3>
            <input name="recipientId" value="<%=dataMap.get("recipientId")%>">
            <h3>End date:</h3>
            <input id="datetimepicker" type="text" name="date" >
            <link rel="stylesheet" type="text/css" media="screen"
                  href="${pageContext.request.contextPath}/resources/lib/datetimepicker-master/jquery.datetimepicker.css">

            <script type="text/javascript">
                $(function(){
                    $('#datetimepicker').appendDtpicker();
                });
            </script>
            <h3>Description:</h3>
            <textarea rows="2" cols="50" type="text" name="description" autofocus maxlength="1000"></textarea>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <input type="submit" value="submit">
        </form>
    </div>
</div>
</body>
</html>
