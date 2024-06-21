<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${chName}チャンネル</title>
<link rel="stylesheet" href="./css/ch.css">
<link rel="icon" href="./img/LinXicon.ico" type="image/png">
</head>
<body>
	<header>
		<h1>${channelName}チャンネル</h1>
		<div class="description">ここは${channelName}情報を共有するチャンネルです。投稿やコメントを通じて交流しましょう！</div>
	</header>
	<main>
		<div class="channel-content" id="posts">
			<c:if test="${empty cardList}">
				<p>まだ投稿はありません</p>
			</c:if>
			<c:forEach var="e" items="${cardList}">
				<div class="post">
					<div class="icon"></div>
					<div class="text">
						<div class="post-title">${e.channels_id}</div>
						<div class="post-content">${e.content}</div>
					</div>
					<div class="comment-section">
						<!-- コメントセクションはここに追加 -->
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="form-container">
			<textarea id="text" placeholder="投稿内容"></textarea>
			<button onclick="addPost()">投稿</button>
		</div>
	</main>
	<footer>
		<a href="ChServlet" class="back-button">戻る</a>
	</footer>
	<script src="js/chPost.js"></script>
</body>
</html>
