<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>

	<head>
		<title>Welcome to the Prospero Checker!</title>
	</head>
	
	
	<body>
		<hr>
		View Summary
		<ul>
			<li><a href="summary">Summary</a></li>
		</ul>
		
		<hr>
		View Subscriptions
		<ul>
			<li><a href="dev/subscriptions">Dev</a></li>
			<li><a href="test/subscriptions">Test</a></li>
			<li><a href="int/subscriptions">Int</a></li>
			<li><a href="ppe/subscriptions">PPE</a></li>
		</ul>
		
		<hr>
		View Buffers
		<ul>
			<li><a href="buffers/">Buffers List</a></li>
		</ul>
		
		<hr>
		View Forms
		<ul>
			<li><a href="sendform">Send Form</a></li>
			<li><a href="subscribeform">Subscribe Form</a></li>
		</ul>
		
		
	</body>

</html>