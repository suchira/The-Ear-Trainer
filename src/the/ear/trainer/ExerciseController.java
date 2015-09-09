package the.ear.trainer;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author Suchira
 */
public class ExerciseController {
    private static ExerciseController singleton = null;
    
    /* Current category, Level and the exercise*/
    private Category category = null; 
    private Level level = null;
    private Exercise exercise = null;
    private JLabel categoryNameLabel, levelLabel;
    /*-----------------------------------------*/
    
    private boolean levelFinished = false;
    private int numberOfExercisesDone = 0, numberOfLevelsDone = 0;
    private ExerciseLoader exerciseLoader;
    
    //Attributes relevant to ReportGEnerator
    private ReportGenerator reportGenerator;
    private Map<String, List<Exercise>> scoresMap;
    private List<Exercise> playedExercises;
    /*---------------------------------------------*/
    
    public static ExerciseController getInstance(){
        if(singleton == null){
            singleton = new ExerciseController();
        }
        return singleton;
    }

    public void setCategory(Category category, int levelNumber) {
        this.category = category;
        setLevel(category.getLevel(levelNumber));
        setLevelExercises();
        //setLabelTexts(categoryNameLabel, levelLabel);
    }

    private void setLevel(Level level) {
        this.level = level;
    }
    
    private ExerciseController(){
        exerciseLoader = ExerciseLoader.getInstance();
        reportGenerator = ReportGenerator.getInstance();    //A report generator must be instantiated at first        
        scoresMap = new HashMap<>();
        reportGenerator.setScoresMap(scoresMap);
    }
    
    public void setLabelTexts(JLabel categoryNameLabel, JLabel levelLabel){
        this.categoryNameLabel = categoryNameLabel;
        this.levelLabel = levelLabel;
        categoryNameLabel.setText(category.getCategoryName());
        levelLabel.setText("Level " + level.getLevelNumber());
        
        reportGenerator.setCategory(category);
        playedExercises = new ArrayList<>(); //A new ArrayList should be created before moving to the next level
        scoresMap.put("level " + level.getLevelNumber(), playedExercises);
        
    }
    
