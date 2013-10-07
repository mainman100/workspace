$(document).ready(function() {
	setContentandSidebarSameHeight();
	setDrowpDownBehavior();
	removeFlashMessage();
});

function setContentandSidebarSameHeight() {
	$("#sidebar").height(
			Math.max($('#sidebar').height(), $('#content').height()));
	$("#content").height(
			Math.max($('#sidebar').height(), $('#content').height()));
}
function setDrowpDownBehavior() {
	$('li.dropdown a').click(function() {
		$(this).parent().find('ul').toggle();
	});
}
function removeFlashMessage() {

	$(".message").delay(2000).fadeOut();
}
function alertDelete(name) {
	$('input[type=submit][name="delete"]').on("click", function() {
		return window.confirm('Are you sure you want to delete this '+name+'?');
	});
}
