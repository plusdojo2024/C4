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
        		<label for="id">社員ID</label>
            	<input type="text" id="id" name="id" value="${user.employeeId}" readonly="readonly" >
        	</div>
			<div>
        		<label for="username">氏名</label>
            	<input type="text" id="username" name="username" value="${user.username}" >
        	</div>

        	<%-- <div>
        		<label for="lang">話せる言語</label>
            	<input type="text" id="lang" name="lang" value="${user.lang}" >
        	</div> --%>

        	<!--改造  -->
			<div>
        		<label for="lang">話せる言語</label>
            	<input type="text" id="lang" name="lang" value="${user.lang}" >
        	</div>


        	<div>
				<label for="birth">誕生日</label>
            	<input type="date" id="birth" name="birth" value="${user.birth}" ><br>
        	</div>
        	<div>
        		<label for="comment">自己紹介</label>
            	<input type="text" id="comment" name="comment" value="${user.comment}" >
        	</div>

         	<div>
        		<label for="point">ポイント日</label>
            	<input type="text" id="point" name="point" value="${user.point}" >
        	</div>

        	<input type="checkbox" name="languages" value="English"> English/英語<br>
        	<input type="checkbox" name="languages" value="Japanese"> Japanese/日本語<br>
        	<input type="checkbox" name="languages" value="Burma">Burma/ビルマ語<br>
        	<input type="checkbox" name="languages" value="Chinese"> Chinese/中国語<br>
        	<input type="checkbox" name="languages" value="Korean"> Korean/韓国語<br>
        	<input type="checkbox" name="languages" value="Vietnamese"> Vietnamese/ベトナム語<br>
        	<input type="checkbox" name="languages" value="Malay"> Malay/マレー語<br>
        	<input type="checkbox" name="languages" value="Thai">Thai/タイ語<br>
        	<input type="checkbox" name="languages" value="Spanish">Spanish/スペイン語<br>
        	<input type="checkbox" name="languages" value="German"> German/ドイツ語<br>
        	<input type="checkbox" name="languages" value="Russian"> Russian/ロシア語<br>
        	<input type="checkbox" name="languages" value="Italian"> Italian/イタリア語<br>
        	<input type="checkbox" name="languages" value="Portuguese"> Portuguese/ポルトガル語<br>
        	<input type="checkbox" name="languages" value="Arabic"> Arabic/アラビア語<br>
        	<input type="checkbox" name="languages" value="French"> French/フランス語<br>

         	<button type="submit" name="submit" value="更新">編集</button>

            <span id="error_message"></span>


		</c:forEach>

		</form>


		<a href="/C4/HomeServlet">ホームページ</a>

</main>

</body>
</html>