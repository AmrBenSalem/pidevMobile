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
public class CoVoiturageOwn {

    Form f;

    public CoVoiturageOwn(Form back, String type) {
        if (type.equals("o")) {
            this.f = new Form("Mes offres", new BoxLayout(BoxLayout.Y_AXIS));
        } else {
            this.f = new Form("Mes demandes", new BoxLayout(BoxLayout.Y_AXIS));
        }

//        Container list = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//        list.setScrollableY(true);
        Db d = Db.getInstance();
        Map x = WebService.getResponse("covoiturage/api/requests/own2?type=" + type + "&iduser=" + d.getUser().getId());
        ArrayList listCov = CoVoiturageParser.getListCoVoiturage(x);
        ArrayList listCor = CoVoiturageParser.getListCoVoiturageRequests(x);

        Button suggestions = new Button("My requests");
        suggestions.addActionListener((evt) -> {
            CoVoiturageRequestsView cos = new CoVoiturageRequestsView(this.f,type);
        });
        this.f.add(suggestions);

        if (listCov.size()==0){
            this.f.add("Aucun résultat");
        }
        else {
        for (Object covv : listCov) {

            CoVoiturage cov = (CoVoiturage) covv;
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
            //Map m = WebService.getResponse("covoiturage/api/offres/ago?id=" + cov.getId());
            Label by = new Label(cov.getCreated() + ", by " + cov.getUser().getUserName());
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

            for (Object corr : listCor) {
                CoVoiturageRequests cor = (CoVoiturageRequests) corr;
                if (cor.getIdc().getId() == cov.getId()) {
                    Container oneLine2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                    Container bottom2 = new Container();
                    bottom2.setLayout(new LayeredLayout());
                    Container left2 = new Container(new FlowLayout(LEFT));
                    Container right2 = new Container(new FlowLayout(RIGHT));

                    /*Container bottom3 = new Container();
                    bottom3.setLayout(new LayeredLayout());
                    Container left3 = new Container(new FlowLayout(LEFT));
                    Container right3 = new Container(new FlowLayout(RIGHT));*/
                    Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Label part = new Label("Demande de la part de : ");
                    part.getAllStyles().setFgColor(0xef6262);
                    c1.add(part);
                    Label us = new Label(cor.getUser().getUserName());
                    us.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    c1.add(us);
                    left2.add(c1);
                    Label etat = new Label("Etat : ");
                    etat.getAllStyles().setFgColor(0xef6262);
                    left2.add(etat);
                    Label et = new Label();
                    if (cor.getEtat().equals("a")) et.setText("En attente");
                    if (cor.getEtat().equals("c")) et.setText("Accepté");
                    if (cor.getEtat().equals("r")) et.setText("Refusé");
                    et.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    left2.add(et);
                    oneLine2.add(left2);
                    Label crea = new Label(cor.getCreated());
                    crea.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_SMALL));
                    oneLine2.add(crea);
                    try {
                        if (cor.getEtat().equals("a")) {
                            Button cancel = new Button(Image.createImage("/cancel.jpg"));
                            cancel.getAllStyles().setBorder(Border.createEmpty());
                            cancel.addActionListener((evt) -> {
                                WebService.getResponse("covoiturage/api/requests/decline?id="+cor.getId());
                                CoVoiturageOwn cow = new CoVoiturageOwn(back, type);
                            });
                            Button accept = new Button(Image.createImage("/accept.jpg"));
                            accept.getAllStyles().setBorder(Border.createEmpty());
                            accept.addActionListener((evt) -> {
                                WebService.getResponse("covoiturage/api/requests/accept?id="+cor.getId());
                                CoVoiturageOwn cow = new CoVoiturageOwn(back, type);
                            });
                            right2.add(cancel);
                            right2.add(accept);
                        } else if (cor.getEtat().equals("c")){
                            Button annuler = new Button("Annuler");
                            annuler.addActionListener((evt) -> {
                                WebService.getResponse("covoiturage/api/requests/delete?id="+cor.getId());
                                CoVoiturageOwn cow = new CoVoiturageOwn(back, type);
                            });
                            right2.add(annuler);
                        }

                    } catch (IOException ex) {
                        //Logger.getLogger(CoVoiturageOwn.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    oneLine2.add(right2);
                    try {
                        ScaleImageLabel img = new ScaleImageLabel(Image.createImage("/Separator.png"));
                        oneLine2.add(img);
                    } catch (IOException ex) {
                        //Logger.getLogger(CoVoiturageView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.f.add(oneLine2);
                }
            }

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
