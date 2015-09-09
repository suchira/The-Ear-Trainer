/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.jfugue.pattern.Pattern;

/**
 *
 * @author Suchira
 */
public class ExerciseLoader {
    private String fileName = null, categoryName = null;
    private static ExerciseLoader singleton = null;
    private final int MAX_NUMBER_OF_EXERCISES = 6;
    
    public static ExerciseLoader getInstance(){
        if(singleton == null){
            singleton = new ExerciseLoader();
        }
        return singleton;
    }
    
    private ExerciseLoader(){
    }
    
    private Exercise LoadFile(String fileName, String exFilename, int exerciseNumber) throws IOException{
        Exercise exercise = null;
        Pattern pattern = Pattern.load(new File(fileName));
        exercise = new Exercise(exFilename, exerciseNumber, pattern);
        return exercise;
    }
    
    //Loading all exercise files with the given "categoryName" and the "level"
    public List<Exercise> loadFilesInALevel(String categoryName, int level){
        List<Exercise> exercises = null;
        exercises = new ArrayList<>();
        String exFileName = categoryName + "_" + "level_" + level + "_";
        String temporyFileName = "exercises\\" + exFileName;
        String fileName = null;
        try {
            for (int exerciseNumber = 1; exerciseNumber <= MAX_NUMBER_OF_EXERCISES; exerciseNumber++) {//  'i' is the exercise number
                fileName = temporyFileName + exerciseNumber + ".jfugue";
                Exercise e = LoadFile(fileName, exFileName, exerciseNumber);
                System.out.println("Exercise File Name: " + fileName); //for the purpose of testings
                exercises.add(e);
            }
        } catch (IOException e) { //If there doesn't exist "MAX_NUMBER_OF_EXERCISES", loading files will throw an exception 
            Logger.getLogger(PracticeButtonPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        
        return exercises;
    }
}
