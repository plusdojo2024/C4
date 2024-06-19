<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
	<header>
        <h1>新規登録</h1>
    </header>
    <main>
        <form method="post" action="/C4/CreateUserServlet">

            社員 ID<input type="text" name="new_id"><br>
            氏名<input type="text" name="name"><br>
            パスワード<input type="password" name="new_pw"><br>
            再度パスワード<input type="password" name="rePw"><br>

            <input type="submit" name="login" value="登録">
            <input type="reset" name="reset" value="リセット">

            <span id="error_message"></span><br>
            <a href="#">利用規約</a><br>

            <a href="/C4/LoginServlet">ログイン</a>

        </form>

    </main>
    <footer>

    </footer>

</body>
</html>