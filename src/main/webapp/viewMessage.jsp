<%@ page import="lv.javaguru.java2.challenge.domain.ChallengeMessageList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/header.jsp"/>
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
                    <form method="post" action="viewMessage" name="AcceptMessage">
                        <button type="submit" value="<%=list.get(i).getChallengeId()%>" name = "AcceptMessageId">Accept</button>
                    </form>
                    <form method="post" action="viewMessage" name="RejectMessage">
                        <button type="submit" value="<%=list.get(i).getChallengeId()%>" name="RejectMessageId">Decline</button>
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
