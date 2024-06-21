<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./css/ch.css">
<title>チャンネル選択</title>
</head>

<body>
	<header>
		<div class="search-container">
			<input type="text" id="searchInput" placeholder="チャンネルを検索">
			<button id="searchButton">検索</button>
		</div>
	</header>
	<main>
		<h1>チャンネル選択</h1>

		<div class="create-channel">
			<form method="post" action="ChServlet">
				<input type="text" id="channelName" name="channelName"
					placeholder="チャンネル名"> <input type="text"
					id="channelDescription" name="channelDescription"
					placeholder="チャンネル説明"> <input type="submit"
					id="createChannelButton" value="作成">
			</form>
		</div>

		<c:forEach var="e" items="${chList}">
			<form method="post" action="ChServlet">
				<div class="channel-container">
					<div class="channel-n">
						<input type="hidden" name="channelName" value="${e.chName}">
						<input type="hidden" name="action" value="view"> <input
							id="channnel-shinji" type="submit" value="${e.chName}">
						<button class="delete-button" type="submit" name="action"
							value="delete">削除</button>
					</div>
				</div>
			</form>
		</c:forEach>
	</main>
	<footer>
		<!-- ここはフッター編集時にはこのコメントを削除 -->
	</footer>

	<!-- <script src="js/ch.js"></script> -->
</body>

</html>