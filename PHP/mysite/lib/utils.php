<?php
class validator{

	public $errors=array();
	function hasErrors(){
		return count($this->errors);
	}
	function getErrorrs(){
		return $this->errors;
	}
	function addError($error,$throwException=true)
	{
		array_push($this->errors,$error);
		if($throwException)
			throw new Exception();
	}
	function samePasswords($pwd1,$pwd2){
		if ($pwd1!=$pwd2)
			array_push($this->errors,"passwords don't match");
	}
	function required($args){
		if(is_array($args))
		{
			foreach ($args as $key=>$value)
				if (!isset($key)||trim($value)=='')
				{
					$key_visualized=str_replace("_", " ", $key);
					array_push($this->errors,"$key_visualized is required");
				}
		}
		else
		{
			if (!isset($args)||trim($args)=='')
			{
				$args_visualized=str_replace("_", " ", $args);
				array_push($this->errors,"$args_visualized is required");

			}
		}
	}
	function __get($name)
	{
		if ($name=="errors"){
			if(count($errors)==0)
				return null;
		}
		return $this->$name;
	}
}
function requireDirectoryFiles($dir){
	$files=scandir($dir);
	//print_r($files);
	foreach ($files as $file )
	{
		if ($file=="."||$file==".."||is_dir($dir."/".$file))
			continue;
		//echo $file."<br>";
		//	echo "required ".$controller_file."<br>";
		require_once $dir."/".$file;
	}

}
//----------- end constants----------------
function render404()
{
	require_once 'views/404.php';
	exit;
}
//--------------
function setMessage($message,$class="success"){
	$_SESSION[MESSAGE]=$message;
}
function deleteMessage(){
	unset($_SESSION[MESSAGE]);
}
function redirect($url){
	header("Location:".$url);
	exit();
}
?>