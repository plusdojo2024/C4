<!-- dm.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./css/dm.css">
<title>Chat</title>
<link rel="icon" href="./img/LinXicon.ico" type="image/png">
</head>

<body>
	<header>

	</header>
	<main>
	<!--左のカラムここから-->
	<div class="left-navi">
		<ul class="menu">
			<li><a href="HomeServlet">TIMELINE</a></li>
			<li><a href="ChServlet">CHANNEL</a></li>
			<li><a href="MessagesServlet">DM</a></li>
			<li><a href="AccountServlet">ACCOUNT</a></li>
		</ul>
	</div>
	<!--左のカラムここまで-->



	<!-- メインチャットここから -->
	<!--  一応コメントしてるだけ。なくても大丈夫
	<div id="room">

        <div class="box-left">
            <p class="message-box white">こんにちは</p>
        </div>

        <div class="box-right">
            <p class="message-box green">こんにちは</p>
        </div>

    </div>-->

	<div id="room">
	<div class="opponent">
	<span>${username}</span>
	</div>
		<c:if test="${empty cardList}">
			<p class="no-data-message">初めての会話相手です！</p>
		</c:if>

		<c:forEach var="e" items="${cardList}">
			<c:choose>
				<c:when test="${e.senderId == id}">
					<div class="box-right">
						<p class="message-box green">${e.messageContent}</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="box-left">
						<p class="message-box white">${e.messageContent}</p>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>

	</div>
	</main>
	<!-- メインチャットここまで -->


	<!-- フッター（ここから） -->
	<footer>
      <div>
			<form action="MessagesServlet" method="post">
				<input type="hidden" name="conversationsId" value="${conversationsId}">
				<input type="hidden" name="username" value="${username}">
				<textarea name="message" id="message" rows="5" cols="50"
					placeholder="メッセージを入力"></textarea>
				<input type="submit" value="送信" class="submit-button">
			</form>
		</div>
	</footer>
	<!-- フッター（ここまで） -->

</body>
</html>