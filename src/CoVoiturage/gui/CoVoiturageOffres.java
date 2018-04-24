/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoVoiturage.gui;

import CoVoiturage.entities.CoVoiturage;
import CoVoiturage.services.CoVoiturageParser;
import CoVoiturage.util.WebService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.LEFT;
import static com.codename1.ui.CN.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Justpro
 */
public class CoVoiturageOffres {

    Form f;

    CoVoiturageOffres(Form back) {

        this.f = new Form("Les offres", new BoxLayout(BoxLayout.Y_AXIS));

//        Container list = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//        list.setScrollableY(true);

        

        Map x = WebService.getResponse("covoiturage/api/offres?type=o");
        ArrayList listCov = CoVoiturageParser.getListCoVoiturage(x);
        //System.out.println(listCov);
        Button suggestions = new Button("Voir nos suggestions");
        suggestions.addActionListener((evt) -> {
            CoVoiturageSuggestions cos = new CoVoiturageSuggestions(this.f,listCov);
        });
        this.f.add(suggestions);
        
        for (Object covv : listCov) {

            CoVoiturage cov = (CoVoiturage) covv;
            Container oneLine = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container departLine = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container destinationLine = new Container(new BoxLayout(BoxLayout.X_AXIS));

            Label depart = new Label("Depart : ");
            depart.getAllStyles().setFgColor(0xef6262);
            departLine.add(depart);
            departLine.add(cov.getDepart());
            oneLine.add(departLine);

            Label destination = new Label("Destination : ");
            destination.getAllStyles().setFgColor(0xef6262);
            destinationLine.add(destination);
            destinationLine.add(cov.getDestination());
            oneLine.add(destinationLine);
            //oneLine.add(new Label(cov.getUser().getUserName()));

            Container bottom = new Container();
            bottom.setLayout(new LayeredLayout());
            Container left = new Container(new FlowLayout(LEFT));
            Container right = new Container(new FlowLayout(RIGHT));
            //Map m = WebService.getResponse("covoiturage/api/offres/ago?id=" + cov.getId());
            left.add(new Label(cov.getCreated() + ", by " + cov.getUser().getUserName()));

            Button info = null;
            try {
                info = new Button(Image.createImage("/information.jpg"));

            } catch (IOException ex) {
                //Logger.getLogger(CoVoiturageOffres.class.getName()).log(Level.SEVERE, null, ex);
            }
            right.add(info);
            bottom.add(left);
            bottom.add(right);
            oneLine.add(bottom);

            info.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    CoVoiturageInfo covi = new CoVoiturageInfo(f, cov);
                }

            });

            try {
                ScaleImageLabel img = new ScaleImageLabel(Image.createImage("/Separator.png"));
                oneLine.add(img);
            } catch (IOException ex) {
                //Logger.getLogger(CoVoiturageView.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.f.add(oneLine);

        }

        this.f.show();
        Toolbar tb = this.f.getToolbar();
        tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
            back.showBack();
        });
    }

    public Form getForm() {
        return this.f;
    }

}
