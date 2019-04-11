package sorting;

import java.util.Random;

//assign each URL with 4 PageRank factor scores and an index, creating a Rank object
public class Rank implements Comparable<Rank>
{
	private int keywordScore;
	private int historyScore;
	private int linkScore;
	private int paidScore;
	private int index;
	private int pageRank;
	private String url;
	private Random rand1 = new Random(); 
	private Random rand2 = new Random(); 
	private Random rand3 = new Random(); 
	private Random rand4 = new Random(); 
	
	@Override
	//defines rules for comparing 2 Rank objects
	//@param an input object to compare with
	//@return value of comparing the two Rank objects
	public int compareTo(Rank arg) 
	{
		return this.getTotalScore()-arg.getTotalScore();
	}
	
	//constructs a Rank object, initially assigning each object with random PageRank factor scores
	public Rank(String url,int index)
	{
		this.url = url;
		this.index = index;
		keywordScore = rand1.nextInt(100);
		historyScore = rand2.nextInt(100);
	    linkScore = rand3.nextInt(100);
	    paidScore = rand4.nextInt(100); 
	}
	
	//constructs a Rank object when the four factor scores and index are known
	public Rank(String url,String keywordScore,String historyScore, String linkScore, String paidScore, int index)
	{
		int k = Integer.parseInt(keywordScore);
		int h = Integer.parseInt(historyScore);
		int l = Integer.parseInt(linkScore);
		int p = Integer.parseInt(paidScore);
		this.url = url;
		this.keywordScore = k;
		this.historyScore = h;
		this.linkScore = l;
		this.paidScore = p;	
		this.index = index;
	}
	
	//set the pageRank
	//@param s the value to be set as the pageRank of a specific Rank object
	public void setPageRank(int s)
	{
		pageRank = s;
	}
	
	//sets keyword score 
	//@param a new keyword score 
	public void setKeywordScore(int s)
	{
		keywordScore = s;
	}
	
	//sets history score
	//@param a new history score
	public void setHistoryScore(int s)
	{
		historyScore = s;
	}
	
	//sets link score
	//@param a new link score
	public void setLinkScore(int s)
	{
		linkScore = s;
	}
	
	//sets paid score
	//a new paid score
	public void setPaidScore(int s)
	{
		paidScore = s;
	}
	
	//calculates the total score of a Rank object
	//@return sum of the 4 score factors
	public int getTotalScore()
	{
		return keywordScore + historyScore + linkScore + paidScore;
	}
	
	//gets index of a Rank object
	//@return index of a Rank object
	public int getIndex()
	{
		return index;
	}

	//gets url of a Rank object
	//@return url of a Rank object
	public String getUrl()
	{
		return url;
	}
	
	public int getPageRank()
	{
		return pageRank;
	}
	
}
