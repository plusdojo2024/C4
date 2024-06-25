 let form_obj = document.getElementById('login_form');
	    let error_message_obj = document.getElementById('error_message');

	    form_obj.onsubmit = function () {
	      if(!form_obj.id.value || !form_obj.pw.value){
	        error_message_obj.textContent = '🚩IDとパスワードを入力してください❗️';
	        return false;
	      }
	      error_message_obj.textContent = null;
	    }

	    form_obj.onreset = function () {
	      error_message_obj.textContent = null;
	    }