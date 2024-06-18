<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	プロフィール</title>
</head>
<body>
	<header>
        <h1>プロフィール</h1>
    </header>
    <main>

        <c:if test="${empty userList}">
			<p>一致するデータはありません。</p>
		</c:if>
		<form  method="post" action="/LinX/UpdateServlet">
			<c:forEach var="user" items="${userList}" >
			<div>
        		<label for="id">氏名</label>
            	<input type="text" id="id" name="id" value="${user.employeeId}" >
        	</div>
			<div>
        		<label for="username">氏名</label>
            	<input type="text" id="username" name="username" value="${user.username}" >
        	</div>
        	<div>
        		<label for="lang">言語設定</label>
            	<input type="text" id="lang" name="lang" value="${user.lang}" >
        	</div>
        	<div>
        		<label for="birth">誕生日</label>
            	<input type="text" id="birth" name="birth" value="${user.birth}" >
        	</div>
        	<div>
        		<label for="comment">自己紹介</label>
            	<input type="text" id="comment" name="comment" value="${user.comment}" >
        	</div>

         	<div>
        		<label for="point">ポイント日</label>
            	<input type="text" id="point" name="point" value="${user.point}" >
        	</div>

        	<div>

         	<button type="submit" name="submit" value="編集">編集</button>

            <span id="error_message"></span>
         </div>

		</c:forEach>

		</form>


		<a href="/LinX/HomeServlet">ホームページ</a>

</main>

</body>
</html>