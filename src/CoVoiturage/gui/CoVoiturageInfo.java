/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoVoiturage.gui;

import CoVoiturage.entities.CoVoiturage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Justpro
 */
public class CoVoiturageInfo {

    Form f;
    CoVoiturageInfo(Form back, CoVoiturage cov) {
        
        this.f = new Form("Les offres",new BoxLayout(BoxLayout.Y_AXIS));
        this.f.show();
        Toolbar tb = this.f.getToolbar();
        tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e->{
            back.showBack();
        });
    }
    
    public Form getForm(){
        return this.f;
    }
    
}
