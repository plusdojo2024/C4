<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${chName}チャンネル</title>
<link rel="stylesheet" href="./css/chpost.css">
<link rel="stylesheet" href="./css/popup.css">
<link rel="icon" href="./img/LinXicon.ico" type="image/png">
</head>
<body>
	<header>
		<h1>${channelName}チャンネル</h1>
		<div class="description">ここは${channelName}情報を共有するチャンネルです。投稿やコメントを通じて交流しましょう！</div>
	</header>
	<main>
	<!--左のカラムここから-->
		<div class="left-navi">
			<ul id="nav">
				<li><a href="${pageContext.request.contextPath}/HomeServlet">タイムライン</a></li>
				<li><a href="${pageContext.request.contextPath}/ChServlet">チャンネル</a></li>
				<li><a href="${pageContext.request.contextPath}/AccountServlet">アカウント</a></li>
			</ul>
		</div>
		<!--左のカラムここまで-->
		<!--メインカラムここから-->
		<div class="main-navi">
			<div class="channel-content" id="posts">
				<c:if test="${empty PostList}">
					<p>まだ投稿はありません</p>
				</c:if>
				<c:forEach var="e" items="${PostList}">
					<div class="home">
						<div class=post-date>投稿日時：${e.created_at}</div>
						<div class="post-content">${e.content}</div>
						<div class="post-footer">
							<div class=comment>コメント</div>
							<div class=reaction>リアクション</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<!--メインカラムここまで-->

	</main>
	<!-- フッター（ここから） -->
	<footer>
	<button class="post-btn" onclick="view()">投稿</button> <!-- 投稿ボタン -->
		<div id="popup" class="popup-overlay">
			<div class="popup-content">
				<form method="POST"
					action="${pageContext.request.contextPath}/HomeServlet">
					<input type="hidden" name="chId" value="${chId}">
					<textarea id="text" name="post" placeholder="投稿内容"></textarea>
					<input type="submit" name="submit" value="投稿">
				</form>
				<button onclick="hide()">閉じる</button>
			</div>
		</div>
		<a href="ChServlet" class="back-button">戻る</a>
	</footer>
	<!-- フッター（ここまで） -->
	<script src="./js/popup.js"></script>
</body>
</html>
