/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.dvdlibrary.dao;

import com.zakharovakr.dvdlibrary.dto.DVD;

import java.io.*;
import java.util.*;

/**
 *
 * @author kristinazakharova
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    //creating a new Hashmap to hold DVD objects
    private Map<String, DVD> dvds = new HashMap<>();
    //declaring final variables for a text file to store DVD data and delimiter
    public static final String DVDLIST_FILE = "dvdList.txt";
    public static final String DELIMITER = "::";


    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadDVDLibrary();
        DVD newDVD = dvds.put(title, dvd);
        writeDVDLibrary();
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        loadDVDLibrary();
        return new ArrayList<>(dvds.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException {
        loadDVDLibrary();
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        loadDVDLibrary();
        DVD removedDVD = dvds.remove(title);
        writeDVDLibrary();
        return removedDVD;
    }

    @Override
    public DVD editDVD(String title, DVD editDVD) throws DVDLibraryDaoException {
        loadDVDLibrary();
        DVD editedDVD = dvds.put(title, editDVD);
        writeDVDLibrary();
        return editedDVD;
    }

    //a method that can translate a line of text into a DVD object - unmarshalling
    private DVD unmarshallDVD (String DVDAsText) {
        // DVDAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // Jaws::1975::PG::Steven Spielberg::Universal::My favourite movie
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in DVDTokens.
        // Which should look like this:
        // _______________________________________________________________
        // |    |    |  |                |         |                  |
        // |Jaws|1975|PG|Steven Spielberg|Universal|My favourite movie|
        // |    |    |  |                |         |                  |
        // ---------------------------------------------------------------
        //  [0]  [1]  [2]      [3]           [4]           [5]

        //use the String.split(…) method to split each line in the file into an array of Strings.
        // When the split method splits a String on the given delimiter, it throws the delimiter away.
        String[] DVDTokens = DVDAsText.split(DELIMITER);
        // Given the pattern above, the DVD title is in index 0 of the array.
        String title = DVDTokens[0];

        //creating a new DVD object
        DVD DVDFromFile = new DVD(title);

        //setting the remaining tokens inside the DVD object
        DVDFromFile.setReleaseDate(DVDTokens[1]);
        DVDFromFile.setMPAARating(DVDTokens[2]);
        DVDFromFile.setDirectorName(DVDTokens[3]);
        DVDFromFile.setStudio(DVDTokens[4]);
        DVDFromFile.setUserNote(DVDTokens[5]);

        //return a new DVD object
        return DVDFromFile;
    }

    private void loadDVDLibrary() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVDLIST_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDVD holds the most recent DVD unmarshalled
        DVD currentDVD;
        // Go through DVDLIST_FILE line by line, decoding each line into a
        // DVD object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DVD
            currentDVD = unmarshallDVD(currentLine);

            // We are going to use the DVD title as the map key for our DVD object.
            // Put currentDVD into the map using DVD title as the key
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }

    private String marshallDVD(DVD aDVD){
        // We need to turn a DVD object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // Jaws::1975::PG::Steven Spielberg::Universal::My favourite movie

        // Start with the title, since that's supposed to be first.
        String DVDAsText = aDVD.getTitle() + DELIMITER;

        // add the rest of the properties in the correct order:
        DVDAsText += aDVD.getReleaseDate() + DELIMITER;
        DVDAsText += aDVD.getMPAARating() + DELIMITER;
        DVDAsText += aDVD.getDirectorName() + DELIMITER;
        DVDAsText += aDVD.getStudio() + DELIMITER;
        DVDAsText += aDVD.getUserNote();

        // return a dvd object as a String of text
        return DVDAsText;
    }

    /**
     * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
     * for file format.
     *
     * @throws ClassRosterDaoException if an error occurs writing to the file
     */
    private void writeDVDLibrary() throws DVDLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVDLIST_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save DVD data.", e);
        }
        // Write out the DVD objects to the file.
        String DVDAsText;
        List<DVD> DVDList = new ArrayList(dvds.values());
        for (DVD currentDVD : DVDList) {
            // turn a DVD into a String
            DVDAsText = marshallDVD(currentDVD);
            // write the DVD object to the file
            out.println(DVDAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
