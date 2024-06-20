<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <div class="search-container">
        <input type="text" id="searchInput" placeholder="チャンネルを検索">
        <button id="searchButton">検索</button>
    </div>
    <title>チャンネル選択</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
        }


        .search-container {
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 20px 0;
        }

        #searchInput {
            width: 200px;
            padding: 10px;
            font-size: 16px;
            box-sizing: border-box;
            transition: width 0.4s ease-in-out;
        }

        #searchInput:focus {
            width: 300px;
        }

        #channnel-shinji {
        	background-color: white;
            border: 2px solid #000;
            padding: 10px;
            text-align: center;
            cursor: pointer;
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-size: 16px;
            margin-right: 1px; /* チャンネルボタンと削除ボタンの間のスペース */
            padding: 10px; /* 必要に応じてパディングを調整 */
            font-size: 15px; /* 必要に応じてフォントサイズを調整 */
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 55px;
            float: left;
            width: 300px; /* 必要に応じて幅を調整 */
            height: 55px; /* 必要に応じて高さを調整 */
        }

         .create-channel form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

         #channelName, #channelDescription {
            text-align: center;
            width: 300px; /* 幅を調整 */
            padding: 10px;
            font-size: 16px;
            margin: 5px 0;
            box-sizing: border-box;
        }

         #createChannelButton {
            display: flex;
            justify-content: center;
            padding: 10px 20px;
            font-size: 15px;
            background-color: black;
            width: 70px; /* 必要に応じて幅を調整 */
            color: white;
            border: none;
            cursor: pointer;
        }

        .channel-container {
            display: flex;
            flex-direction: column;
            width: 90%;
            max-width: 350px;
        }

        .channel {
            background-color: white;
            border: 2px solid #000;
            padding: 10px;
            text-align: center;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s;
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-size: 16px;
            margin-right: 1px; /* チャンネルボタンと削除ボタンの間のスペース */
            padding: 10px; /* 必要に応じてパディングを調整 */
            font-size: 18px; /* 必要に応じてフォントサイズを調整 */
        }

        .channel:hover {
            background-color: #e0e0e0;
            transform: scale(1.05);
        }

        .channel-name {
            flex: 1;
        }

        .delete-button {
            background-color: red;
            color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .delete-button:hover {
            background-color: darkred;
        }

        .create-channel {
            margin-bottom: 20px;
            /* .create-channelの下に20pxの間隔を設定 */
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 5px;
            margin-top: 20px;
            padding: 10px;
            border: 2px solid #000;
            border-radius: 10px;
            background-color: white;
            width: 70%;
            max-width: 300px;
        }

        .create-channel input {
            padding: 15px;
            width: 90%;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .create-channel button {
            padding: 10px 20px;
            background-color: #000;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .create-channel button:hover {
            background-color: #555;
        }

        .channel-n {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 55px;
            float: left;
            width: 300px; /* 必要に応じて幅を調整 */
            height: 55px; /* 必要に応じて高さを調整 */

          }


    }


        }
    </style>
</head>

<body>
    <h1>チャンネル選択</h1>

    <div class="create-channel">
    <form method="post" action="ChServlet">
        <input type="text" id="channelName" name="channelName" placeholder="チャンネル名">
        <input type="text" id="channelDescription" name="channelDescription" placeholder="チャンネル説明">
        <input type="submit" id="createChannelButton" value="作成">
        </form>
    </div>

    <c:if test="${empty chList}">
    	<p>一致するデータはありません。</p>
	</c:if>
	<c:forEach var="e" items="${chList}" >
		<form method="post" action="ChsPostServlet">
    		<div class="channel-container">
        		<div class="channel-n">
            		<input id="channnel-shinji" type="submit" value="${e.chName}">
            		<button class="delete-button">削除</button>
        		</div>
    		</div>
    	</form>
	</c:forEach>
    <!-- <script src="js/ch.js"></script> -->
</body>

</html>