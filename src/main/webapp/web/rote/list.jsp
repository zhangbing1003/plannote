<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/web/taglib/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<script src="../angular/angular.min.js"></script>
<script src="../angular/angular-route.js"></script>
<script type="text/javascript" src="../angular/app.js"></script>
<script type="text/javascript" src="../angular/main.js"></script>
</head>
<body>
  <hr/>  
<h3>Route : List.html</h3>  
<ul>  
    <li ng-repeat="id in [1, 2, 3 ]">  
        <a href="#/list/{{ id }}"> ID{{ id }}</a>  
    </li>  
</ul> 
</body>
</html>