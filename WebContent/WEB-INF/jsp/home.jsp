<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title></title>
<body>
	<!--ヘッダーここから-->
	<header>
		<div>
			<h1 id="logo">
				<a href="HomeServlet"><img src="" alt="LinX"></a>
			</h1>
			<form method="get">
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
			<div class=date>日付</div>
			<div class=comment>コメント</div>
			<div class=reaction>リアクション</div>
		</div>
	</div>
	<!--メインカラムここまで-->

	<!--右のカラムここから-->
	<div class="right-navi">
		<div class=noti></div>
		<div class=clock></div>
		<div class=calender></div>
	</div>
	<!--右のカラムここまで-->


	<!--メインーここまで-->
	<!-- フッター（ここから） -->
	<div class="footer">
		<div>
			<div class=makepost></div>
		</div>
	</div>
	<!-- フッター（ここまで） -->

</body>
</head>

</html>

<!--３カラム
-->