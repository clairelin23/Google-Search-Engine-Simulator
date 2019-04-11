package sorting;
import java.io.IOException;
import java.util.ArrayList;

//a class for sorting a list of Rank Objects
public class Quicksort 
{
	//partition the list with a certain pivot element, helper method of quickSort
	//@param a the list to be partitioned
	//@param p the index of the first element in the list to be partitioned
	//@param the index of the last element in the list to be partitioned
	//@return the index of the pivot element
	public int partition(ArrayList<Rank> a, int p, int r)
	{
		Rank x = a.get(r);
		int i=p-1;
		for (int j =p; j<=r-1;j++)
		{
			if (a.get(j).compareTo(x)>0)
			{
				i++;
				swap(a,i,j);
			}
		}
		swap(a,i+1,r);
		return i+1;	
	}
	
	//sorted the input list from big to small order
	//@param a the list to be sorted
	//@param p the index of the first element in the list to be sorted
	//@param the index of the last element in the list to be sorted
	public void quickSort(ArrayList<Rank> a, int p, int r)
	{
		if(p<r)
		{
		    int q = partition(a,p,r);
			quickSort(a,p,q-1);
			quickSort(a,q+1,r);
		}
	}
	
	
	//swaps the position of two objects in a Rank array
	//@param a the Rank array
	//@param x the position of one object
	//@param y the position of the other object
	//@return the new Array with swapped objects
	public ArrayList<Rank> swap(ArrayList<Rank> a, int x, int y)
	{
		Rank temp = a.get(x);
		a.set(x, a.get(y));
		a.set(y, temp);
		return a;
	}
	
	//assign PageRank to each Rank element in the sorted Array
	//@param a the list to be assigned PageRank
	public void assignPageRank(ArrayList<Rank> a)
	{
		int counter =1;
		for (int i=0; i<a.size();i++)
		{
			a.get(i).setPageRank(counter);
			counter++;
		}
	}
	

		
	
}
