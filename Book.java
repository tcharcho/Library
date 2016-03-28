/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.ArrayList;

/**
 *
 * @author tamaracharch
 */
public class Book extends Reference{
    private String publisher;
    private String authors;
    
    public Book ()
    {
        
    }
    public Book(String theTitle, String theCallNumber, int theYear, String thePublisher, String theAuthors)
    {
        super(theTitle, theCallNumber, theYear);
        publisher = thePublisher;
        authors = theAuthors;
    }

    
    public void setPublisher (String aPublisher)
    {
        publisher = aPublisher;
    }
    
    public void setAuthors(String someAuthors)
    {
        authors = someAuthors;
    }
    
    public String getPublisher ()
    {
        return publisher;
    }
    
    public String getAuthors()
    {
        
        return authors;
    }
    
    public String toString()
    {
        String toDisplay = callNumber +"\n";
        if (authors.length() == 0 && publisher.equals(""))
        {
            toDisplay += "n/a\n" + title + "\nn/a\n";
        }
        else if (authors.length() == 0)
        {
            toDisplay += "n/a\n" + title + "\n" + publisher + "\n";
        }
        else if (publisher.equals(""))
        {
            toDisplay += getAuthors() + "\n" + title + "\nn/a\n";
        }
        else
        {
            toDisplay += getAuthors() + "\n" + title + "\n" + publisher + "\n";
        }
        
        toDisplay += year + "\n";
        
        
        return toDisplay;
    }
}
