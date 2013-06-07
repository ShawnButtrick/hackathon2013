<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>

<head>
	<title>Playlist Downloader Page</title>
	<link href="<c:url value="/resources/form.css" />" rel="stylesheet"  type="text/css" />		
	<script type="text/javascript" src="<c:url value="/resources/jquery/1.6/jquery.js" />"></script>
</head>

<body>
	<div id="formsContent">
		<h2>Blah blah blah ...........</h2>
		
		<form:form id="form" method="post" modelAttribute="subscribeBean" cssClass="cleanform">
			<div class="header">
		  		<h2>Form</h2>
		  		<c:if test="${not empty error}">
					<div id="error" class="error">${error}</div>	
		  		</c:if>
		  		<c:if test="${not empty message}">
					<div id="message" class="info">${message}</div>	
		  		</c:if>
		  		<s:bind path="*">
		  			<c:if test="${status.error}">
				  		<div id="message" class="error">Form has errors</div>
		  			</c:if>
		  		</s:bind>
			</div>
			
		  	<fieldset>
		  		<legend>Download</legend>
		  		
				<form:label path="searchString">
		  			Youtube Playlist ID <form:errors path="searchString" cssClass="error" />
		 		</form:label>
		  		<form:input path="searchString" />
		  		
		  		<form:label path="maxResults">
		  			Max Results to Get <form:errors path="maxResults" cssClass="error" />
		 		</form:label>
		  		<form:input path="maxResults" />
			</fieldset>
			
			<p><button type="submit">Submit</button></p>
		</form:form>
		
		<p>
			See the <code>org.springframework.samples.mvc.form</code> package for the sample code that this was derived from.
		</p>

		<script type="text/javascript">
			$(document).ready(function() {
				$("#form").submit(function() {  
					$.post($(this).attr("action"), $(this).serialize(), function(html) {
						$("#formsContent").replaceWith(html);
						$('html, body').animate({ scrollTop: $("#message").offset().top }, 500);
					});
					return false;  
				});			
			});
		</script>
	</div>

</body>

</html>
