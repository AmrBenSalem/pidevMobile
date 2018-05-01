package com.mycompany.myapp;

import CoVoiturage.entities.User;
import CoVoiturage.gui.CoVoiturageView;
import CoVoiturage.services.CoVoiturageParser;
import CoVoiturage.util.Db;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import CoVoiturage.util.WebService;
import Event.GUI.GUIEvent;
import Objet.GUI.AffichObjPerd;
import Objet.GUI.AffichObjTrouv;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BorderLayout;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename
 * One</a> for the purpose of building native mobile applications using Java.
 */
public class MyApplication {

    Map h;
    private Form hi;
    private Resources theme;
     private Container mainContainer;
 

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);
        
       
        
        // Pro only feature
      //  Log.bindCrashProtection(true);
    }

    public void start() {
        if (hi != null) {
            hi.show();
            return;
        }
        hi = new Form("Hi World");
     
        Toolbar tb = hi.getToolbar();
        

        tb.addMaterialCommandToSideMenu("Objets Perdus", FontImage.MATERIAL_HOME, e -> {AffichObjPerd a = new AffichObjPerd();
        
        });
        tb.addMaterialCommandToSideMenu("Objets Trouvés", FontImage.MATERIAL_WEB, e -> {  AffichObjTrouv a = new AffichObjTrouv();
        });
       

        hi.addComponent(new Label("Hi World"));
        hi.show();
       //AffichObjTrouv a =  new AffichObjTrouv();
        /*  mainContainer = new Container();
        mainContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        mainForm = new Form();
        mainForm.setLayout(new BorderLayout());
        mainForm.add(BorderLayout.CENTER, mainContainer);
        cmd1= new Command("afficher les objets perdus");
        cmd2= new Command("afficher les objets trouvés");
        
        mainForm.addCommandListener(ev -> {
            if (ev.getCommand() == cmd1) {
                AffichObjPerd a = new AffichObjPerd();
            }
            if (ev.getCommand() == cmd2) {
                AffichObjTrouv a = new AffichObjTrouv();
            }
        });
                       
        mainForm.setTitle("Les objets");
        mainForm.show();
      /*  Form hi = new Form("Hi World", BoxLayout.y());
        hi.add(new Label("Hi World"));
        User user = new User();
        user.setId(5);
        user.setUserName("amr");
        Db.getInstance().insertUser(user);*/


    }

    public void stop() {
        hi = getCurrentForm();
        if (hi instanceof Dialog) {
            ((Dialog) hi).dispose();
            hi = getCurrentForm();
        }
    }

    public void destroy() {
    }

}
