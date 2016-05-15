<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <h3 class="panel-title">Please Sign In</h3>
                </div>
                <div class="panel-body">
                    <c:url value="/login" var="loginUrl"/>
                    <form action="${loginUrl}" method="post">
                    <fieldset>

                        <c:if test="${param.error != null}">
                            <div id = "wrongLogin" class="alert alert-danger" style="display: none">
                                Wrong login or password.
                            </div>
                        </c:if>

                        <c:if test="${param.logout != null}">
                            <div id = "wrongPass" class="alert alert-danger" style="display: none">
                                You have been logged out.
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input class="form-control" type="text" id="username" name="username" placeholder="Username"/>
                        </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input class="form-control" placeholder="Password" type="password" id="password" name="password"/>
                            </div>

                        <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <button class="btn btn-lg btn-success btn-block" type="submit" class="btn">Log in</button>
                            <!-- Change this to a button or input when using this as a form -->
                            <div>
                                <button type="button" class="btn btn-link pull-right" onclick="location.href='${pageContext.request.contextPath}/register'; return false;">Register</button>
                            </div>

                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>
