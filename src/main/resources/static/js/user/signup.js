	$(document).ready(function(){
		
		$("#allcheck").on("click", function(){
			if($("#allcheck").is(":checked")){
				$(".chk").prop("checked", true);
			}else{
				$(".chk").prop("checked", false);
			}
		});
		
		$(".chk").on("click", function(){
			var total_chk_num = $(".chk").length;
			var checked = $(".chk:checked").length;
			
			if(total_chk_num != checked){
				$("#allcheck").prop("checked", false);
			}else{
				$("#allcheck").prop("checked", true);
			}
		});
		
		$("#signup_submit").on("click", function(){
			let isValid = true;
	    	//alertMsg = $("<div>항목을 채워주세요</div>").attr("class", "alert-msg");
	    	
	    	$('.essential').each(function() {
				  if ($(this).val().trim() === '') {
					  alertMsg = $("<div>항목을 채워주세요</div>").attr("class", "alert-msg");
				//	  alert("test")
					//  alert($(this).val())
					  $(this).parent().append(alertMsg);
					  $(this).addClass('alert-style');
					  isValid=false;
				  }else{
					  $(this).removeClass('alert-style');
				  }
	    	});
	    	
	    	$('.essential_chk').each(function(){
	    		if (!$(this).prop('checked')){
	    			if($(this).next().is('label')){
	    				$(this).next().addClass('alert-style-chk');
	    			}else{
	    				$(this).siblings('label').addClass('alert-style-chk');
	    			}
	    			isValid=false;
	    		}else{
	    			if($(this).next().is('label')){
	    				$(this).next().removeClass('alert-style-chk');
	    			}else{
	    				$(this).siblings('label').removeClass('alert-style-chk');
	    			}
	    		}
	    	});
	    	
	    	
	    	if(isValid){
	    		location.href="/metahaus/login"
	    		return true;
	    	}else{
	    		$('html, body').animate({ scrollTop: 0 }, "0.5s");
	    		alert("필수 입력사항은 모두 입력해주세요.")
	    		return false;
	    	}
		});
	});