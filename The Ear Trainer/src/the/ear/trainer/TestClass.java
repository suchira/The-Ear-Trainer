/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;

import java.util.ArrayList;

/**
 *
 * @author Suchira
 */
public class TestClass {
    private ReportHandler reportHandler;
    
//    public void ReportTest(){
//        reportHandler = new ReportHandler();
//        User user = new User();
//        user.setUserName("kgsd");
//        user.setPassword("123");
//        reportHandler.getReports(user);
//        ArrayList<Report> list = reportHandler.getReport();
//        
//        for (Report report : list) {
//            System.out.println("Date: " + report.getDateTime());
//        }
//    }
    
    public void SignUpTest(){
        SignUpJFrame signUp = new SignUpJFrame();
        signUp.setVisible(true);
    }
}
