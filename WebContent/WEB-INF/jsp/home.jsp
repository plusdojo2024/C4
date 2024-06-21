<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/ch.css">
<link rel="stylesheet" href="./css/home.css">
<link rel="icon" href="./img/LinXicon.ico" type="image/png">
<title>ホーム</title>
<script src="./js/list.js"></script>
</head>
<body>
	<!--ヘッダーここから-->
	<header>
		<h1 id="logo">
			<a href="${pageContext.request.contextPath}/HomeServlet"><img src="./img/LinXlogo.jpg" alt="LinX"></a>
		</h1>
		<form method="post"
			action="${pageContext.request.contextPath}/HomeServlet">
			<input type="search" name="search" placeholder="キーワードを入力"> <input
				type="submit" name="submit" value="検索">
		</form>

		<h2>ランキング</h2>
	</header>
	<!--ヘッダーここまで-->
	<!--メインーここから-->
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
			<div class="main-contents">
				<div class=allpost>
					<div class=post>ポスト</div>
				</div>
			</div>
			<div class="channel-content" id="posts">
				まだ投稿はありません
				<c:forEach var="e" items="${PostList}">
					<div class="home">
						<div></div>
						<div>${e.content}</div>
						<div>${e.created_at}</div>
					</div>
					<div class=date>日付</div>
					<div class=comment>コメント</div>
					<div class=reaction>リアクション</div>
					<hr>
				</c:forEach>
			</div>
			<div class="form-container">
				<textarea id="text" placeholder="投稿内容"></textarea>
				<button onclick="addPost()">投稿</button>
			</div>
		</div>
		<!--メインカラムここまで-->

		<!--右のカラムここから-->
		<div class="right-navi">
			<div class="clock">
				<canvas id="clock" width="400" height="400"></canvas>
				<br> <span id="date"></span><br>
				<hr>
			</div>
			<div class="weather">
				<select id="from"></select><br> 天気:<span id="fromWeather"></span><br>
				気温:<span id="placeWeather"></span><br>
				<hr>
				今日:<span id="todayWeather"></span><br> <span id="todayTemps"></span><br>
				<br> 明日:<span id="tomorrowWeather"></span><br> <span
					id="tomorrowTemps"></span><br>
			</div>
			<div class="calendar">
				<button id="lastMonth" type="button" onclick="lastCalendar()">前の月</button>
				<button id="nextMonth" type="button" onclick="nextCalendar()">次の月</button>
				<span id="calendar"></span>
			</div>
		</div>
		<!--右のカラムここまで-->

	</main>
	<!--メインーここまで-->
	<!-- フッター（ここから） -->
	<footer>
		<div class=makepost>
			<a href="${pageContext.request.contextPath}/ChsPostServlet">投稿</a>
		</div>
	</footer>
	<!-- フッター（ここまで） -->
	<script src="./js/chPost.js"></script>
	<script src="./js/info.js"></script>
</body>

</html>

<!--３カラム-->