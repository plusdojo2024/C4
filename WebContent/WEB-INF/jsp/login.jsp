<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<header>
        <h1>ログインページ</h1>
    </header>
    <main>
        <form method="post" action="/LinX/LoginServlet">

            社員 ID<input type="text" name="id"><br>
            パスワード<input type="password" name="pw"><br>
            <input type="submit" name="login" value="ログイン">
            <input type="reset" name="reset" value="リセット">

            <span id="error_message"></span><br>

            <a href="/LinX/RegistServlet">新規登録</a>

        </form>

    </main>
    <footer>

    </footer>

</body>
</html>