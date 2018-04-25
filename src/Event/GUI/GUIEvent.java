/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.GUI;

import CoVoiturage.services.CoVoiturageParser;
import Event.Entities.Avis;
import Event.Entities.Event;
import Event.Services.ServiceEvent;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.Map;
import util.WebService;

/**
 *
 * @author Liwa
 */
public class GUIEvent {

    Form f;
    SpanLabel lb;
    EncodedImage encoded;

    public GUIEvent() {

        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        ServiceEvent serviceevent = new ServiceEvent();
        Map x = WebService.getResponse("event/ej");
        Map x2 = WebService.getResponse("event/ej/2/show");
        Map x3 = WebService.getResponse("event/ej/avis/event/2/");
        System.out.println("--------------x--------------" + x);
        System.out.println("--------------x22222222222--------------" + x2);
        
        Event e = serviceevent.getEvent(x2);
        int idevent = e.getId();
        System.out.println(e.toString());
        
        
        
        
        ArrayList<Event> listevents = serviceevent.getListEvents(x);
        
        for (Event ev : listevents) {

            Container bouhom = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            //Image i = URLImage.createToStorage(encoded, ev.getPhoto(), "http://localhost/mobile/images/"+e.getPhoto());
            
            Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label tit = new Label("Titre : ");
            Label description = new Label("Description : ");
            Label l = new Label(ev.getTitre());
            Label l2 = new Label(ev.getDescription());
            //Label l3 = new Label(e.getDateDebut().toString());
            //Label l4 = new Label(e.getDateFin().toString());
            Label l5 = new Label(ev.getNb_max()+"");
            Label l6 = new Label(ev.getLieu());
            C1.add(tit);
            C1.add(l);
            C2.add(description);
            C2.add(l2);
            //C2.add(l2);
            //C2.add(l3);
            //C2.add(l4);
            //C2.add(l5);
            //C2.add(l6);

            //C1.add(i);

            bouhom.add(C1);
            bouhom.add(C2);

            C1.setLeadComponent(l);

            f.add(bouhom);

        }

    }

    public Form getForm() {
        return this.f;
    }
}
