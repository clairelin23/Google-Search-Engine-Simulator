package sorting;

import java.awt.Color;
import java.util.ArrayList;

//contains methods to buildup and manipulate a RBT of nodes with keys of Rank Object
public class RBTree 
{
	private Node root;
	private Node nil;
	private ArrayList<Node> a  = new ArrayList<Node>();
	private int counter =1;
	
	//default constructor of RBT
	public RBTree()
	{
	}
		
	//sets the root of this RBT when the first node is inserted to the BST
	//@param Node the new root
	public void initialSetRoot(Node r)
	{
		this.root = r;
		this.root.setColor("black");
		this.root.setParent(nil);
		this.root.setLeft(nil);
		this.root.setRight(nil);
	}
	
	//sets the nil of this RBT, nil's parent, children, key are immaterial
	//@param Node the T.nil
	public void setNil(Node r)
	{
		this.nil = r;
	}
	
	//gets the root of this RBT
	//@return the root of this RBT
	public Node getRoot()
	{
		return root;
	}
	
	//inserts an element into a RB Tree
	//@param T the RB Tree where a Node is going to be inserted
	//@param z the Node to be inserted
	public void treeInsert(RBTree T, Node z)
	{
		Node y = T.nil;
		Node x = T.root;
		while (x!=T.nil )
		{
			y = x;
			if (z.getKey().compareTo(x.getKey())<0)	
			{
				x = x.getLeft();
			}
			else
			{
				x = x.getRight();
			}
		}
		z.setParent(y);
		if (y ==T.nil)//tree T was empty
		{
			T.root = z;	
			root.setParent(nil);
		}
		else if(z.getKey().compareTo(y.getKey())<0)
		{
			y.setLeft(z);
		}
		else
		{
			y.setRight(z);
		}
		z.setLeft(T.nil);
		z.setRight(T.nil);
		z.setColor("red");
		RBInsertFixup(T,z);
	}
	
	//fixes up RBT property violations caused by treeInsert
	//@param T the RB-tree to be operated on
	//@param z the node where RBInsertFixup starts taking place
	public void RBInsertFixup(RBTree T, Node z)
	{
		while (z.getParent().getColor().equals("red"))
		{
			if (z.getParent() == z.getParent().getParent().getLeft())
			{
				Node y = z.getParent().getParent().getRight();
				if (y.getColor().equals("red"))
				{
					
					z.getParent().setColor("black");
					y.setColor("black");
					z.getParent().getParent().setColor("red");
					z = z.getParent().getParent();
				}
				else 
				{
					if (z == z.getParent().getRight())
					{
						
						z = z.getParent();
						leftRotate(T,z);
				}
					
					z.getParent().setColor("black");
					z.getParent().getParent().setColor("red");
					rightRotate(T,z.getParent().getParent());
				}
			}
			else
			{
				Node y = z.getParent().getParent().getLeft();
				if (y.getColor().equals("red"))
				{
					
					z.getParent().setColor("black");
					y.setColor("black");
					z.getParent().getParent().setColor("red");
					z = z.getParent().getParent();
				}
				else 
				{
					if (z == z.getParent().getLeft())
				    {
						
						z = z.getParent();
						rightRotate(T,z);
				    }
					
						z.getParent().setColor("black");
						z.getParent().getParent().setColor("red");
						leftRotate(T,z.getParent().getParent());
				}					
			}
		}
		T.root.setColor("black");
	}	
	
	//operation on left-rotating a RBT restructure/balance the tree
	//@param T the RB-tree to be operated on
	//@param x the Node where the left-rotation takes place
	public void leftRotate(RBTree T, Node x)
	{
			Node y = x.getRight();
			x.setRight(y.getLeft());
			if (y.getLeft()!=T.nil)
			{
				y.getLeft().setParent(x);
			}
			y.setParent(x.getParent());
			if (x.getParent()==T.nil)
			{
				T.root = y;
				root.setParent(T.nil);
			}
			else if (x == x.getParent().getLeft())
			{
				x.getParent().setLeft(y);
			}
			else 
			{
				x.getParent().setRight(y);
			}
			y.setLeft(x);
			x.setParent(y);	
	}

	//operation on right-rotating a RB-tree restructure/balance the tree
	//@param T the RB=tree to be operated on
	//@param y the Node where the right-rotation takes place
	public void rightRotate(RBTree T, Node y)
	{
			Node x = y.getLeft();
			y.setLeft(x.getRight());
			if (x.getRight()!=T.nil)
			{
				x.getRight().setParent(y);
			}
			x.setParent(y.getParent());
			if (y.getParent()==T.nil)
			{
				T.root = x;
				root.setParent(T.nil);
			}
			else if (y == y.getParent().getRight())
			{
				y.getParent().setRight(x);
			}
			else 
			{
				y.getParent().setLeft(x);
			}
			x.setRight(y);
			y.setParent(x);	
		}
	
	//searches and returns the node containing the Rank object with input pageRank
	//@param x a Node in a RBT to start searching
	//@param pageRank the input pageRank 
	//@return the node containing the Rank object with input pageRank
	public Node treeSearch(Node x, String pageRank)
	{
		int i =Integer.parseInt(pageRank);
		while (x!=nil && i!=x.getKey().getPageRank())
		{
			if (i < x.getKey().getPageRank())
			{
				x = x.getRight();
			}
			else
			{
				x = x.getLeft();
			}
		}
		return x;
	}
	
