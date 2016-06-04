<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<jsp:include page="/header.jsp"/>
<body>

    <div class="container">
        <div id = "appError"class="alert alert-danger" style="display: none">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Application Error</strong> Please refresh the page.
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign Up</h3>
                    </div>
                    <div class="panel-body">
                            <c:url value="/register" var="registrUrl"/>
                            <form action="${registrUrl}" method="post">
                            <fieldset>
                                <c:if test="${param.error != null}">
                                    <div id = "emptyFields" class="alert alert-danger" style="display: none">
                                        Fill all fields
                                    </div>
                                </c:if>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Login" name="login" type="text" autofocus>
                                </div>
                                <c:if test="${param.error != null}">
                                    <div id = "existLogin" class="alert alert-danger" style="display: none">
                                        User already exists
                                    </div>
                                </c:if>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" />
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Firstname" name="firstname" type="text"/>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Lastname" name="lastname" type="text"/>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Email" name="email" type="email" />
                                </div>

                                <!-- Change this to a button or input when using this as a form -->
                                <button class="btn btn-lg btn-success btn-block" type="submit" class="btn">Registration</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
