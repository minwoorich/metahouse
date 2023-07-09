$(document).ready(function() {
    $("input:radio[name=point]").on("click", function() {
        $(this).prop("checked", true);
        if($(this).attr("id") == "input_point") {
            console.log("id=" + $(this).attr("id"));
            $("#selected_point_div").show();
            $("#selected_point").focus();
        }else{
            console.log("id=" + $(this).attr("id"));
            $("#selected_point_div").hide();
            $("#selected_point").val("");
        }
    })

    $("#selected_point").on("keyup", function() {
        $("#input_point").attr("value", $(this).val());
        console.log($("#input_point").val());
    })
})