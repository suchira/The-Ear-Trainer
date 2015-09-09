/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JLabel;
import static org.junit.Assert.*;
import org.junit.Test;
import the.ear.trainer.Category;
import the.ear.trainer.ExerciseController;

/**
 *
 * @author Suchira
 */
public class ExerciseControllerJUnitTest {
    ExerciseController exerciseController = ExerciseController.getInstance();

    
    public ExerciseControllerJUnitTest() {
        
        Category category = new Category("Chord");
        int levelNumber = 1;
        JLabel categoryLabel = new JLabel(), levelLabel = new JLabel();
        
        exerciseController.setCategory(category, levelNumber);
        exerciseController.setLabelTexts(categoryLabel, levelLabel);
    }
    
  
    
}
