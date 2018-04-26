/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.gui;

import EspaceEtude.entities.Matiere;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author oussema
 */
class AfficherMatiere {
    Form f;
    public AfficherMatiere(Form back ,int sectionId) {
        
        f=new Form("Matiere", new BoxLayout(BoxLayout.Y_AXIS));
        
        ArrayList<Matiere> matiereList=EspaceEtude.Service.EspaceEtudeService.getAllMatiere(sectionId);
        //Button myBtn = new Button(); 
        for(Matiere s : matiereList){
            Container matiere=new Container(new BoxLayout(BoxLayout.X_AXIS));
            ScaleImageLabel folder;
            try {
                folder = new ScaleImageLabel(Image.createImage("/folder.JPG"));
                matiere.add(folder);
            } catch (IOException ex) {
                //Logger.getLogger(AfficherNiveau.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label l =new Label(s.getLibelle());
            matiere.add(l);
            this.f.add(matiere);
            Button myBtn = new Button();
            myBtn.addActionListener(e -> {
                new AfficherDocuments(f,s.getId());
            });
            matiere.setLeadComponent(myBtn);
            
            }
        
       
        
            
            this.f.show(); 
         Toolbar tb = this.f.getToolbar();
        tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
            back.showBack();
        });
    }
    
}
