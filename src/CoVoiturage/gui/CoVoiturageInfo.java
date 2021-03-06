/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoVoiturage.gui;

import CoVoiturage.entities.CoVoiturage;
import CoVoiturage.entities.CoVoiturageDays;
import CoVoiturage.services.CoVoiturageParser;
import CoVoiturage.util.Db;
import CoVoiturage.util.WebService;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.MyApplication;
import java.util.Map;

/**
 *
 * @author Justpro
 */
public class CoVoiturageInfo {

    Form f;

    CoVoiturageInfo(Form back, CoVoiturage cov) {
        this.f = new Form("Informations", new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tb = this.f.getToolbar();
        tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
            back.showBack();
        });
        Container departLine = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container destinationLine = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container placesLine = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container quotidiennementLine = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container dateLine = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label depart = new Label("Depart : ");
        depart.getAllStyles().setFgColor(0xef6262);
        departLine.add(depart);
        Label dep = new Label(cov.getDepart());
        dep.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        departLine.add(dep);
        c.add(departLine);

        Label destination = new Label("Destination : ");
        destination.getAllStyles().setFgColor(0xef6262);
        destinationLine.add(destination);
        Label dest = new Label(cov.getDestination());
        dest.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        destinationLine.add(dest);
        c.add(destinationLine);

        Label quotidiennement = new Label("Quotidiennement : ");
        quotidiennement.getAllStyles().setFgColor(0xef6262);
        quotidiennementLine.add(quotidiennement);
        if (cov.getOnetime().equals("off")) {
            Label quot = new Label("Non");
            quot.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            quotidiennementLine.add(quot);
            Label date = new Label("Date : ");
            date.getAllStyles().setFgColor(0xef6262);
            dateLine.add(date);
            Map m = WebService.getResponse("covoiturage/api/offres/date?id=" + cov.getId());
            Label dd = new Label((String) m.get("covoituragedate"));
            dd.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            dateLine.add(dd);
        } else {
            Label quot = new Label("Oui");
            quot.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            quotidiennementLine.add(quot);
            Label date = new Label("Les jours : ");
            date.getAllStyles().setFgColor(0xef6262);
            dateLine.add(date);
            Map x = WebService.getResponse("covoiturage/api/days?idc=" + cov.getId());
            CoVoiturageDays cod = CoVoiturageParser.getListCoVoiturageDays(x);
            String days = "";
            if (cod.getLundi() != null) {
                days = days + " Lundi";
            }
            if (cod.getMardi() != null) {
                days = days + " Mardi";
            }
            if (cod.getMercredi() != null) {
                days = days + " Mercredi";
            }
            if (cod.getJeudi() != null) {
                days = days + " Jeudi";
            }
            if (cod.getVendredi() != null) {
                days = days + " Vendredi";
            }
            if (cod.getSamedi() != null) {
                days = days + " Samedi";
            }
            Label dayz = new Label(days);
            dayz.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            dateLine.add(dayz);
        }

        c.add(quotidiennementLine);
        c.add(dateLine);

        if (cov.getType().equals("o")) {
            Label places = new Label("Places disponibles: ");
            places.getAllStyles().setFgColor(0xef6262);
            placesLine.add(places);
            Label placess = new Label("" + cov.getPlacedisponibles());
            placess.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            placesLine.add(placess);
            c.add(placesLine);
        }
        Db d = Db.getInstance();
        if (cov.getUser().getId() == d.getUser().getId()) {
            Button delete = new Button("Supprimer");
            delete.addActionListener((evt) -> {
                WebService.getResponse("covoiturage/api/offres/delete?id=" + cov.getId());
                CoVoiturageView c1 = new CoVoiturageView();
                CoVoiturageOffres c2 = new CoVoiturageOffres(c1.getForm(), cov.getType());
            });
            c.add(delete);
        } else {
            Map m = WebService.getResponse("covoiturage/api/requests/own?id=" + d.getUser().getId());
            if (m.get("covoituragerequests").equals("no")) {
                Button request = new Button("Request");
                request.addActionListener((evt) -> {
                    WebService.getResponse("covoiturage/api/requests/add?id=" + cov.getId() + "&iduser=" + d.getUser().getId());
                    CoVoiturageInfo coi = new CoVoiturageInfo(back, cov);
                });
                c.add(request);
            } else {
                c.add("Vous avez déjà une demande");
            }
        }

        BrowserComponent browser = new BrowserComponent();
        browser.setScrollVisible(false);
        browser.setURL("http://"+WebService.ip+"/pidev2/web/app_dev.php/covoiturage/api/map?departid=" + cov.getDepart_id() + "&destinationid=" + cov.getDestination_id());
        browser.setPreferredH(500);
        this.f.add(c);
        this.f.add(browser);

        this.f.show();
        
    }

    public Form getForm() {
        return this.f;
    }

}
