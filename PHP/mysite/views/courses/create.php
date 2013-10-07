<?php require_once 'views/template/header_begin.php';?>
<title>Create a course</title>
<?php require_once 'views/template/header_end.php'; ?>

<?php require_once 'views/template/nav_begin.php';?>
<?php require_once 'views/template/nav_end.php'; ?>

<?php require_once 'views/template/sidebar_begin.php';?>
<?php require_once 'views/courses/courses_sidebar.php';?>
<?php require_once 'views/template/sidebar_end.php'; ?>
<div id="content">
	<?php require_once 'views/template/errors.php';?>
	<h2>Create course</h2>
	<form action="" method="post">
		<table>
			<tr>
				<td align="left">Title:</td>
				<td align="left"><input type="text" name="title"
					value="<?php if (isset($flash))echo $flash["title"];?>"></td>

			</tr>
			<tr>
				<td align="left">Description:</td>
				<td align="left"><textarea name="description" rows="10" cols="40">
						<?php if (isset($flash))echo $flash["description"];?>
					</textarea>
				</td>
			</tr>
		</table>
		<input type="submit" value="create">
	</form>
</div>

<?php  require_once 'views/template/footer.php'; ?>