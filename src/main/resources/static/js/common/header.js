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


