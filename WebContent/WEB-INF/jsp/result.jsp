<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css">
<title>LinX</title>
</head>

<body class="text-center">


  	<div style=" margin-top: 180px; margin-bottom: 200px;">
  		<h1>${result.title}</h1>
		<p>${result.message}</p>
		<a href="${result.backTo}">戻る</a>
  	</div>

</body>

</html>
