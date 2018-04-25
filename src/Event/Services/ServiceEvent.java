/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.Services;

import CoVoiturage.entities.CoVoiturageDays;
import CoVoiturage.entities.CoVoiturageRequests;
import Event.Entities.Avis;
import Event.Entities.Event;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Liwa
 */
public class ServiceEvent {
        static Map events;

    public  ArrayList<Event> getListEvents(Map m){
        ArrayList<Event> listEvents = new ArrayList<>();
        ArrayList d = (ArrayList) m.get("root");
        //Map f =  (Map) d.get(0);
        System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Event e = new Event();
            Double ll = (Double) f.get("id");
            e.setId(ll.intValue());
            //Double l2 = (Double) f.get("nb_max");
            //e.setNb_max(l2.intValue());
            
            e.setTitre((String) f.get("titre"));
            e.setDescription((String) f.get("description"));
            e.setCategorie((String) f.get("categorie"));
            e.setPhoto((String) f.get("photo"));
            e.setDateDebut((Date)f.get("dateDebut"));
            e.setDateFin((Date)f.get("dateFin"));
            //e.setCreatedAt((Date)f.get("createdAt"));
            e.setLieu((String) f.get("lieu"));
            
            e.setNb_max(((Double) f.get("nbMax")).intValue());
            listEvents.add(e);  
        }        
        return listEvents;
        
    }
    
    
    public  Event getEvent(Map m){
        Event e = new Event();
        //Map<String, Object> d = (Map<String, Object>) m.get("root");
        
            e.setId(((Double) m.get("id")).intValue());        
            e.setTitre((String) m.get("titre"));
            e.setDescription((String) m.get("description"));
            e.setCategorie((String) m.get("categorie"));
            e.setPhoto((String) m.get("photo"));
            e.setDateDebut((Date)m.get("dateDebut"));
            e.setDateFin((Date)m.get("dateFin"));
            //e.setCreatedAt((Date)f.get("createdAt"));
            e.setLieu((String) m.get("lieu"));
            e.setNb_max(((Double) m.get("nbMax")).intValue());
            e.setPhoto(m.get("photo").toString());
                
        return e;
        
    }
    
    public void participer (Map m){
        
    }
    
    public  ArrayList<Avis> getListAvis(Map m){
        ArrayList<Avis> listAvis= new ArrayList<>();
        ArrayList d = (ArrayList) m.get("root");
        //Map f =  (Map) d.get(0);
        System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Avis e = new Avis();
            
            //Double l2 = (Double) f.get("nb_max");
            //e.setNb_max(l2.intValue());
            
            e.setAvis(((Double) f.get("avis")).intValue());
            e.setIdevent(((Double) f.get("idevent")).intValue());
            e.setIduser(((Double) f.get("iduser")).intValue());
            
            listAvis.add(e);  
        }        
        return listAvis;
        
    }
    
}
