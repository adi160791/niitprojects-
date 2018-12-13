<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
 <%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container">
		<div class="panel panel-info" style="width: 500px">
			
			<div class="panel-heading"><b>Select Or Add Shipping Address</b></div>

			<div class="panel-body">
					<table>
					<c:forEach items="${shipaddress}" var="address" >
						<tr>
							<td>${address.apartmentnumber },${address.streetname },${address.city },<br>
							${address.state },${address.country }, Pin :${address.zipcode }
							</td>
							<td><span style="display:inline-block; width: 70px;"></span></td>
							<td><a href="<c:url value='/cart/createorder'></c:url>" class="btn btn-success pull-right">
							Select Address </a>
							
							</td>
						</tr>
						<tr>
						<td>
						<br>
						</td>
						</tr>
						</c:forEach>
						<tr>
						<td>
						<br>
						</td>
						</tr>
						<tr>
						<td>
						<a href="<c:url value='/cart/getshippingform'></c:url>" class="btn btn-success pull-left"> Add New Address
						</a>
						</td>
						</tr>
					</table>
			</div>

		</div>
	</div>
</body>
</html>