/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;


public class Assignment3 extends JFrame
{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    

    private JPanel welcomePanel;
    private JPanel addPanel;
    private JPanel searchPanel;
    private JPanel quitPanel;
    private JPanel addM = new JPanel();
    private JPanel typePanel = new JPanel();
    private JPanel callNumPanel = new JPanel();
    private JPanel authorsPanel = new JPanel();
    private JPanel titlePanel = new JPanel();
    private JPanel publisherPanel = new JPanel();
    private JPanel yearPanel = new JPanel();
    private JPanel organizationPanel = new JPanel();
    private JPanel MessagesPanel = new JPanel();
    private JPanel searchResultsPanel = new JPanel();
    private JPanel searchM = new JPanel();
    private JPanel buttonsPanel = new JPanel();
    private JPanel searchCallNumPanel = new JPanel();
    private JPanel searchTitlePanel = new JPanel();
    private JPanel searchStartYearPanel = new JPanel();
    private JPanel searchEndYearPanel = new JPanel();
    private JPanel searchButtonPanel = new JPanel();
    private JLabel welcomeMessage = new JLabel("<html><br><br><br><br>Welcome to Library Search!<br><br>"
                + "Choose a command from \"Commands\" menu above for adding<br>"
                + "a reference, searching references, or quitting the<br>"
                + "program.</html>");
    private JLabel addMessage = new JLabel("Adding a reference");
    private JLabel searchMeassage = new JLabel("Searching References");
    private JLabel messagesLabel = new JLabel("Messages");
    private JLabel typeLabel = new JLabel("Type:");
    private JLabel callNumLabel = new JLabel("Call No:");
    private JLabel authorLabel = new JLabel("Authors:");
    private JLabel titleLabel = new JLabel("Title:");
    private JLabel publisherLabel = new JLabel("Publisher:");
    private JLabel yearLabel = new JLabel("Year:");
    private JLabel organizationLabel = new JLabel("Organization:");
    private JLabel searchResultsLabel = new JLabel("Search Results");
    private JLabel startYearLabel = new JLabel("Start Year:");
    private JLabel endYearLabel = new JLabel("End Year:");
    private JLabel searchCallNumLabel = new JLabel("Call No:");
    private JLabel searchTitleLabel = new JLabel("Title Keyword:");
    private JTextArea messagesTA = new JTextArea(6, 25);
    private JTextArea searchTA = new JTextArea(6, 25);
    private JTextField callNumTF = new JTextField();
    private JTextField authorsTF = new JTextField();
    private JTextField titleTF = new JTextField();
    private JTextField publisherTF = new JTextField();
    private JTextField yearTF = new JTextField();
    private JTextField searchCallNumTF = new JTextField();
    private JTextField searchTitleTF = new JTextField();
    private JTextField searchstartYearTF = new JTextField();
    private JTextField searchendYearTF = new JTextField();
    private JTextField organizationTF = new JTextField();
    private JComboBox typeCB;
    private JButton resetButton = new JButton("Reset");
    private JButton addButton = new JButton("Add");
    private JButton searchResetButton = new JButton("Reset");
    private JButton searchButton = new JButton("Search");
    private String[] types = {"Book", "Journal"};
    private JPanel A = new JPanel();
    private JPanel S = new JPanel();
    
    static LibrarySearch myLib= new LibrarySearch();
    static HashMap hm = new HashMap(); 
    static int refNum;
    static String file;
    
