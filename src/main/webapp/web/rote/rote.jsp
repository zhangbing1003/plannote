<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/web/taglib/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="routeApp">
<head>
<title></title>
<script src="../angular/angular.min.js"></script>
<script src="../angular/angular-route.js"></script>
<script type="text/javascript" src="../angular/app.js"></script>
<script type="text/javascript" src="../angular/main.js"></script>
</head>
<body >  
<h1>Route Demo index</h1>  
<div ng-view></div>
</body> 

</html>