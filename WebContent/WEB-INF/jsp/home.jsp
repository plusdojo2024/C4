<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.HomeDao" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/home.css">
<link rel="stylesheet" href="./css/popup.css">
<link rel="icon" href="./img/LinXicon.ico" type="image/png">
<title>ホーム</title>
<script src="./js/list.js"></script>
</head>
<body>
	<!--ヘッダーここから-->
	<header>
		<div>
			<h1 id="logo">
				<a href="${pageContext.request.contextPath}/HomeServlet"><img
					class="round-image" src="./img/LinXlogo.jpg" alt="LinX"></a>
			</h1>
		</div>
		<span>${username}</span>
		<form method="POST"
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
				<li><a href="${pageContext.request.contextPath}/HomeServlet">TIMELINE</a></li>
				<li><a href="${pageContext.request.contextPath}/ChServlet">CHANNEL</a></li>
				<li><a href="${pageContext.request.contextPath}/AccountServlet">ACCOUNT</a></li>
				<li><a href="${pageContext.request.contextPath}/LoginServlet"
					class="logout-button" onclick="confirmLogout(event)">LOGOUT</a></li>
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
						<c:if test="${e.comments != 0}">
							<div class="comuser">&gt;&gt;${e.comuser}</div>
						</c:if>
						<div class=name>${e.username}</div>
						<div class=post-date>投稿日時：${e.created_at}</div>
						<div class="post-content">${e.content}</div>
						<div class="post-footer">
							<div class=comment>
								<button class="com-btn" onclick="view${e.post_id}()">コメント</button>
								<!-- コメントボタン -->
								<div id="popup-com${e.post_id}" class="popup-overlay">
									<div class="popup-content">
										<form method="POST"
											action="${pageContext.request.contextPath}/HomeServlet">
											<input type="hidden" name="chId" value="0"> <input
												type="hidden" name="postId" value="${e.post_id}">
											<textarea id="text" name="post" placeholder="コメント内容"></textarea>
											<input type="submit" name="submit" value="コメント">
										</form>
										<button onclick="hide()">閉じる</button>
									</div>
								</div>
							</div>
							<div class=reaction></div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<!--メインカラムここまで-->

		<!--右のカラムここから-->
		<div class="right-navi">
			<div class="clock">
				<!-- <canvas id="clock" width="400" height="400"></canvas> -->
				<br> <span id="date"></span><br>
				<hr>
			</div>
			<!-- <div class="weather">
				<select id="from"></select><br> 天気:<span id="fromWeather"></span><br>
				気温:<span id="placeWeather"></span><br>
				<hr>
				今日:<span id="todayWeather"></span><br> <span id="todayTemps"></span><br>
				<br> 明日:<span id="tomorrowWeather"></span><br> <span
					id="tomorrowTemps"></span><br>
			</div> -->
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
	<!-- 投稿用ポップアップ -->
	<footer>
		<button class="post-btn" onclick="view()">投稿</button>
		<!-- 投稿ボタン -->
		<div id="popup" class="popup-overlay">
			<div class="popup-content">
				<form method="POST"
					action="${pageContext.request.contextPath}/HomeServlet">
					<input type="hidden" name="chId" value="0">
					<textarea id="text" name="post" placeholder="投稿内容"></textarea>
					<input type="submit" name="submit" value="投稿">
				</form>
				<button onclick="hide()">閉じる</button>
			</div>
		</div>
	</footer>
	<!-- フッター（ここまで） -->


	<script src="./js/info.js"></script>
	<c:forEach var="e" items="${PostList}">
	<script>
	function view${e.post_id}() {
		document.getElementById('popup-com${e.post_id}').style.display = 'block';
	}
	function hide${e.post_id}() {
		document.getElementById('popup-com${e.post_id}').style.display = 'none';
	}
	</script>
	</c:forEach>
	<script src="./js/popup.js"></script>
	<script src="./js/home.js"></script>
</body>

</html>

<!--３カラム-->