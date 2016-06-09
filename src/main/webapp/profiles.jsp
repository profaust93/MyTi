<%@ page import="java.util.Map" %>
<%@ page import="lv.javaguru.java2.profile.domain.UserProfileList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 2016.04.23.
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/header.jsp"/>
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
