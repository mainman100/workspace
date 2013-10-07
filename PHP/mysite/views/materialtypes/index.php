<?php require_once 'views/template/header_begin.php';?>
<?php require_once 'views/template/header_end.php'; ?>

<?php require_once 'views/template/nav_begin.php';?>
<?php require_once 'views/template/nav_end.php'; ?>

<?php require_once 'views/template/sidebar_begin.php';?>
<hr>
<li style="padding-left: 20px; font-size: large;">Admin</li>
<li><a href="/materialtypes/create/"> Create material type</a> </li>
<?php require_once 'views/template/sidebar_end.php'; ?>

<div id="content">

	<h1>Available material types</h1>
	<ul>
		<?php foreach($materialTypes as $materialType):?>
		<li><a href="/materialtypes/edit/<?php echo $materialType->id;?>"><h3> <?php echo $materialType->title;?>
		</h3></a>
		</li>
		<?php endforeach;?>
	</ul>
</div>

<?php  require_once 'views/template/footer.php'; ?>