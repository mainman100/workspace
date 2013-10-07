<?php
/** @Entity **/
class Post
{
	/** @Id @GeneratedValue @Column(type="integer") **/
	protected $id;
	/** @Column(type="string") **/
	protected $title;
	/** @Column(type="text") **/
	protected $body;
	/**
	 * @ManyToOne(targetEntity="User")
	 **/
	protected $author;

	public function __construct(User $user)
	{
		$this->author = $user;
	}
}