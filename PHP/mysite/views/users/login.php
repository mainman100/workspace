<?php require_once 'views/template/header_begin.php';?>
<title>Login</title>
<?php require_once 'views/template/header_end.php'; ?>

<?php require_once 'views/template/nav_begin.php';?>
<?php require_once 'views/template/nav_end.php'; ?>

<?php require_once 'views/template/sidebar_begin.php';?>
<?php require_once 'views/template/sidebar_end.php'; ?>
<div id="content">
	<div id="errors">
		<?php if(isset($errors)):?>
		<h3 class="error">Oops ! Fix these errors !</h3>
		<ul>
			<?php foreach ($errors as $error):?>
			<li class="error"><?php echo $error?>
			</li>
			<?php endforeach?>
		</ul>
		<?php endif?>
	</div>
	<h2>Login</h2>
	<form action="" method="post">
		<div>
			<input type="text" name="username" placeholder="username"
				value="<?php if (isset($flash))echo $flash["username"] ?>">
		</div>
		<div>
			<input type="password" name="password" placeholder="password">
		</div>
		<input type="submit" value="login">
	</form>

</div>

<?php 

require_once 'views/template/footer.php';
?>