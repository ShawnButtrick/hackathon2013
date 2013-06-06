<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>

	<head>
		<title>Welcome to the Prospero Checker BUFFERS Page!</title>
	</head>
	
	
	<body>
		Allocated Buffers List:
		<table border="1">
			<tr>
    			<th>Buffer Name</th>
    			<th>Buffer Usage</th>
    			<th>Link To Contents</th>
   			</tr>
    			
    			
			<c:forEach items="${buffersList}" var="buffer">
				<tr>
					<td>
						<c:out value="${buffer.idString}" />
					</td>
					<td>
						<c:out value="${buffer.usage}" />
					</td>
					<td>
						<a href="<c:out value="${buffer.idString}"/>">LINK</a>
					</td>
				</tr> 
			</c:forEach>
		
		</table>					
</html>