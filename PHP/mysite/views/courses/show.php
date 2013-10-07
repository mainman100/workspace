<?php require_once 'views/template/header_begin.php';?>
<title><?php echo $course->title?></title>
<?php require_once 'views/template/header_end.php'; ?>

<?php require_once 'views/template/nav_begin.php';?>
<?php require_once 'views/template/nav_end.php'; ?>

<?php require_once 'views/template/sidebar_begin.php';?>
<?php require_once 'views/courses/courses_sidebar.php';?>
<?php if(hasPermission("admin")):?>
<li><a href="/courses/edit/<?php echo $course->id;?>"> Edit this course</a>
</li>
<?php endif?>
<?php require_once 'views/template/sidebar_end.php'; ?>

<div id="content">
	<h1>
		<?php echo $course->title;?>
	</h1>
	<p style="font-size: medium;">
		<?php echo nl2br($course->description);?>
	</p>
	<hr>
</div>

<?php  require_once 'views/template/footer.php'; ?>