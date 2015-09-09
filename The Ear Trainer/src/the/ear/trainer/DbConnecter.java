/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;
import java.sql.*;

/**
 *
 * @author Pravinda Perera
 */
//public class DbConnecter {
    



public class DbConnecter {
    private static DbConnecter singleton = null;
    private Connection connection;
    //private Statement st;
    //private ResultSet rs;
    
  public synchronized static DbConnecter getConnection(){
        if(singleton == null){
            singleton = new DbConnecter();
            return singleton;
        }
        else{
            return singleton;
        }
    }
    
    private DbConnecter(){
        
        try{
          //  Class.forName("com.mysql.jdbc.Driver");
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ear_trainer","root","");
           // st = con.createStatement();
            
            
        }
        catch(Exception ex){
            System.out.println("DbConnecter Error "+ex);
        }
    }
    
   

    Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 

    public Connection getCon() {
        return connection;
    }

    public void setCon(Connection con) {
        this.connection = con;
    }


    
}

