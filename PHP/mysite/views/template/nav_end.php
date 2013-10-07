</ul>
</nav>
<?php if (isset($_SESSION[MESSAGE])):?>
<div class="message">
	<?php echo $_SESSION[MESSAGE]?>
</div>
<?php 
unset($_SESSION[MESSAGE]);
endif
?>