<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${channelName}チャンネル</title>
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
</head>
<body>
    <h1>${channelName} チャンネル</h1>
    <div class="description">ここは${channelName}の情報を共有するチャンネルです。投稿やコメントを通じて交流しましょう！</div>
    <div class="channel-content" id="posts">
        <c:if test="${empty cardList}">
            <p>まだ投稿はありません</p>
        </c:if>
        <c:forEach var="e" items="${cardList}">
            <div class="post">
                <div class="icon"></div>
                <div class="text">
                    <div class="post-title">${e.channels_id}</div>
                    <div class="post-content">${e.content}</div>
                </div>
                <div class="comment-section">
                    <!-- コメントセクションはここに追加 -->
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="form-container">
        <textarea id="text" placeholder="投稿内容"></textarea>
        <button onclick="addPost()">投稿</button>
    </div>
    <a href="ChServlet" class="back-button">戻る</a>
    <script src="js/chPost.js"></script>
</body>
</html>
