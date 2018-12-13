<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
      <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Users</title>
<form:form method="POST" action="/ekart/addUsers">    
        <table >    
        <tr>  
        <td></td> 
         <tr>    
          <td>userid : </td>   
          <td><form:input path="userId"  /></td>  
         </tr>   
         <tr>    
          <td>firstName : </td>   
          <td><form:input path="firstName"  /></td>  
         </tr>    
         <tr>    
          <tr>  
          <tr>  
        <td></td>    
         <tr>    
          <td>lastName : </td>   
          <td><form:input path="lastName"  /></td>  
         </tr>      
         <tr>  
        <td></td>    
         <tr>    
          <td>contact : </td>   
          <td><form:input path="contact"  /></td>  
         </tr> 
          <tr>    
          <td>password : </td>   
          <td><form:input path="password"  /></td>  
         </tr> 
         <tr>    
          <td>useraddress : </td>   
          <td><form:input path="userAddress"  /></td>  
         </tr>
         
          <tr>    
          <td> </td>    
          <td><input type="submit" value="Save" /></td>    
         </tr>  
        </table>    
       </form:form>    
</head>
<body>

</body>
</html>