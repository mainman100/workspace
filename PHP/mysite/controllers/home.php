<?php
class Home extends Controller{
	function index(){
	//	$text = file_get_contents('test.txt');		
	//	$this->youtubeTextTransfer($text);
		$this->render("index");
	}
	function show($id=null){
		if (!isset($id))
			$this->render404();

		$this->render("show.php",array("id"=>$id));
	}
}

?>