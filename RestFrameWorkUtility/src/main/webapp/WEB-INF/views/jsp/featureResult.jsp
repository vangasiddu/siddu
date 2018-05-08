<%@page contentType="text/html;charset = UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href='https://fonts.googleapis.com/css?family=Merienda'
	rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Dekko'
	rel='stylesheet'>
<link href="<c:url value="/resources/css/osiform.css" />" rel="stylesheet">

<html>
<head>
<style>
td.rowData1 {
	width: 150px;
	font: 15pt serif;
	text-align: justify;
	left: 10px;
}

.table-curved {
	border-collapse: separate;
	border: solid E1E0CEc 4px;
	overflow: hidden;
	align: center;
}




</style>
<title>Feature File Details</title>


<table align="center">
	<h1 align="center"
		style="font: 17pt Seri Bold SC; color: white; font-weight: Bold">
		 
Feature File Step Def, Test Layer created successfully 
 
		</h1>
</table>
</head>

<body background="<c:url value='/resources/img/osi.jpg' />">
	
	<table align="center" class="table-curved" " bgcolor="white "
		style="font-family: Merienda;">





		<tr>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">Scenario</td>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">${scenario}</td>
		</tr>

		<tr>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">Request
				Method</td>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">${requestMethod}</td>
		</tr>


		<tr>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">URI</td>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">${uri}</td>
		</tr>

		<tr>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">Query
				Parameters</td>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">${queryParameters}</td>
		</tr>

		<tr>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">Path
				Parameters</font>
			</td>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">${pathParameters}</td>
		</tr>

		<tr>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">Header
				Parameters</td>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">${headerParameters}</td>
		</tr>


		<tr>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">Request
				File</td>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">${requestFile}</td>
		</tr>

		<tr>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">Response
				File</td>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">${responseFile}</td>
		</tr>
		<tr>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">Feature
				File Name</td>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">${featureFile}</td>
		</tr>

		<tr>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">Step Def
				File Name</td>
			<td bgcolor="E1E0CE" style="font: 15pt Seri Bold SC;">${stepDefFile}</td>
		</tr>

	</table>
</body>

</html>