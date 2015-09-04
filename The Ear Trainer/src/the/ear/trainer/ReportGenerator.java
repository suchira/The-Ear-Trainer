/*
 * This class will genarate user "Report"s according to user perormance in exercises
 */

package the.ear.trainer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Suchira
 */
public class ReportGenerator {
    private static ReportGenerator singleton = null;
    private Map<String, List<Exercise>> scoresMap;
    private Category category = null;
    public static ReportGenerator getInstance(){
        if(singleton == null){
            singleton = new ReportGenerator();
        }
        return singleton;
    }
    
    private ReportGenerator(){
        scoresMap = new HashMap<>();
    }

    public Map<String, List<Exercise>> getScoresMap() {
        return scoresMap;
    }

    public void setScoresMap(Map<String, List<Exercise>> scoresMap) {
        this.scoresMap = scoresMap;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public double calculate(){
        String currentLevel = "level ";
        List<Exercise> exList;
        int maxLevels = category.getNoOfLevels();//Is this correct?? o.O
        System.out.println("maxLevels = " + maxLevels);
        int totNumberOfEx = 0;
        double givenScore = 0.0, finalScore = 0.0;
        
        for(int i = 1; i<=maxLevels; i++){
            currentLevel = currentLevel + Integer.toString(i);
            exList = scoresMap.get(currentLevel);
            if(exList != null){
                for (Exercise exercise : exList) {
                    totNumberOfEx++;
                    int attempts = exercise.getAttempts();
                    if(attempts == 1){
                        givenScore++;
                    }
                    else if(attempts < 3){
                        givenScore += 0.5;
                    }
                    else if(attempts < 4){
                        givenScore += 0.25;
                    }
                }
            }    
        }//for loop is finished from here
        
        //calculating the final score for the category
        if(totNumberOfEx != 0){
            finalScore = (givenScore/totNumberOfEx) * 100;
        }
        System.out.println("Final Score = " + finalScore);
        return finalScore;
    }
    
    private Report generateReport(){
        User user = User.getUser();
        Report report = new Report();
        
        //Converting the current date to DateTime format
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        //String currentDateTime = sdf.format(date);
        
        report.setDateTime(date);
        report.setFinalScore(calculate());
        report.setCategory(category.getCategoryName());
        report.setUserName(user.getUserName());
        
        return report;
    }

    public void updateDataBase() {
        Report report = generateReport();
        ReportHandler reportHandler = new ReportHandler();
        reportHandler.enterNewReport(report);
    }
}
