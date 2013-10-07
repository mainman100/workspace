<?php 

require_once 'lib/Controller.php';
require_once 'lib/utils.php';
require_once 'lib/Model.php';
require_once 'lib/config.php';
require_once 'lib/auth.php';

//************** require all controllers ********************
/*$controllers_dir="controllers";
 $controller_files=scandir($controllers_dir);
foreach ($controller_files as $controller_file )
{
if ($controller_file=="."||$controller_file=="..")
	continue;
//	echo "required ".$controller_file."<br>";
require_once $controllers_dir."/".$controller_file;
}*/

requireDirectoryFiles("controllers");
requireDirectoryFiles("models");
requireDirectoryFiles("models/ManyToMany");
requireDirectoryFiles("models/auth");


//************* end requiring controllers ********************
$router = new Router();
$router->executeAction();

class Router{
	private $controller;
	private $action;
	private $id;
	public function __construct()
	{
		if ($_REQUEST['controller'] == "") {
			$this->controller = "home";
		} else {
			$this->controller = $_REQUEST['controller'];
		}
		if ($_REQUEST['action'] == "") {
			$this->action = "index";
		} else {
			$this->action = $_REQUEST['action'];
		}
		if ($_REQUEST['id'] == "") {
			$this->id = null;
		} else {
			$this->id = $_REQUEST['id'];
		}
	}
	public function executeAction() {
		//	echo $this->controller.", ".$this->action."<br>";
		//	echo class_exists($this->controller).method_exists($this->controller,$this->action);
		//does the class exist?
		if (class_exists($this->controller) && method_exists($this->controller,$this->action)) {
			$controller=new $this->controller();
			if ($this->id==null)
				$controller->{$this->action}();
			else
				$controller->{$this->action}($this->id);

		}
		else {
			//bad method error
			render404();
		}
	}
}

?>