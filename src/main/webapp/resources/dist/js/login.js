function login() {
    $("#wrongPass").hide();
    $("#wrongLogin").hide();
    $.post( "login.jsp", $("#loginForm").serialize())
        .done(function( data ) {
            if(data.status == "OK") {
                window.location.href = data.redirectTo;
            } else if(data.status == "NOK") {
                switch (data.ERROR) {
                    case "Wrong Password":
                        $("#wrongPass").show();
                        break;
                    case "User Does Not Exist":
                        $("#wrongLogin").show();
                        break;
                    default:
                        $("#appError").show();
                        break;
                }
            } else {
                $("#appError").show();
            }
        }).fail(function() {
        $("#appError").show();
    });
}

function register() {
    $("#existLogin").hide();
    $("#existEmail").hide();
    $("#emptyFields").hide();
    $.post("register", $("#registerForm").serialize())
        .done(function(data){
            if(data.status == "OK") {
                window.location.href = data.redirectTo;
            }else if(data.status == "NOK") {
                switch (data.ERROR) {
                    case "User already exists":
                        $("#existLogin").show();
                        break;
                    case "Email already exists":
                        $("#existEmail").show();
                        break;
                    case "Login is empty":
                    case "Password is empty":
                    case "First Name is empty":
                    case "Last Name is empty":
                    case "Email is empty":
                        $("#emptyFields").show();
                        break;
                    default:
                        $("#appError").show();
                        break;
                }
            }else{
                $("#appError").show();
            }
        }).fail(function(){
        $("#appError").show();
    });
}