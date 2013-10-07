<?php require_once 'views/template/header_begin.php';?>
<title> Change password</title>
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
	<form action="" method="post">
		<h2>Change password</h2>
		<table>
			<tr>
				<td align="left">Old password:</td>
				<td align="left"><input type="password" name="old_password" />
				</td>

			</tr>
			<tr>
				<td align="left">New password:</td>
				<td align="left"><input type="password" name="new_password" />
				</td>
			</tr>
			<tr>
				<td align="left">Repeat new password:</td>
				<td align="left"><input type="password" name="new_password_repeat" />
				</td>
			</tr>
		</table>
		<input type="submit" value="submit">
	</form>

</div>

<?php 

require_once 'views/template/footer.php';
?>