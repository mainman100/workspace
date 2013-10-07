
<?php 
?>

<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
<script src="/public/js/scripts.js"> </script>

<link rel="stylesheet" href="/public/css/base.css" />
</head>
<body>
	<nav>
		<?php if (isset($_SESSION["user"])):?>
		<span id="welcome"> Welcome, <?php echo $_SESSION["user"];?>
		</span>


		<?php endif?>
		<a href="/home/">Home</a> <a href="#">Our apps</a> <a href="#">Support</a>
		<a href="#">About</a>
		<?php if (!isset($_SESSION["user"])):?>
		<a href="/users/login/">login</a> <a href="/users/register/">register</a>
		<?php else:?>
		<a href="/users/logout/">logout</a>
		<?php endif?>

	</nav>

	<?php if (isset($_SESSION[MESSAGE])):?>
	<div class="message">
		<?php echo $_SESSION[MESSAGE]?>
	</div>
	<?php 
	unset($_SESSION[MESSAGE]);
	endif
	?>

	<div id="sidebar">
		<?php if (isset($_SESSION["user"])):?>
		<ul id="root">
			<li class="dropdown"><a href="#">Account</a>
				<ul>
					<li><a href="/users/customize">Change personal data</a></li>
					<li><a href="/users/changePassword">Change password</a></li>
				</ul>
			</li>
			<li class="dropdown"><a href="#">Enroll in a course</a>
				<ul>
					<li><a href="#">CSEN 301</a></li>
					<li><a href="#">CSEN 302</a></li>
				</ul>
			</li>
		</ul>
		<?php endif?>
	</div>