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
public class TheEarTrainer_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TestClass test = new TestClass();
//        test.ReportTest();
//        test.SignUpTest();
        loggin();
    }
    
    public static void loggin(){
        LoginJFrame login = new LoginJFrame();
        login.setVisible(true);
    }
    
}
