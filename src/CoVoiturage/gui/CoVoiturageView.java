/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoVoiturage.gui;

import CoVoiturage.services.CoVoiturageParser;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import java.util.Map;
import CoVoiturage.util.WebService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;

/**
 *
 * @author Justpro
 */
public class CoVoiturageView {
    
    Form f;
    
    public CoVoiturageView(){
        this.f = new Form("aa");
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
        covoiturage.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        covoiturage.add(new Label("2"));
        menu.addTab("Objects",object);
        menu.addTab("CoVoiturage",covoiturage);
        menu.addTab("coco", colocation);
        menu.addTab("coco", event);
        menu.addTab("coco", etudes);
        menu.setWidth(600);
        //menu.add(object);
        //menu.add(covoiturage);
        this.f.add(menu);
        Map x = WebService.getResponse("covoiturage/api/days?idc=123");
        //System.out.println(x.get("covoiturage"));
        System.out.println(CoVoiturageParser.getListCoVoiturageDays(x));
        /*Toolbar tb = this.f.getToolbar();
            
            tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e->{
            
            home.showBack();
            
            });*/
        this.getForm().show();
        
    }

    public CoVoiturageView(Form back) {
        this.f = new Form("CoVoiturage",new FlowLayout(CENTER));
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
        
        /*menu.addTab("Objects",object);
        menu.addTab("CoVoiturageView",covoiturage);
        menu.addTab("coco", colocation);
        menu.addTab("coco", event);
        menu.addTab("coco", etudes);
        menu.setWidth(600);
        this.f.add(menu);*/
        
        
        
        Toolbar tb = this.f.getToolbar();
        tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e->{
            back.showBack();
        });
        
        try {
            ScaleImageLabel img = new ScaleImageLabel(Image.createImage("/OffresCov.png"));
            this.getForm().add(img);
        } catch (IOException ex) {
            //Logger.getLogger(CoVoiturageView.class.getName()).log(Level.SEVERE, null, ex);
        }
        Button btnOffre = new Button("Tout les offres");
        btnOffre.addActionListener((evt) -> {
            CoVoiturageOffres covo = new CoVoiturageOffres(this.getForm());
        });
        Button btnOwnOffre = new Button("Vos offres");
        btnOwnOffre.addActionListener((evt) -> {
            
        });
        this.getForm().add(btnOffre);
        this.getForm().add(btnOwnOffre);
        
        
        try {
            ScaleImageLabel img2 = new ScaleImageLabel(Image.createImage("/DemandesCov.png"));
            this.getForm().add(img2);
        } catch (IOException ex) {
            //Logger.getLogger(CoVoiturageView.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        Button btnDemande = new Button("Tout les demandes");
        btnDemande.addActionListener((evt) -> {
            
        });
        Button btnOwnDemande = new Button("Vos demandes");
        btnOwnDemande.addActionListener((evt) -> {
            
        });
        this.getForm().add(btnDemande);
        this.getForm().add(btnOwnDemande);
        
        this.getForm().show();
    }
    
    public Form getForm(){
        return this.f;
    }
}
