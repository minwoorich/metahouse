$(document).ready(()=>{

	$(searchForm).on("submit", (e)=>{
		const selectCate = document.getElementById("searchCategory");
		if(selectCate == null) return;
		
		const category = document.getElementById("searchCategory").value;
		const option = document.getElementById("searchOption").value;
		const keyword = keywordInput.value;
		
		const cate = document.createElement("input");
		cate.value = category;
		cate.name = "category";
		cate.setAttribute("type","hidden");
		const opt = document.createElement("input");
		opt.value = option;
		opt.name = "option";
		opt.setAttribute("type","hidden");
		
		searchForm.appendChild(cate);
		searchForm.appendChild(opt);
		
	});
	
});



var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
(function(){
var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
s1.async=true;
s1.src='https://embed.tawk.to/64a79c3fcc26a871b026dfce/1h4nbkmct';
s1.charset='UTF-8';
s1.setAttribute('crossorigin','*');
s0.parentNode.insertBefore(s1,s0);
})();
