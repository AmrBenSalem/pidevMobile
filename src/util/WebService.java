/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

/**
 *
 * @author Justpro
 */
public class WebService {
    static Map h;
    
    public static Map<String, Object> getResponse(String url){
        
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl(url);
        r.setPost(false);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
            try {
                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                h= p.parseJSON(targetReader);
                /*h=p.parseJSON(targetReader);
                ArrayList l = (ArrayList) h.get("covoiturage");
                for (int i=0;i<l.size();i++){
                    //System.out.println(i+"aaa"+l.get(i));
                    System.out.println(new HashMap((Map) l.get(i)).get("user"));
                }*/
                
            } catch (IOException ex) {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return h; 
    }
    
    /*ConnectionRequest r = conn();
        r.addResponseListener((evt) -> {
            try {
                Map h;
                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                h=p.parseJSON(targetReader);
                ArrayList l = (ArrayList) h.get("covoiturage");
                for (int i=0;i<l.size();i++){
                    //System.out.println(i+"aaa"+l.get(i));
                    System.out.println(new HashMap((Map) l.get(i)).get("user"));
                }
                
            } catch (IOException ex) {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });*/
    
    
    
}
