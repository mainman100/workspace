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
	// for odesk
	function youtubeTextTransfer($text){
		$ret="";
		for($i=0; $i<strlen($text); $i++){
			//search for <iframe
			if(substr($text, $i,7)!="<iframe")
			{
				$ret.=$text[$i];
				continue;
			}				
			//tag found, extract the id
			while(substr($text,$i,5)!="embed")
				$i++;
			//escape the /
			$i+=strlen("embed")+1;
			$id="";
			while($text[$i]!="?")
				$id.=$text[$i++];
			while(substr($text,$i,6)!="iframe")
				$i++;
			$i+=strlen("iframe");
			//replace the string
			$ret.="<a href=\"http://www.youtube.com/embed/".$id."\">";
			$ret.="<img src=\"http://img.youtube.com/vi/".$id."/0.jpg\"/>";
			$ret.="</a>";
		}
		echo $ret;
	}
}

?>