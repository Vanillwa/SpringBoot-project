$(document).ready(function() {
	let theme = localStorage.getItem("theme") || 'light'
	$('body').addClass(theme)
	$('.navbar').attr('data-bs-theme', theme)
	
	$("#theme-toggle-btn").click(function() {
		$('body').toggleClass('dark').toggleClass('light')
		theme = $("body").hasClass("dark") ? "dark" : "light";
		localStorage.setItem("theme", theme);
		$('nav').attr('data-bs-theme', theme)
		$(this).text(theme)
	});
});