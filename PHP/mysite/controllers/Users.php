<?php
require_once 'models/ManyToMany/User_Course.php';
class Users extends Controller{

	function index(){
		$this->render("index");
	}
	function register()
	{
		$errors=array();
		if ($_SERVER["REQUEST_METHOD"]=="POST")
		{
			try{

				extract($_POST);
				$validator =new validator();
				$validator->required($_POST);
				$validator->samePasswords($password, $repeat_password);
				if (User::get(array("username"=>$username)))
					$validator->addError("This username is taken, try another one");
				if(User::get(array("email"=>$email)))
					$validator->addError("This email is taken, try another one");
				//if (User::isRegistered("email",$email))
				//$validator->addError("This email is taken, try another one");
				if($validator->hasErrors())
					throw new Exception();
				$user=new User(array("username"=>$username,"password"=>$password,"email"=>$email));
				if(!$user->save())
					$validator->addError("Sorry, some error occured. Couldn't register you");
				if($validator->hasErrors())
					throw new Exception();
				$_SESSION[user_id]=$user->id;
				$_SESSION[username]=$username;
				$_SESSION[MESSAGE]="Welcome to our site";
				header("Location:/home/");
				exit();
			}
			catch (Exception $e)	{
				$this->render("register",array(FLASH=>$_POST,ERRORS=>$validator->getErrorrs()));
			}
		}
		// in case of GET
		$this->render("register");

	}
	function login(){
		if($_SERVER["REQUEST_METHOD"]=="POST")
		{
			extract($_POST);
			try{
				if(!User::authenticate($username, $password))
					throw new Exception("Invalid username or password");
				$user=User::get(array("username"=>$username));
				$_SESSION[user_id]=$user->id;
				$_SESSION[username]=$username;
				$_SESSION[MESSAGE]="Welcome back, ".$username;
				header("Location:/home");
				exit();
			}
			catch (Exception $e){
				$this->render("login",array(FLASH=>$_POST,ERRORS=>array($e->getMessage())));
			}
		}
		$this->render("login");

	}
	function logout(){
		session_destroy();
		header("Location:/home");
		exit();
	}
	/**
	 * expects $_POST[$old_password], $_POST[$new_password] and $_POST[$new_password_repeat]
	 */
	function changePassword(){
		requireLogin();
		if ($_SERVER["REQUEST_METHOD"]=="POST"){
			try{
				extract($_POST);
				$validator=new validator();
				$validator->required($_POST);
				if($validator->hasErrors())
					throw new Exception();
				$user=User::findById($_SESSION["user_id"]);
				//$user=User::get(array("username"=>$_SESSION["user"]));
				if(sha1($old_password)!=$user->password){
					$validator->addError("Old password is not correct !");
					throw new Exception();
				}
				if($new_password!=$new_password_repeat){
					$validator->addError("Passwords don't match");
					throw new Exception();
				}
				if(!$user->changePassword($new_password))
				{
					echo "couldn't change the password<br>";
					//echo "new user data: ".$user."<br>";
					throw new Exception();
				}
				$_SESSION[MESSAGE]="Your password is changed successfully";
				header("Location:/home");
				exit();
			}
			catch (Exception $e){
				$this->render("change_password",array(ERRORS=>$validator->getErrorrs()));
			}
		}
		$this->render("change_password");
	}
	/**
	 * expects $_POST[username] and $_POST[email].
	 */
	function editProfile(){
		requireLogin();
		$user=User::findById($_SESSION["user_id"]);
		if ($_SERVER["REQUEST_METHOD"]=="POST"){
			try{
				extract($_POST);
				$validator=new validator();
				$validator->required($_POST);
				if($validator->hasErrors())
					throw new Exception();
				//This new name is already taken
				if($username!=$_SESSION[username]&&User::get(array("username"=>$username)))
				{
					$validator->addError("This new username is already taken !");
					throw new Exception();
				}
				//$user=User::get(array("username"=>$_SESSION["user"]));
				$user->username=$username;
				$user->email=$email;
				// may if he updated the username, it already exists
				if(!$user->save())
				{
					//	echo "couldn't save the new user data<br>";
					//echo "new user data: ".$user."<br>";
					$validator->addError("Couldn't save the changes, please try again later");
					throw new Exception();
				}
				//$_SESSION["user"]=$username;
				$_SESSION[username]=$username;
				$_SESSION[MESSAGE]="Your profile is successfully updated";
				//$this->render("customize.php");
				header("Location:/home");
				exit();
			}
			catch (Exception $e){
				$this->render("edit_profile",array(FLASH=>$_POST,ERRORS=>$validator->getErrorrs()));
			}
		}
		//$user= User::get(array("username"=>$_SESSION["user"]));
		//	echo "user :".$user;
		$this->render("edit_profile",array("username"=>$user->username,"email"=>$user->email));
	}
}

?>