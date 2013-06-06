<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>A simple AJAX website with jQuery</title>
<link rel="stylesheet" type="text/css" href="demo.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    var status = ${status} ;
    appendTable(status);
    //$('#submit').bind('click',
	//	function(event, ui){
	//		appendTable($('input').val());
	//}); 
});

function appendTable(json_data){

    $('table tr').remove();
    var table_obj = $('table');
    $.each(json_data, 
        function(index, item){
    		var tags = '<ul>';
    		$.each(item.subscription.tags,
    		        function(index, item){
    					tags = tags + '<li>' +item.tag.id +'</li>';
    				});		
    		tags = tags + '</ul>';
            table_obj.append($('<tr id="'+item.subscription.id+'"><td><a href="delete?url=${url}&principal=${principal}&subId='+item.subscription.id+'">Delete '+item.subscription.id+'</a></td><td>'+item.subscription.principal_id+'</td><td>'+item.subscription.callback_url+'</td><td>'+item.subscription.wsdl_uri+'</td><td>'+item.subscription.queue_name+'</td><td>'+item.subscription.date_created+'</td><td>'+item.subscription.date_cancelled+'</td><td>'+item.subscription.id+'</td><td>'+tags+'</td></tr>'));
        });
}

</script>

</head>

<body>
<div>
    <table border="1">
        <tr>
            <td>row 1, cell 1</td>
            <td>row 1, cell 2</td>
        </tr>
        <tr>
            <td>row 2, cell 1</td>
            <td>row 2, cell 2</td>
        </tr>
    </table> 
</div>

<div>
    <input type = "hidden" id="principal" value="${principal}" />
    <input type = "hidden" id="url" value="${url}" /> 
</div>

</body>
</html>