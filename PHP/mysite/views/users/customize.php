<?php
require_once 'views/template/header.php';
?>
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
		<h2>Edit personal data</h2>
		<table>
			<tr>
				<td align="left">Username:</td>
				<td align="left"><input type="text" name="username"
					value="<?php if (isset($flash))echo $flash["username"]; else echo $username ?>" />
				</td>

			</tr>
			<tr>
				<td align="left">Email:</td>
				<td align="left"><input type="text" name="email"
					value="<?php if (isset($flash))echo $flash["email"];else echo  $email ?>" />
				</td>
			</tr>
		</table>
		<input type="submit" value="submit">
	</form>

</div>

<?php 

require_once 'views/template/footer.php';
?>