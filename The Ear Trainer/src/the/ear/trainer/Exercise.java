/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 *
 * @author Suchira
 */
public class Exercise {
    private String name, correctAnswer;
    private Pattern musicPattern;
    private int exerciseNumber, attempts;
    private boolean selected = false;
    private boolean isCorrect = false;   //Is correct kiyana eka kohewath set kale naa
    
    public Exercise(String name, int exerciseNumber, Pattern musicPattern){
        this.name = name;
        this.exerciseNumber = exerciseNumber;
        this.musicPattern = musicPattern;
        setCorrectAnswer(musicPattern.toString());
        String pattern = musicPattern.getPattern().toString();
        this.correctAnswer = MainMenuJFrame.getMainMenuJFrame(User.getUser()).answerMap.get(pattern);
        this.attempts = 1;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public void play(){
        Player player = new Player();
        player.play(musicPattern);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    
    public void increaseAttempts(){
        attempts++;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getName() {
        return name;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
    
}
