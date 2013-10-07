<?php
class Courses extends Controller{

	function index(){
		$courses=Course::all();
		$this->render("index",array("courses"=>$courses));
	}
	function show($id){
		$course=Course::findById($id);
		$this->render("show",array("course"=>$course));
	}
	/**
	 * expects $_POST[title], $_POST[description]
	 */
	function create(){
		requirePermission("admin");
		if($_SERVER["REQUEST_METHOD"]=="POST")
		{
			try{
				extract($_POST);
				$validator=new validator();
				$validator->required($_POST);
				if(Course::get(array("title"=>$_POST["title"])))
					$validator->addError("This title already exists");
				if($validator->hasErrors())
					throw new Exception();
				$course=new Course(array("title"=>$_POST["title"],"description"=>$_POST["description"]));
				if(!$course->save())
				{
					$validator->addError("Couldn't create the course");
					throw new Exception();
				}
				header("Location:/courses/");
				exit();
			}
			catch(Exception $e)
			{
				$this->render("create",array(FLASH=>$_POST,ERRORS=>$validator->getErrorrs()));
			}
		}
		$this->render("create");
	}
	function edit($id){
		requirePermission("admin");
		$course=Course::findById($id);
		if($_SERVER["REQUEST_METHOD"]=="POST")
		{
			if(isset($_POST["delete"])){
				if($course->delete())
					setMessage("Successfully deleted ".$course->title." course");
				else
					setMessage("Couldn't delete the course, please try again later");
				redirect("/courses/");
			}
			try{
				extract($_POST);
				$validator=new validator();
				$validator->required($_POST);
				if($validator->hasErrors())
					throw new Exception();
				if($title!=$course->title && Course::get(array("title"=>$title)))
					$validator->addError("This title already exists");
				$course->title=$title;
				$course->description=$description;
				if(!$course->save())
					$validator->addError("Couldn't edit the course");
				setMessage("Successfully updated ".$course->title." course data");
				redirect("/courses/show/".$id);
			}
			catch(Exception $e)
			{
				$this->render("edit",array("course"=>$course,FLASH=>$_POST,ERRORS=>$validator->getErrorrs()));
			}
		}
		$this->render("edit",array("course"=>$course));
	}
}