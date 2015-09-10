/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * 
 */
public class Database {
    private Statement statement;
    private ResultSet resultSet;
    
//    public static void setDatabase(){
//        String[] ar = {"a143RegII231GUI", "R24213LI-UITMR-1231S", "MIT331TTRVSFHG", "GND_4212323", "IJSE_CMJD_19"};
//        DBCreator dBCreator;
//        dBCreator = new DBCreator("./src/databasefile/PharmacyDatabase.sql", DBCreator.WINDOWS, "root", "home", "localhost", ar);
//        dBCreator.createDatabase();
//    }

    public boolean getBackup(String dbName,String dbUserName, String dbPassword, String path) throws IOException, InterruptedException {

        String executeCmd = "C:\\xampp\\mysql\\bin\\mysqldump.exe -u" + dbUserName + " " + dbName + " -r " + path ;
        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        int processComplete = runtimeProcess.waitFor();

        if (processComplete == 0){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean restoreBackup(String dbName,String dbUserName, String dbPassword, String path) throws IOException, InterruptedException {
        
        String[] executeCmd = new String[]{"C:\\xampp\\mysql\\bin\\mysql.exe", "--user=" + dbUserName, "--password=" + dbPassword, dbName,"-e", " source "+path};
        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        int processComplete = runtimeProcess.waitFor();

        if (processComplete == 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void reset(){
        String query1, query2, query3, query4;
        
        query1 = "TRUNCATE user";
        query2 = "TRUNCATE report";
        query3 = "TRUNCATE finalaccessdate";
        
        try {
            statement = DbConnecter.getConnection().getCon().createStatement();
            statement.executeUpdate(query2);
            statement.executeUpdate(query1);
            statement.executeUpdate(query3);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
