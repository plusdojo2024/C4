<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>アカウント</title>
</head>

<body>
    <h1>アカウント一覧</h1>
    <ul>

        <li><a href="home.html">タイムライン</a></li>
        <li><a href="channel.html">チャンネル</a></li>
        <li><a href="messages.html">アカウント</a></li>
    </ul>
    <!--キーワード検索か空検索でユーザー一覧表示-->
    <div class="search-container">
        <input type="text" id="searchInput" placeholder="ユーザーを検索">
        <button id="searchButton">検索</button>
    </div>
    <!--自分のプロフィール表示-->

<h1>my prof</h1>
<hr>
    <!-- フォームの開始、データをServletにPOSTする actionにルートを入れる-->
    <form method="post" action="">
        <!-- 各フィールドのラベルと入力欄を作成 -->
        <div>
            <label>アイコン</label>
            <input type="file" name="icon" >
        </div>
        <div>
            <label>氏名</label>
            <input type="text" name="username" value="${username}">
        </div>
        <div>
            <label>ユーザーネーム</label>
            <input type="text" name="employee_id" value="${employee_id}" readonly="readonly">
        </div>
        <div>
            <label>コメント</label>
            <input type="text" name="comment" value="${comment}">
        </div>

    </form>
    <hr>

 <!--全てのプロフィール表示-->
    <h1>all prof</h1>
    <hr>
        <!-- フォームの開始、データをServletにPOSTする actionにルートを入れる-->
        <form method="post" action="Servlet">
            <!-- 各フィールドのラベルと入力欄を作成 -->
            <div>
                <label>アイコン</label>
                <input type="file" name="icon" value="" readonly="readonly">
            </div>
            <div>
                <label>氏名</label>
                <input type="text" name="username" value="${username}" readonly="readonly">
            </div>
            <div>
                <label>ユーザーネーム</label>
                <input type="text" name="employee_id" value="${employee_id}" readonly="readonly">
            </div>
            <div>
                <label>コメント</label>
                <input type="text" name="comment" value="${comment}" readonly="readonly">
            </div>

        </form>
        <hr>



    <!--プロフィールをクリックしたら個人プロフィール画面に遷移-->





</body>
</html>