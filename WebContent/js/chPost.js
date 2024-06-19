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