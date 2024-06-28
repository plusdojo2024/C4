<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
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
			</ul>
		</div>
		<!--左のカラムここまで-->

		<!--メインカラムここから-->
		 <div class="main-navi">

			<!-- 自分のプロフィール表示 -->
			<h1> <a href="${pageContext.request.contextPath}/ProfileServlet">Myプロフィール</a></h1>

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
		    <h1>全てのアカウント</h1>
		    <div class="all-prof">
		        <%
		        List<User> userList = (List<User>) request.getAttribute("userList");
		        if (userList != null && !userList.isEmpty()) {
		            for (User user : userList) {
		        %>
		            <div class="user-profile">
						<img src="./images/<%= user.getIcon() %>" class="profile-pic" alt="Profile Picture">
		                <div>
		                    <label>氏名 </label>
		                    <span><%= user.getUsername() %></span>
		                </div>
		                <div>
		                    <label>ユーザーネーム </label>
		                    <span><%= user.getemployee_Id() %></span>
		                </div>
		                <div>
		                    <label>言語 </label>
		                </div>
		                <div>
		                    <label>コメント </label>
		                    <span><%= user.getComment() %></span>
		                </div>

		                <hr>
		            </div>
		        <%
		            }
		        } else if (userList != null) {
		        %>
		            <p>該当するユーザーが見つかりませんでした。</p>
		        <%
		        }
		        %>
		    </div>

		    <!-- アカウント検索結果表示 -->
		    <h1>検索結果</h1>
		    <div class="searchResults-prof">
		        <%
		        List<User> searchResults = (List<User>) request.getAttribute("searchResults");
		        if (searchResults != null && !searchResults.isEmpty()) {
		            for (User user : searchResults) {
		        %>
		            <div class="searchResults-profile"
		            onclick="location.href='/C4/MessagesServlet?employee_Id=<%= user.getemployee_Id() %>'">
		                <div>
		                    <label>氏名</label>
		                    <span><%= user.getUsername() %></span>
		                </div>
		                <div>
		                    <label>ユーザーネーム</label>
		                    <span><%= user.getemployee_Id() %></span>
		                </div>
		                <div>
		                    <label>言語</label>
		                </div>
		                <div>
		                    <label>コメント</label>
		                    <span><%= user.getComment() %></span>
		                </div>
		                <hr>
		            </div>
		        <%
		            }
		        } else if (searchResults != null) {
		        %>
		            <p>該当するユーザーが見つかりませんでした。</p>
		        <%
		        }
		        %>
		    </div>
	</div>

 	<%-- <c:if test="${empty userList}">
		<p>一致するデータはありません。</p>
	</c:if>
 	<c:forEach var="user" items="${userList}">

	 	<!-- <br> -->
        <div class="form-group">
				<label for="id">社員ID</label> <input type="text" id="id" name="id" value="${user.employee_Id}" readonly="readonly">
			</div>
		<img src="./images/${user.icon}" class="profile-pic" alt="Profile Picture">

 	</c:forEach> --%>

	</main>
	</body>
</html>