<?php
/**
 * This class needs special handeling, since the password is stored after encrypting it, so we
 * need to change the save and update methods
 */
class User extends Model
{
	//used for defining the interface only, though never accessed directly
	private $username;
	private $email;
	private $password;
	private $age;
	static function authenticate($username,$password)
	{
		$user=User::get(array("username"=>$username,"password"=>sha1($password)));
		if($user!=null)
			return true;
		return false;
	}
	public function __toString(){
		return "username:".$this->fields["username"]."<br> email: ".$this->fields["email"]."<br>age:".$this->fields["age"]."<br>";
	}
	/**
	 * acts like a decorater, to encrypt the password before saving it if it is the first time
	 */
	public function save(){
		//if it is the first time, save it, otherwise if we are just updating user data, don't re encrypt it
		if(!isset($this->fields["id"]))
			$this->fields["password"]=sha1($this->fields["password"]);
		return parent::save();
	}
	/**
	 * @return boolean
	 */
	public function changePassword($newPassword){
		$this->fields["password"]=sha1($newPassword);
		//	echo "new pass: ".$newPassword."<br>";
		//echo "encrypted: ".$this->fields["password"]."<br>";
		return parent::save();
	}
	/**
	 * returns whether this user has the given permission string
	 * @return boolean
	 */
	public function hasPermission($permission){
		$user_permissions=$this->getManyToMany("Permission");
		//print_r($user_permissions);
		foreach ($user_permissions as $user_permission)
		{
			if($user_permission->title==$permission)
				return true;
		}

		return false;
	}

}

?>