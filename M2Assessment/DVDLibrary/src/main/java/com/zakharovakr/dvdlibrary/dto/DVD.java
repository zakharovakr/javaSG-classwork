/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.dvdlibrary.dto;

/**
 *
 * @author kristinazakharova
 */
public class DVD {
    //properties
    private String title;
    private String releaseDate;
    private String MPAARating;
    private String directorName;
    private String studio;
    private String userNote;

    //constructor - passing a title as a parameter
    public DVD (String title) {
        this.title = title;
    }

    //getters and setters
    public String getTitle() {
        return title;
    }

    /*public void setTitle(String title) {
        this.title = title;
    } should probably be read only */

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMPAARating() {
        return MPAARating;
    }

    public void setMPAARating(String MPAARating) {
        this.MPAARating = MPAARating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
}
