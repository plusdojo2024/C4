<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント</title>
</head>

<body>
    <h1>アカウント一覧</h1>
    <ul>
        <li><a href="${pageContext.request.contextPath}/HomeServlet">タイムライン</a></li>
			<li><a href="${pageContext.request.contextPath}/ChServlet">チャンネル</a></li>
			<li><a href="${pageContext.request.contextPath}/AccountServlet">アカウント</a></li>
    </ul>

    <!-- キーワード検索フォーム -->
    <div class="search-container">
        <form method="post" action="AccountServlet">
            <input type="text" name="username" id="searchInput" placeholder="ユーザーを検索">
            <button type="submit" id="searchButton">検索</button>
        </form>
    </div>


	 <!-- 自分のプロフィール表示 -->
    <h1>my prof</h1>
    <hr>
    <form method="post" action="AccountServlet">
        <div class="myprof">
            <label>アイコン</label>
            <input type="file" name="icon">
        </div>
        <div>
            <label>氏名</label>
            <input type="text" name="username" value="${username}">
        </div>
        <div>
            <label>ユーザーネーム</label>
            <input type="text" name="employeeId" value="${employeeId}" readonly="readonly">
        </div>
        <div>
            <label>コメント</label>
            <input type="text" name="comment" value="${comment}">
        </div>
    </form>


    <hr>
    <!-- アカウント一覧表示 -->
    <h1>all prof</h1>
    <div class="all-prof">
        <%
        List<User> userList = (List<User>) request.getAttribute("userList");
        if (userList != null && !userList.isEmpty()) {
            for (User user : userList) {
        %>
            <div class="user-profile"
            onclick="location.href='/C4/MessagesServlet?employeeId=<%= user.getEmployeeId() %>'">
                <div>
                    <label>氏名: </label>
                    <span><%= user.getUsername() %></span>
                </div>
                <div>
                    <label>ユーザーネーム: </label>
                    <span><%= user.getEmployeeId() %></span>
                </div>
                <div>
                    <label>言語: </label>
                    <span><%= user.getLang() %></span>
                </div>
                <div>
                    <label>コメント: </label>
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
    <h1>searchResults prof</h1>
    <div class="searchResults-prof">
        <%
        List<User> searchResults = (List<User>) request.getAttribute("searchResults");
        if (searchResults != null && !searchResults.isEmpty()) {
            for (User user : searchResults) {
        %>
            <div class="searchResults-profile"
            onclick="location.href='/C4/MessagesServlet?employeeId=<%= user.getEmployeeId() %>'">
                <div>
                    <label>氏名: </label>
                    <span><%= user.getUsername() %></span>
                </div>
                <div>
                    <label>ユーザーネーム: </label>
                    <span><%= user.getEmployeeId() %></span>
                </div>
                <div>
                    <label>言語: </label>
                    <span><%= user.getLang() %></span>
                </div>
                <div>
                    <label>コメント: </label>
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


</body>
</html>
