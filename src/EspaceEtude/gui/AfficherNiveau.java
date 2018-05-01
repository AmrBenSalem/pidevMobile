/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.gui;

import EspaceEtude.entities.Niveau;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author oussema
 */
public class AfficherNiveau {
    Form f;

    public AfficherNiveau() {
        this.f=new Form("Niveau", new BoxLayout(BoxLayout.Y_AXIS));
        List<Niveau> a=Arrays.asList(Niveau.values());
        for(Niveau n : a){
            Container img=new Container(new BoxLayout(BoxLayout.X_AXIS));
            ScaleImageLabel folder;
            try {
                folder = new ScaleImageLabel(Image.createImage("/folder.JPG"));
                img.add(folder);
            } catch (IOException ex) {
                //Logger.getLogger(AfficherNiveau.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Container niveauName=new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label l =new Label(n.name());
            Container niveau=new Container(new BoxLayout(BoxLayout.X_AXIS));
            niveau.add(img);
            niveau.add(l);
            Button myBtn = new Button(); 
            myBtn.addActionListener(e -> {
                if(n.name().equals("premiereA"))
                    new AfficheSection(f,"1A");
                if(n.name().equals("deuxiemeA"))
                    new AfficheSection(f,"2A");
                if(n.name().equals("deuxiemeB"))
                    new AfficheSection(f,"2B");
                if(n.name().equals("troisiemeA"))
                    new AfficheSection(f,"3A");
                if(n.name().equals("troisiemeB"))
                    new AfficheSection(f,"3B");
                if(n.name().equals("quatriemeA"))
                    new AfficheSection(f,"4A");
                if(n.name().equals("quatriemeB"))
                    new AfficheSection(f,"4B");
                if(n.name().equals("cinquiemeA"))
                    new AfficheSection(f,"5A");
                if(n.name().equals("cinquiemeB"))
                    new AfficheSection(f,"5B");
   
            });
            niveau.setLeadComponent(myBtn);
            this.f.add(niveau);
            this.f.show();
        }
    }
    
}
