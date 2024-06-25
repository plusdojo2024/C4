<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新規登録 | LinX </title>
    <link rel="stylesheet" type="text/css" href="css/user.css"  >

</head>
<body class="body_bgcolor">
	<header id="login-header">
        <h1>新規登録</h1>
    </header>
    <main>
   		<div class="login" style="margin-bottom: 40px">
              <form id="login_form"  method="post" action="/C4/CreateUserServlet">

                    <table>

                        <tr>
                          <td>
                            <label>社員 ID
                            <input type="text" name="new_id" required>
                            </label>
                          </td>
                        </tr>
                        <tr>
                            <td>
                              <label> 名前（フルネーム）
                              <input type="text" name="name" required>
                              </label>
                            </td>
                          </tr>

                        <tr>
                          <td>
                            <label>パスワード
                            <input type="password" name="new_pw" id="new_pw" required>
                            </label>
                          </td>
                        </tr>
                        <tr>
                            <td>
                              <label>確認パスワード
                              <input type="password" name="rePw" id="rePw" required>
                              </label><br><br>
                            </td>
                          </tr>

                        <tr>
                          <td colspan="2">
                            <input type="submit" name="submit" value="登録">
                            <input type="reset" name="reset" value="リセット"><br><br>
                            <span id="error_message"  style="display: none">パスワードが一致しません </span>
                          <td>
                        </tr>

                      </table>
                      <div>
                        <a href="#">利用規約</a><br>
                        <a href="/C4/LoginServlet">ログイン</a>
                      </div>

                </form>
            </div>


    </main>
    <footer>

    </footer>
  <script src="./js/confirmPass.js"></script>


</body>
</html>