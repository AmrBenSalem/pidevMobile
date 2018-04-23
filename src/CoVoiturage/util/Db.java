/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoVoiturage.util;

import CoVoiturage.entities.User;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import java.io.IOException;

/**
 *
 * @author Justpro
 */
public class Db {

    Database db;
    private static Db d;
    boolean created =false;

    private Db() {
        /*try {
            Database.delete("db");
        } catch (IOException ex) {
            //Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        created = Database.exists("db");
        try {
            
            db = Database.openOrCreate("db");
           if(created == false) 
           {
               db.execute("create table user (id INTEGER, idd INTEGER, username TEXT);");
           }
        } catch (IOException ex) {

        }
    }
    
    public static Db getInstance(){
        if (d==null) d = new Db();
        return d;
    }

    public void insertUser(User user) {
        
        try {
            db.execute("delete from user;");
            db.execute("insert into user (idd , username) values ('" + user.getId() + "','" + user.getUserName() + "');");
        } catch (IOException ex) {
            //Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUser() {
        try {
            Cursor c = db.executeQuery("select * from user");
            while (c.next()) {
                Row r = c.getRow();
                int idd = r.getInteger(1);
                String username = r.getString(2);
                User u = new User();
                u.setId(idd);
                u.setUserName(username);
                //c.close();
                return u;
            }

        } catch (IOException ex) {
        }
        return null;
    }

}
