/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author tamaracharch
 */
public class Reference {
    protected String callNumber;
    protected String title;
    protected int year;
    
    public Reference()
    {
        
    }
    
    public Reference(String theTitle, String theCallNumber, int theYear)
    {
        title = theTitle;
        callNumber = theCallNumber;
        year = theYear;
    }
    
    public void setTitle(String newTitle)
    {
        title = newTitle;
    }
    
    public void setCallNumber(String newCallNumber)
    {
        callNumber = newCallNumber;
    }
    
    public void setYear(int newYear)
    {
        year = newYear;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getCallNumber()
    {
        return callNumber;
    }
    
    public int getYear()
    {
        return year;
    }
    
    public boolean equals(Reference other)
    {
        if (other == null)
        {
            return false;
        }
        else
        {
            return (callNumber.equals(other.getCallNumber()) && year == other.getYear());
        }
    }
    
    
}
