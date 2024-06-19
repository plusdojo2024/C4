<!-- dm.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="dm.css">
    <title>Chat</title>
</head>
<body>
<ul>
        <li><a href="HomeServlet">タイムライン</a></li>
        <li><a href="ChServlet">チャンネル</a></li>
        <li><a href="">通知</a></li>
    </ul>
    <a href="">相手の名前</a>
   <!--   <div id="room">

        <div class="box-left">
            <p class="message-box white">こんにちは</p>
        </div>

        <div class="box-right">
            <p class="message-box green">こんにちは</p>
        </div>

    </div>-->
    <div>
    <c:if test="${empty cardList}">
	<p>一致するデータはありません。</p>
</c:if>
<c:forEach var="e" items="${cardList}" >

	<input type="text" name="messageContent" value="${e.messageContent}" readonly="readonly"><br>



</c:forEach>
    </div>

    <form action = "MessagesServlet" method="post">


        <textarea name="message" id="message" rows="5" cols="50" placeholder="メッセージを入力"></textarea>
        <br><br>
        <input type="submit" value="送信">
    </form>

</body>
</html>