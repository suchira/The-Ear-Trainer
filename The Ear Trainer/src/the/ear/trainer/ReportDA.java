/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Suchira
 */
public class ReportDA {
    private static ReportDA singleton = null;
    private static DbConnecter dbCon = DbConnecter.getConnection();
    private Statement statement;
    private ResultSet resultSet;
    
    public synchronized static ReportDA getConnection() {
        if (singleton == null) {
            singleton = new ReportDA();
            return singleton;
        } else {
            return singleton;
        }
    }
    
    private ReportDA() {
    }
    
    public ArrayList<Report> getReports(User user){
        ArrayList<Report> reportList = new ArrayList<Report>();
       // Date date = new Date();
        
        try {
            statement = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `report` WHERE userName = \"" + user.getUserName() + "\"";
            resultSet = statement.executeQuery(query);
            System.out.println("Records from the database: getReports(user)");
            while (resultSet.next()) {
                Report report = new Report();
                report.setIdentifier(resultSet.getInt(1));
                report.setFinalScore(resultSet.getInt(4));
                report.setCategory(resultSet.getString(5));
//                report.setFinalVisitedLevel(resultSet.getString(5));
//                report.setStartedFrom(resultSet.getString(6));
             
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String dateTime = resultSet.getString(3);
                    Date date;
                    date = sdf.parse(dateTime);
                    report.setDateTime(date);
                } catch (ParseException e) {
                    System.out.println("Error in converting the date...." + e.getMessage());
                }
                reportList.add(report);    
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        
        return reportList;
    } 
    
    //resultSet should be regarding report data
    public Report setReport(ResultSet resultSet) throws SQLException{
        Report report = new Report();
                report.setIdentifier(resultSet.getInt(1));
                report.setFinalScore(resultSet.getInt(4));
                report.setCategory(resultSet.getString(5));
//                report.setFinalVisitedLevel(resultSet.getString(5));
//                report.setStartedFrom(resultSet.getString(6));
             
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String dateTime = resultSet.getString(3);
                    Date date;
                    date = sdf.parse(dateTime);
                    report.setDateTime(date);
                } catch (ParseException e) {
                    System.out.println("Error in converting the date...." + e.getMessage());
                }
                return report;
    }
    
    public boolean enterNewReport(Report report){
        try {
            statement = dbCon.getCon().createStatement();
            int identifier;
            double finalScore;
            String date, username, category, query;
            username = /*report.getUserName()*/ "kgsd";
            finalScore = report.getFinalScore();
            category = report.getCategory();
            //Converting the current date to DateTime format
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date d = report.getDateTime();
            date = sdf.format(d);
        
            query = "INSERT INTO report VALUES ('" + username + "', '" + date + "', " + finalScore + ", '" + category + "')";
            System.out.println("enterNewReport() query: " + query);
            
            statement.executeQuery(query);
            return true;
        } catch (Exception e) {
            System.err.println("Error in 'enterNewReport()' in report_DA." + e.getMessage());
            return false;
        }
    }
    
    public List<Report> getPerformance(Date dateTime){
        List<Report> reportsOfTheDay = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH) + 1;//month is from 0 - 11
        int year = calendar.get(Calendar.YEAR);
        try {
            statement = dbCon.getCon().createStatement();
            String query = "SELECT userName, AVG(finalScore) as average, category, createdDate FROM report WHERE userName = 'kgsd" /*+ User.getUser().getUserName()*/ + "' AND YEAR(createdDate) = " + year + " AND MONTH(createdDate) = " + month + " AND DAY(createdDate) = " + date + " GROUP BY category";
            resultSet = statement.executeQuery(query);
            
            System.out.println("getReports() query: " + query);
            
            while(resultSet.next()){
                Report report = new Report();
                report.setUserName(resultSet.getString(1));
                report.setFinalScore(resultSet.getDouble(2));
                report.setCategory(resultSet.getString(3));
                report.setDateTime(resultSet.getDate(4));

                Date dateTimeNow = new Date();
                report.setDateTime(dateTimeNow);
                
                reportsOfTheDay.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reportsOfTheDay;
    }

    public List<Report> getMonthlyPerformnce(Date dateTime) {
        List<Report> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        int month = calendar.get(Calendar.MONTH) + 1;//month is from 0 - 11
        int year = calendar.get(Calendar.YEAR);
        try {
            statement = dbCon.getCon().createStatement();
            String query = "SELECT userName, AVG(finalScore) as average, category FROM report WHERE userName = 'kgsd" /*+ User.getUser().getUserName()*/ + "' AND YEAR(createdDate) = " + year + " AND MONTH(createdDate) = " + month + " GROUP BY category";
            resultSet = statement.executeQuery(query);
            
            System.out.println("getReports() query: " + query);
            
            while(resultSet.next()){
                Report report = new Report();
                report.setUserName(resultSet.getString(1));
                report.setFinalScore(resultSet.getDouble(2));
                report.setCategory(resultSet.getString(3));

                Date dateTimeNow = new Date();
                report.setDateTime(dateTimeNow);
                
                list.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Report> getOverallPerformnce(Date dateTime) {
        List<Report> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        int month = calendar.get(Calendar.MONTH) + 1;//month is from 0 - 11
        int year = calendar.get(Calendar.YEAR);
        try {
            statement = dbCon.getCon().createStatement();
            String query = "SELECT userName, AVG(finalScore) as average, category FROM report WHERE userName = 'kgsd" /*+ User.getUser().getUserName()*/ + "' AND YEAR(createdDate) = " + year + " GROUP BY category";
            resultSet = statement.executeQuery(query);
            
            System.out.println("getReports() query: " + query);
            
            while(resultSet.next()){
                Report report = new Report();
                report.setUserName(resultSet.getString(1));
                report.setFinalScore(resultSet.getDouble(2));
                report.setCategory(resultSet.getString(3));

                Date dateTimeNow = new Date();
                report.setDateTime(dateTimeNow);
                
                list.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Report> getAllReports() throws ParseException{
        List<Report> reports = new ArrayList<>();
        
        try {
            statement = dbCon.getCon().createStatement();
            String query = "SELECT userName, finalScore, category, createdDate, identifier FROM report WHERE userName = 'kgsd'" /*+ User.getUser().getUserName()*/ ;
            resultSet = statement.executeQuery(query);
            
            System.out.println("getReports() query: " + query);
            
            while(resultSet.next()){
                Report report = new Report();
                report.setUserName(resultSet.getString(1));
                report.setFinalScore(resultSet.getDouble(2));
                report.setCategory(resultSet.getString(3));
                report.setIdentifier(resultSet.getInt(5));
                
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String dateTime = resultSet.getString(4);
                    Date date;
                    date = sdf.parse(dateTime);
                    report.setDateTime(date);
                
                reports.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reports;
    }
}
