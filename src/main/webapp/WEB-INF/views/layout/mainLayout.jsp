<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
	/* Remove the navbar's default margin-bottom and rounded borders */
	*{
	    margin: 0;
	    padding: 0;
	}
	.row{
		display: flex;
		justify-content: center;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<div>
		<tiles:insertAttribute name="top"/>
	</div>
	<div class="row">
	 	<div class="col-sm-2">
	 		 <div style="border-color:black;" class="sidebar">
				<tiles:insertAttribute name="menu"/>
			</div>
	 	</div>
	 	<div class="col-sm-6"> 
	 		<tiles:insertAttribute name="content"/>	
	 	</div>
	</div>
	<div>
		<tiles:insertAttribute name="footer"/>
	</div>
	
</body>
</html>




