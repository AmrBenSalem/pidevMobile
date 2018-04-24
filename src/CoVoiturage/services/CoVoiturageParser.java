/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoVoiturage.services;

import CoVoiturage.entities.CoVoiturage;
import CoVoiturage.entities.CoVoiturageDays;
import CoVoiturage.entities.CoVoiturageRequests;
import CoVoiturage.entities.User;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Justpro
 */
public class CoVoiturageParser {
    
    public static ArrayList<CoVoiturage> getListCoVoiturage(Map m){
        ArrayList CovList = new ArrayList();
        
        ArrayList d = (ArrayList) m.get("covoiturage");
        //Map f =  (Map) d.get(0);
        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            CoVoiturage cov = new CoVoiturage();
            Double ll = (Double) f.get("id");
            cov.setId(ll.intValue());
            cov.setDepart((String) f.get("depart"));
            cov.setDestination((String) f.get("destination"));
            cov.setOnetime((String) f.get("onetime"));
            ll = (Double) f.get("placedisponibles");
            cov.setPlacedisponibles(ll.intValue());
            cov.setType((String) f.get("type"));
            Map user = (Map) f.get("user");
            ll = (Double) user.get("id");
            User c = new User(ll.intValue(),user.get("username").toString(), user.get("nom").toString(), user.get("prenom").toString());
            cov.setUser(c);
            cov.setDepart_id((String) f.get("departId"));
            cov.setDestination_id((String) f.get("destinationId"));
            cov.setDepart_lat((double) f.get("departLat"));
            cov.setDepart_lng((double) f.get("departLng"));
            CovList.add(cov);  
        }        
        return CovList;
    }
    
    public static ArrayList<CoVoiturageRequests> getListCoVoiturageRequests(Map m){
        ArrayList CovList = new ArrayList();
        
        ArrayList d = (ArrayList) m.get("covoituragerequests");
        //Map f =  (Map) d.get(0);
        //System.out.println(d);

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            CoVoiturageRequests cov = new CoVoiturageRequests();
            Double ll = (Double) f.get("id");
            cov.setId(ll.intValue());
            Map idc = (Map) f.get("idc");
            ll = (Double) idc.get("id");
            cov.setIdc(ll.intValue());
            cov.setEtat((String) f.get("etat"));
            Map user = (Map) f.get("user");
            ll = (Double) user.get("id");
            cov.setUser(ll.intValue());
            CovList.add(cov);  
        }        
        return CovList;
    }
    
    public static CoVoiturageDays getListCoVoiturageDays(Map m){
        Map f = (Map) m.get("covoituragedays");
        //Map f =  (Map) d.get(0);
        //System.out.println(d);
        //System.out.println(d);

            CoVoiturageDays cov = new CoVoiturageDays();
            Double ll = (Double) f.get("id");
            cov.setId(ll.intValue());
            Map idc = (Map) f.get("idc");
            ll = (Double) idc.get("id");
            cov.setIdc(ll.intValue());
            cov.setLundi((String) f.get("lundi"));
            cov.setMardi((String) f.get("mardi"));
            cov.setMercredi((String) f.get("mercredi"));
            cov.setJeudi((String) f.get("jeudi"));
            cov.setVendredi((String) f.get("vendredi"));
            cov.setSamedi((String) f.get("samedi"));  
        return cov;
    }
}
