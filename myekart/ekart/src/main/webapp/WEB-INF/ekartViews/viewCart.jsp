<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<c:if test="${error!=null}">
<h1 style="background-color:Blue;">Product {product.productId} has been removed from cart due to out of stock!!</h1>
</c:if>
<div>
<div>

<table class="table table-striped">
<thead id="thead">
<tr><th>ProductName</th><th>Quantity</th><th>Total Price</th><th>Remove</th>
</tr>
</thead>
<!-- double grandTotal=0 -->
<c:set var="grandTotal" value="0"></c:set>
<tbody id="tbody">
<c:forEach items="${listC}" var="cartItem">
<tr>
<td>${cartItem.product.productName}</td>
<td>${cartItem.qty }</td>
<td>${cartItem.subTotal }</td>

<td><a href="<c:url value='/cart/deleteCart/${cartItem.cartItemId}'></c:url>" class="label label-danger" pull-left>

<span class="glyphicon glyphicon-remove" ></span>Remove
</a></td>
<!--  grandTotal = cartItem.totalPrice + grandTotal -->

<c:set var="grandTotal" value="${grandTotal + cartItem.subTotal }"></c:set>
</tr>
<hr>
</c:forEach>
</tbody>
</table>
<b>Total Price : ${grandTotal }</b>
</div>
<c:if test="${empty(listC) }">
<h3 style="background-color:Red;">Your Cart is empty..Please Add product </h3>
</c:if>


<c:if test="${!empty(listC)}">

<a href="<c:url value='/cart/checkout'></c:url>" class="btn btn-success pull-right">
<span class="glyphicon glyphicon-shopping-cart"></span> Place Order  </a>
</c:if>

</body>
</html>