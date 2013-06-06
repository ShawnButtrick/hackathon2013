<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>

	<head>
		<title>Prospero Status Summary Across Environments</title>
	</head>
	
	
	<body>
		<table border="1">
			<tr>
    			<th>Enviro</th>
    			<th>Status or Error</th>
   			</tr>
    			
    			
			<c:forEach items="${statusesSummary.prosperoStatuses}" var="prosperoStatus">
				<tr>
					<td>
						<c:out value="${prosperoStatus.enviro}" />
					</td>
					<td>
						<c:if test="${empty prosperoStatus.error}">
							<FONT COLOR="Green">Served By:</FONT>
							<c:out value="${prosperoStatus.status.served_by}" />
							<br>
							<FONT COLOR="Green">Current Time:</FONT>
							<c:out value="${prosperoStatus.status.current_time}" />
							<br>
							<br>
							<FONT COLOR="Green">Nodes:</FONT>
							<ol>
							<c:forEach items="${prosperoStatus.status.up_nodes}" var="anonNode">
								<li>
									<c:out value="${anonNode.node.name}" />
								</li>
							</c:forEach>
							 </ol>
						</c:if>
						
						<c:if test="${not empty prosperoStatus.error}">
							<FONT COLOR="Red">
							<c:out value="${prosperoStatus.error}" />
							</FONT>
						</c:if>
					</td>
				</tr> 
			</c:forEach>
		
		</table>			
	</body>		
</html>