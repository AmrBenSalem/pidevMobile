/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.util;

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
            url = "http://localhost/EspritEntreAide/web/app_dev.php/"+url;
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl(url);
        //r.setPost(false);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
                try {
                    //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
                    
                    JSONParser p = new JSONParser();
                    Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                    h= p.parseJSON(targetReader);
                } catch (IOException ex) {
                    //Logger.getLogger(WebService.class.getName()).log(Level.SEVERE, null, ex);
                }
 
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return h; 
    }
     
    
}
