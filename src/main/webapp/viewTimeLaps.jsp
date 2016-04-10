<%@ page import="java.util.Map" %>
<%@ page import="lv.javaguru.java2.domain.TimeLapsList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 16.29.3
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
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
</head>
<body>
<% List<TimeLapsList> list = (List<TimeLapsList>) request.getAttribute("data");%>

<h2>View Time Laps Page</h2>
<% for (int i = 0; i < list.size() ; i++) { %>
<button class="accordion"><p>Name:<%=list.get(i).getTimeLapsName()%></p><p>ID:<%=list.get(i).getTimeLapsId()%></p></button>
<div class="panel">
    <div>
        <p><b>Compete Time:</b><%=list.get(i).getCompleteTime()%></p>
    </div>
    <div>
        <h4>Short Description:</h4>
        <p><%=list.get(i).getShortDescription()%></p>
    </div>
    <div>
        <h4>Long Description:</h4>
        <p><%=list.get(i).getLongDescription()%></p>
    </div>
    <div>
        <form method="get" action="editTimeLaps" name="TimeLapsId">
            <button type="submit" value="<%=list.get(i).getTimeLapsId()%>" name = "TimeLapsId">Edit</button>
        </form>
    </div>

</div>
<% } %>

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
</body>
</html>
