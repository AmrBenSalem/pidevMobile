/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoVoiturage.gui;

import CoVoiturage.entities.CoVoiturage;
import CoVoiturage.services.CoVoiturageParser;
import CoVoiturage.util.WebService;
import com.codename1.components.MultiButton;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.list.MultiList;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Justpro
 */
public class CoVoiturageOffres {

    Form f;
    
    CoVoiturageOffres(Form form) {
        this.f = new Form("CoVoiturage",new BoxLayout(BoxLayout.Y_AXIS));
        
        Container list = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        list.setScrollableY(true);
        
        Map x = WebService.getResponse("covoiturage/api/offres?type=o");
        ArrayList listCov = CoVoiturageParser.getListCoVoiturage(x);
        for (Object covv : listCov){
            MultiButton mb = new MultiButton();
            CoVoiturage cov = (CoVoiturage) covv;
            Container oneLine = new Container(new BoxLayout(BoxLayout.X_AXIS));
            mb.setTextLine1("Destination : "+cov.getDestination());
            mb.setTextLine2("Depart : "+cov.getDepart());
            mb.setTextLine3(cov.getUser().getUserName());
            mb.setTextLine4(cov.getUpdated());
            mb.setScrollableY(true);
            
            //mb.setEmblemPosition(cov.getDestination());
            list.add(mb);
        }
        this.f.add(list);
        this.f.show();
    }
    
     public Form getForm(){
        return this.f;
    }
    
}
