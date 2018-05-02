/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objet.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import Objet.entities.FosUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ayedi
 */
public class UserService {
        public ArrayList<FosUser> getlistUser() {
      ArrayList<FosUser> listUser = new ArrayList<>(); 
        ConnectionRequest con ;
                con  = new ConnectionRequest();
         con.setUrl("http://localhost/Login.php");
       con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
               try {
                //renvoi une map avec clÃ© = root et valeur le reste
                Map<String, Object> users = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("user");
                System.out.println(users);
                for (Map<String, Object> obj : list) {
                    FosUser us = new FosUser();
                    int id = Integer.parseInt(obj.get("id").toString());
                    us.setId(id);
                    us.setUsername(obj.get("username").toString());
                    us.setPassword(obj.get("password").toString());
                    listUser.add(us);
                }
            } catch (IOException ex) {
            }
        });
      NetworkManager.getInstance().addToQueueAndWait(con);
        return listUser;
    }
        
}
