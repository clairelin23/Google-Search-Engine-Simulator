package sorting;

import java.awt.Color;

//a class for storing Rank object, assigning Rank object linking property with other Nodes to support BST manipulation.
public class Node 
{
	private Node left;
	private Node right;
	private Node p;
	private Rank key;
	private String color;
	
	//default constructor
	public Node() 
	{}
	
	//constructor when the key of Node is known
	//@param key the Rank object to construct the Node
	public Node(Rank key)
	{
		this.key = key;
		this.left =null;
		this.right = null;
		this.p =null;
		
	}
	
	//gets the color of the Node
	//@return the color of the Node
	public String getColor()
	{
		return this.color;
	}
	
	//sets history score
	//@param a new history score
	public void setColor(String color)
	{
		this.color = color;
	}
	
	//gets the left node
	//@return the list node
	public Node getLeft()
	{
		return this.left;
	}
	
	//gets the right node
	//@return the right node
	public Node getRight()
	{
		return this.right;
	}
	
	//gets the parent node
	//@return the parent node
	public Node getParent()
	{
		return this.p;
	}
	
	//gets the key of node
	//@return the key of node
	public Rank getKey()
	{
		return key;
	}
	
	//sets the left node
	//@param i the node to be set as left node
	public void setLeft(Node i)
	{
		this.left = i;
	}
	
	//sets the right node
	//@param i the node to be set as right node
	public void setRight(Node i)
	{
		this.right =i;
	}
	
	//sets the parent node
	//@param i the node to be set as the parent node
	public void setParent(Node i)
	{
		this.p =i;
	}
	
	//sets the key of node
	//@param r the Rank object to be set as the node's key
	public void setKey(Rank r)
	{
		this.key = r;
	}
}
