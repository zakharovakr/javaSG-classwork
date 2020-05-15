/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.dvdlibrary;

import com.zakharovakr.dvdlibrary.controller.DVDLibraryController;
import com.zakharovakr.dvdlibrary.dao.DVDLibraryDao;
import com.zakharovakr.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.zakharovakr.dvdlibrary.ui.DVDView;
import com.zakharovakr.dvdlibrary.ui.UserIO;
import com.zakharovakr.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author kristinazakharova
 */
public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DVDView myView = new DVDView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        controller.run();
    }
}
