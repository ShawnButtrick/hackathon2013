<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>

	<head>
		<title>Results View Page</title>
	</head>
	
	
	<body>
		<c:if test="${not empty results.errorString}">
			<c:out value="${results.errorString}"/>
		</c:if>
	
		<table border="1">
			<tr>
    			<th>column a</th>
    			<th>column b</th>
  			</tr>
  			
			<c:forEach items="${results.resultsList}" var="result">
				<tr>
					<td>
						<c:out value="${result.stringA}" />
					</td>
					<td>
						<c:out value="${result.stringB}" />
					</td>
				</tr>
			</c:forEach>
		</table>
		
	</body>

</html>