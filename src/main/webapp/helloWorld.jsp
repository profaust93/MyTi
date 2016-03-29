<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP page</title>
</head>
<body>

    <h1>Hello from JSP file!</h1>
    <p>Message is :<%=request.getAttribute("data")%>
    </p>

</body>
</html>
