$( "#deadline" ).datepicker();

$('.goalCheck').click(function() {
    if( $(this).is(':checked')) {
        $("#goalsDiv").show();
    } else {
        $("#goalsDiv").hide();
    }
});