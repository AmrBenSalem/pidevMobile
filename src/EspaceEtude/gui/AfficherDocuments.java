/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.gui;

import EspaceEtude.entities.Documents;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 *
 * @author oussema
 */
class AfficherDocuments {

    Form f;
   
    public AfficherDocuments(Form back, int id) {
        
        f=new Form("Documents", new BoxLayout(BoxLayout.Y_AXIS));
        Button myBtn = new Button(); 
        ArrayList<Documents> documentList=EspaceEtude.Service.EspaceEtudeService.getAllDocuments(id);
        for(Documents s : documentList){
            Container doc=new Container(new BoxLayout(BoxLayout.X_AXIS));
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(75,75), true);
            URLImage image = URLImage.createToStorage(placeholder,s.getLibelle(),
            "http://localhost/EspritEntreAide/web"+s.getImage());
            Label l =new Label(s.getLibelle());
            doc.add(image);
            doc.add(l);
            f.add(doc);
            myBtn.addActionListener(e -> {
            FileSystemStorage fs = FileSystemStorage.getInstance();
            String fileName = fs.getAppHomePath() + s.getLibelle()+s.getTypeDocument() ;
                
            if(!fs.exists(fileName)) {
                Util.downloadUrlToFile("http://localhost/EspritEntreAide/web"+s.getPath(), fileName, true);
            }
            Display.getInstance().execute(fileName);
            });
            doc.setLeadComponent(myBtn);
            
            }
        
       
        
            
            this.f.show(); 
         Toolbar tb = this.f.getToolbar();
        tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
            back.showBack();
        });
    }
    
}
