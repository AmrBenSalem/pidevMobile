/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoVoiturage.gui;

import CoVoiturage.entities.CoVoiturageRequests;
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
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;

/**
 *
 * @author Justpro
 */
public class CoVoiturageView {
    
    Form f;
    

    public CoVoiturageView() {
        this.f = new Form("CoVoiturage",new BoxLayout(BoxLayout.Y_AXIS));
        this.f.setToolbar(MyApplication.tb);
        
        try {
            ScaleImageLabel img1 = new ScaleImageLabel(Image.createImage("/SeparatorOffre.png"));
            ScaleImageLabel img2 = new ScaleImageLabel(Image.createImage("/OffresCov2.png"));
            this.getForm().add(img1);
            this.getForm().add(img2);
        } catch (IOException ex) {
            //Logger.getLogger(CoVoiturageView.class.getName()).log(Level.SEVERE, null, ex);
        }
        Button btnOffre = new Button("Tout les offres");
//        btnOffre.getAllStyles().setFgColor(0xffc870);
        btnOffre.addActionListener((evt) -> {
            CoVoiturageOffres covo = new CoVoiturageOffres(this.getForm(),"o");
        });
        Button btnOwnOffre = new Button("Vos offres");
        btnOwnOffre.getStyle().setBackgroundGradientStartColor(0x202020, true);
        
        
        btnOwnOffre.addActionListener((evt) -> {
            CoVoiturageOwn covo = new CoVoiturageOwn(this.getForm(),"o");
        });
        this.getForm().add(btnOffre);
        this.getForm().add(btnOwnOffre);
        
        
        try {
            ScaleImageLabel img3 = new ScaleImageLabel(Image.createImage("/SeparatorDemande.png"));
            ScaleImageLabel img4 = new ScaleImageLabel(Image.createImage("/DemandeCov2.png"));
            this.getForm().add(img3);
            this.getForm().add(img4);
        } catch (IOException ex) {
            //Logger.getLogger(CoVoiturageView.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        Button btnDemande = new Button("Tout les demandes");
        btnDemande.addActionListener((evt) -> {
            CoVoiturageOffres covo = new CoVoiturageOffres(this.getForm(),"d");
        });
        Button btnOwnDemande = new Button("Vos demandes");
        btnOwnDemande.addActionListener((evt) -> {
            CoVoiturageOwn covo = new CoVoiturageOwn(this.getForm(),"d");
        });
//        btnDemande.getAllStyles().setFgColor(0x8cd3c7);
//        btnDemande.getUnselectedStyle().setBgColor(0x8cd3c7);
//        btnOwnDemande.getAllStyles().setFgColor(0xef6262);
        this.getForm().add(btnDemande);
        this.getForm().add(btnOwnDemande);
        
        this.getForm().show();
    }
    
    public CoVoiturageView(int x){
        
    }
    
    public Form getForm(){
        return this.f;
    }
}
