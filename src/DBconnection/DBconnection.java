/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sachithra
 */
public class DBconnection {
    private  Connection connection;
    private static DBconnection dBconnection;
    private DBconnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "nanayakkara");
    }
      public static DBconnection getInstance() throws ClassNotFoundException, SQLException{
        if(dBconnection==null){
            dBconnection=new DBconnection();
        }
        return dBconnection;
    }
    public Connection getConnection(){
        return connection;
    }
}

