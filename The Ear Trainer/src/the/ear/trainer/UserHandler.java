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
public class UserHandler {
    private UserDA userDA = UserDA.getConnection();
    private User user;

    public User getUser() {
        return user;
    }
    
    public boolean checkForPassword(String userName, String password){
        try{
            if(userName == null || password == null){
                System.out.println("Insert Correct Inputs");
                return false;
            }
            if(userName == "" || password == ""){
                System.out.println("Insert Correct Inputs");
                return false;
            }

            user = userDA.GetUser(userName, password);
            String enteredPassword = password;

            if(user.getPassword().equals(enteredPassword)){
                return true;
            }
            return false;
        }
        catch(Exception e){
            System.out.println("Error in 'checkForPassword method'");
            System.out.println("'checkForPassword method': " + e.getMessage());
        }
        return false;
    }
    
    public boolean enterANewUser(User user){
        boolean done = false;
        if(user.getUserName() == null || user.getUserName() == ""){
            System.out.println("Enter a correct user name");
            return false;
        }
        
        done = userDA.enterAUser(user);
        return done;
    }
    
}
