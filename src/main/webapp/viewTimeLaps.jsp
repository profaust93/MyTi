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

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

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
