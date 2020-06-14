/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.dvdlibrary.controller;

import com.zakharovakr.dvdlibrary.dao.DVDLibraryDao;
import com.zakharovakr.dvdlibrary.dao.DVDLibraryDaoException;
import com.zakharovakr.dvdlibrary.dto.DVD;
import com.zakharovakr.dvdlibrary.ui.DVDView;

import java.util.List;

/**
 *
 * @author kristinazakharova
 */
public class DVDLibraryController {

    DVDView view;
    DVDLibraryDao dao;

    //constructor for dependency injection
    public DVDLibraryController(DVDLibraryDao dao, DVDView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDVDs();
                        break;
                    case 2:
                        createDVD();
                        break;
                    case 3:
                        viewDVD();
                        break;
                    case 4:
                        removeDVD();
                        break;
                    case 5:
                        editDVD();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    //a method in the Controller to orchestrate the creation of a new DVD
    private void createDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }

    private void listDVDs() throws DVDLibraryDaoException  {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    private void viewDVD() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        String title = view.getDVDTitleChoice();
        DVD dvd = dao.getDVD(title); //the search is case sensitive. If i have time later, change it
        view.displayDVD(dvd);
    }

    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String title = view.getDVDTitleChoice();
        DVD removedDVD = dao.removeDVD(title); //the search is case sensitive. If i have time later, change it
        view.displayRemoveResult(removedDVD);
    }

    private void editDVD() throws DVDLibraryDaoException {
        //banner
        view.displayEditDVDBanner();
        //get an existing dvd by title
        String title = view.getDVDTitleChoice();
        DVD currentDVD = dao.getDVD(title);
        if(currentDVD != null) {
            //update DVD info if dvd exists
            DVD editedDVD = view.getEditedDVDInfo(currentDVD);
            dao.editDVD(title, editedDVD);
        }
        view.displayEditResult(currentDVD);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
