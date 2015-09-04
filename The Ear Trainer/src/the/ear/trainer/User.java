/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;

/**
 *
 * @author Suchira
 */
public class User {
    private String userName;
    private String password;
    private int noOfVisits;
    private Report[] report;
    private static User theUser = null;

    public static User getUser(){
        if(theUser == null){
            theUser = new User();
        }
        return theUser;
    }
    
    private User(){
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
        this.password = password;
    }

    public int getNoOfVisits() {
        return noOfVisits;
    }

    public void setNoOfVisits(int noOfVisits) {
        this.noOfVisits = noOfVisits;
    }

    public Report[] getReport() {
        return report;
    }

    public void setReport(Report[] report) {
        this.report = report;
    }
    
    
}
