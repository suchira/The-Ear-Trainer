/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Suchira
 */
public class UserDA {
    private static DbConnecter dbCon = DbConnecter.getConnection();
    private Statement statement, statement2, statement3;
    private ResultSet resultSet, resultSet2, resultSet3;
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
        String password2 = password;
        User user = User.getUser(); // Shouldn't be changed 
        try {
            password = MD5Hashing.getHashed(password);
            System.out.println("PASS: " + password);
            statement = dbCon.getCon().createStatement();
            statement2 = dbCon.getCon().createStatement();
            statement3 = dbCon.getCon().createStatement();
            
            String query = "SELECT * FROM `user` WHERE userName = \"" + userName + "\"";
            
            String query2 = "SELECT * FROM bestscores WHERE userName = '" + userName + "'";
            
            String query3 = "SELECT * FROM finalaccessdate WHERE userName = '" + userName + "'";
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            Date da = new Date();
//            String date = sdf.format(da);
//            String query3 = "INSERT INTO finalaccessdate VALUES ('" + userName + "', '" + date + "')";
            
            resultSet = statement.executeQuery(query);
            
            System.out.println("Records from the database");
            
            while (resultSet.next()) {
                password1 = resultSet.getString("password");
                if(password1.equals(password)){
                    user.setUserName(userName);
                    user.setPassword(password2);
                    user.setName(resultSet.getString("name"));
                    user.setPriority(resultSet.getString("priority"));
                    
                    resultSet2 = statement2.executeQuery(query2);
                    while(resultSet2.next()){
                        double d = resultSet2.getDouble("finalScore");
                        String category = resultSet2.getString("category") ;
                        
                        System.out.println("d and cat: " + d + " , " + category);
                       
                        if(category.equals("Chords")){
                            user.setChordsBestscore(d);
                        }
                        if(category.equals("Scale")){
                            user.setScalesBestscore(d);
                        }
                        if(category.equals("Intervals")){
                            user.setIntervalsBestscore(d);
                        }
                        if(category.equals("Notes")){
                            user.setNotesBestscore(d);
                        }
                    }
                    
                    resultSet3 = statement3.executeQuery(query3);
                    
                    while(resultSet3.next()){
                        user.setLastVisited(resultSet3.getString("datetime"));
                    
                        System.out.println("Last Seen: " + resultSet3.getString("datetime"));
                    }
//                    statement2 = dbCon.getCon().createStatement();
//                    resultSet2 = statement2.executeQuery(query3);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
            ex.printStackTrace();
        }
        return user;
    }
    
    public boolean enterAUser(User user){
        boolean entered = false;
        try {
            statement = dbCon.getCon().createStatement();
            statement2 = dbCon.getCon().createStatement();
            
            String query2 = "SELECT * FROM user";
            
            resultSet = statement2.executeQuery(query2);
            if(!resultSet.next()){
                user.setPriority("Admin");
            }
            
            String query = "INSERT INTO `ear_trainer`.`user` VALUES ('" + user.getUserName() + "', '" + user.getPassword() + "', '" + user.getPriority() + "', '" + user.getName() +"')";
            statement.executeUpdate(query);
            System.out.println("Updated the 'user' table");
            entered = true;
            insertoFinalaccess();
        } catch (Exception e) {
            System.out.println("Error occured while updating the 'user' table....");
            e.printStackTrace();
        }
        finally{  
            return entered;
        }
    }

    public void updateUser() {
        User user = User.getUser(); // Shouldn't be changed 
        try {
            statement = dbCon.getCon().createStatement();
            
            String query2 = "SELECT * FROM bestScores WHERE userName = '" + user.getUserName() + "'";
            resultSet2 = statement.executeQuery(query2);
            System.out.println("Updating the user....");
            
            while(resultSet2.next()){
                double d = resultSet2.getDouble("finalScore");
                String category = resultSet2.getString("category") ;
                if(category.equals("Chords")){
                    user.setChordsBestscore(d);
                }
                if(category.equals("Scales")){
                    user.setScalesBestscore(d);
                }
                if(category.equals("Intervals")){
                    user.setIntervalsBestscore(d);
                }
                if(category.equals("Notes")){
                    user.setNotesBestscore(d);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
            ex.printStackTrace();
        }
    }
    
    public void insertoFinalaccess(){
        try {
            statement = dbCon.getCon().createStatement();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date da = new Date();
            String date = sdf.format(da);
            
            String query = "INSERT INTO finalaccessdate VALUES ('" + User.getUser().getUserName() + "', '" + date + "')";

            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public boolean isUsernameTaken(String username){
        try {
            statement = dbCon.getCon().createStatement();
            
            String query = "SELECT userName FROM user WHERE userName = '" + username + "'";
            resultSet = statement.executeQuery(query);
            
            if(!resultSet.next()){
                return false;
            }
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
