/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objet.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import Objet.services.UserService;
import Objet.entities.FosUser;
import java.io.IOException;
import java.util.ArrayList;

import utils.NavigatorData;

/**
 *
 * @author Ayedi
 */
public class LoginForm {
     
    Form f;
    Button Login;
    TextField User;
    TextField Pass;
    Label username;
    Label password;
    boolean b = true;
    public LoginForm() {
        
        f = new Form("CONNEXION",BoxLayout.y());
        User=new TextField();
        Pass=new TextField();
        username=new Label(" Nom d'utilisateur");
        password=new Label("Mot de passe ");
        Login=new Button("Se Connecter");
       
          UserService UserSer=new UserService();
            ArrayList<FosUser> lisuser=UserSer.getlistUser();
          
                 Login.addActionListener(new ActionListener() {
               @Override
                  public void actionPerformed(ActionEvent evt) {
                  {
                      for (FosUser user : lisuser){
                          if (user.getUsername().equals(User.getText())){
                              if (user.getPassword().equals(Pass.getText())){
                              
                                   
                                      b = false;
                                      NavigatorData.getInstance().connectedUser = user;
                                      Dialog.show("Hello"," Content de vous revoir "+User.getText()+ "!", "ok",null);
                                     // AffichageP h=new AffichageP();
                                     // h.getF().show();
                                     AffichObjPerd a = new AffichObjPerd();
                                     
                                 
                              }
                          }
                      }
                     
                      if (b) {
                           Dialog.show("Hello"," Sorry  !", "ok",null);
                          
                      }
                     
                   }
                   
                     }
                   
                         }); 
            
          
                    
            
        f.add(username);
        f.add(User);
        f.add(password);
        f.add(Pass);
        f.add(Login);
       f.getToolbar().addCommandToLeftBar("<-back", null, (ev) -> {
            
                 AffichObjPerd a = new AffichObjPerd();
                //AffichageP h = new AffichageP();
                //  h.getF().show();
           
            
        });
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
