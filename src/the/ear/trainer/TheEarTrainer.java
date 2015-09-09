/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;

/**
 *
 * @author Suchira
 */
public class TheEarTrainer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TestClass test = new TestClass();
//        test.ReportTest();
//        test.SignUpTest();
        //loggin();
        

        //Starts with login page
        LoginJFrame login = new LoginJFrame();
        login.setVisible(true);
        
        
       /* //Starts only with the main menu
        MainMenuJFrame main = MainMenuJFrame.getMainMenuJFrame(User.getUser());
        main.setVisible(true);
        */    
               
    }
    
    public static void loggin(){
        LoginJFrame login = new LoginJFrame();
        login.setVisible(true);
    }
    
}
