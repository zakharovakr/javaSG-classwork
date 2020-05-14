/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.dvdlibrary.controller;

import com.zakharovakr.dvdlibrary.dao.DVDLibraryDao;
import com.zakharovakr.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.zakharovakr.dvdlibrary.dto.DVD;
import com.zakharovakr.dvdlibrary.ui.DVDView;
import com.zakharovakr.dvdlibrary.ui.UserIO;
import com.zakharovakr.dvdlibrary.ui.UserIOConsoleImpl;

import java.util.List;

/**
 *
 * @author kristinazakharova
 */
public class DVDLibraryController {
    private DVDView view = new DVDView();
    private DVDLibraryDao dao = new DVDLibraryDaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
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
                    io.print("EDIT DVD");
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("GOOD BYE");
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    //a method in the Controller to orchestrate the creation of a new DVD
    private void createDVD() {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }

    private void listDVDs() {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    private void viewDVD() {
        view.displayDisplayDVDBanner();
        String title = view.getDVDTitleChoice();
        DVD dvd = dao.getDVD(title); //the search is case sensitive. If i have time later, change it
        view.displayDVD(dvd);
    }

    private void removeDVD() {
        view.displayRemoveDVDBanner();
        String title = view.getDVDTitleChoice();
        DVD removedDVD = dao.removeDVD(title); //the search is case sensitive. If i have time later, change it
        view.displayRemoveResult(removedDVD);
    }
}
