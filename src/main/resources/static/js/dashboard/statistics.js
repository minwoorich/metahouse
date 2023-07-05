$(document).ready(function(){

	// 에셋 갯수 목록
	var distributionByAgeData = {
		labels : [ '마인크래프트', '제페토', '로블록스', '게더타운', '더샌더박스',],
		datasets : [ {
			label : 'Dataset 1',
			data : [ 40, 20, 15, 15, 5,  ],
			backgroundColor : [ 'rgb(255, 99, 132)', 'rgb(54, 162, 235)',
					'rgb(255, 205, 86)', 'rgb(55, 235, 31)', 'rgb(21, 205, 86)',
					 ]
		} ]
	};

	var distributionByAgeOptions = {
		responsive : true,
		plugins : {
			legend : {
				position : 'top',
			},
			title : {
				display : true,
				text : 'Chart.js Doughnut Chart'
			}
		}
	};

	var distributionByAgeChart = new Chart(document
			.getElementById('d1'), {
		type : 'doughnut',
		data : distributionByAgeData,
		options : distributionByAgeOptions
	});
	
	// 프로젝트 갯수 목록
	var distributionByAgeData2 = {
		labels : [ '마인크래프트', '제페토', '로블록스', '게더타운', '더샌더박스',],
		datasets : [ {
			label : 'Dataset 1',
			data : [ 40, 20, 15, 15, 5,   ],
			backgroundColor : [ 'rgb(255, 99, 132)', 'rgb(54, 162, 235)',
					'rgb(255, 205, 86)', 'rgb(55, 235, 31)', 'rgb(21, 205, 86)',
					 ]
		} ]
	};

	var distributionByAgeOptions2 = {
		responsive : true,
		plugins : {
			legend : {
				position : 'top',
			},
			title : {
				display : true,
				text : 'Chart.js Doughnut Chart'
			}
		}
	};

	var distributionByAgeChart = new Chart(document
			.getElementById('d2'), {
		type : 'doughnut',
		data : distributionByAgeData2,
		options : distributionByAgeOptions2
	});
})