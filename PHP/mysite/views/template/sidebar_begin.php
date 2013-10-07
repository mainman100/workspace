<div id="sidebar">
		<ul id="root">

		<?php if (isset($_SESSION[user_id])):?>
			<li class="dropdown"><a href="#">My Account</a>
				<ul>
					<li><a href="/users/editProfile">Edit profile</a></li>
					<li><a href="/users/changePassword">Edit password</a></li>
				</ul>
			</li>
		<?php endif?>