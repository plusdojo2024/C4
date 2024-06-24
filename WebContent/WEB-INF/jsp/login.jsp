<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン | LinX </title>
    <link rel="stylesheet" type="text/css" href="css/user.css"  >
</head>
<body class="body_bgcolor">
	<header id="login-header">
      <h1>ログイン</h1>
  	</header>
    <main>
       <div class="login" style="margin-bottom: 70px">
        <form id="login_form"  method="post" action="/C4/LoginServlet">

              <table>

                  <tr>
                    <td>
                      <label>社員 ID
                      <input type="text" name="id">
                      </label><br>
                    </td>
                  </tr>

                  <tr>
                    <td>
                      <label>パスワード
                      <input type="password" name="pw">
                      </label><br>
                    </td>
                  </tr>

                  <tr>
                    <td colspan="2">
                      <input type="submit" name="login" value="ログイン">
                      <input type="reset" name="reset" value="リセット">
                      <b><span id="error_message"></span></b>
                    <td>
                  </tr>
                </table>

                <div >
                    <a  href="/LinX/CreateUserServlet">アカウントを作成</a>
                </div>
          </form>
      </div>


    </main>
    <footer>

    </footer>
    <script>
	    let form_obj = document.getElementById('login_form');
	    let error_message_obj = document.getElementById('error_message');

	    form_obj.onsubmit = function () {
	      if(!form_obj.id.value || !form_obj.pw.value){
	        error_message_obj.textContent = '🚩 IDとパスワードを入力してください❗️';
	        return false;
	      }
	      error_message_obj.textContent = null;
	    }

	    form_obj.onreset = function () {
	      error_message_obj.textContent = null;
	    }
  </script>

</body>
</html>