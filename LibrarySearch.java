/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tamaracharch
 */
public class LibrarySearch {

    private ArrayList<Reference> myReferences = new ArrayList<>();

    public LibrarySearch() {

    }

    public void addReference(Reference newReference) {
        if (!checkDuplicate(newReference)) {
            myReferences.add(newReference);
        } else {
            System.out.println("You are trying to add a reference that already exists (has the same call number and/or year as another item).\n"
                    + "Action terminated.\n");
        }

    }

    public boolean checkDuplicate(Reference aReference) {
        int length = myReferences.size();
        int i;

        for (i = 0; i < length; i++) {
            if (myReferences.get(i).equals(aReference)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Reference> searchLib(String keyword, int startYear, int endYear, String aCallNumber, String type, HashMap myMap) {
        int length = myReferences.size();
        int y;
        boolean found = true;
        int theYear = 1;
        int yearRangeStart = startYear;
        int yearRangeEnd = endYear;
        keyword = keyword.toLowerCase();
        //StringTokenizer yearToke = new StringTokenizer(aYear, "-");
        String theTitle;
        ArrayList<Reference> theRefs = new ArrayList<>();
        ArrayList<Integer> v = null;
        ArrayList<Integer> c = new ArrayList<>();

        if (yearRangeStart == yearRangeEnd)
        {
            theYear = yearRangeStart;
        }


        //if the user entered a key phrase
        if (keyword.length() != 0) {
            //tokenize keyword
            StringTokenizer myToke = new StringTokenizer(keyword);
            String dummy;
            //for every token
            while (myToke.hasMoreTokens()) {
                v = new ArrayList<>();
                dummy = myToke.nextToken();
                //check if token exists in hash map
                if (myMap.containsKey(dummy)) {
                    v = (ArrayList<Integer>) myMap.get(dummy);
                    //if it's the first token and there is nothing in c
                    if (c.size() == 0) {
                        c = v;
                    } else {
                        //for every element in the value (a.k.a array list of ints that corresponds to records) 
                        for (int s = 0; s < v.size(); s++) {
                            //if that element does not already exist in c (the array list of ints that correspond to references)
                            if (!c.contains(v.get(s))) {
                                c.add(v.get(s));
                            }
                        }
                    }

                } else {
                    found = false;
                    break;
                }
            }
            for (y = 0; y < c.size(); y++) {
                int z = c.get(y);
                //if match found for keyword, and if there is one year or no year, and if the call number matches or was not specified
                if (found && yearRangeStart == 0 && yearRangeEnd == 0
                        && (theYear == 0 || theYear == myReferences.get(z).getYear())
                        && (aCallNumber.equals("") || aCallNumber.equals(myReferences.get(z).getCallNumber()))) {
                    if ((type.equals("book") && myReferences.get(z).getClass() == new Book().getClass())
                            || (type.equals("journal") && myReferences.get(z).getClass() == new Journal().getClass()) || type.equals("")) {
                        theRefs.add(myReferences.get(z));
                    }

                } //if match found for keyword, and Rerefence year falls in the range of years, and call number matches or was not specified
                else if (found && yearRangeStart <= myReferences.get(z).getYear() && yearRangeEnd >= myReferences.get(z).getYear()
                        && (aCallNumber.equals("") || aCallNumber.equals(myReferences.get(z).getCallNumber()))) {
                    if ((type.equals("book") && myReferences.get(z).getClass() == new Book().getClass())
                            || (type.equals("journal") && myReferences.get(z).getClass() == new Journal().getClass()) || type.equals("")) {
                        theRefs.add(myReferences.get(z));
                    }
                }
            }
        } //if the user did not enter a key phrase
        else {
            //traverse the list to find a reference that has matching call number and/or year(s)
            for (y = 0; y < length; y++) {
                if (yearRangeStart == 0 && yearRangeEnd == 0
                        && (theYear == 0 || theYear == myReferences.get(y).getYear())
                        && (aCallNumber.equals("") || aCallNumber.equals(myReferences.get(y).getCallNumber()))) {
                    if ((type.equals("book") && myReferences.get(y).getClass() == new Book().getClass())
                            || (type.equals("journal") && myReferences.get(y).getClass() == new Journal().getClass()) || type.equals("")) {
                        theRefs.add(myReferences.get(y));
                    }

                } else if (yearRangeStart <= myReferences.get(y).getYear() && yearRangeEnd >= myReferences.get(y).getYear()
                        && (aCallNumber.equals("") || aCallNumber.equals(myReferences.get(y).getCallNumber()))) {

                    if ((type.equals("book") && myReferences.get(y).getClass() == new Book().getClass())
                            || (type.equals("journal") && myReferences.get(y).getClass() == new Journal().getClass()) || type.equals("")) {
                        theRefs.add(myReferences.get(y));
                    }
                }
            }
        }

        if (theRefs.size() == 0) {
            return null;
        } else {
            return theRefs;
        }
    }

    public String printAll() {
        String toPrint = "";
        for (int z = 0; z < myReferences.size(); z++) {
            toPrint += myReferences.get(z).toString() + "\n";
        }
        return toPrint;
    }

    public String printSome(ArrayList<Reference> someRefs) {
        String some = "";
        for (int y = 0; y < someRefs.size(); y++) {
            some += someRefs.get(y).toString() + "\n";
        }
        return some;
    }

    public void printBooks() {
        for (int x = 0; x < myReferences.size(); x++) {
            if (myReferences.get(x).getClass() == new Book().getClass()) {
                System.out.println(myReferences.get(x).toString());
            }
            
        }
    }

    public void printJournals() {
        for (int x = 0; x < myReferences.size(); x++) {
            if (myReferences.get(x).getClass() == new Journal().getClass()) {
                System.out.println(myReferences.get(x).toString());
            }
        }
    }

    public int listSize() {
        return myReferences.size();
    }

    public void catalogItem(String fileName, String type, Reference addRef) {
        PrintWriter catalog;

        if (type.equals("b")) {
            type = "book";
        } else if (type.equals("j")) {
            type = "journal";
        }
        try {
            catalog = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
            catalog.println(type + "\n"
                    + addRef.toString());
            catalog.close();
        } catch (IOException ex) {
            Logger.getLogger(Reference.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashMap loadReferences(String fileName, HashMap myMap) {
        Scanner textStream = null;
        String title = "";
        String callNum = "";
        int year = 0;
        String pubOrg = "";
        String authors = "";
        int refNum = 0;

        try {
            textStream = new Scanner(new FileInputStream(fileName));
            while (textStream.hasNext()) {
                title = "";
                callNum = "";
                year = 0;
                pubOrg = "";
                String otherDummy = textStream.nextLine();

                if (otherDummy.equals("book")) {
                    callNum = textStream.nextLine();
                    String dummy = textStream.nextLine();
                    if (!dummy.equals("n/a")) {
                        authors = dummy;
                    }
                    title = textStream.nextLine();
                    dummy = textStream.nextLine();
                    if (!dummy.equals("n/a")) {
                        pubOrg = dummy;
                    }
                    year = textStream.nextInt();
                    Book newBook = new Book(title, callNum, year, pubOrg, authors);
                    addReference(newBook);
                    addToMap(myMap, title, refNum);
                    refNum++;
                } else if (otherDummy.equals("journal")) {
                    callNum = textStream.nextLine();
                    title = textStream.nextLine();
                    String dummy = textStream.nextLine();
                    if (!dummy.equals("n/a")) {
                        pubOrg = dummy;
                    }
                    year = textStream.nextInt();

                    Journal newJournal = new Journal(title, callNum, year, pubOrg);
                    addReference(newJournal);
                    addToMap(myMap, title, refNum);
                    refNum++;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous Reference records to load into Library.");

        }

        return myMap;
    }

    public HashMap addToMap(HashMap myMap, String newTitle, int refNum) {
        newTitle = newTitle.toLowerCase();
        ArrayList<Integer> v = null;
        StringTokenizer myToke = new StringTokenizer(newTitle, " ");
        String dummy = "";
        while (myToke.hasMoreTokens()) {
            v = new ArrayList<>();
            dummy = myToke.nextToken();
            if (!myMap.containsKey(dummy)) {
                v.add(refNum);
                myMap.put(dummy, v);
            } else {
                v = (ArrayList<Integer>) myMap.get(dummy);
                v.add(refNum);
                myMap.put(dummy, v);
            }
        }
        return myMap;
    }

}
