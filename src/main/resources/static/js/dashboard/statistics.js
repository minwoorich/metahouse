$(document).ready(function(){
	// 일일 방문자수 그래프
	var dailyVisitorsData = {	
		labels : [ '2023-05-01', '2023-05-02', '2023-05-03', '2023-05-04',	'2023-05-05' ],
		datasets : [{
			label : '일일 방문자수',
			data : [ 50, 60, 70, 80, 90 ],
			backgroundColor : 'rgba(75, 192, 192, 0.2)',
			borderColor : 'rgba(75, 192, 192, 1)',
			borderWidth : 1
		}]
	};

	var dailyVisitorsOptions = {
		scales : {
			y : {
				beginAtZero : true
			}
		}
	};

	var dailyVisitorsChart = new Chart(document
			.getElementById('dailyVisitorsChart'), {
		type : 'line',
		data : dailyVisitorsData,
		options : dailyVisitorsOptions
	});

	// 월별 방문자수 그래프
	var monthlyVisitorsData = {
		labels : [ '2023-01', '2023-02', '2023-03', '2023-04', '2023-05' ],
		datasets : [ {
			label : '월별 방문자수',
			data : [ 200, 300, 400, 350, 500 ],
			backgroundColor : 'rgba(54, 162, 235, 0.2)',
			borderColor : 'rgba(54, 162, 235, 1)',
			borderWidth : 1
		} ]
	};

	var monthlyVisitorsOptions = {
		scales : {
			y : {
				beginAtZero : true
			}
		}
	};

	var monthlyVisitorsChart = new Chart(document
			.getElementById('monthlyVisitorsChart'), {
		type : 'line',
		data : monthlyVisitorsData,
		options : monthlyVisitorsOptions
	});

	// 일별 수익 그래프
	var dailyRevenueData = {
		labels : [ '2023-05-01', '2023-05-02', '2023-05-03', '2023-05-04',
				'2023-05-05' ],
		datasets : [ {
			label : '일별 수익',
			data : [ 500, 600, 700, 800, 900 ],
			backgroundColor : 'rgba(255, 99, 132, 0.2)',
			borderColor : 'rgba(255, 99, 132, 1)',
			borderWidth : 1
		} ]
	};

	var dailyRevenueOptions = {
		scales : {
			y : {
				beginAtZero : true
			}
		}
	};

	var dailyRevenueChart = new Chart(document.getElementById('dailyRevenueChart'),
			{
				type : 'line',
				data : dailyRevenueData,
				options : dailyRevenueOptions
			});

	// 월별 수익 그래프
	var monthlyRevenueData = {
		labels : [ '2023-01', '2023-02', '2023-03', '2023-04', '2023-05' ],
		datasets : [ {
			label : '월별 수익',
			data : [ 2000, 3000, 2500, 3500, 4000 ],
			backgroundColor : 'rgba(255, 159, 64, 0.2)',
			borderColor : 'rgba(255, 159, 64, 1)',
			borderWidth : 1
		} ]
	};

	var monthlyRevenueOptions = {
		scales : {
			y : {
				beginAtZero : true
			}
		}
	};

	var monthlyRevenueChart = new Chart(document
			.getElementById('monthlyRevenueChart'), {
		type : 'line',
		data : monthlyRevenueData,
		options : monthlyRevenueOptions
	});

	// -----------------------------------------------------------------
	const DATA_COUNT = 7;
	const NUMBER_CFG = {
		count : DATA_COUNT,
		min : 0,
		max : 100
	};

	// 연령별 분포도 그래프
	var distributionByAgeData = {
		labels : [ '10대', '20대', '30대', '40대', '50대', '60대', '60대 이상' ],
		datasets : [ {
			label : 'Dataset 1',
			data : [ 40, 20, 15, 15, 5, 4, 1 ],
			backgroundColor : [ 'rgb(255, 99, 132)', 'rgb(54, 162, 235)',
					'rgb(255, 205, 86)', 'rgb(55, 235, 31)', 'rgb(21, 205, 86)',
					'rgb(225, 23, 86)', 'rgb(123, 205, 211)' ]
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
			.getElementById('distributionByAgeChart'), {
		type : 'doughnut',
		data : distributionByAgeData,
		options : distributionByAgeOptions
	});
})