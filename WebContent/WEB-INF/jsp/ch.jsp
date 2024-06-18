<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

        }
    </style>
</head>

<body>
    <h1>チャンネル選択</h1>

    <div class="create-channel">
        <input type="text" id="channelName" placeholder="チャンネル名">
        <input type="text" id="channelDescription" placeholder="チャンネル説明">
        <button id="createChannelButton">作成</button>
    </div>

    <div class="channel-container">
        <!-- スノーボードチャンネル -->
        <div class="channel-n">
            <button class="channel channel-name  channel-n" onclick="window.location.href='snowboard.jsp'"
                class="channel-link">スノーボード</button>
            <button class="delete-button">削除</button>
        </div>
    </div>
    <div class="channel-container">
        <!-- 野球部チャンネル -->
        <div class="channel-n">
            <button class="channel channel-name channel-n" onclick="window.location.href='baseball.jsp'"
                class="channel-link">野球部</button>
            <button class="delete-button">削除</button>
        </div>
    </div>
    <div class="channel-container">
    <!-- サウナチャンネル -->
    <div class="channel-n">
        <button class="channel channel-name channel-n" onclick="window.location.href='sauna.jsp'"
            class="channel-link">サウナ</button>
        <button class="delete-button">削除</button>
    </div>
    </div>
    <div class="channel-container">
        <!-- マラソンチャンネル -->
        <div class="channel-n">
            <button class="channel channel-name channel-n" onclick="window.location.href='marathon.jsp'"
                class="channel-link">マラソン</button>
            <button class="delete-button">削除</button>
        </div>
    </div>


    <script>
        // チャンネル作成ボタンのクリックイベント
        document.getElementById('createChannelButton').addEventListener('click', function () {
            const name = document.getElementById('channelName').value;
            const description = document.getElementById('channelDescription').value;
            if (name && description) {
                // 新しいチャンネル要素を作成
                const newChannel = document.createElement('div');
                newChannel.classList.add('channel');
                newChannel.innerHTML = `
                    <span class="channel-name">${name}</span>
                    <a href="channel.html" class="channel-link">Go</a>
                    <button class="delete-button">削除</button>
                `;
                // チャンネルコンテナに追加
                const channelContainer = document.querySelector('.channel-container');
                channelContainer.appendChild(newChannel);

                // 入力フィールドをクリア
                document.getElementById('channelName').value = '';
                document.getElementById('channelDescription').value = '';

                alert(`新しいチャンネルが作成されました！\n名前: ${name}\n説明: ${description}`);
            } else {
                alert('チャンネル名と説明を入力してください。');
            }
        });

        // 削除ボタンのクリックイベント
        const deleteButtons = document.querySelectorAll('.delete-button');
        deleteButtons.forEach(button => {
            button.addEventListener('click', function (event) {
                event.stopPropagation(); // 親要素へのクリックイベント伝播を止める
                const channel = this.parentElement;
                channel.remove();
                alert(`${channel.querySelector('.channel-name').textContent} チャンネルが削除されました。`);
            });
        });

        // 各チャンネルリンクのクリックイベント
        const channelLinks = document.querySelectorAll('.channel-link');
        channelLinks.forEach(link => {
            link.addEventListener('click', function (event) {
                event.preventDefault(); // デフォルトのリンク先への遷移をキャンセル
                const url = this.getAttribute('href');
                if (url) {
                    window.location.href = url; // 指定されたURLに遷移
                }
            });
        });
    </script>
</body>

</html>