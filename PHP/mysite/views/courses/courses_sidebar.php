<?php if(hasPermission("admin") ):?>
<hr>
<li style="padding-left: 20px; font-size: large;">Admin</li>
<li><a href="/courses/create/">Create course</a>
</li>
<li class="dropdown"><a href="#">Material Types</a>
	<ul>
		<li><a href="/materialtypes/create/">Add material type</a></li>
	</ul>
</li>
<?php endif?>