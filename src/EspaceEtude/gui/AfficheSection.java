/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.gui;

import EspaceEtude.entities.Section;
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
class AfficheSection {
    
    Form f;
    public AfficheSection(Form back,String b) {
        this.f=new Form("Niveau", new BoxLayout(BoxLayout.Y_AXIS));
        
         
        ArrayList<Section> sectionList=EspaceEtude.Service.EspaceEtudeService.getAllSection(b);
        for(Section s : sectionList){
            Container section=new Container();
             Container img=new Container(new BoxLayout(BoxLayout.X_AXIS));
            ScaleImageLabel folder;
            try {
                folder = new ScaleImageLabel(Image.createImage("/folder.JPG"));
                img.add(folder);
            } catch (IOException ex) {
                //Logger.getLogger(AfficherNiveau.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label l =new Label(s.getLibelle());
            section.add(img);
            section.add(l);
            Button myBtn = new Button();
            myBtn.addActionListener(e -> {
                new AfficherMatiere(f,s.getId());
            });
             section.setLeadComponent(myBtn);
             this.f.add(section);
        
            
            }
        
             this.f.show();
            Toolbar tb = this.f.getToolbar();
            tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
            back.showBack();
            });
       
    }

    
}
