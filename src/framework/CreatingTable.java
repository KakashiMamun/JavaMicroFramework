/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 *
 * @author Mamun
 */


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreatingTable {

    private static Connection connect;
    private static Statement stmt;
    private static ResultSet result;
    public static String str="create table if not exists ";
    public static String spr = "table_name username varchar 6 password int 6 city varchar 6";
    public static int count = 0;
    public static int flag = 1;
    public static String temp = "";
    public static int check;
    public static StringTokenizer token = new StringTokenizer(spr);

    public static void main(String[ ] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
                stmt = (Statement) connect.createStatement();
                while (token.hasMoreTokens()) {

                    count++;
                    temp = token.nextToken();
                    if(count!=1 && (count%3)==1)
                    {
                        try
                        {
                            check = Integer.parseInt(temp);
                        }
                        catch(Exception e)
                        {
                            flag=0;
                        }
                    }
                 
                    if(count==1) 
                    {
                        str+=temp;
                        str+='(';
                    }
                    if(count%3==2)
                    {
                        str+=" "+temp+" ";
                    }
                    if(count%3== 0)
                    {
                        if(temp.equalsIgnoreCase("varchar")|| temp.equalsIgnoreCase("int"))
                        {
                            str+=" "+temp+" ";
                        }
                        else flag=0;
                    }
                    if(count!=1 && count%3==1)
                    {
                        str+='('+temp+')';
                        if(token.hasMoreTokens()) str+=',';
                    }
                   
                }
              
                str+=')';
              
                str+=';';
             
                if(flag==1)stmt.executeUpdate(str);
                else System.out.println("your program has an error");
                
                // stmt.executeUpdate(str);

            } catch (SQLException ex) {
                Logger.getLogger(IntroToDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IntroToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
//made by nath