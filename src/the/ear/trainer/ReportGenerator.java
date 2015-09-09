/*
 * This class will genarate user "Report"s according to user perormance in exercises
 */

package the.ear.trainer;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public void put(String string, List<Exercise> list){
        scoresMap.put(string, list);
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
        String currentLevel = "", tempCurrentLevel = "level ";
        List<Exercise> exList;
        int maxLevels = category.getNoOfLevels();//Is this correct?? o.O
        System.out.println("maxLevels = " + maxLevels);
        int totNumberOfEx = 0;
        double givenScore = 0.0, finalScore = 0.0;
        
        
        for (Map.Entry<String, List<Exercise>> entry : scoresMap.entrySet()) {
            exList = entry.getValue();
            if(exList != null){
                for (Exercise exercise : exList) {
                    totNumberOfEx++;
                    int attempts = exercise.getAttempts();
                    if(exercise.isCorrect()){
            System.out.println("exercise: " + exercise.getCorrectAnswer());
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
                    if(attempts == 0){
                            totNumberOfEx--; //To correct the number of ex: played
                    }
                }
            }    
            
        }
//        for(int i = 1; i<=maxLevels; i++){
//            currentLevel = tempCurrentLevel + Integer.toString(i);
//            exList = scoresMap.get(currentLevel);
//            
//            System.out.println(" Current Level : " + currentLevel);
//            
//            if(exList != null){
//                for (Exercise exercise : exList) {
//                    totNumberOfEx++;
//                    int attempts = exercise.getAttempts();
//                    if(exercise.isCorrect()){
//                        if(attempts == 1){
//                            givenScore++;
//                        }
//                        else if(attempts < 3){
//                            givenScore += 0.5;
//                        }
//                        else if(attempts < 4){
//                            givenScore += 0.25;
//                        }
//                    }
//                    if(attempts == 0){
//                            totNumberOfEx--; //To correct the number of ex: played
//                    }
//                }
//            }    
//        }//for loop is finished from here
        
        //calculating the final score for the category
        if(totNumberOfEx != 0){
            finalScore = (givenScore/totNumberOfEx) * 100;
            
            System.out.println("Total number of exe: " + totNumberOfEx);
        }
        finalScore = round(finalScore, 2);
        System.out.println("Final Score = " + finalScore);
        return finalScore;
    }
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
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
        
        int numberOfExTried = 0;
        int numberOfExCorrect = 0;
        int numberOfExFullCorrect = 0;
        int numberOfExCorrectTwise = 0;   // Selected the correct answer trying more than twise
        List<Exercise> exList;
        for (Map.Entry<String, List<Exercise>> entry : scoresMap.entrySet()) {
            exList = entry.getValue();
            if(exList != null){
                for (Exercise exercise : exList) {
                    numberOfExTried++;
                    int attempts = exercise.getAttempts();
                    if(exercise.isCorrect()){
                        numberOfExCorrect++;
                        
                        System.out.println("exercise: " + exercise.getCorrectAnswer());
                        
                        if(attempts == 1){
                            numberOfExFullCorrect++;
                        }
                        else if(attempts > 2){
                            numberOfExCorrectTwise++;
                        }
                    }
                    if(attempts == 0){
                        numberOfExTried--; //To correct the number of ex: played
                    }
                }
            }
        }
        
        report.setExTried(numberOfExTried);
        report.setExCorrect(numberOfExCorrect);
        report.setExFullMarks(numberOfExFullCorrect);
        report.setExMoreThanTwise(numberOfExCorrectTwise);
        
        
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
