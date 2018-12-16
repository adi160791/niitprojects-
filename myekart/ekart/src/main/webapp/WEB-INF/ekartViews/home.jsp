
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp" %>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>EKART</title>
<style>
body 
  {
   
  background-color: #e6e6e6;
}
</style>
</head>
<body>
<div class="container">
  <h2 style="background-color:violet;">WELCOME TO EKART</h2>  
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
      <div class="item active">
       <img src="${pageContext.request.contextPath}/resources/images/image3.jpg" alt="3" style="width:100%;">>
      </div>

      <div class="item">
        <img src="${pageContext.request.contextPath}/resources/images/image2.jpg" alt="2" style="width:100%;">>
      </div>
    
      <div class="item">
       <img src="${pageContext.request.contextPath}/resources/images/image1.jpg" alt="1" style="width:100%;">>
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
  <div class="col-sm-8 text-left"> 
      <h1>Welcome</h1>
      <p style="background-color:green;">welcome to Ekart ....
         enjoy various deals and amazing products ....
         we hope to hear from you ...
         Happy Shopping !!!!  
     </p>
      <hr>
      <h3></h3>
      <p></p>
    </div>
</div>
<footer class="container-fluid text-center">
  <p style="background-color:yellow;">EKART CONTACT NUMBER - 9988774455</p>
</footer>

</body>
</html>