	//printing sorted RBT, assign pageRank during in-order-tree walk (Helper method)
	//@param x the node to start treeWalk
	//@return the ArrayList containing Rank objects from big to small
	public void treeWalk( Node x)
	{
		if (x!=nil)
		{	
			treeWalk(x.getRight());
			x.getKey().setPageRank(counter);
			counter++;
			a.add(x);
			treeWalk(x.getLeft());
		}
	}

	//the complete method for printing sorted RBT, 
	//because the array containing the sorted elements need to be clear every time before tree walk
	public void completeTreeWalk()
	{
		a.clear();
		counter = 1;
		treeWalk(root);
	}
	
	//gets the sorted RBT nodes in ArrayList form
	//@return the sorted RBT nodes in ArrayList form
	public ArrayList<Node> getNodesArray()
	{
		return a;
	}
	
	//helper method for assigning an index to a newly inserted node with new key from the user
	//@return the index of the newly inserted node's index
	public int getCounter()
	{
		return counter-1;
	}
	
	//delete a Node from the RBT
	//@param T the RBT where the deletion takes place
	//@param z the Node in the RBT to be deleted
	public void RBTDelete(RBTree T, Node z)
	{
		
		Node y = z;
		Node x;
		String yOriColor = y.getColor();
		if (z.getLeft()==T.nil)
		{
			
			
			x = z.getRight();
			RBTransplant(T,z,z.getRight());
		}
		else if (z.getRight()==T.nil)
		{
			
			x = z.getLeft();
			RBTransplant(T,z,z.getLeft());
		}
		else
		{
			
			y = treeMin(T,z.getRight());
			yOriColor = y.getColor();
			x = y.getRight();
			if (y.getParent()==z)
			{
				x.setParent(y);
			}
			else
			{
				RBTransplant(T,y,y.getRight());
				y.setRight(z.getRight());
				y.getRight().setParent(y);
			}
			RBTransplant(T,z,y);
			y.setLeft(z.getLeft());
			y.getLeft().setParent(y);	
			y.setColor(z.getColor());
		}
		if (yOriColor.equals("black"))
		{
			RBDeleteFixup(T,x);
		}
	}
	
	//fixes up RBT property violations caused by RBTDelete
	//@param T the RB-tree to be operated on
	//@param z the node where RBDeleteFixup starts taking place
	public void RBDeleteFixup(RBTree T, Node x)
	{
		while(x!=T.root && x.getColor().equals("black"))
		{
			if (x ==x.getParent().getLeft())
			{
				
				Node w = x.getParent().getRight();
				if (w.getColor().equals("red"))
				{
					
					w.setColor("black");
					x.getParent().setColor("red");
					leftRotate(T,x.getParent());
					w = x.getParent().getRight();
				}
				if (w.getLeft().getColor().equals("black") && w.getRight().getColor().equals("black"))
				{
					w.setColor("red");
					x = x.getParent();
				}
				else 
				{
					if (w.getRight().getColor().equals("black"))
					{
						
						w.getLeft().setColor("black");
						w.setColor("red");
						rightRotate(T,w);
						w = x.getParent().getRight();
					}
					
					w.setColor(x.getParent().getColor());
					x.getParent().setColor("black");
					w.getRight().setColor("black");
					leftRotate(T,x.getParent());
					x = T.getRoot();
				}
			}
			else
			{
				
				Node w = x.getParent().getLeft();
				System.out.println("Node w: " + w.getColor());
				System.out.println("Node w's right: " + w.getRight().getColor());
				System.out.println("Node w'sleft: " + w.getLeft().getColor());
				if (w.getColor().equals("red"))
				{
					
					w.setColor("black");
					x.getParent().setColor("red");
					rightRotate(T,x.getParent());
					w = x.getParent().getLeft();
				}
				if (w.getRight().getColor().equals("black") && w.getLeft().getColor() .equals("black"))
				{
					
					w.setColor("red");
					x = x.getParent();
				}
				else 
				{
					if (w.getLeft().getColor().equals("black"))
					{
						
						w.getRight().setColor("black");
						w.setColor("red");
						leftRotate(T,w);
						w = x.getParent().getLeft();
					}
					
					w.setColor(x.getParent().getColor());
					x.getParent().setColor("black");
					w.getLeft().setColor("black");
					rightRotate(T,x.getParent());
					x = T.getRoot();
				}
			}
		}
		
		x.setColor("black");
	}	
	
	//a helper method of RBTDelete, transplanting a branch of RBT with specified root to another specified root
	//@param T the RBT where transplants takes place
	//@param u the Node to be replaced
	//@param v the Node and its children to be transplanted to Node u
	public void RBTransplant(RBTree T, Node u, Node v)
	{
		if (u.getParent()==T.nil)
		{
			T.root = v;
			v.setParent(T.nil);
		}
		else if (u == u.getParent().getLeft())
		{
			u.getParent().setLeft(v);
		}
		else 
		{
			u.getParent().setRight(v);
		}
		v.setParent(u.getParent());
	}
	
	//gets the minimum of the RBT with input root
	//@param x the input root
	//@return the minimum of the RBT with input root
	public Node treeMin(RBTree T, Node x)
	{
		while (x.getLeft()!= T.nil)
		{
			x = x.getLeft();
		}
		return x;
	}
	
	//swaps the position of two objects in a Rank array
	//@param a the Rank array
	//@param x the position of one object
	//@param y the position of the other object
	//@return the new Array with swapped objects
	public Rank[] swap(Rank[] a, int x, int y)
	{
		Rank temp = a[x];
		a[x] = a[y];
		a[y] =temp;
		return a;
	}
}



