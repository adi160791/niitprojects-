<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
    <%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Register New Shipping Address </h1>
<form:form method="post" action="/cart/createorder">
<table > 

		<tr>
        <td> id : </td>  
  		<td><form:hidden path="id" /></td>
  		</tr>
  		 
  		 <%-- <tr>
         <td> UserID : </td> 
         <td><form:hidden  path="user" /></td>
         </tr> --%>
         
         <tr>    
          <td> Apartment Number : </td>   
          <td><form:input path="apartmentnumber"  /></td>  
          
         </tr>    
         
         <tr>    
          <td> Street Name: </td>   
          <td><form:input path="streetname"  /></td>  
         </tr> 
      
         <tr>    
          <td> City : </td>   
          <td><form:input path="city"  /></td>  
         </tr>    
         
         <tr>    
          <td> State : </td>   
          <td><form:input path="state"  /></td>  
         </tr>  
         
         
         <tr>    
          <td> Country : </td>   
          <td><form:input path="country"  /></td>  
         </tr>  
                   
         <tr>    
         <td> Pin Code : </td>   
         <td><form:input path="zipcode"  /></td>  
         </tr> 
               
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Save" /></td>    
         </tr>    
        </table>    
       </form:form>
       
</body>
</html>