 $(document).ready(function () {
      $('.nav-tabs a').click(function () {
        var tabId = $(this).attr('href');
        if (tabId === '#services') {
          $('.row .card').show();
          $('.expert-card').hide();
        } else if (tabId === '#experts') {
          $('.row .card').hide();
          $('.expert-card').show();
        }
      });

      $('.pagination a').click(function (e) {
        e.preventDefault();
        var page = $(this).text(); // 클릭한 페이지 번호
        // 페이지 이동을 위한 추가적인 로직을 여기에 작성하세요.
        console.log('Go to page', page);
      });
      
      
      
});
 
 function goLink(type , no){
	 let link = "";
	  if(type ==="a"){
		  link = "/metahaus/asset/detail?assetNum="+no;
	  }else{
		 link =  "/project/detail?projectNum="+no;
	  }
	  
	  location.href = link;
 }