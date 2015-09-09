/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JLabel;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import the.ear.trainer.Category;
import the.ear.trainer.Exercise;
import the.ear.trainer.ExerciseController;
import the.ear.trainer.Level;

/**
 *
 * @author Suchira
 */

public class ExerciseControllerJUnitTest {
    static ExerciseController exerciseController = ExerciseController.getInstance();
    Exercise e;
    String answer;
    boolean expected;
    
    
   
    
    public ExerciseControllerJUnitTest() {
        
//        this.e = e;
//        this.answer = answer;
//        this.expected = expected;
        
//        Category category = new Category("Chord");
//      Level level = new Level(1);
//      category.addNewLevel(level);
//        int levelNumber = 1;
//        JLabel categoryLabel = new JLabel(), levelLabel = new JLabel();
//        
//        exerciseController.setCategory(category, levelNumber);
//        exerciseController.setLabelTexts(categoryLabel, levelLabel);
//        
//        List answers;
//         e = exerciseController.nextExercise();
//        answers = exerciseController.answerSet();
//        answers.remove(e.getCorrectAnswer());
//        answer = e.getCorrectAnswer();
//        expected = true;
    }
    

//    public static Collection create(){
//        Exercise e;
//        List answers;
//        List<Object[]> totalList = new ArrayList<>();
//        for (int j = 0; j < 10; j++) {
//            e = exerciseController.nextExercise();
//            System.out.println("E: " + e);
//            answers = exerciseController.answerSet();
//            answers.remove(e.getCorrectAnswer());
//            totalList.add(new Object[]{e, e.getCorrectAnswer(), true});
//            totalList.add(new Object[]{e, answers.get(0), false});
//        }
//        return totalList;
//    }
    
    @Test
    public void checkAnswerTest(){
        Category category = new Category("Chords");
        Level level = new Level(1);
        category.addNewLevel(level);
        int levelNumber = 1;
        JLabel categoryLabel = new JLabel(), levelLabel = new JLabel();
        
        exerciseController.setCategory(category, levelNumber);
        exerciseController.setLabelTexts(categoryLabel, levelLabel);
        
        List answers;
         e = exerciseController.nextExercise();
        answers = exerciseController.answerSet();
        answers.remove(e.getCorrectAnswer());
        answer = e.getCorrectAnswer();
        expected = true;
        assertEquals(expected, exerciseController.checkAnswer2(e, answer));
    }
}
