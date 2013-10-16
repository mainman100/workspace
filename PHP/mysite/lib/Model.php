<?php
class Model {
	private static $db = null;
	protected $table="";
	/**
	 * Constructs a new DB Object and returns it
	 *
	 * @return mysqli connection to the database
	 */
	public static function getDb() {
		if (static::$db === null) {
			static::$db = new mysqli(db_host,db_username, db_password, db_name);
		}

		return static::$db;
	}
	public $fields=array();
	public function __construct(array $fields=array()){
		if ($fields==null)
			$fields=array();
		foreach($fields as $key=>$value)
			$this->fields[$key]=$value;
		$this->table=get_class($this);
	}
	public function __get($key){
		if(isset($this->fields[$key]))
			return $this->fields[$key];
		return null;
	}
	public function __set($key,$value){
		$this->fields[$key]=$value;
	}
	public function exists(){
		return isset($this->fields["id"]);
	}
	/**
	 * Returns the object matched by its id
	 * @return Model
	 */
	public static function findById($id)
	{
		return static::get(array("id"=>$id));
	}
	/**
	 * Returns all the records in the table
	 * @return array
	 */
	public static function all(){
		return static::find();
	}
	/**
	 * performs a select query, conjucting all parameters with and.
	 * @return array
	 */
	public static function find($params=null){
		$query="select * from ".get_called_class();
		//	echo "in find ".$params;
		if(isset($params)){
			$query.=" where ";
		}
		else{
			$params=array();
			//for removing the suffix of the where clause below
			$query.="     ";
		}
		foreach ($params as $column=>$value)
		{
			if(gettype($value)=="string")
				$query.=$column."= '$value' and ";
			else
				$query.=$column."= $value and ";
			//	echo "in find loop:<br>";
		}
		$query=substr($query, 0,-4);
		$db=Model::getDb();
		$result=$db->query($query);
		//echo "query: ".$query."<br>";
		$objects=array();
		$class=get_called_class();
		for($i=0;$i<$result->num_rows;$i++){
			$row=$result->fetch_assoc();
			array_push($objects, new $class($row));
		}
		return $objects;
	}
	/**
	 * Returns the first matching result of findAll()
	 * @return Model
	 */
	public static function get($params=null){
		//	echo "in get :".$params["id"]."<br>";
		$all=static::find($params);
		if(count($all)==0)
			return null;
		return $all[0];
	}
	public static function count(){
		$db=Model::getDb();
		$query="select count(*) from ".get_called_class();
		$result=$db->query($query);
		$row=$result->fetch_row();
		return $row[0];
	}
	/**
	 * saves the pbject and returns its id
	 */
	public function save(){
		$db=Model::getDb();
		//assue that it has atleast one attributre !!
		$head="insert into ";
		$tail="";
		if($this->exists()){
			$head="update ";
			$tail=" where id=".$this->fields["id"];
		}
		$body=" set ";
		foreach($this->fields as $column=>$value){
			if($column=="id")
				continue;
			if (!isset($column)||gettype($value)=="NULL")
				$body.=$column."=NULL, ";
			else{
				if(gettype($value)=="string")
					$body.=$column."='$value', ";
				else
					$body.=$column."=$value, ";
			}
			//echo "type: ".gettype($value);
		}
		$body=substr($body, 0,-2);
		$query=$head.$this->table.$body.$tail;
		//	echo "query: ".$query;
		if(!$db->query($query)){
			echo "Query not executed<br>";
			return false;
		}
		$this->fields["id"]=$db->insert_id;
		return true;
	}
	/**
	 * Deleted the current entity and return whether it is deleted
	 * @return boolean
	 */
	public function delete(){
		$db=Model::getDb();
		$query="delete from ".get_called_class()." where id=".$this->fields["id"];
		return $db->query($query);

	}
	public function __toString(){
		return "MODEL";
	}
	/**
	 * ManyToOne
	 * @return Model
	 */
	public function getManyToOne($dst_class,$column_name=null){
		if(!isset($column_name))
			$column_name=strtolower($dst_class)."_id";
		$result=$dst_class::get(array("id"=>$this->fields[$column_name]));
		return $result;
	}
	/**
	 * oneToMany
	 * @param $dst_class the class that contains a foreign key to this class
	 * @param $dst_column the name of the foreign key column in the dst_class, if this argument
	 * is not set, it will be toLowerCae($dst_column)_id
	 * @return array
	 */
	public function getList($dst_class,$dst_column=null){
		if(!isset($dst_column))
			$dst_column_name=strtolower($dst_class)."_id";
		$result=$dst_class::find(array($dst_column=>$this->fields["id"]));
		return $result;
	}

	/**
	 * given the manyToMany table name, it assumes there is a foreign key there
	 * with name <toLowerCase(this_class_name)_id>, so it get all classes of the other entity
	 * @param $dst_class the name of the related entity class
	 * @param $owner whether the name of the manyToMany table in the db begin with this class name,
	 * for exmaple if this class is User, and the $dst_class=Course, then the db contains User_Course,
	 * else Course_User
	 * @return array
	 */
	public function getManyToMany($dst_class,$owner=true,$dst_column_name=null){
		$result=array();
		//assue we are given $dst_class= Courses
		//user_id
		$this_column_name=strtolower(get_class($this))."_id";
		//course_id
		if($dst_column_name==null)
			$dst_column_name=strtolower($dst_class)."_id";
		if($owner)
			$manyToMany_className=get_class($this)."_".$dst_class;
		else
			$manyToMany_className=$dst_class."_".get_class($this);
		$manyToManyRows=$manyToMany_className::find(array($this_column_name=>$this->fields["id"]));
		foreach ($manyToManyRows as $manyToManyRow){
			$dst_entity=$dst_class::findById($manyToManyRow->$dst_column_name);
			array_push($result, $dst_entity);
		}
		return $result;
	}
}
?>