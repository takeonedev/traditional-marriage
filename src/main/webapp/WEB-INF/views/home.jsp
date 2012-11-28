<%@ page contentType = "text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<table>
	<thead>
		<tr>
			<td>Female</td>
			<td></td>
			<td>Male</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="match" items="${matches}">
			<tr>
				<td>${match.value.name}</td>
				<td>---></td>
				<td>${match.key.name}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>
