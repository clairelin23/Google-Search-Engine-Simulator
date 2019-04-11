package sorting;

import java.io.IOException;
import java.util.ArrayList;

//storing each search keyword with its URLs retrieved from webCrawler, creating a Keyword object
public class Keyword implements Comparable<Keyword>
{
	private int count =1;
	private String word;
	private ArrayList<String> urlList;
	
	//constructor when the input work and its URLs are known
	//@param word the search word
	//@param urlList the list of URLs for the search word
	public Keyword(String word, ArrayList<String> urlList)
	{
		this.word =word;
		this.urlList = urlList;
	}
	
	//default constructor 
	public Keyword()
	{}
	
	//given a Keyword and a list of Keyword, return the index at which the Keyword is in the list
	//@param a the list to find the Keyword index
	//@returns the index of the Keyword in the list
	public int findKeywordIndex(ArrayList<Keyword> a) throws IOException
	{
		for(int i=0;i<a.size();i++)
	   	{
	   		if (this.equals(a.get(i)))
	   		{	
	   			return i;
			}
	   	} 
		throw new IOException("index not found");	
	}
	//sets URL list 
	//@param a the new list of URL
	public void setURL(ArrayList<String> a)
	{
		urlList = a;
	}
	
	//sets word
	//@param a the new search word
	public void setWord(String a)
	{
		word = a;
	}
	
	//increase count as the search word appears again to call web crawler
	//return the increased count
	public Keyword increaseCount()
	{
		 count++;
		 return this;
	}
	
	//gets count
	//return count
	public int getCount()
	{
		return count;
	}
	
	//gets word
	//return the word of Keyword object
	public String getWord()
	{
		return word;
	}
	
	//gets url
	//@returns the list of URLs
	public ArrayList<String> getUrl()
	{
		return urlList;
	}
	

	@Override 
	//for comparing two Keyword objects
	//@param other another Keyword object to be compared with
	//@return a value results from comparing
	public int compareTo(Keyword other) 
	{
		
		return this.count - other.count;
	}
	
	@Override
	//for checking if two Keyword objects are equal
	//@param o another Keyword objects to be compared with
	//@return true if two Keyword are equal, false otherwise
	public boolean equals(Object o)
	{
		Keyword that = (Keyword) o;
		if (this.word.equals(that.word))
		{
			return true;
		}
		return false;
	}
}
