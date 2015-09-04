/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Suchira
 */
public class ReportHandler {
    private ReportDA reportDA = ReportDA.getConnection();
    ArrayList<Report> report;
    
    public ArrayList<Report> getReport(){
        return report;
    }
    
    public void getReports(User user){
        report = reportDA.getReports(user);
    }
    
    public List<Report> getDailyPerformance(){
        List<Report> list = reportDA.getPerformance(new Date());
        for (Report report1 : list) {
            System.out.println(report1.getCategory() + " : " + report1.getFinalScore());
        }
        return list;
    }
    public void enterNewReport(Report report){
        boolean success = false;
        if(report != null){
            success = reportDA.enterNewReport(report); // return true if the transaction was successful
        }
    }
    
    public List<Report> getMonthPerformance(){
        List<Report> list = reportDA.getMonthlyPerformnce(new Date());
        for (Report report1 : list) {
            System.out.println(report1.getCategory() + " : " + report1.getFinalScore());
        }
        
        return list;
    }
    public List<Report> getOverallPerformance(){
        List<Report> list = reportDA.getOverallPerformnce(new Date());
        for (Report report1 : list) {
            System.out.println(report1.getCategory() + " : " + report1.getFinalScore());
        }
        
        return list;
    }
    public List<Report> getAllReports(){
        List<Report> list = new ArrayList<>();
        try {
            list = reportDA.getAllReports();
        } catch (ParseException ex) {
            Logger.getLogger(ReportHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            return list;
        }
    }
    
    
}
