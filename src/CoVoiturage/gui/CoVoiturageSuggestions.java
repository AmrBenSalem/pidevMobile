/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoVoiturage.gui;

import CoVoiturage.entities.CoVoiturage;
import CoVoiturage.entities.CoVoiturageSuggestion;
import CoVoiturage.util.Db;
import CoVoiturage.util.WebService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.LEFT;
import static com.codename1.ui.CN.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
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
import com.codename1.ui.plaf.Border;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 *
 * @author Justpro
 */
public class CoVoiturageSuggestions {

    Form f;

    CoVoiturageSuggestions(Form back, ArrayList listCov) {
        this.f = new Form("Nos suggestions", new BoxLayout(BoxLayout.Y_AXIS));
        Location position = LocationManager.getLocationManager().getCurrentLocationSync();
        if (position.getStatus() != LocationManager.AVAILABLE){
            Dialog.show("Erreur", "L'application n'a pas pu vous localiser", "ok", "cancel");
        }
        ArrayList<CoVoiturageSuggestion> listOfSugg = new ArrayList<>();
        Db d = Db.getInstance();
        for (Object covv : listCov) {
            CoVoiturage cov = (CoVoiturage) covv;
            double lat = abs(abs(position.getLatitude()) - abs(cov.getDepart_lat()));
            double lng = abs(abs(position.getLongitude()) - abs(cov.getDepart_lng()));
            double value = lat + lng;
            listOfSugg.add(new CoVoiturageSuggestion(cov.getId(), d.getUser().getUserName(), cov.getUser().getId(), cov.getDepart(), cov.getDestination(), value, cov.getUpdated(), cov));
        }
        Collections.sort(listOfSugg, new CoVoiturageSuggestion());
        int j = 0;
        for (Object covv : listOfSugg) {
            CoVoiturageSuggestion cov = (CoVoiturageSuggestion) covv;
            if (j == 3) {
                break;
            }
            if (cov.getIdUser() != d.getUser().getId()) {
                j++;
                Container oneLine = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Container departLine = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container destinationLine = new Container(new BoxLayout(BoxLayout.X_AXIS));

                Label depart = new Label("Depart : ");
                depart.getAllStyles().setFgColor(0xef6262);
                departLine.add(depart);
                Label dep = new Label(cov.getDepart());
                dep.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                departLine.add(dep);
                oneLine.add(departLine);

                Label destination = new Label("Destination : ");
                destination.getAllStyles().setFgColor(0xef6262);
                destinationLine.add(destination);
                Label dest = new Label(cov.getDestination());
                dest.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                destinationLine.add(dest);
                oneLine.add(destinationLine);
                //oneLine.add(new Label(cov.getUser().getUserName()));

                Container bottom = new Container();
                bottom.setLayout(new LayeredLayout());
                Container left = new Container(new FlowLayout(LEFT));
                Container right = new Container(new FlowLayout(RIGHT));
                Map m = WebService.getResponse("covoiturage/api/offres/ago?id=" + cov.getId());
                Label by = new Label(m.get("covoiturageago") + ", by " + cov.getCov().getUser().getUserName());
                by.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_SMALL));
                left.add(by);
                Button info = null;
                try {
                    info = new Button(Image.createImage("/information.jpg"));
                    info.getAllStyles().setBorder(Border.createEmpty());
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
                        CoVoiturageInfo covi = new CoVoiturageInfo(f, cov.getCov());
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

        }
        this.f.show();
        Toolbar tb = this.f.getToolbar();
        tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
            back.showBack();
        });
    }

    public Form getF() {
        return f;
    }

}
