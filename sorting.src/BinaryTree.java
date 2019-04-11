package sorting;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//a class that stores method for manipulating a BST
public class BinaryTree
{	
	private Node root;
	private ArrayList<Rank> a  = new ArrayList<Rank>();
	private int counter =1;
	
	//default constructor of BST
	public BinaryTree()
	{
	}
	
	//construct of BST when the root is known
	//@param root the Node to be root
	public BinaryTree(Node root)
	{
		this.root = root;
	}
	
	//sets the root of this BST
	//@param Node the new root
	public void setRoot(Node r)
	{
		this.root = r;
	}
	
	//gets the root of this BST
	//@return the root of this BST
	public Node getRoot()
	{
		return root;
	}
	
	//inserts an element to a BST
	//@param T the BST where a Node is going to be inserted
	//@param z the Node to be inserted
	public void treeInsert(BinaryTree T, Node z)
	{
		Node y = null;
		Node x = T.root;
		while (x != null)
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
		if (y==null)//tree T was empty
		{
			T.root = z;		
		}
		else if(z.getKey().compareTo(y.getKey())<0)
		{
			y.setLeft(z);
		}
		else
		{
			y.setRight(z);
		}
	}
	
	//searches and returns the node containing the Rank object with input pageRank
	//@param x a Node in a BST to start searching
	//@param pageRank the input pageRank 
	//@return the node containing the Rank object with input pageRank
	public Node treeSearch(Node x, String pageRank)
	{
		int i =Integer.parseInt(pageRank);
		while (x!=null && i!=x.getKey().getPageRank())
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
	
	
	//printing sorted BST, assign pageRank during in-order-tree walk (Helper method)
	//@param x the node to start treeWalk
	//@return the ArrayList containing Rank objects from big to small
	public void treeWalk(Node x)
	{
		if (x!=null)
		{	
			treeWalk(x.getRight());
			x.getKey().setPageRank(counter);
			counter++;
			a.add(x.getKey());
			treeWalk(x.getLeft());
		}
	}
	
	//the complete method for printing sorted BST, 
	//because the array containing the sorted elements need to be clear every time before tree walk
	public void completeTreeWalk()
	{
		a.clear();
		counter = 1;
		treeWalk(root);
	}
	
	//gets the sorted BST keys in ArrayList form
	//@return the sorted BST keys in ArrayList form
	public ArrayList<Rank> getNodeKeysArray()
	{
		return a;
	}
	
	//helper method for assigning an index to a newly inserted node with new key from the user
	//@return the index of the newly inserted node's index
	public int getCounter()
	{
		return counter-1;
	}
	
	//delete a Node from the BST
	//@param T the BST where the deletion takes place
	//@param z the Node in the BST to be deleted
	public void treeDelete(BinaryTree T, Node z)
	{
		Node y = null;
		if (z.getLeft()==null)
		{
			transplant(T,z,z.getRight());
		}
		else if (z.getRight()==null)
		{
			transplant(T,z,z.getLeft());
		}
		else
		{
			y = treeMin(z.getRight());
			if (y.getParent()!=z)
			{
				transplant(T,y,y.getRight());
				y.setRight(z.getRight());
				y.getRight().setParent(y);
			}
			transplant(T,z,y);
			y.setLeft(z.getLeft());
			y.getLeft().setParent(y);	
		}
	}
	
	//a helper method of treeDelete, transplanting a branch of BST with specified root to another specified root
	//@param T the BST where transplants takes place
	//@param u the Node to be replaced
	//@param v the Node and its children to be transplanted to Node u
	public void transplant(BinaryTree T, Node u, Node v)
	{
		if (u.getParent()==null)
			T.root = v;
		else if (u == u.getParent().getLeft())
			u.getParent().setLeft(v);
		else 
			u.getParent().setRight(v);
		if (v!= null)
			v.setParent(u.getParent());
	}
	
	//gets the minimum of the BST with input root
	//@param x the input root
	//@return the minimum of the BST with input root
	public Node treeMin(Node x)
	{
		while (x.getLeft()!= null)
			x = x.getLeft();
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
