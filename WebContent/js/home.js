function confirmLogout(event) {
	event.preventDefault();
	if (confirm("ログアウトしますか？")) {
		window.location.href = event.target.href;
	}
}