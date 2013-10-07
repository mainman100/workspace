<?php require_once 'views/template/header_begin.php';?>
<?php require_once 'views/template/header_end.php'; ?>

<?php require_once 'views/template/nav_begin.php';?>
<?php require_once 'views/template/nav_end.php'; ?>

<?php require_once 'views/template/sidebar_begin.php';?>
<?php require_once 'views/template/sidebar_end.php'; ?>

<div id="content">
	<?php require_once 'views/template/errors.php';?>
	<h1>
		<?php echo strtoupper($action);?>
		material type
		<?php if ($action=="edit") echo $materialType->title?>
	</h1>
	<form action="" method="post">

		<table>
			<tr>
				<td>Title:</td>
				<td><input type="text" name="title"
					value="<?php if (isset($flash)) echo $flash["title"];else echo $materialType->title; ?>">
				</td>
			</tr>
		</table>
		<input type="submit" value="<?php echo $action?>">
	</form>
</div>

<?php  require_once 'views/template/footer.php'; ?>