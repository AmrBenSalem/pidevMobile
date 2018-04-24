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
            cov.setType((String) f.get("type"));
            if (cov.getType().equals("o")){
                cov.setPlacedisponibles(ll.intValue());
            }
            Map user = (Map) f.get("user");
            ll = (Double) user.get("id");
            User c = new User(ll.intValue(),user.get("username").toString(), user.get("nom").toString(), user.get("prenom").toString());
            cov.setUser(c);
            cov.setDepart_id((String) f.get("departId"));
            cov.setDestination_id((String) f.get("destinationId"));
            cov.setDepart_lat((double) f.get("departLat"));
            cov.setDepart_lng((double) f.get("departLng"));
            cov.setCreated((String) f.get("created"));
            //cov.setDate((String) f.get("date"));
            CovList.add(cov);  
        }        
        return CovList;
    }
    
    public static ArrayList<CoVoiturageRequests> getListCoVoiturageRequests(Map m){
        ArrayList CovList = new ArrayList();
        
        if (m.get("covoituragerequests").equals("no")) return null;
        ArrayList d = (ArrayList) m.get("covoituragerequests");
        //Map f =  (Map) d.get(0);
        //System.out.println(d);

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            CoVoiturageRequests cov = new CoVoiturageRequests();
            Double ll = (Double) f.get("id");
            cov.setId(ll.intValue());
            Map idc = (Map) f.get("idc");
            
            
            CoVoiturage covv = new CoVoiturage();
            Double lll = (Double) idc.get("id");
            covv.setId(lll.intValue());
            covv.setDepart((String) idc.get("depart"));
            covv.setDestination((String) idc.get("destination"));
            covv.setOnetime((String) idc.get("onetime"));
            lll = (Double) idc.get("placedisponibles");
            covv.setType((String) idc.get("type"));
            if (covv.getType().equals("o")){
                covv.setPlacedisponibles(lll.intValue());
            }
            Map user2 = (Map) idc.get("user");
            lll = (Double) user2.get("id");
            User cc = new User(ll.intValue(),user2.get("username").toString(), user2.get("nom").toString(), user2.get("prenom").toString());
            covv.setUser(cc);
            covv.setDepart_id((String) idc.get("departId"));
            covv.setDestination_id((String) idc.get("destinationId"));
            covv.setDepart_lat((double) idc.get("departLat"));
            covv.setDepart_lng((double) idc.get("departLng"));
            //covv.setCreated((String) idc.get("created"));
            cov.setIdc(covv);
            
            
            //ll = (Double) idc.get("id");
            //cov.setIdc(ll.intValue());
            cov.setEtat((String) f.get("etat"));
            Map user = (Map) f.get("user");
            ll = (Double) user.get("id");
            User c = new User(ll.intValue(),user.get("username").toString(), user.get("nom").toString(), user.get("prenom").toString());
            cov.setUser(c);
            cov.setCreated((String) f.get("created"));
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