    public void updateLevel(){
        try {
            if(levelFinished ){//If the level is over
                increaseNoOfLevsDone();
                level = new Level(level.getLevelNumber()+1);
                setLevelExercises();
                if(/*category.getNoOfLevels() == numberOfLevelsDone*/   isCategoryOver()){
                    level = null;
                    setLevelExercises(); 
                    //Labels should be changed in a proper manner
                    System.out.println("Category is over 2");
                }
                else{
                    //level = category.getLevel(level.getLevelNumber()+1);
                    setLevelExercises();
                    setLabelTexts(categoryNameLabel, levelLabel);
                    levelFinished = false;
                    numberOfExercisesDone = 0;
                    playedExercises = new ArrayList<>(); // Creating a new list to add the exercises played in the new level
                    scoresMap.put("level" + level.getLevelNumber(), playedExercises);
                }
            }
            else{
                System.out.println("Category is over 2");
            }
        } catch (Exception e) {//if the +1 level doesn't exist....!!!
            System.out.println("No 'Levels' any more...");
            Logger.getLogger(ExerciseController.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }
    
    public Exercise nextExercise(){
        try {
            if(numberOfExercisesDone == level.getMAX_EXERCISES()){
                levelFinished = true;
                System.out.println("Level: " + level.getLevelNumber() + " is finished: " + levelFinished);
            }
            if(levelFinished){ //If all the exercises in the level has done, move to the net level. Otherwise the while loop will be an infinite one
                updateLevel();
            }
            if(level == null){
                return null;
            }
            int no_execises = level.getExercises().size();
            int randInt = randInt(1, no_execises);
            boolean selected = false;
            
//            System.out.println("Generated Random Integer: " + randInt);
            
            if(isCategoryOver()){
                JOptionPane.showMessageDialog(new JFrame(), "Category is over!!!", "Dialog",JOptionPane.WARNING_MESSAGE);
                return null;
            }
            while(!selected && (level != null)){  //Select an exercise from the level randomely and check whether it has being selected before
                Exercise temporyExercise = level.getExercise(randInt);
                
                System.out.println("DEBug in while loop");
                
                if(temporyExercise.isSelected()){
                    selected = false;
                    randInt = randInt(1, no_execises);
//                    System.out.println("Generated Random Integer: " + randInt);
                }
                else{
                    selected = true;
                    exercise = temporyExercise;
                    exercise.setSelected(selected);
                    playedExercises.add(exercise);
                }
            }
            increaseNoOfExeSelected();
//            exercise.increaseAttempts();  //attempts should be increased when the user checked it
//            System.out.println("MAX_EXERCISES: " + level.getMAX_EXERCISES());// For error checking purpose
            
            
        } catch (Exception e) {
            System.out.println("ERROR in nextExercise() method!!!    " );
            e.printStackTrace();
        }
        return exercise;
    }
    
    public boolean checkAnswer(String answer){
        boolean correct = exercise.isCorrect();
        MainMenuJFrame mainMenu = MainMenuJFrame.getMainMenuJFrame(User.getUser());

        try {
            //increase number of tries only if the answer is still not correct
            if(!exercise.isCorrect()){
                exercise.increaseAttempts();
            }
            if(answer == exercise.getCorrectAnswer()){
                correct = true;
                if(!exercise.isCorrect() && exercise.getAttempts() == 1){//if already checked
                    mainMenu.increaseNoExCorrect2();
                    mainMenu.increaseNoExCorrect();
                }
            }
            if(!exercise.isCorrect() && exercise.getAttempts() == 1){//if already NOT checked
                //updating the current score and statistics in "main menu"
                mainMenu.increaseNoExDone2();
                mainMenu.increaseNoExDone();
                
               
            }
        } catch (Exception e) {
            Logger.getLogger(ExerciseController.class.getName()).log(java.util.logging.Level.SEVERE, null, e); 
        }
        exercise.setIsCorrect(correct);
        
        
        //Setting up final value
        mainMenu.setCurrentScore(ReportGenerator.getInstance().calculate());
        System.out.println(" SCORE: " + ReportGenerator.getInstance().calculate());
        
        
        if(answer != exercise.getCorrectAnswer() && correct == true){
            return false;
        }
        
        return correct;
    }
    
    public boolean checkAnswer2(Exercise e, String answer){
        exercise = e;
        return checkAnswer(answer);
    }
    
    public void play(){
        exercise.play();
    }
    
    //Methana Awulak enna puluwan
//    public void setNextLevel(){
//        if(numberOfExercisesDone == level.getMAX_EXERCISES()){
//            levelFinished = true;
//        }
//        updateLevel();
//    }
    
    
    private void increaseNoOfExeSelected(){
        ++numberOfExercisesDone;
    }
    
    private void increaseNoOfLevsDone(){
        ++numberOfLevelsDone;
    }
    
    //No need of try again function in here
    public Exercise tryAgain(){
        if(!exercise.isCorrect()){ //The user may try again even he know the correct answer
            exercise.increaseAttempts();
        }
        return exercise;
    }
    
    private int randInt(int min, int max) {        
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
    private List<Integer> randInt(int min, int max, int numberOfIntegers){
        int count = 0;
        ArrayList<Integer> randomIntegers = new ArrayList<>();
        while (count != numberOfIntegers) {            
            Integer rand = randInt(min, max);
            if(!randomIntegers.contains(rand)){
                randomIntegers.add(rand);
                count++;
            }
        }
        return randomIntegers;
    }
    
    //Create set of answers for an exercise including the correct answer
    public List<String> answerSet(){
        List<String> answers = new ArrayList<>();
        answers.add("");
        answers.add("");
        answers.add("");
        answers.add("");
        
        String correctAnswer = exercise.getCorrectAnswer();
        int indexOfCorrectAns;
        boolean containTheCorrectAnsIndex = true;
        
        //for testing, the correct answer will be added to the list, answers.
//        answers.add(exercise.getCorrectAnswer());
        
        List<String> allAnswers = new ArrayList<>();
        MainMenuJFrame menu = MainMenuJFrame.getMainMenuJFrame(User.getUser());
        if(category.getCategoryName().equals("Chords")){
            allAnswers = menu.getChordsList();
        }
        if(category.getCategoryName().equals("Notes")){
            allAnswers = menu.getNotesList();
        }
        if(category.getCategoryName().equals("Intervals")){
            allAnswers = menu.getIntervelsList();
        }
        if(category.getCategoryName().equals("Scales")){
            allAnswers = menu.getScalesList();
        }
        
        indexOfCorrectAns = allAnswers.indexOf(correctAnswer);
        List<Integer> randList = randInt(0, allAnswers.size()-1, 3);
        while(randList.contains(indexOfCorrectAns)){
            randList = randInt(0, allAnswers.size()-1, 3);
        }
        List<Integer> randIntList2 = randInt(0, 3, 4);
        answers.add(randIntList2.get(0), correctAnswer);
        answers.add(randIntList2.get(1), allAnswers.get(randList.get(0)));
        answers.add(randIntList2.get(2), allAnswers.get(randList.get(1)));
        answers.add(randIntList2.get(3), allAnswers.get(randList.get(2)));
        answers.remove("");
        answers.remove("");
        answers.remove("");
        answers.remove("");
        
        return answers;
    }
    
    public boolean isCategoryOver(){
        boolean isOver = false;
//        if(numberOfLevelsDone == category.getNoOfLevels()){
//            isOver = true;
//        }
        if(level.getExercises().size() == 0){
            isOver = true;
        }
        return isOver;
    }
    
    //This method will load all the exercises relevant to a given level
    public void setLevelExercises(){
        try {
            if(level != null){ // If the category is over, the current level will be null
                List<Exercise> exerciseList = exerciseLoader.loadFilesInALevel(category.getCategoryName(), level.getLevelNumber());
                level.setExercises(exerciseList);
                System.out.println("NUMBER OF EXERCISES IN LEVEL " + level.getLevelNumber() + ": " + exerciseList.size());
                System.out.println("NUMBER OF LEVELS DONE: " + numberOfLevelsDone);
            }
        } catch (Exception e) {
            System.out.println("Error in setLevelExercises() method: " + e.getMessage());
        }
    }
    
    //Finish the current session. Update the database and refresh the controller
    public void Finish() throws Throwable{
        //Initialize current stat in main menu to zero
        MainMenuJFrame menuJFrame = MainMenuJFrame.getMainMenuJFrame(User.getUser());
        menuJFrame.initializeCurrentStat();
        
        //if the following throws an exception usesr data might not be updated
        reportGenerator.updateDataBase(); //Update the data base with the current values
        
        //after updating the database, update the main menu as well
        PerformanceGenerator performanceGenerator = new PerformanceGenerator();
        performanceGenerator.setMainMenuStat();
        
        this.finalize();
    }
}
