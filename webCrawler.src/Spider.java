package webCrawler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Spider 
{
	// Fields
    private static final int MAX_PAGES_TO_SEARCH = 100;
    private Set<String> pagesVisited = new HashSet<String>();
    private LinkedList<String> pagesToVisit = new LinkedList<String>();
    private ArrayList<String> usableURLs = new ArrayList<String>();
    private int keywordUrlCount =0;


    
    /**
     * Our main launching point for the Spider's functionality. 
     * Internally it creates spider legs
     * that make an HTTP request and parse the response (the web page).
     * 
     * @param url
     *            - The starting point of the spider
     * @param searchWord
     *            - The word or string that you are searching for
     */
    public void search(String url, String searchWord)
    {
    		
        while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH)
        {
            String currentUrl;
            SpiderLeg leg = new SpiderLeg();
            if(this.pagesToVisit.isEmpty())
            {
                currentUrl = url;
                this.pagesVisited.add(url);
            }
            else
            {
                currentUrl = this.nextUrl();
            }
            leg.crawl(currentUrl); // Lots of stuff happening here. Look at the crawl method in
                                   // SpiderLeg
            boolean success = leg.searchForWord(searchWord);
            if(success)
            {
                System.out.println(String.format("**Success** Word %s found" , searchWord));
                keywordUrlCount++;
                usableURLs.add(currentUrl);
                if (keywordUrlCount==30)
                {
                break;
                }
            }
            
            this.pagesToVisit.addAll(leg.getLinks());
        }
        System.out.println(String.format("**Done** Visited %s web page(s), %s web page(s) with keyword retreived", this.pagesVisited.size(),keywordUrlCount));
        System.out.println("");
    }
    
    /**
     * Returns the next URL to visit (in the order that they were found). We also do a check to make
     * sure this method doesn't return a URL that has already been visited.
     * 
     * @return
     */
    private String nextUrl()
    {
        String nextUrl;
        do
        {
            nextUrl = this.pagesToVisit.remove(0);
        } while(this.pagesVisited.contains(nextUrl));
         this.pagesVisited.add(nextUrl);
        return nextUrl;
    }
    public ArrayList<String> getUsableLinks()
    {
    		return usableURLs;
    }
}    
  
