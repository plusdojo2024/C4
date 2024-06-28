<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/account.css">
<link rel="icon" href="./img/LinXicon.ico" type="image/png">
<title>アカウント</title>
<script src="./js/account.js"></script>
</head>

<body>
<!--ヘッダーここから-->
	<header>
		<div>
		<h1 id="logo">
			<a href="${pageContext.request.contextPath}/HomeServlet"><img
				src="./img/LinXlogo.jpg" alt="LinX"></a>
		</h1>
		</div>
		<form method="post"
			action="${pageContext.request.contextPath}/AccountServlet">
			<input type="search" name="username" placeholder="キーワードを入力">
			<input type="submit" name="submit" value="検索">
		</form>


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

			<!-- 自分のプロフィール表示 -->
			<h3> <a href="${pageContext.request.contextPath}/ProfileServlet">Myプロフィール</a></h3>

		    <!--<h1>Myプロフィール 編集</h1>
		    <hr>
		    <form method="post" action="AccountServlet">
		        <div class="myprof"
				>
			        <div>
			            <label>アイコン</label>
			            <input type="file" name="icon">
			        </div>
			        <div>
			            <label>氏名</label>
			            <input type="text" name="username" value="${username}">
			        </div>
			        <div>
			            <label>ユーザーネーム</label>
			            <input type="text" name="employee_Id" value="${employee_Id}" readonly="readonly">
			        </div>
			        <div>
			            <label>コメント</label>
			            <input type="text" name="comment" value="${comment}">
			        </div>
				 </div>
		    </form>
			-->

		    <hr>

		    <!-- アカウント一覧表示 -->
		    <h3>全てのアカウント</h3>
		    <div class="all-prof">
		    	<c:if test="${empty userList}">
						<p>該当するユーザーが見つかりませんでした。</p>
				</c:if>
		    	<c:forEach var="user" items="${userList}">
		            <div class="user-profile" >
		            	<div>
		                    <span>${user.username}</span>
		                </div>
		            	<div class="container">

						<img src="./img/${user.icon}" class="profile-pic" alt="Profile Picture">

	                	<div>
		                    <span>${user.comment}</span>
		                </div>

		                <hr>

		            </div>

		           </div>
		        </c:forEach>
		    </div>

		    <!-- アカウント検索結果表示 -->
		    <h3>検索結果</h3>
		    <div class="searchResults-prof">
		    	<c:if test="${empty searchResults}">
						<p>該当するユーザーが見つかりませんでした。</p>
				</c:if>
		        <c:forEach var="s" items="${searchResults}">
		            <div class="user-profile" >
		            	<div>
		                    <span>${s.username}</span>
		                </div>
		            	<div class="container">

						<img src="./img/${s.icon}" class="profile-pic" alt="Profile Picture">

	                	<div>
		                    <span>${s.comment}</span>
		                </div>

		                <hr>

		            </div>
		            <hr>
		           </div>
		        </c:forEach>
		    </div>
	</div>
	</main>
	</body>
</html>