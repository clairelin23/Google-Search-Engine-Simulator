package sorting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

//a class that stores methods for running BucketSort
public class Bucket 
{
	//sorted the URL list of the input Keyword object in alphabetical order
	//@param a the input Keyword object
	public LinkedList<String> bucketSort(Keyword a)
	{
		int n = a.getUrl().size();
		ArrayList<LinkedList<String>> b = new ArrayList<LinkedList<String>>();
		LinkedList<String> sorted = new LinkedList<String>();
	
		for (int i=0; i<26;i++)
		{
			b.add(i, new LinkedList<String>());
		}
		for (int i=0; i< n; i++)
		{
			char ch = a.getUrl().get(i).charAt(8);
		     int pos = ch - 'a' ;
		     b.get(pos).add(a.getUrl().get(i));
		}
		for(int i=0;i<26;i++)
		{
			insertionSort(b.get(i));
		}
		for (int i=0; i<26;i++)
		{
		   sorted.addAll(b.get(i));		
		}
		return sorted;	
	}
		
	//sorts a list of Strings, helper method of bucketSort
	//@param a the list to be sorted
	public void insertionSort(LinkedList<String> a)
	{
			String key; 					
			int i; 						
			for (int j=1; j<a.size(); j++)
			{
				key = a.get(j); 				
				//insert a{i} into the sorted sequence a{1..i-1}
				i = j-1;					
				while (i>=0 && a.get(i).compareTo(key)>0)
				{
					a.set(i+1,a.get(i)); 		 
					i = i-1; 			
				}
				a.set(i+1, key);		
			}											 
	}				
	
}
	