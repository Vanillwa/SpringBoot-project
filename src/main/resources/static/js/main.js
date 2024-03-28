let theme = localStorage.getItem("theme") || 'light'

$('body').addClass(theme)
$('.navbar').attr('data-bs-theme', theme)
if (theme == 'dark'){
	$('#flexSwitchCheckDefault').prop('checked', true)
	$('#toggle-icon').addClass('bi-moon-fill')
}else{
		$('#toggle-icon').addClass('bi-sun-fill')
}
	
	


$("#flexSwitchCheckDefault").click(function() {
	$('body').toggleClass('dark').toggleClass('light')
	$('#toggle-icon').toggleClass('bi-sun-fill').toggleClass('bi-moon-fill')
	theme = theme == "dark" ? "light" : "dark";
	localStorage.setItem("theme", theme);
	$('nav').attr('data-bs-theme', theme)
});