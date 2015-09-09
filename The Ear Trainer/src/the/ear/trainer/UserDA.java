/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Suchira
 */
public class UserDA {
    private static DbConnecter dbCon = DbConnecter.getConnection();
    private Statement statement;
    private ResultSet resultSet;
    private static UserDA singleton = null;
    
    public synchronized static UserDA getConnection() {
        if (singleton == null) {
            singleton = new UserDA(dbCon);
            return singleton;
        } else {
            return singleton;
        }
    }
    
    private UserDA(DbConnecter dbCon) {
        this.dbCon = dbCon;
    }
    
    public User GetUser(String userName, String password){
        String password1 = null;
        User user = User.getUser(); // Shouldn't be changed 
        try {
            statement = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `user` WHERE userName = \"" + userName + "\"";
            resultSet = statement.executeQuery(query);
            System.out.println("Records from the database");
            while (resultSet.next()) {
                password1 = resultSet.getString("password");
                if(password1.equals(password)){
                    user.setUserName(userName);
                    user.setPassword(password);
                    user.setNoOfVisits(resultSet.getInt("noOfVisits"));
                }
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return user;
    }
    
    public boolean enterAUser(User user){
        boolean entered = false;
        try {
            statement = dbCon.getCon().createStatement();
            String query = "INSERT INTO `ear_trainer`.`user` (`userName`, `password`, `noOfVisits`) VALUES ('" + user.getUserName() + "', '" + user.getPassword() +"', '0')";
            statement.executeUpdate(query);
            System.out.println("Updated the 'user' table");
            entered = true;
        } catch (Exception e) {
            System.out.println("Error occured while updating the 'user' table....");
        }
        return entered;
    }
}
