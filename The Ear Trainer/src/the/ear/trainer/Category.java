/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Suchira
 */
public class Category {
    private List<Level> levels;
    private int noOfLevels;
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.levels = new ArrayList<>();
        this.noOfLevels = 0;
    }
    
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public Level getLevel(int levelNumber) {
        Level requestedLevel = null;
        for (Level level : levels) {
            if(level.getLevelNumber() == levelNumber){
                requestedLevel = level;
            }
        }
        return requestedLevel;
    }

    public void setLevels(List<Level> level) {
        this.levels = level;
        setNoOfLevels();
    }

    public int getNoOfLevels() {
        return noOfLevels;
    }

    public void setNoOfLevels() {
        this.noOfLevels = levels.size();
    }

    //Add only one level
    public void addNewLevel(Level l){
        levels.add(l);
        setNoOfLevels();
    }
    
    //Add many levels
    public void addNewLevels(List<Level> levelList){
        for (Level level : levelList) {
            levels.add(level);
        }
        setNoOfLevels();
    }
    
}
