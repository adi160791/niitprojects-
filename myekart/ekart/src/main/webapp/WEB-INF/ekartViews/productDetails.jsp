<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
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

<h1>Product List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>productId</th><th>productName</th><th>productDesc</th><th>price</th><th>quantity</th><th>productCategory</th><th>image</th></tr>  

<tr>  
   <td>${productDescription.productId}</td>  
   <td>${productDescription.productName}</td> 
   <td>${productDescription.productDesc}</td> 
   <td>${productDescription.price}</td> 
   <td>${productDescription.quantity}</td> 
   <td>${productDescription.productCategory.categoryName}</td>
   <td><img src="${pageContext.request.contextPath}/${productDescription.productImage}" height="50" width="50"></td> 
   
   
   </tr>
   
   
   
   
   </table>



</body>
</html>