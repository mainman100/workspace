<?php
class MaterialTypes extends Controller{
	function index(){
		$materialTypes=MaterialType::all();
		$this->render("index",array("materialTypes"=>$materialTypes));
	}
	function edit($id){
		$materialType=MaterialType::findById($id);
		if($_SERVER["REQUEST_METHOD"]=="POST")
		{
			try{
				extract($_POST);
				$validator=new validator();
				$validator->required($_POST);
				if($validator->hasErrors())
					throw new Exception();
				if($title!=$materialType->title&&MaterialType::get(array("title"=>$title)))
					$validator->addError("This title already exists");
				$materialType->title=$title;
				if(!$materialType->save())
					$validator->addError("Sorry, couldn't update the material type, try again");
				setMessage("Successfully updated $title material Type");
				redirect("/materialtypes/");
			}
			catch(Exception $e){
				$this->render("create_or_edit",array("action"=>"edit","materialType"=>$materialType,FLASH=>$_POST,ERRORS=>$validator->getErrorrs()));
			}
		}
		$this->render("create_or_edit",array("action"=>"edit","materialType"=>$materialType));
	}
	/**
	 * expects $_POST[title]
	 */
	function create(){
		if($_SERVER["REQUEST_METHOD"]=="POST")
		{
			try{
				extract($_POST);
				$validator=new validator();
				$validator->required($_POST);
				if($validator->hasErrors())
					throw new Exception();
				if(MaterialType::get(array("title"=>$title)))
					$validator->addError("This title already exists");
				$materialType=new MaterialType(array("title"=>$title));
				if(!$materialType->save())
					$validator->addError("Sorry, couldn't save the material type, try again");
				setMessage("Successfully created the new material Type");
				redirect("/materialtypes/");
			}
			catch(Exception $e){
				$this->render("create_or_edit",array("action"=>"create",FLASH=>$_POST,ERRORS=>$validator->getErrorrs()));
			}
		}
		$this->render("create_or_edit",array("action"=>"edit"));
	}

}