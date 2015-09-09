package the.ear.trainer;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Suchira
 */
public class Level {
    private List<Exercise> exercises;
    private int levelNumber;
    private int MAX_EXERCISES = 2;
    
    public Level(int levelNumber){
        exercises = new ArrayList<Exercise>();
        this.levelNumber = levelNumber;
    }
    
    //Add only one exercise at a time to this level
    public void addExercise(Exercise e){
        exercises.add(e);
    }
    
    //Add many exercises at a time to this level
    public void addExercises(List<Exercise> eList){
        for (Exercise exercise : eList) {
            exercises.add(exercise);
        }
        MAX_EXERCISES = exercises.size();
    }

    //Get all the eercises this level has
    public List<Exercise> getExercises() {
        return exercises;
    }

    //Get the level number
    public int getLevelNumber() {
        return levelNumber;
    }
    
    public Exercise getExercise(int number){
        return exercises.get(number-1);
    }

    public int getMAX_EXERCISES() {
        return MAX_EXERCISES;
    }

    public void setMAX_EXERCISES(int MAX_EXERCISES) {
        this.MAX_EXERCISES = MAX_EXERCISES;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
    
}
