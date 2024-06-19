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