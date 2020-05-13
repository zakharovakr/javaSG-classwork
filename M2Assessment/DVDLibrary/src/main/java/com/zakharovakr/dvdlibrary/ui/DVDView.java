/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.dvdlibrary.ui;

import com.zakharovakr.dvdlibrary.dto.DVD;

/**
 *
 * @author kristinazakharova
 */
public class DVDView {
    private UserIO io = new UserIOConsoleImpl();

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs in the Collection");
        io.print("2. Add New DVD");
        io.print("3. View a DVD");
        io.print("4. Remove a DVD");
        io.print("5. Edit DVD Information");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    //method that gets info from user about a new DVD and creates a new DVD object
    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter DVD's title");
        String releaseDate = io.readString("Please enter the release date");
        String MPAARating = io.readString("Please enter the MPAA rating");
        String directorName = io.readString("Please enter director's name");
        String studio = io.readString("Please enter the studio name");
        String userNote = io.readString("Please enter some notes/your personal rating");

        //constructing a new DVD object
        DVD currentDVD = new DVD(title);
        //setting the properties
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMPAARating(MPAARating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserNote(userNote);
        //returning a new DVD object
        return currentDVD;
    }

    //this method displays a banner or heading to the UI
    // indicating that the next interactions on the screen will be for creating a new DVD
    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    //this method displays a message that the new DVD was successfully created
    // and waits for the user to hit Enter to continue
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created. Please hit enter to continue");
    }
}
