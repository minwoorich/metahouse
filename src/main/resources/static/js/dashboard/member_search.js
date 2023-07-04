$(document).ready(function(){
	
	const searchInput = document.getElementById("search_input");
	
	searchInput.addEventListener("keyup", (e)=>{
		if(e.keyCode != 13) return;
		
		console.log("keyup");
		onSearch(searchInput.value);
	});
	
	function onSearch(keyword){
		$.ajax({
			url:"/metahaus/dashboard/member/search",
			type:"POST",
			data:{keyword:keyword},
			
			success:(res)=>{
				console.log("ajax success");
				$(searchResult).empty();
				
				res.forEach((e)=>{
					console.log("forEach");
					let tr = createResult(e);
					searchResult.appendChild(tr);
				});
				
				
			},
			
		});
	}
	
	function createResult(data){
		
		userEditForm.user_id.value = data.userId;
		userEditForm.email.value = data.email;
		userEditForm.user_name.value = data.userName;
		userEditForm.password.value = data.password;
		userEditForm.phone_number.value = data.phoneNumber;
		userEditForm.gender.value = data.gender;
		userEditForm.birth.value = data.birth;
		userEditForm.self_introduction.value = data.selfIntroduction;
		
		let tr = document.createElement("tr");
		
		const idTd = document.createElement("td");
		idTd.textContent = data.userId;
		
		const emailTd = document.createElement("td");
	    emailTd.textContent = data.email;

	    const nameTd = document.createElement("td");
	    nameTd.textContent = data.userName;

	    const phoneTd = document.createElement("td");
	    phoneTd.textContent = data.phoneNumber;

	    const genderTd = document.createElement("td");
	    genderTd.textContent = data.gender;

	    const birthTd = document.createElement("td");
	    birthTd.textContent = data.birth;

	    const createTd = document.createElement("td");
	    
	    let d = new Date(data.createDate);
	    
	    let dateString = d.getFullYear() + "년 " +
	    (d.getMonth()+1) + "월 " + d.getDate() +
	    "일 " + d.getHours() + "시 " +  '일월화수목금토'.charAt(d.getUTCDay())+'요일';
	    
	    createTd.textContent = dateString;

	    const gradeTd = document.createElement("td");
	    gradeTd.textContent = data.userGrade;

	    const pointTd = document.createElement("td");
	    pointTd.textContent = data.point;

	    tr.appendChild(idTd);
	    tr.appendChild(emailTd);
	    tr.appendChild(nameTd);
	    tr.appendChild(phoneTd);
	    tr.appendChild(genderTd);
	    tr.appendChild(birthTd);
	    tr.appendChild(createTd);
	    tr.appendChild(gradeTd);
	    tr.appendChild(pointTd);

	    return tr;

	}
	
	
})