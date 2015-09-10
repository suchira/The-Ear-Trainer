/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Suchira
 */
public class User {
    private String userName, name;
    private String password;
    private String priority = "User";
    private Report[] report;
    
    private double scalesBestscore = 0;
    private double notesBestscore = 0;
    private double intervalsBestscore = 0;
    private double chordsBestscore = 0;
    
    private String lastVisited = null;
    
    private static User theUser = null;

    public static User getUser(){
        if(theUser == null){
            theUser = new User();
        }
        return theUser;
    }
    
    private User(){
    }

    public String getLastVisited() {
        return lastVisited;
    }

    public void setLastVisited(String lastVisited) {
        this.lastVisited = lastVisited;
    }

    public double getScalesBestscore() {
        return scalesBestscore;
    }

    public void setScalesBestscore(double scalesBestscore) {
        this.scalesBestscore = scalesBestscore;
    }

    public double getNotesBestscore() {
        return notesBestscore;
    }

    public void setNotesBestscore(double notesBestscore) {
        this.notesBestscore = notesBestscore;
    }

    public double getIntervalsBestscore() {
        return intervalsBestscore;
    }

    public void setIntervalsBestscore(double intervalsBestscore) {
        this.intervalsBestscore = intervalsBestscore;
    }

    public double getChordsBestscore() {
        return chordsBestscore;
    }

    public void setChordsBestscore(double chordsBestscore) {
        this.chordsBestscore = chordsBestscore;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            password = MD5Hashing.getHashed(password);
            this.password = password;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR IN 'setPassword().......'");
        }
    }

    public Report[] getReport() {
        return report;
    }

    public void setReport(Report[] report) {
        this.report = report;
    }
    
    
}
