<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>マラソンチャンネル</title>
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
    <h1>マラソンチャンネル</h1>
    <div class="description">ここはどこまでも走るチャンネルです。投稿やコメントを通じて交流しましょう！</div>
    <div class="channel-content" id="posts">
        まだ投稿はありません
    </div>
    <div class="form-container">
        <textarea id="text" placeholder="投稿内容"></textarea>
        <button onclick="addPost()">投稿</button>
    </div>
    <a href="ch.html" class="back-button">戻る</a>
    <script>
        function addPost() {
            const text = document.getElementById('text').value;
            if (text) {
                const posts = document.getElementById('posts');
                if (posts.innerHTML === 'まだ投稿はありません') {
                    posts.innerHTML = '';
                }
                const postId = `post-${Date.now()}`;
                const post = document.createElement('div');
                post.className = 'post';
                post.id = postId;
                post.innerHTML = `
                    <div class="post-content">
                        <div class="icon"></div>
                        <div class="text">${text}</div>
                        <button class="delete-button" onclick="confirmDeletePost('${postId}')">削除</button>
                    </div>
                    <div class="comment-section" id="${postId}-comments"></div>
                    <div class="comment-form">
                        <textarea id="${postId}-comment-text" placeholder="コメント内容"></textarea>
                        <button onclick="addComment('${postId}')">コメント</button>
                    </div>
                `;
                posts.insertBefore(post, posts.firstChild);
                document.getElementById('text').value = '';
            }
        }

        function addComment(postId) {
            const commentText = document.getElementById(`${postId}-comment-text`).value;
            if (commentText) {
                const comments = document.getElementById(`${postId}-comments`);
                const comment = document.createElement('div');
                comment.className = 'comment';
                comment.innerHTML = `
                    <div class="icon"></div>
                    <div class="text">${commentText}</div>
                `;
                comments.appendChild(comment);
                document.getElementById(`${postId}-comment-text`).value = '';
            }
        }

        function confirmDeletePost(postId) {
            if (confirm("本当に削除しますか？")) {
                deletePost(postId);
            }
        }

        function deletePost(postId) {
            const post = document.getElementById(postId);
            if (post) {
                post.remove();
            }
        }
    </script>
</body>
</html>

