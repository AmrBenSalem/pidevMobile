/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objet.services;

import Objet.entities.Interaction;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import Objet.entities.Objet;
import Objet.GUI.AffichObjTrouv;
/*import com.mycompany.myapp.AffichageEvenements;
import Entity.Participation;
import Entity.Session;
import Utils.Statics;*/
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bader
 */
public class ObjetService {

    static Map objets;
    private ConnectionRequest connectionRequest;

    public ArrayList<Objet> findAllObjets(Map m) {
        ArrayList<Objet> listobjet = new ArrayList<>();
        ArrayList d = (ArrayList) m.get("ObjTrouv");
        System.out.println("retour objsre" + d);
        //Map f =  (Map) d.get(0);
//        System.out.println("dddddddddddddd :::::::::"+d.size());

        for (int i = 0; i < d.size(); i++) {
            Map f = (Map) d.get(i);
            Objet o = new Objet();
            Double ll = (Double) f.get("id");
            o.setId(ll.intValue());
            //Double l2 = (Double) f.get("nb_max");
            //e.setNb_max(l2.intValue());

            o.setType((String) f.get("type"));
            o.setDescription((String) f.get("description"));
            //   e.setCategorie((String) f.get("categorie"));
            o.setPhoto((String) f.get("photo"));
            Date da = new Date(1000 * ((Double) ((Map<String, Object>) f.get("date")).get("timestamp")).longValue());
            o.setDate(da);
//            o.setDate ((Date)f.get("date"));
            // e.setDateFin((Date)f.get("dateFin"));
            //e.setCreatedAt((Date)f.get("createdAt"));
            o.setLieu((String) f.get("lieu"));
            String username = new String(((String) ((Map<String, Object>) f.get("user")).get("username")));
            o.setNature(username);

            //   e.setNb_max(((Double) f.get("nbMax")).intValue());
            listobjet.add(o);
        }
        return listobjet;

    }

    public ArrayList<Objet> findAllObjetslost(Map m) {
        ArrayList<Objet> listobjet = new ArrayList<>();
        ArrayList d = (ArrayList) m.get("ObjPerd");
        System.out.println("retour objsre" + d);
        //Map f =  (Map) d.get(0);
//        System.out.println("dddddddddddddd :::::::::"+d.size());

        for (int i = 0; i < d.size(); i++) {
            Map f = (Map) d.get(i);
            Objet o = new Objet();
            Double ll = (Double) f.get("id");
            o.setId(ll.intValue());
            //Double l2 = (Double) f.get("nb_max");
            //e.setNb_max(l2.intValue());

            o.setType((String) f.get("type"));
            o.setDescription((String) f.get("description"));
            //   e.setCategorie((String) f.get("categorie"));
            o.setPhoto((String) f.get("photo"));
            Date da = new Date(1000 * ((Double) ((Map<String, Object>) f.get("date")).get("timestamp")).longValue());
            o.setDate(da);
//            o.setDate ((Date)f.get("date"));
            // e.setDateFin((Date)f.get("dateFin"));
            //e.setCreatedAt((Date)f.get("createdAt"));
            o.setLieu((String) f.get("lieu"));
            String username = new String(((String) ((Map<String, Object>) f.get("user")).get("username")));
            o.setNature(username);

            //   e.setNb_max(((Double) f.get("nbMax")).intValue());
            listobjet.add(o);
        }
        return listobjet;

    }

    public Objet getObjet(Map m) {
        Objet o = new Objet();
        //Map<String, Object> d = (Map<String, Object>) m.get("root");

        o.setId(((Double) m.get("id")).intValue());
        o.setType((String) m.get("type"));
        o.setDescription((String) m.get("description"));
        // e.setCategorie((String) m.get("categorie"));
        o.setPhoto((String) m.get("photo"));
        //o.setDate ((Date)m.get("date"));
        //  e.setDateFin((Date)m.get("dateFin"));
        //e.setCreatedAt((Date)f.get("createdAt"));
        o.setLieu((String) m.get("lieu"));
        //  e.setNb_max(((Double) m.get("nbMax")).intValue());
        o.setPhoto(m.get("photo").toString());

        return o;

    }

    public void ajouterReclamationObjPerd(Objet o) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Succes", "Cet Objet Perdu a été trouvé par vous", "ok", null);

            }
        };

        connectionRequest.setUrl("http://localhost/pidev2/web/app_dev.php/reclamer/" + o.getId() + "/" + "1");
        NetworkManager.getInstance().addToQueue(connectionRequest);

    }

    public void ajouterReclamationObjTrouv(Objet o) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Succes", "Cet Objet Trouvé appartient à vous", "ok", null);
            }
        };

        connectionRequest.setUrl("http://localhost/pidev2/web/app_dev.php/reclamer/" + o.getId() + "/" + "1");
        NetworkManager.getInstance().addToQueue(connectionRequest);

    }

    public void SupprimerReclamation(Objet o) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Succes", "Cette Réclamation a été Supprimée", "ok", null);
            }
        };

        connectionRequest.setUrl("http://localhost/pidev2/web/app_dev.php/suppprec/" + o.getId());
        NetworkManager.getInstance().addToQueue(connectionRequest);

    }

}
