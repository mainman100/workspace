<?php require_once 'views/template/header_begin.php';?>
<title>Courses</title>
<?php require_once 'views/template/header_end.php'; ?>

<?php require_once 'views/template/nav_begin.php';?>
<?php require_once 'views/template/nav_end.php'; ?>

<?php require_once 'views/template/sidebar_begin.php';?>
<?php require_once 'views/courses/courses_sidebar.php';?>
<?php require_once 'views/template/sidebar_end.php'; ?>

<div id="content">
	<h1>Available courses</h1>
	<ul>
		<?php foreach ($courses as $course):?>

		<li><a href="/courses/show/<?php echo $course->id?>"><h3>
					<?php echo $course->title?>
				</h3> </a>
		</li>

		<?php endforeach?>
	</ul>

</div>

<?php  require_once 'views/template/footer.php'; ?>