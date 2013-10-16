<?php

class Controller{
	public $viewsDir;
	public function __construct()
	{
		$this->viewsDir="views/".strtolower(get_class($this))."/";
		session_start();
	}
	function render($file,$args=null){
		if(is_array($args))
			extract($args);
		require $this->viewsDir.$file.".php";
		exit();
	}
	function render404()
	{
		require "views/404.php";
		exit();
	}
}
?>