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