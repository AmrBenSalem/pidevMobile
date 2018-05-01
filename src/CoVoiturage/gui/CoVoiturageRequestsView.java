/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoVoiturage.gui;

import CoVoiturage.entities.CoVoiturage;
import CoVoiturage.entities.CoVoiturageRequests;
import CoVoiturage.services.CoVoiturageParser;
import CoVoiturage.util.Db;
import CoVoiturage.util.WebService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.LEFT;
import static com.codename1.ui.CN.RIGHT;
import com.codename1.ui.Container;
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
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Justpro
 */
public class CoVoiturageRequestsView {

    Form f;

    public CoVoiturageRequestsView(Form back, String type) {

        this.f = new Form("My requests", new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tb = this.f.getToolbar();
        tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
            back.showBack();
        });
        Db d = Db.getInstance();
        Map x = WebService.getResponse("covoiturage/api/requests/own?id=" + d.getUser().getId());
        ArrayList listCov = CoVoiturageParser.getListCoVoiturageRequests(x);
        //System.out.println(listCov);
        Button suggestions = null;
        if (type.equals("o")) {
            suggestions = new Button("Mes offres");
        } else {
            suggestions = new Button("Mes demandes");
        }
        suggestions.addActionListener((evt) -> {
            CoVoiturageOwn cow = new CoVoiturageOwn(back, type);
        });
        this.f.add(suggestions);

        if (listCov == null){
            this.f.add("Vous n'avez pas de requests");
        }
        else {
        for (Object covv : listCov) {

            CoVoiturageRequests cov = (CoVoiturageRequests) covv;
            Container oneLine = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container departLine = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container destinationLine = new Container(new BoxLayout(BoxLayout.X_AXIS));

            Label depart = new Label("Depart : ");
            depart.getAllStyles().setFgColor(0xef6262);
            departLine.add(depart);
            Label dep = new Label(cov.getIdc().getDepart());
            dep.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            departLine.add(dep);
            oneLine.add(departLine);

            Label destination = new Label("Destination : ");
            destination.getAllStyles().setFgColor(0xef6262);
            destinationLine.add(destination);
            Label dest = new Label(cov.getIdc().getDestination());
            dest.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            destinationLine.add(dest);
            oneLine.add(destinationLine);
            //oneLine.add(new Label(cov.getUser().getUserName()));

            Container bottom = new Container();
            bottom.setLayout(new LayeredLayout());
            Container left = new Container(new FlowLayout(LEFT));
            Container right = new Container(new FlowLayout(RIGHT));
            
            Label by = new Label(cov.getCreated() + ", by " + cov.getIdc().getUser().getUserName());
            by.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_SMALL));
            left.add(by);
            

            Container bottom2 = new Container();
            bottom2.setLayout(new LayeredLayout());
            Container left2 = new Container(new FlowLayout(LEFT));
            Container right2 = new Container(new FlowLayout(RIGHT));
            Label et = new Label();
            et.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            if (cov.getEtat().equals("a")) {
                et.setText("En attente");
            }
            if (cov.getEtat().equals("r")) {
                et.setText("Refusé");
                WebService.getResponse("covoiturage/api/requests/delete?id=" + cov.getId());
            }
            if (cov.getEtat().equals("c")) {
                et.setText("Accepté");
            }
            left2.add(et);
            Button info = null;
            try {
                info = new Button(Image.createImage("/information.jpg"));
                info.getAllStyles().setBorder(Border.createEmpty());
            } catch (IOException ex) {
                //Logger.getLogger(CoVoiturageOffres.class.getName()).log(Level.SEVERE, null, ex);
            }
            right.add(info);
            if (!cov.getEtat().equals("r")) {
                Button annuler = new Button("Annuler");
                annuler.addActionListener((evt) -> {
                    WebService.getResponse("covoiturage/api/requests/delete?id=" + cov.getId());
                    CoVoiturageRequestsView cow = new CoVoiturageRequestsView(back, type);
                });
                right2.add(annuler);
            }
            bottom.add(left);
            bottom.add(right);
            bottom2.add(left2);
            bottom2.add(right2);
            oneLine.add(bottom);
            oneLine.add(bottom2);

            info.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    CoVoiturageInfo covi = new CoVoiturageInfo(f, cov.getIdc());
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
        
    }

    public Form getF() {
        return f;
    }
}
