<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
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
                        <form id="registerForm" method="post" role="form">
                            <fieldset>
                                <div id = "emptyFields" class="alert alert-danger" style="display: none">
                                    Fill all fields
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Login" name="login" type="text" autofocus>
                                </div>
                                <div id = "existLogin" class="alert alert-danger" style="display: none">
                                    User already exists
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" value="">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Username" name="firstname" type="text" value="">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Lastname" name="lastname" type="text" value="">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Email" name="email" type="email" value="">
                                </div>
                                <div id = "existEmail" class="alert alert-danger" style="display: none">
                                    Email already exists
                                </div>

                                <!-- Change this to a button or input when using this as a form -->
                                <a class="btn btn-lg btn-success btn-block" onclick="register(); return false;">Registration</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
