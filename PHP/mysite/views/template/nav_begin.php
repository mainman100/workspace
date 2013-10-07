<body>
	<nav>
		<?php if (is_loggedin()):?>
		<span id="welcome"> Welcome, <?php echo $_SESSION[username];  ?>
		</span>


		<?php endif?>
		<ul>
			<li><a href="/home/">Home</a>
			</li>
			<li><a href="/courses/">Courses</a>
			</li>
			<li><a href="#">Support</a>
			</li>
			<li><a href="#">About</a>
			</li>
			<?php if (!is_loggedin()):?>
			<li><a href="/users/login/">login</a>
			</li>
			<li><a href="/users/register/">register</a>
			</li>
			<?php else:?>
			<li><a href="/users/logout/">logout</a>
			</li>
			<li><a href="">Admin</a>
				<ul>
					<li><a href="#">Courses</a></li>
					<li><a href="#">Material types</a></li>
					<li><a href="#">Material items</a></li>
				</ul>
			</li>
			<?php endif?>