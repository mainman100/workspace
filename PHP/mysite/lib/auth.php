<?php

function requireLogin(){
	if(is_loggedin())
		return;
	header("Location:/home/");
	exit();
}
function is_loggedin(){
	return isset($_SESSION["user_id"]);
}
function getCurrentUser(){
	return User::findById($_SESSION[user_id]);
}
function hasPermission($permission){
	if (is_loggedin())
	{
		$user=getCurrentUser();
		return $user->hasPermission($permission);
	}
	return false;
}
function requirePermission($permission){
	$user=getCurrentUser();
	if (!$user->hasPermission($permission))
	{
		echo "<h1> You are not allowed to view the content of this page</h1>";
		exit();
	}
}

?>