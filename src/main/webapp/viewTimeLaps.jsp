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
<jsp:include page="/header.jsp"/>
<body>
<link href="${pageContext.request.contextPath}/resources/css/timelaps.css" type="text/css" rel="stylesheet" />

<div id="wrapper">
    <jsp:include page="/navbar.jsp"/>
    <div id="page-wrapper">



<% List<TimeLapsList> timeLapsList = (List<TimeLapsList>) request.getAttribute("data");%>


        <form method="post" action="viewTimeLaps" name="DeleteAllTimeLaps">
            <button type="submit" value="Delete" name = "DeleteAllTimeLaps">Delete All</button>
        </form>

        <form method="post" action="viewTimeLaps" name="AddTimeLaps">
            <button type="submit" value="add" name="AddTimeLaps">Add New</button>
        </form>

<h2>View Time Laps Page</h2>
        <%Integer sportCount = 0;
        Integer workCount =0;
        Integer funCount= 0;
        Integer homeCount = 0;
        %>
        <% for (int i = 0; i < timeLapsList.size() ; i++) {
            if (timeLapsList.get(i).getCategory().equalsIgnoreCase("Sport")){
                sportCount++;
            }
            if (timeLapsList.get(i).getCategory().equalsIgnoreCase("Work")){
                workCount ++;
            }
            if (timeLapsList.get(i).getCategory().equalsIgnoreCase("Fun")){
                funCount++;
            }
            if (timeLapsList.get(i).getCategory().equalsIgnoreCase("Home")){
                homeCount++;
            }


        } %>
        <ul class="tab">
            <li><a href="#" class="tablinks" onclick="openCategory(event, 'All')">All(<%= timeLapsList.size()%>)</a></li>
            <li><a href="#" class="tablinks" onclick="openCategory(event, 'Sport')">Sport(<%=sportCount%>)</a></li>
            <li><a href="#" class="tablinks" onclick="openCategory(event, 'Work')">Work(<%=workCount%>)</a></li>
            <li><a href="#" class="tablinks" onclick="openCategory(event, 'Fun')">Fun(<%=funCount%>)</a></li>
            <li><a href="#" class="tablinks" onclick="openCategory(event, 'Home')">Home(<%=homeCount%>)</a></li>
        </ul>

        <div id="All" class="tabcontent">
            <% for (int i = 0; i < timeLapsList.size() ; i++) { %>

            <button class="accordion"><p>Name:<%=timeLapsList.get(i).getTimeLapsName()%></p><p>ID:<%=timeLapsList.get(i).getTimeLapsId()%></p></button>
            <div class="panel">
                <div>
                    <p><b>Compete Time:</b><%=timeLapsList.get(i).getCompleteTime()%></p>
                </div>
                <div>
                    <p><b>Category:</b><%=timeLapsList.get(i).getCategory()%></p>
                </div>
                <div>
                    <h4>Short Description:</h4>
                    <p><%=timeLapsList.get(i).getShortDescription()%></p>
                </div>
                <div>
                    <h4>Long Description:</h4>
                    <p><%=timeLapsList.get(i).getLongDescription()%></p>
                </div>
                <div>
                    <form method="get" action="editTimeLaps" name="TimeLapsId">
                        <button type="submit" value="<%=timeLapsList.get(i).getTimeLapsId()%>" name = "TimeLapsId">Edit</button>
                    </form>
                    <form method="post" action="viewTimeLaps" name="DeleteTimeLapsById">
                        <button type="submit" value="<%=timeLapsList.get(i).getTimeLapsId()%>" name="DeleteTimeLapsById">Delete</button>
                    </form>
                </div>
            </div>

            <% } %>

        </div>
        <div id="Sport" class="tabcontent">
            <% for (int i = 0; i < timeLapsList.size() ; i++) { %>
            <% if (timeLapsList.get(i).getCategory().equalsIgnoreCase("sport")){%>
            <button class="accordion"><p>Name:<%=timeLapsList.get(i).getTimeLapsName()%></p><p>ID:<%=timeLapsList.get(i).getTimeLapsId()%></p></button>
            <div class="panel">
                <div>
                    <p><b>Compete Time:</b><%=timeLapsList.get(i).getCompleteTime()%></p>
                </div>
                <div>
                    <p><b>Category:</b><%=timeLapsList.get(i).getCategory()%></p>
                </div>
                <div>
                    <h4>Short Description:</h4>
                    <p><%=timeLapsList.get(i).getShortDescription()%></p>
                </div>
                <div>
                    <h4>Long Description:</h4>
                    <p><%=timeLapsList.get(i).getLongDescription()%></p>
                </div>
                <div>
                    <form method="get" action="editTimeLaps" name="TimeLapsId">
                        <button type="submit" value="<%=timeLapsList.get(i).getTimeLapsId()%>" name = "TimeLapsId">Edit</button>
                    </form>
                    <form method="post" action="viewTimeLaps" name="DeleteTimeLapsById">
                        <button type="submit" value="<%=timeLapsList.get(i).getTimeLapsId()%>" name="DeleteTimeLapsById">Delete</button>
                    </form>
                </div>

            </div>
            <%}%>
            <% } %>
        </div>
        <div id="Work" class="tabcontent">
            <% for (int i = 0; i < timeLapsList.size() ; i++) { %>
            <% if (timeLapsList.get(i).getCategory().equalsIgnoreCase("Work")){%>
            <button class="accordion"><p>Name:<%=timeLapsList.get(i).getTimeLapsName()%></p><p>ID:<%=timeLapsList.get(i).getTimeLapsId()%></p></button>
            <div class="panel">
                <div>
                    <p><b>Compete Time:</b><%=timeLapsList.get(i).getCompleteTime()%></p>
                </div>
                <div>
                    <p><b>Category:</b><%=timeLapsList.get(i).getCategory()%></p>
                </div>
                <div>
                    <h4>Short Description:</h4>
                    <p><%=timeLapsList.get(i).getShortDescription()%></p>
                </div>
                <div>
                    <h4>Long Description:</h4>
                    <p><%=timeLapsList.get(i).getLongDescription()%></p>
                </div>
                <div>
                    <form method="get" action="editTimeLaps" name="TimeLapsId">
                        <button type="submit" value="<%=timeLapsList.get(i).getTimeLapsId()%>" name = "TimeLapsId">Edit</button>
                    </form>
                    <form method="post" action="viewTimeLaps" name="DeleteTimeLapsById">
                        <button type="submit" value="<%=timeLapsList.get(i).getTimeLapsId()%>" name="DeleteTimeLapsById">Delete</button>
                    </form>
                </div>

            </div>
            <%}%>
            <% } %>
        </div>
        <div id="Fun" class="tabcontent">
            <% for (int i = 0; i < timeLapsList.size() ; i++) { %>
            <% if (timeLapsList.get(i).getCategory().equalsIgnoreCase("Fun")){%>
            <button class="accordion"><p>Name:<%=timeLapsList.get(i).getTimeLapsName()%></p><p>ID:<%=timeLapsList.get(i).getTimeLapsId()%></p></button>
            <div class="panel">
                <div>
                    <p><b>Compete Time:</b><%=timeLapsList.get(i).getCompleteTime()%></p>
                </div>
                <div>
                    <p><b>Category:</b><%=timeLapsList.get(i).getCategory()%></p>
                </div>
                <div>
                    <h4>Short Description:</h4>
                    <p><%=timeLapsList.get(i).getShortDescription()%></p>
                </div>
                <div>
                    <h4>Long Description:</h4>
                    <p><%=timeLapsList.get(i).getLongDescription()%></p>
                </div>
                <div>
                    <form method="get" action="editTimeLaps" name="TimeLapsId">
                        <button type="submit" value="<%=timeLapsList.get(i).getTimeLapsId()%>" name = "TimeLapsId">Edit</button>
                    </form>
                    <form method="post" action="viewTimeLaps" name="DeleteTimeLapsById">
                        <button type="submit" value="<%=timeLapsList.get(i).getTimeLapsId()%>" name="DeleteTimeLapsById">Delete</button>
                    </form>
                </div>

            </div>
            <%}%>
            <% } %>
        </div>
        <div id="Home" class="tabcontent">
            <% for (int i = 0; i < timeLapsList.size() ; i++) { %>
            <% if (timeLapsList.get(i).getCategory().equalsIgnoreCase("Home")){%>
            <button class="accordion"><p>Name:<%=timeLapsList.get(i).getTimeLapsName()%></p><p>ID:<%=timeLapsList.get(i).getTimeLapsId()%></p></button>
            <div class="panel">
                <div>
                    <p><b>Compete Time:</b><%=timeLapsList.get(i).getCompleteTime()%></p>
                </div>
                <div>
                    <p><b>Category:</b><%=timeLapsList.get(i).getCategory()%></p>
                </div>
                <div>
                    <h4>Short Description:</h4>
                    <p><%=timeLapsList.get(i).getShortDescription()%></p>
                </div>
                <div>
                    <h4>Long Description:</h4>
                    <p><%=timeLapsList.get(i).getLongDescription()%></p>
                </div>
                <div>
                    <form method="get" action="editTimeLaps" name="TimeLapsId">
                        <button type="submit" value="<%=timeLapsList.get(i).getTimeLapsId()%>" name = "TimeLapsId">Edit</button>
                    </form>
                    <form method="post" action="viewTimeLaps" name="DeleteTimeLapsById">
                        <button type="submit" value="<%=timeLapsList.get(i).getTimeLapsId()%>" name="DeleteTimeLapsById">Delete</button>
                    </form>
                </div>

            </div>
            <%}%>
            <% } %>
        </div>
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
        <script>
            (function ($) {
                jQuery.expr[':'].Contains = function(a,i,m){
                    return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase())>=0;
                };

                function filterList(header, list) {
                    var form = $("<form>").attr({"class":"filterform","action":"#"}),
                            input = $("<input>").attr({"class":"filterinput","type":"text"});
                    $(form).append(input).appendTo(header);

                    $(input)
                            .change( function () {
                                var filter = $(this).val();
                                if(filter) {

                                    $matches = $(list).find('a:Contains(' + filter + ')').parent();
                                    $('button', list).not($matches).slideUp();
                                    $matches.slideDown();

                                } else {
                                    $(list).find("button").slideDown();
                                }
                                return false;
                            })
                            .keyup( function () {
                                $(this).change();
                            });
                }

                $(function () {
                    filterList($("#form"), $("#list"));
                });
            }(jQuery));
        </script>
    </div>
</div>
</body>
</html>