    /*When the user chooses the add command*/
    private class AddListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            searchCallNumTF.setText(""); 
            searchTitleTF.setText(""); 
            searchstartYearTF.setText(""); 
            searchendYearTF.setText("");
            searchTA.setText(""); 
            addPanel.setVisible(true);
            A.setVisible(true);
            searchPanel.setVisible(false);
            welcomePanel.setVisible(false);
            S.setVisible(false);
            add(A);
        }
    } 

    /*when the user chooses the search command*/
    private class SearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            callNumTF.setText(""); 
            authorsTF.setText(""); 
            titleTF.setText(""); 
            publisherTF.setText(""); 
            yearTF.setText(""); 
            organizationTF.setText("");
            messagesTA.setText("");
            searchPanel.setVisible(true);
            S.setVisible(true); 
            addPanel.setVisible(false);
            welcomePanel.setVisible(false);
            A.setVisible(false);
            searchTA.setText("Guidelines:\n"
                        + "1. To see all the references in the library,\n"
                        + "simply hit search without filling in any of the fields.\n"
                        + "2. The years must be between 1000 and 2015\n"
                        + "3. If you want to search with a single year,\n"
                        + "enter the same year in both the start year and end year fields.\n"
                        + "4. If you wish to search from a certain year until now,\n"
                        + "leave the end year field blank\n"
                        + "5. If you wish to search from 1000 until a certain year,\n"
                        + "leave the start year field blank.\n");
            add(S); 
        }
    } 

    /*when user chooses the quit command*/
    private class QuitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    } 

    public static void main(String[] args)
    {
        file = args[0];
        hm = myLib.loadReferences(file, hm);
        refNum = myLib.listSize();
        Assignment3 gui = new Assignment3( );
        gui.setVisible(true);
    }

    public Assignment3( )
    {
        super("Library Search");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        /**************************ADD**************************/
        addPanel = new JPanel( );
        addPanel.setVisible(false);
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        //addPanel.setLayout(new BorderLayout());
        
        addM.setLayout(new BorderLayout());
        addM.add(addMessage, BorderLayout.PAGE_START);
        addPanel.add(addM);
        
        typePanel.setLayout(new BorderLayout(100,1));
        typePanel.add(typeLabel, BorderLayout.WEST);
        typeCB = new JComboBox(types);
        /*combo box for a choice of adding book or journal*/
        typeCB.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                if (e.getItem().toString().equals("Journal"))
                {
                    organizationPanel.setVisible(true);
                    publisherPanel.setVisible(false);
                    authorsPanel.setVisible(false); 
                } 
                else
                {
                    organizationPanel.setVisible(false);
                    authorsPanel.setVisible(true); 
                    publisherPanel.setVisible(true);
                }
                callNumTF.setText(""); 
                authorsTF.setText(""); 
                titleTF.setText(""); 
                publisherTF.setText(""); 
                yearTF.setText(""); 
                organizationTF.setText("");
            }
        }); 
        typePanel.add(typeCB, BorderLayout.CENTER);
        addPanel.add(typePanel);
        
        callNumPanel.setLayout(new BorderLayout());
        callNumPanel.add(callNumLabel, BorderLayout.WEST);
        callNumPanel.add(callNumTF, BorderLayout.CENTER);
        addPanel.add(callNumPanel);
        
        authorsPanel.setLayout(new BorderLayout());
        authorsPanel.add(authorLabel, BorderLayout.WEST);
        authorsPanel.add(authorsTF, BorderLayout.CENTER);
        addPanel.add(authorsPanel);
        
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(titleTF, BorderLayout.CENTER);
        addPanel.add(titlePanel);
        
        publisherPanel.setLayout(new BorderLayout());
        publisherPanel.add(publisherLabel, BorderLayout.WEST);
        publisherPanel.add(publisherTF, BorderLayout.CENTER);
        addPanel.add(publisherPanel);
        
        organizationPanel.setLayout(new BorderLayout());
        organizationPanel.add(organizationLabel, BorderLayout.WEST);
        organizationPanel.add(organizationTF, BorderLayout.CENTER);
        organizationPanel.setVisible(false); 
        addPanel.add(organizationPanel); 
        
        yearPanel.setLayout(new BorderLayout(100, 1));
        yearPanel.add(yearLabel, BorderLayout.WEST);
        yearPanel.add(yearTF, BorderLayout.CENTER);
        addPanel.add(yearPanel);
        
        
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        /*when user wishes to reset all the text fields*/
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                callNumTF.setText(""); 
                authorsTF.setText(""); 
                titleTF.setText(""); 
                publisherTF.setText(""); 
                yearTF.setText(""); 
                organizationTF.setText("");             
            }          
        });
        
        /*when user wishes to add a reference*/
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                String theCallN = "";
                String theAuthors = "";
                String theTitle = "";
                String thePub = "";
                String theOrg = "";
                String aYear = "";
                int theYear = 0;
                boolean f = true;
                
                theCallN = callNumTF.getText();
                
                if (theCallN.length() < 1)
                {
                    messagesTA.append("Invalid entry for call number.\n"); 
                    f = false;
                }
                
                if (typeCB.getSelectedItem().toString().equals("Book"))
                {
                    theAuthors = authorsTF.getText();
                    thePub = publisherTF.getText();
                }
                
                if (typeCB.getSelectedItem().toString().equals("Journal"))
                {
                    theOrg = organizationTF.getText();
                }
                
                theTitle = titleTF.getText();
                
                if(theTitle.length() < 1)
                {
                    messagesTA.append("Invalid enrty for title.\n");
                    f = false;
                }
                
                aYear = yearTF.getText();
                
                if (aYear.length() < 1 )
                {
                    messagesTA.append("Invalid entry for year.\n");
                    f = false;
                }
                else
                {
                    try
                    {
                        theYear = Integer.parseInt(aYear);
                        if (theYear < 1000 || theYear > 2016)
                        {
                            messagesTA.append("Invalid entry for year.\n");
                            f = false;
                        }
                    }
                    catch(Exception e)
                    {
                        messagesTA.append("Invalid entry for year.\n");
                        f = false;
                        System.err.println("Caught IOException" + e.getMessage());
                    }
                }
                
                if (f)
                {
                    if (typeCB.getSelectedItem().toString().equals("Book"))
                    {
                        Book newBook = new Book(theTitle, theCallN, theYear, thePub, theAuthors);
                        if (myLib.checkDuplicate(newBook))
                        {
                            messagesTA.append("You are trying to add a reference that already exists "
                                    + "\n(has the same call number and/or year as another item).\n"
                                    + "Action terminated.\n");
                        }
                        else
                        {
                            myLib.addReference(newBook);
                            myLib.catalogItem(file, "book", newBook);
                            messagesTA.append("Reference successfully added!\n");
                            hm = myLib.addToMap(hm, theTitle, refNum);
                            refNum++;
                        }
                    }
                    else if (typeCB.getSelectedItem().toString().equals("Journal"))
                    {
                        Journal newJournal = new Journal(theTitle, theCallN, theYear, theOrg);
                        if (myLib.checkDuplicate(newJournal))
                        {
                            messagesTA.append("You are trying to add a reference that already exists "
                                    + "\n(has the same call number and/or year as another item).\n"
                                    + "Action terminated.\n");
                        }
                        else
                        {
                            myLib.addReference(newJournal);
                            myLib.catalogItem(file, "journal", newJournal);
                            messagesTA.append("Reference successfully added!\n");
                            hm = myLib.addToMap(hm, theTitle, refNum);
                            refNum++;
                        }
                    }
                    
                    
                }
                callNumTF.setText(""); 
                authorsTF.setText(""); 
                titleTF.setText(""); 
                publisherTF.setText(""); 
                yearTF.setText(""); 
                organizationTF.setText("");
                /*TESTING*/
                //System.out.println();
                //System.out.println(myLib.printAll());
                //System.out.println(hm);
                messagesTA.append("\n");
            }          
        });
        buttonsPanel.add(resetButton);
        buttonsPanel.add(addButton); 
        A.setLayout(new BorderLayout());
        A.add(addPanel, BorderLayout.WEST);
        A.add(buttonsPanel, BorderLayout.EAST);
        
        MessagesPanel.setLayout(new BorderLayout());
        MessagesPanel.add(messagesLabel, BorderLayout.PAGE_START);
        messagesTA.setEditable(false); 
        JScrollPane sp = new JScrollPane(messagesTA);
        sp.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
        MessagesPanel.add(sp, BorderLayout.CENTER);
        A.add(MessagesPanel, BorderLayout.SOUTH);
        
        /**************************SEARCH**************************/
        searchPanel = new JPanel( );
        searchPanel.setVisible(false);
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        
        searchM.setLayout(new BorderLayout());
        searchM.add(searchMeassage, BorderLayout.PAGE_START);
        searchPanel.add(searchM);
        
        searchCallNumPanel.setLayout(new BorderLayout());
        searchCallNumPanel.add(searchCallNumLabel, BorderLayout.WEST);
        searchCallNumPanel.add(searchCallNumTF, BorderLayout.CENTER);
        searchPanel.add(searchCallNumPanel);
        
        searchTitlePanel.setLayout(new BorderLayout());
        searchTitlePanel.add(searchTitleLabel, BorderLayout.WEST);
        searchTitlePanel.add(searchTitleTF, BorderLayout.CENTER);
        searchPanel.add(searchTitlePanel);
        
        searchStartYearPanel.setLayout(new BorderLayout());
        searchStartYearPanel.add(startYearLabel, BorderLayout.WEST);
        searchStartYearPanel.add(searchstartYearTF, BorderLayout.CENTER);
        searchPanel.add(searchStartYearPanel);
        
        searchEndYearPanel.setLayout(new BorderLayout());
        searchEndYearPanel.add(endYearLabel, BorderLayout.WEST);
        searchEndYearPanel.add(searchendYearTF, BorderLayout.CENTER);
        searchPanel.add(searchEndYearPanel);
        
        searchButtonPanel.setLayout(new BoxLayout(searchButtonPanel, BoxLayout.Y_AXIS));
        /*when user wisher to reset all of the text fields*/
        searchResetButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent evt)
            {
                searchCallNumTF.setText(""); 
                searchTitleTF.setText(""); 
                searchstartYearTF.setText(""); 
                searchendYearTF.setText("");
            }          
        });
        /*when user wishes to search the library*/
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                searchTA.setText(""); 
                ArrayList<Reference> found = new ArrayList<>();
                String callN = "";
                String keyword = "";
                int startYear = 0;
                int endYear = 0;
                boolean valid = true;
                
                /*read in entry from text fields*/
                callN = searchCallNumTF.getText();
                keyword = searchTitleTF.getText();
                
                /*error check for year entry, making sure it is an integer and between 1000 and 2016*/
                /*start year*/
                try
                {
                    startYear = Integer.parseInt(searchstartYearTF.getText());
                    if (startYear < 1000 || startYear > 2016)
                        {
                            searchTA.append("Invalid entry for start year.\n"
                                    + "Enter a year between 1000 and 2015.\n"
                                    + "Leave field blank if not applicable");
                            valid = false;
                        }
                }
                catch(Exception e)
                {
                    if (searchstartYearTF.getText().length() < 1)
                    {
                        startYear = 0;
                    }
                    else
                    {
                        searchTA.append("Invalid entry for start year.\n");
                        valid = false;
                        System.err.println("Caught IOException" + e.getMessage());
                    }
                }
                /*end year*/
                try
                {
                    endYear = Integer.parseInt(searchendYearTF.getText());
                    if (endYear < 1000 || endYear > 2016)
                        {
                            searchTA.append("Invalid entry for end year.\n"
                                    + "Enter a year between 1000 and 2015.\n"
                                    + "Leave field blank if not applicable");
                            valid = false;
                        }
                }
                catch(Exception e)
                {
                    if (searchendYearTF.getText().length() < 1)
                    {
                        if (startYear > 0)
                        {
                            endYear = 2015;
                        }
                        else
                        {
                            endYear = 0;
                        }
                    }
                    else
                    {
                        searchTA.append("Invalid entry for end year.\n");
                        valid = false;
                        System.err.println("Caught IOException" + e.getMessage());
                    }
                }
                
                if (startYear == 0 && endYear > 0)
                {
                    startYear = 1000;
                }
                /*if user entered appropriate input*/
                if (valid)
                {
                    //if user didnt provide any info
                    if (callN.equals("") && keyword.equals("") && startYear == 0 && endYear == 0)
                    {
                        searchTA.append(myLib.printAll());
                    }
                    else
                    {
                        found = myLib.searchLib(keyword, startYear, endYear, callN, "", hm);
                        if (found != null)
                        {
                            searchTA.append(myLib.printSome(found));
                        }
                        else
                        {
                            searchTA.append("No results found.\n");
                        }
                    }
                }
                searchCallNumTF.setText(""); 
                searchTitleTF.setText(""); 
                searchstartYearTF.setText(""); 
                searchendYearTF.setText("");
            }          
        });
        searchButtonPanel.add(searchResetButton);
        searchButtonPanel.add(searchButton);
        S.setLayout(new BorderLayout());
        S.add(searchPanel, BorderLayout.CENTER);
        S.add(searchButtonPanel, BorderLayout.EAST);
        
        searchResultsPanel.setLayout(new BorderLayout());
        searchResultsPanel.add(searchResultsLabel, BorderLayout.PAGE_START);
        searchTA.setEditable(false); 
        JScrollPane SP = new JScrollPane(searchTA);
        SP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        SP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
        searchResultsPanel.add(SP, BorderLayout.CENTER);
        S.add(searchResultsPanel, BorderLayout.SOUTH);
        
        
        /**************************WELCOME**************************/
        welcomePanel = new JPanel( );
        welcomePanel.setVisible(true);
        welcomePanel.add(welcomeMessage);
        add(welcomePanel);
        
        
        /**************************QUIT**************************/
        quitPanel = new JPanel( );

        /**************************MENU**************************/
        JMenu commandMenu = new JMenu("Commands");

        JMenuItem addChoice = new JMenuItem("Add");
        addChoice.addActionListener(new AddListener( ));
        commandMenu.add(addChoice);

        JMenuItem searchChoice = new JMenuItem("Search");
        searchChoice.addActionListener(new SearchListener( ));
        commandMenu.add(searchChoice);

        JMenuItem ExitChoice = new JMenuItem("Quit");
        ExitChoice.addActionListener(new QuitListener( ));
        commandMenu.add(ExitChoice);

        JMenuBar bar = new JMenuBar( );
        bar.add(commandMenu);
        setJMenuBar(bar);
    }

}



