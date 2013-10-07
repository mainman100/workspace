$(document).ready(function() {
	setContentandSidebarSameHeight();
});

function setContentandSidebarSameHeight() {
	$("#sidebar").height(
			Math.max($('#sidebar').height(), $('#content').height()));
	$("#content").height(
			Math.max($('#sidebar').height(), $('#content').height()));
}