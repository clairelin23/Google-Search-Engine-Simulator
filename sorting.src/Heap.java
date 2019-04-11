package sorting;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//a class that stores methods for manipulating a heap tree
public class Heap
{	

	//calculates the index of a parent node
	//@param i the index of the node
	//@return the index of the parent node
	public int parent(int i)
	{
		return i/2;
	}
	
	//calculates the index of a left child node
	//@param i the index of the node
	//@return the index of the left child node
	public int left(int i)
	{
		if (i==0) {return 1;};
		return 2*i;
	}
	
	//calculate the index of a left child node
	//@param i the index of the node
	//@return the index of the left child node
	public int right(int i)
	{
		if (i==0) {return 2;};
		return 2*i+1;
	}
	
	//maintains max-heap property by moving down the indicated node in heap tree
	//@param a the Rank array where a node is indicated
	//@param i the index of the indicated node
	public void maxHeapify(ArrayList<Keyword> a, int i)
	{
		int largest;
		int l = left(i);
		int r = right(i);
		if(l<=a.size()-1 && a.get(l).compareTo(a.get(i))>0)
		{	largest =l;}
		else 
		{	largest =i;}
		
		if (r<=a.size()-1 && a.get(r).compareTo(a.get(largest))>0)
		{	largest =r;}
		
		if(!(largest ==i))
		{
			swap(a,i,largest);
			maxHeapify(a,largest);	
		}			
	}
	
	//builds a max-heap tree
	//@param a the Rank array to be constructed as max-heap
	public void buildMaxHeap(ArrayList<Keyword> a)
	{
		for (int i=(a.size()-1)/2 ;i>=0;i--)
		{
			maxHeapify(a,i);
		}
	}
	
	//sorts an array from nodes small to big
	//@param a the Rank array to be sorted
	public void heapSort(ArrayList<Keyword> a)
	{
		buildMaxHeap(a);
		for (int i=a.size()-1;i>0;i--)
		{
			swap(a,0,i);
			maxHeapify(a,0);
		}	
	}
	
	//gives the first node of a max-heap-tree
	//@param a the Rank array that is a max-heap-tree
	//@return the first node of the input array
	public Keyword heapMaximum(ArrayList<Keyword> a)
	{
		return a.get(0);
	}

	//increases the key of a Keyword object, thereby changing the heap-tree structure order
	//@param a the Rank array 
	//@param i the index of the object to be replaced
	//@param key the new Rank object
	public void heapIncreaseKey(ArrayList<Keyword> a, int i, Keyword key) throws IOException
	{
		if(key.compareTo(a.get(i))<0)
		{
			throw new IOException("new key is smaller than current key");
		}
		a.set(i,key);
		while(i>0 && a.get(parent(i)).compareTo(a.get(i))<0)
		{
			swap(a,i,parent(i));
			i = parent(i);
		}
	}
	 
	//inserts a new node to a max-heap-tree, maintaining max-heap property
	//@param a the Rank array where a new node is inserted
	//@param key the Rank object to be inserted
	public void maxHeapInsert(ArrayList<Keyword> a, Keyword key) throws IOException
	{
		Keyword temp = new Keyword("temp",null);
		a.add(temp);
		heapIncreaseKey(a,a.indexOf(temp),key);
	}
		
	 //removes and returns the max value node from a max-heap tree
     //@param a the Rank array from which to be extracted 
	//@return the max value node
	public Keyword extractMax(ArrayList<Keyword> a) throws IOException
	{
		if (a.size()<0)
		{
			throw new IOException("heap underflow");
		}		
		Keyword max = a.get(0);
	    a.set(0, a.get(a.size()-1));
		a.remove(a.size()-1);
		maxHeapify(a,0);	
		return max;
	}
		
		
	//swaps the position of two objects in a Rank array
	//@param a the Rank array
	//@param x the position of one object
	//@param y the position of the other object
	//@return the new Array with swapped objects
	public ArrayList<Keyword> swap(ArrayList<Keyword> a, int x, int y)
	{
		Keyword temp = a.get(x);
		a.set(x, a.get(y));
		a.set(y, temp);
		return a;
	}
}
