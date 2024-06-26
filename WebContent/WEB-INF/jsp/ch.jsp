<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./css/ch.css">
<link rel="icon" href="./img/LinXicon.ico" type="image/png">
<title>チャンネル選択</title>
</head>

<body>
	<header>
		<div class="search-container">
		<form method="post" action="ChServlet">
			<input type="text" id="searchInput" name="channelName" placeholder="チャンネルを検索">
			<input type="submit"  name="submit" value="検索">
		</form>
		</div>
	</header>
	<main>
		<h1>チャンネル選択</h1>

		<div class="create-channel">
			<form method="post" action="ChServlet">
				<input type="text" id="channelName" name="channelName" placeholder="チャンネル名">
				<input type="text" id="channelDescription" name="channelDescription" placeholder="チャンネル説明">
				<input type="submit" id="createChannelButton"  name="submit" value="作成">
			</form>
		</div>

		<c:forEach var="e" items="${chList}">
			<div class="channel-container">
				<div class="channel-n">
					<form method="post" action="ChServlet">
						<input type="hidden" name="channelId" value="${e.channelId}">
						<input type="hidden" name="channelName" value="${e.chName}">
						<input type="submit" id="channnel-shinji" name="submit" value="${e.chName}">
						<input type="submit" id="delete-button" name="submit" value="削除">
					</form>
				</div>
			</div>
		</c:forEach>
	</main>
	<footer>
		<p>${testMess}</p>
	</footer>
</body>

</html>