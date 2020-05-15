/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.dvdlibrary.dao;

import com.zakharovakr.dvdlibrary.dto.DVD;

import java.util.*;

/**
 *
 * @author kristinazakharova
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    //creating a new Hashmap to hold DVD objects
    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public DVD addDVD(String title, DVD dvd) {
        DVD newDVD = dvds.put(title, dvd);
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVDs() {
        return new ArrayList<>(dvds.values());
    }

    @Override
    public DVD getDVD(String title) {
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title) {
        DVD removedDVD = dvds.remove(title);
        return removedDVD;
    }

    @Override
    public DVD editDVD(String title, DVD editDVD) {
        DVD editedDVD = dvds.put(title, editDVD);
        return editedDVD;
    }
}
