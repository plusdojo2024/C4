<!-- dm.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="dm.css">
    <title>Chat</title>
</head>
<body>
    <div id="room">

        <div class="box-left">
            <p class="message-box white">こんにちは</p>
        </div>

        <div class="box-right">
            <p class="message-box green">こんにちは</p>
        </div>

    </div>

    <form action = "MessagesServlet" method="post">
    <input type="hidden" name="conversations_id" value="1"> <!-- 会話IDは適宜設定 -->
        <input type="hidden" name="sender_id" placeholder="Your ID" required><br>
        <input type="hidden" name="receiver_id" placeholder="Receiver's ID" required><br>
        <textarea name="message_content" rows="3" cols="50" placeholder="Type your message..." required></textarea><br>
        <input type="submit" value="Send">
     </form>
</body>
</html>