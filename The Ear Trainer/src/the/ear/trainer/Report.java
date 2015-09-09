/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;

import java.util.Date;



/**
 *
 * @author Suchira
 */
class Report {
    private int identifier;
    private double finalScore;
    private String Category;
    //private String startedFrom;
    private Date dateTime;
    private String userName;

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

//    public String getFinalVisitedLevel() {
//        return finalVisitedLevel;
//    }
//
//    public void setFinalVisitedLevel(String finalVisitedLevel) {
//        this.finalVisitedLevel = finalVisitedLevel;
//    }
//
//    public String getStartedFrom() {
//        return startedFrom;
//    }
//
//    public void setStartedFrom(String startedFrom) {
//        this.startedFrom = startedFrom;
//    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
