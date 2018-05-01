/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Colocation.gui;

import CoVoiturage.gui.CoVoiturageOffres;
import CoVoiturage.gui.CoVoiturageOwn;
import com.codename1.components.ScaleImageLabel;
import com.codename1.gif.GifImage;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;
import java.io.InputStream;



/**
 *
 * @author douha
 */
public class ColocationMenu {
    
     Form f;
      



    public ColocationMenu() {
        this.f = new Form("Colocation",new FlowLayout(CENTER));
        //this.f.add(new Label("ss"));
        this.f.setWidth(600);
        Tabs menu = new Tabs();

        Container object = new Container();
        object.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Container event = new Container();
        event.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Container colocation = new Container();
        colocation.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Container etudes = new Container();
        etudes.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        object.add(new Label("1"));
        
        
      
        Container covoiturage = new Container();
        //covoiturage.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        covoiturage.add(new Label("2"));
        
        
        //InputStream i = Display.getInstance().getResourceAsStream(getClass(),"/ggt_1409378296.gif");
    
        //GifImage  image = GifImage.decode((i),1177720);
       // image.scale(1500,1000);
      //  Label gif =new Label(image);
        
      
        
        try {
            
             InputStream i = Display.getInstance().getResourceAsStream(getClass(),"/chambre.gif");
          GifImage  image = GifImage.decode((i),1177720);
        image.scale(300,300);
        Label gif =new Label(image);
         //   ScaleImageLabel img = new ScaleImageLabel(Image.createImage("/offres.jpg"));
           this.getForm().add(image);
        } catch (IOException ex) {
            //Logger.getLogger(CoVoiturageView.class.getName()).log(Level.SEVERE, null, ex);
        }
        Button btnOffre = new Button("Ajouter offre");
        btnOffre.getAllStyles().setFgColor(0xffc870);
        btnOffre.addActionListener((evt) -> {
            AddColocation covo = new AddColocation();
        });
        Button btnOwnOffre = new Button("les offres");
        btnOwnOffre.getAllStyles().setFgColor(0xef6262);
        btnOwnOffre.addActionListener((evt) -> {
            try {
                AffichCol c = new AffichCol();
            } catch (IOException ex) {
              
            }
        });
        this.getForm().add(btnOffre);
        this.getForm().add(btnOwnOffre);
        
        
        try {
           InputStream i = Display.getInstance().getResourceAsStream(getClass(),"/GIF_17.gif");
          GifImage  image = GifImage.decode((i),1177720);
        image.scale(300,300);
        Label gif =new Label(image);
         //   ScaleImageLabel img = new ScaleImageLabel(Image.createImage("/GIF_17.gif"));
           this.getForm().add(image);
        } catch (IOException ex) {
            //Logger.getLogger(CoVoiturageView.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        Button btnDemande = new Button("Ajouter demande");
        btnDemande.addActionListener((evt) -> {
            AjouterDemande covo = new AjouterDemande();
        });
        Button btnOwnDemande = new Button("les demandes");
        btnOwnDemande.addActionListener((evt) -> {
            CoVoiturageOwn covo = new CoVoiturageOwn(this.getForm(),"d");
        });
        btnDemande.getAllStyles().setFgColor(0x8cd3c7);
        btnOwnDemande.getAllStyles().setFgColor(0xef6262);
        this.getForm().add(btnDemande);
        this.getForm().add(btnOwnDemande);
        
        this.getForm().show();
    }
    
    
    
    public Form getForm(){
        return this.f;
    }
}
