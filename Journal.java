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
public class Journal extends Reference{
    private String organization;
    
    public Journal()
    {
    
    }
    public Journal(String theTitle, String theCallNumber, int theYear, String theOrganization)
    {
        super(theTitle, theCallNumber, theYear);
        organization = theOrganization;
    }
    
    public void setOrganization (String anOrganization)
    {
        organization = anOrganization;
    }
    
    public String getOrganization ()
    {
        return organization;
    }
    
    public String toString()
    {
        String toDisplay = callNumber +"\n";
        
        if (organization.equals(""))
        {
            toDisplay += title + "\nn/a\n";
        }
        else 
        {
            toDisplay += title + "\n" + organization + "\n";
        }
        toDisplay += year + "\n";
        
        return toDisplay;
    }
    
}
