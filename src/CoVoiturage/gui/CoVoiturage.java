/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoVoiturage.gui;

import CoVoiturage.services.CoVoiturageParser;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BoxLayout;
import java.util.Map;
import javafx.scene.control.Tab;
import util.WebService;

/**
 *
 * @author Justpro
 */
public class CoVoiturage {
    
    Form f;
    
    public CoVoiturage(){
        this.f = new Form("aa");
        //this.f.add(new Label("ss"));
        this.f.setWidth(600);
        Tabs menu = new Tabs();

        Container object = new Container();
        object.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Container event = new Container();
        event.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Container colocation = new Container();
        colocation.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Container etudes = new Container();
        etudes.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        object.add(new Label("1"));
        Container covoiturage = new Container();
        covoiturage.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        covoiturage.add(new Label("2"));
        menu.addTab("Objects",object);
        menu.addTab("CoVoiturage",covoiturage);
        menu.addTab("coco", colocation);
        menu.addTab("coco", event);
        menu.addTab("coco", etudes);
        menu.setWidth(600);
        //menu.add(object);
        //menu.add(covoiturage);
        this.f.add(menu);
        Map x = WebService.getResponse("covoiturage/api/days?idc=123");
        //System.out.println(x.get("covoiturage"));
        System.out.println(CoVoiturageParser.getListCoVoiturageDays(x));
        this.getForm().show();
        
    }
    
    public Form getForm(){
        return this.f;
    }
}
