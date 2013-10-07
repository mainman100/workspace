<div id="errors">
	<?php if(isset($errors)):?>
	<h3 class="error">Oops ! Fix these errors !</h3>
	<ul>
		<?php foreach ($errors as $error):?>
		<li class="error"><?php echo $error?></li>
		<?php endforeach?>
	</ul>
	<?php endif?>
</div>
