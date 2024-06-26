<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>プロフィール | LinX</title>
<link rel="stylesheet" type="text/css" href="css/user.css">
<link rel="stylesheet" href="./css/popup.css">
<link rel="icon" href="./img/LinXicon.ico" type="image/png">
</head>
<body>
	<header> </header>
	<main style="margin-top: 250px">
		<!-- Photo Update Form -->
		<form method="post" action="/C4/UploadIconServlet"
			enctype="multipart/form-data">

			<div>
				<input type="text" id="id" name="id" hidden="hidden"
					value="${user.employee_Id}">
			</div>

			<%-- <div class="form-group">
                <div class="profile-pic-wrapper">
					 <img src="./images/${user.icon}" class="profile-pic">
				    <input type="file" id="icon" name="icon" accept="images/*" >

                </div>
			</div> --%>

			<div class="form-group">
				<div class="profile-pic-wrapper" onclick="document.getElementById('icon').click();">
					<img src="./img/${user.icon}" class="profile-pic" alt="Profile Picture">
					<div class="profile-pic-overlay">アップロード</div>
					<input type="file" id="icon" name="icon" accept="image/*" style="display: none;">
				</div>
			</div>
			<button type="submit" class="btn btn-secondary" name="submit"
				value="変更">変更</button>
		</form>

		<!-- Update Information Form -->
		<form method="post" action="/C4/UpdateServlet">

			<div class="form-group">
				<label for="id">社員ID</label> <input type="text" id="id" name="id"
					value="${user.employee_Id}" readonly="readonly">
			</div>

			<div class="form-group">
				<label for="username">氏名</label> <input type="text" id="username"
					name="username" value="${user.username}">
			</div>

			<div class="form-group">
				<label for="lang">話せる言語</label>
				<c:forEach var="lang" items="${user.langList}">
					<input type="text" id="lang" name="lang" value="${lang}">
				</c:forEach>
				<button type="button" onclick="view()">言語選択</button>
				<div id="popup" class="popup">
					<input type="checkbox" name="languages" value="English">
					English/英語 <input type="checkbox" name="languages" value="Japanese">
					Japanese/日本語 <input type="checkbox" name="languages" value="Burma">Burma/ビルマ語
					<input type="checkbox" name="languages" value="Chinese">
					Chinese/中国語<br> <input type="checkbox" name="languages"
						value="Korean"> Korean/韓国語 <input type="checkbox"
						name="languages" value="Vietnamese"> Vietnamese/ベトナム語 <input
						type="checkbox" name="languages" value="Malay"> Malay/マレー語
					<input type="checkbox" name="languages" value="Thai">Thai/タイ語<br>
					<input type="checkbox" name="languages" value="Spanish">Spanish/スペイン語
					<input type="checkbox" name="languages" value="German">
					German/ドイツ語 <input type="checkbox" name="languages" value="Russian">
					Russian/ロシア語<br> <input type="checkbox" name="languages"
						value="Italian"> Italian/イタリア語 <input type="checkbox"
						name="languages" value="Portuguese"> Portuguese/ポルトガル語 <input
						type="checkbox" name="languages" value="Arabic">
					Arabic/アラビア語 <input type="checkbox" name="languages" value="French">
					French/フランス語<br>
					<button type="button" onclick="hide()">完了</button>
				</div>
			</div>

			<div class="form-group">
				<label for="birth">誕生日</label> <input type="date" id="birth"
					name="birth" value="${user.birth}"><br>
			</div>
			<div class="form-group">
				<label for="comment">自己紹介</label> <input type="text" id="comment"
					name="comment" value="${user.comment}">
			</div>

			<div class="form-group">
				<label for="point">ポイント</label> <input type="text" id="point"
					name="point" value="${user.point}" readonly="readonly">
			</div>

			<button type="submit" class="btn btn-secondary" name="submit"
				value="更新">編集</button>
			<br> <a href="/C4/HomeServlet">ホームページ</a> <span
				id="error_message"></span>

		</form>
	</main>
	<footer> </footer>
	<script src="./js/popup.js"></script>

</body>
</html>