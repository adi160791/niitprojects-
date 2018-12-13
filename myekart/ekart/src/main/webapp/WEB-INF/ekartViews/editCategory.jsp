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
<h1>edit Category</h1>  
       <form:form method="POST" action="/ekart/editsave">    
        <table >    
        <tr>  
        <td></td>    
         <td><form:hidden  path="categoryId" /></td>  
         </tr>   
         <tr>    
          <td>CategoryName : </td>   
          <td><form:input path="categoryName"  /></td>  
         </tr>    
         <tr>    
          <tr>    
          <td> </td>    
          <td><input type="submit" value="Edit Save" /></td>    
         </tr>    
        </table>    
       </form:form>    
</body>
</html>