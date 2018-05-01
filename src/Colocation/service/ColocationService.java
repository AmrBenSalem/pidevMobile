/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Colocation.service;

import Colocation.entities.Colocation;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author douha
 */
public class ColocationService {

    
    
    public void ajoutColocation(Colocation colo) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pidev2/web/app_dev.php/colocation/ajout/" + colo.getLoyer()+ "/"+ colo.getTitre()+ "/"  + colo.getDescription() + "/" + colo.getType()+ "/" + colo.getMeuble()+ "/" + colo.getVille()+ "/" + colo.getPhoto();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void delete (int id){
        
        ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/pidev2/web/app_dev.php/user/colocation/supprimer/" +id+ "");

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                       
                        System.out.println(s);
                        
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "evenement supprimé avec succès", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                            System.out.println(s);
                            
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
                
                
            }
            
           
      
    
   // /**************************************************************************************************/ 
    public ArrayList<Colocation> getList2() {
        ArrayList<Colocation> listcol = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev2/web/app_dev.php/colocation/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" + tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {
                        Colocation colo = new Colocation();
                        int id = Integer.parseInt(obj.get("id").toString());

                        colo.setId(id);
                        colo.setTitre(obj.get("titre").toString());
                         float loyer = Float.parseFloat(obj.get("loyer").toString());
                        colo.setDescription(obj.get("description").toString());
                        colo.setType(obj.get("type").toString());
                        colo.setMeuble(obj.get("meuble").toString());
                        colo.setVille(obj.get("ville").toString());
                        colo.setPhoto(obj.get("photo").toString());
                        colo.setLoyer(loyer);
                        
                        listcol.add(colo);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcol;
    }
/////////////////////////////////////////////////////////////////////
    
     public ArrayList<Colocation> getList3() {
        ArrayList<Colocation> listcol = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev2/web/app_dev.php/colocation/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {
                        Colocation colo = new Colocation();
                        float id = Float.parseFloat(obj.get("id").toString());

                        colo.setTitre(obj.get("titre").toString());
                         float loyer = Float.parseFloat(obj.get("loyer").toString());
                        colo.setDescription(obj.get("description").toString());
                        colo.setType(obj.get("type").toString());
                        colo.setMeuble(obj.get("meuble").toString());
                        colo.setVille(obj.get("ville").toString());
                        colo.setPhoto(obj.get("photo").toString());
                        colo.setLoyer(loyer);
                        
                        listcol.add(colo);

                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcol;
    }
     
     
     /*********************************************************************************/
     public ArrayList<Colocation> getByTorD(String s) {
        ArrayList<Colocation> am = new ArrayList<>();

        ConnectionRequest con = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) data.get("flatshare");
                    am.clear();

                    for (Map<String, Object> obj : list) {
                        Colocation me = new Colocation();
                        me.setId(Integer.parseInt(obj.get("id").toString()));
                        me.setTitre(obj.get("title").toString());
                        me.setDescription(obj.get("description").toString());
                        me.setType(obj.get("type").toString());
                        am.add(me);
                    }

                } catch (ClassCastException err) {
                    Log.e(err);
                }
            }

        };
        con.setUrl("" + s);
        con.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (am == null) {
            return null;
        } else {
            return am;
        }

    }
}
