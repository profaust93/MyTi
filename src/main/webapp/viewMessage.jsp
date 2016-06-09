<%@ page import="lv.javaguru.java2.challenge.domain.ChallengeMessageList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/header.jsp"/>
<head>

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
</head>
<body>
<%List<ChallengeMessageList> list = (List<ChallengeMessageList>) request.getAttribute("data");%>
<div id="wrapper">
    <jsp:include page="/navbar.jsp"/>
    <div id="page-wrapper">

            <% for (int i = 0; i < list.size() ; i++) { %>

            <button class="accordion"><p>From user ID:<%=list.get(i).getSenderId()%> </p></button>
            <div class="panel">
                <div>
                    <p><b>Message ID:</b><%=list.get(i).getMessageId()%></p>
                </div>
                <div>
                    <p><b>Description:</b><%=list.get(i).getMessage()%></p>
                </div>
                <div>
                    <form method="get" action="acceptMessage" name="AcceptMessage">
                        <button type="submit" value="<%=list.get(i).getMessageId()%>" name = "AcceptMessageId">Accept</button>

                    </form>
                    <form method="get" action="rejectMessage" name="RejectMessage">
                        <button type="submit" value="<%=list.get(i).getMessageId()%>" name="RejectMessageId">Decline</button>
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

</body>
</html>
