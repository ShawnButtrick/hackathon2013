<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>

<head>
	<title>Prospero Message Sender Page</title>
	<link href="<c:url value="/resources/form.css" />" rel="stylesheet"  type="text/css" />		
	<script type="text/javascript" src="<c:url value="/resources/jquery/1.6/jquery.js" />"></script>
</head>

<body>
	<div id="formsContent">
		<h2>This page will allow you to SEND Prospero messages.</h2>
		<p>
			See <a href="http://code.pearson.com/prospero-integration-guide-version-1-producing-messages-rest" target="_blank">HERE</a> for more details about Prospero messages.
		</p>
		
		
		<form:form id="form" method="post" modelAttribute="sendBean" cssClass="cleanform">
			<div class="header">
		  		<h2>Form</h2>
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
				<legend>Environment</legend>
				<form:label path="enviro">
					(select one)
				</form:label>
				<form:select path="enviro">
					<form:option value="dev">Dev</form:option>
					<form:option value="test">Test</form:option>
					<form:option value="int">Int</form:option>
					<form:option value="ppe">PPE</form:option>
				</form:select>
		  	</fieldset>
		  	<fieldset>
		  		<legend>Client Info</legend>
		  		
		  		<form:label path="clientId">
		  			Client ID <form:errors path="clientId" cssClass="error" />
		 		</form:label>
		  		<form:input path="clientId" />
		  		
				<form:label path="clientString">
		  			Client String <form:errors path="clientString" cssClass="error" />
		 		</form:label>
		  		<form:input path="clientString" />
			</fieldset>
			
			<fieldset>
		  		<legend>System Info</legend>
		  		
		  		<form:label path="system">
		  			System <form:errors path="system" cssClass="error" />
		 		</form:label>
		  		<form:input path="system" />
		  		
				<form:label path="subSystem">
		  			Sub System <form:errors path="subSystem" cssClass="error" />
		 		</form:label>
		  		<form:input path="subSystem" />
		  	</fieldset>	
		  		
	  		<fieldset>
	  			<legend>Realm & Principal</legend>
	  			
	  			<form:label path="principal">
		  			Principal <form:errors path="principal" cssClass="error" />
		 		</form:label>
		  		<form:input path="principal" />
	  			
	  			<form:label path="key">
		  			Key <form:errors path="key" cssClass="error" />
		 		</form:label>
		  		<form:input path="key" />
	  			
		  		<form:label path="realm">
		  			Realm <form:errors path="realm" cssClass="error" />
		 		</form:label>
		  		<form:input path="realm" />
			</fieldset>
			
			<fieldset>
		  		<legend>Message Info</legend>
		  		
		  		<form:label path="messageType">
		  			Message Type <form:errors path="messageType" cssClass="error" />
		 		</form:label>
		  		<form:input path="messageType" />
		  		
		  		<form:label path="payloadContentType">
		  			Payload Content Type <form:errors path="payloadContentType" cssClass="error" />
		 		</form:label>
		  		<form:input path="payloadContentType" />
		  		
				<form:label path="payloadContent">
		  			Payload Content <form:errors path="payloadContent" cssClass="error" />
		 		</form:label>
		  		<form:input path="payloadContent" />
		  		
		  		<form:label path="tags">
		  			Tags <form:errors path="tags" cssClass="error" />
		 		</form:label>
		  		<form:input path="tags" />
		  		
			</fieldset>
			
			<fieldset>
		  		<form:label path="inquiryDetails">
		  			Details
		  		</form:label>
		  		<form:textarea path="inquiryDetails" />
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
