/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.dvdlibrary.dao;

import com.zakharovakr.dvdlibrary.dto.DVD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kristinazakharova
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    /*private Map<String, Student> students = new HashMap<>();
    * Student prevStudent = students.put(studentId, student);
    return prevStudent;*/

    //creating a new Hashmap to hold DVD objects
    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public DVD addDVD(String title, DVD dvd) {
        DVD newDVD = dvds.put(title, dvd);
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVDs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DVD getDVD(String title) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DVD removeDVD(String title) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DVD editDVD(String title) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
