function confirmLogout(event) {
	event.preventDefault();
	if (confirm("ログアウトしますか？")) {
		document.getElementById('logout-form').submit();
		return;
	}
}