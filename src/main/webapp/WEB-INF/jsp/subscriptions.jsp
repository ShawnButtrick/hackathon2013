<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>

	<head>
		<title>Subscriptions View Page</title>
	</head>
	
	
	<body>
		<c:if test="${not empty subscriptions.errorString}">
			<c:out value="${subscriptions.errorString}"/>
		</c:if>
	
		<table border="1">
			<tr>
    			<th>id</th>
    			<th>principal id</th>
    			<th>queue name</th>
				<th>tags</th>
				<th>callback url</th>
				<th>date created</th>
    			<th>date cancelled</th>
				<th>Delete Subscription Link</th>
  			</tr>
  			
			<c:forEach items="${subscriptions.anonSubscriptionsList}" var="anonSub">
				<tr>
					<td>
						<c:out value="${anonSub.subscription.id}" />
					</td>
					<td>
						<c:out value="${anonSub.subscription.principal_id}" />
					</td>
					<td>
						<c:out value="${anonSub.subscription.queue_name}" />
					</td>
					<td>
						<ul>
						<c:forEach items="${anonSub.subscription.tags}" var="anonTag">
							<li><c:out value="${anonTag.tag.id}" /></li>
						</c:forEach>
						</ul>
					</td>
					<td>
						<c:out value="${anonSub.subscription.callback_url}" />
					</td>
					<td>
						<c:out value="${anonSub.subscription.date_created}" />
					</td>
					
					<td>
						<c:if test="${empty anonSub.subscription.date_cancelled}">
							<c:out value="---"/>
						</c:if>
						<c:out value="${anonSub.subscription.date_cancelled}" />
					</td>
					<td>
						<a href="subscriptions/delete/<c:out value="${anonSub.subscription.id}" />"><img src="/ProsperoChecker/resources/skull.png" height="50" width="50"></a>
					</td>
				</tr>
			</c:forEach>
		</table>
		
	</body>

</html>