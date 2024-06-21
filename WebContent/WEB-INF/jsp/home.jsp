<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
    <!--<link rel="stylesheet" href="./css/style.css">-->
    <link rel="icon" href="./img/LinXicon.ico" type="image/png">
<title>ホーム</title>
<style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background-color: #f0f0f0;
            margin: 0;
        }
        h1 {
            margin-bottom: 10px;
        }
        .description {
            margin-bottom: 20px;
            font-size: 16px;
            color: #555;
        }
        .channel-content {
            background-color: white;
            border: 2px solid #000;
            padding: 20px;
            width: 1000px;
            height: 600px;
            display: flex;
            flex-direction: column;
            align-items: center;
            overflow-y: scroll;
        }
        .post {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            border-bottom: 1px solid #ccc;
            padding: 10px;
            width: 100%;
        }
        .post-content {
            display: flex;
            align-items: center;
            width: 100%;
        }
        .icon {
            border-radius: 50%;
            width: 50px;
            height: 50px;
            background-color: #ccc;
            margin-right: 10px;
        }
        .text {
            flex-grow: 1;
            color: #333;
            font-size: 18px;
        }
        .comment-section {
            width: 100%;
            padding-left: 60px; /* Adjust padding to align with the icon */
            margin-top: 10px;
        }
        .comment {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .comment .icon {
            width: 30px;
            height: 30px;
        }
        .comment .text {
            font-size: 16px;
            margin-left: 10px;
        }
        .form-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 20px;
            width: 1000px;
        }
        .form-container textarea {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            font-size: 16px;
            height: 100px;
        }
        .form-container button {
            padding: 10px 20px;
            background-color: #000;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .back-button {
            margin-top: 20px;
            background-color: #000;
            color: #fff;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
        }
        .comment-form {
            display: flex;
            flex-direction: column;
            margin-top: 10px;
        }
        .comment-form textarea {
            width: 100%;
            padding: 5px;
            margin-bottom: 5px;
            font-size: 14px;
            height: 50px;
        }
        .comment-form button {
            padding: 5px 10px;
            font-size: 14px;
            background-color: #000;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            align-self: flex-end;
        }
        .delete-button {
            margin-left: 10px;
            background-color: red;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
    <script src="./js/list.js"></script>
</head>
<body>
	<!--ヘッダーここから-->
	<header>
		<div>
			<h1 id="logo">
				<a href="${pageContext.request.contextPath}/HomeServlet"><img src="./img/LinXlogo.jpg" alt="LinX"></a>
			</h1>
			<form method="get"
				action="${pageContext.request.contextPath}/HomeServlet">
				<input type="search" name="search" placeholder="キーワードを入力"> <input
					type="submit" name="submit" value="検索">
			</form>

			<h2>ランキング</h2>
		</div>
	</header>
	<!--ヘッダーここまで-->
	<!--メインーここから-->

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
	<div class="main-contents">
		<div class=allpost>
			<div class=post>ポスト</div>
		</div>
	</div>
	<div class="channel-content" id="posts">
        まだ投稿はありません
        <c:forEach var="e" items="${PostList}">
				<div class="home">
                <div>

                </div>
					<div>
						${e.content}
					</div>
					<div>
						${e.created_at}
					</div>
				</div>
				<div class=date>日付</div>
				<div class=comment>コメント</div>
				<div class=reaction>リアクション</div>
			</c:forEach>
    </div>
	<div class="form-container">
        <textarea id="text" placeholder="投稿内容"></textarea>
        <button onclick="addPost()">投稿</button>
    </div>
	<!--メインカラムここまで-->

	<!--右のカラムここから-->
	<div class="right-navi">
		<div class="clock">
            <canvas id="clock" width="400" height="400"></canvas><br>
            <span id="date"></span><br><hr>
        </div>
        <div class="weather">
            <select id="from"></select><br>
            天気:<span id="fromWeather"></span><br>
            気温:<span id="placeWeather"></span><br><hr>
            今日:<span id="todayWeather"></span><br>
            <span id="todayTemps"></span><br><br>
            明日:<span id="tomorrowWeather"></span><br>
            <span id="tomorrowTemps"></span><br>
        </div>
        <div class="calendar">
            <button id="lastMonth" type="button" onclick="lastCalendar()">前の月</button>
            <button id="nextMonth" type="button" onclick="nextCalendar()">次の月</button>
            <span id="calendar"></span>
        </div>
	</div>
	<!--右のカラムここまで-->


	<!--メインーここまで-->
	<!-- フッター（ここから） -->
	<div class="footer">
		<div>
			<div class=makepost>
			<a href="${pageContext.request.contextPath}/ChsPostServlet">投稿</a>
			</div>
		</div>
	</div>
	<!-- フッター（ここまで） -->

<script src="./js/chPost.js"></script>
<script src="./js/info.js"></script>
</body>

</html>

<!--３カラム-->