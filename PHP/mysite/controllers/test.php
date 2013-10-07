<?php

class Test extends Controller
{
	function index(){
		//	$user=User::findById($_SESSION[user_id]);
		//	$user_courses=$user->getCourses();
		//		//print_r($user_courses);
		//	$user_courses=$user->getManyToMany("Course");
		//	$course=Course::findById(1);
		//	$user_courses=$course->getManyToMany("User",false);
		$course=Course::findById(2);
		print_r($course->getManyToOne("User","lecturer_id"));
	}
	function testCreateUser()
	{
		$user= new User();
		$user->username="ali_tr";
		$user->password="123";
		$user->email="rokneldien@hotrrmailt.com";
		$user->age=12;
		$user->save();
	}
	function testUpdateUser(){
		//TODO
		$user->email="updated twice";
		$user->save();
	}
	function testFetchUser(){
		//TODO
		echo "Total number of users: ".User::count()."<br>";
		$all=User::all();
		$loai=User::find(array("username"=>"loai"));
		$first=User::get(array("age"=>12));
		print_r ($all);
		echo "<br>";
		print_r($loai);
		echo "<br>";
		print_r($first);
		echo "<br>";
		print_r(User::findById(15));
		$toDelete=User::get(array("username"=>"hashem"));
		if($toDelete!=null)
			$toDelete->delete();
	}
}



?>