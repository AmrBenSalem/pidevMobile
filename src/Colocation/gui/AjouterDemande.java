/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Colocation.gui;

import Colocation.entities.Colocation;
import Colocation.service.ColocationService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;

/**
 *
 * @author douha
 */
public class AjouterDemande {
    
     Form f;

    TextField titre;
    TextField loyer;
    TextField description;
    ComboBox type;
    ComboBox meuble;
    TextField ville;
   

    Button btnajout, btnaff;
    
    public AjouterDemande() {
        
        
        f = new Form("Ajout");
        
         f.getToolbar().addCommandToLeftBar("<- Retour", null, (ev) -> {
            ColocationMenu h = new ColocationMenu();
            h.getForm().show();

        });

        titre = new TextField();
        loyer = new TextField();
        description = new TextField();
        type = new ComboBox("Maison", "Studio");
        meuble = new ComboBox("Meublé", "Non Meublé", "Partiellement Meublé");
        ville = new TextField();
        btnajout = new Button("ajouter");
        btnaff = new Button("Affichage");

        titre.setHint("titre");
        f.add(titre);
        loyer.setHint("loyer");
        f.add(loyer);
        description.setHint("description");
        f.add(description);
        f.add(type);
        f.add(meuble);

        ville.setHint("Ville");
        f.add(ville);

        f.add(btnajout);
        f.add(btnaff);
        
          btnajout.addActionListener((e) -> {
            ColocationService ser = new ColocationService();
            float x = Float.parseFloat(loyer.getText());
            Colocation c = new Colocation(titre.getText(), x, description.getText(),type.getSelectedItem().toString(),
                    meuble.getSelectedItem().toString(),ville.getText());
            ser.ajoutColocation(c);

        });
        btnaff.addActionListener((e) -> {
      //      AffichColocation a = new AffichColocation();
          //  a.getF().show();
        });
      f.show();
    }

  //  AjouterDemande(Form form, String d) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
