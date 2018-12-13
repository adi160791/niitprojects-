<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
 <title>Header</title>
   <script defer src="https://use.fontawesome.com/releases/v5.0.1/js/all.js"></script>
  
  <c:set var="context" value="${pageContext.request.contextPath}"/>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
     
      <a class="navbar-brand" href="#"><i class="fas fa-shopping-cart"></i>ekart</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
           <!-- each <li> </li> is one menu option -->
        <li class="active"><a href="${pageContext.request.contextPath}/index">Home</a></li>
        <!-- <li class="dropdown"> is used to create a menu option which will have sub menu -->
        <li class="dropdown">
        
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Products <span class="caret"></span></a>
         <!-- <ul class="dropdown-menu">  is used to create the sub menu items -->
          <ul class="dropdown-menu">
          
          <!-- display category names as menu option  -->
           <c:forEach items="${categoryList}" var="category">
            <li><a href="${pageContext.request.contextPath}/viewCategoryProduct/${category.categoryId}">${category.categoryName}</a></li>
             </c:forEach>
          </ul>
        </li>
        
       <security:authorize access="hasRole('ROLE_ADMIN')"><li></li></security:authorize>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        
        
        <c:if test="${pageContext.request.userPrincipal.name==null}"><li><a href="${pageContext.request.contextPath}/loginPage"><span class="glyphicon glyphicon-log-in"></span> Login</a></li></c:if>
        <c:if test="${pageContext.request.userPrincipal.name!=null}"><li><a href="">Hello ${pageContext.request.userPrincipal.name}</a></li></c:if>
         <c:if test="${pageContext.request.userPrincipal.name!=null }">
				<li><a href='<c:url value="/j_spring_security_logout"></c:url>'>logout</a></li>
				</c:if>

     
      
          <c:if test="${pageContext.request.userPrincipal.name==null}"><li><a href="${pageContext.request.contextPath}/register"><span class="glyphicon glyphicon-register"></span> Sign UP</a></li></c:if>
        
      </ul>
      
      <ul class="nav navbar-nav">

<security:authorize access="hasRole('ROLE_ADMIN')">
<li><a href="${context}/viewProducts">Admin Control</a></li>
</security:authorize>

<security:authorize access="hasRole('ROLE_USER')">
<li><a href="${pageContext.request.contextPath}/cart/viewCart">Cart</a></li>
</security:authorize>

<%-- <c:if test="${pageContext.request.userPrincipal.name==null}"><li><a href="${pageContext.request.contextPath}/register">SignUp</a></li></c:if>
<c:if test="${pageContext.request.userPrincipal.name!=null}"><li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li></c:if>
<c:if test="${pageContext.request.userPrincipal.name==null}"><li><a href="${pageContext.request.contextPath}/loginPage">Login</a></li></c:if> --%>


</ul>
      
      
    </div>
  </div>
</nav>
