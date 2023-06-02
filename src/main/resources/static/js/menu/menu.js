$(document).ready(function(){
    $(".menu_detail_ul").on("click", function(){ 
    	var submenu = $(this).next("ul");
        if( submenu.is(":visible") ){
            submenu.slideUp();
        }else{
            submenu.slideDown();
        }
    });
});