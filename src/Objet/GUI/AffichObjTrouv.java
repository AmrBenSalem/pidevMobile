/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objet.GUI;

import CoVoiturage.util.WebService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import Objet.services.ObjetService;

import Objet.entities.Objet;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SliderBridge;
import com.codename1.components.SpanLabel;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.Animation;
import com.codename1.ui.events.StyleListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author bader
 */
public class AffichObjTrouv extends SliderBridge implements Animation, StyleListener {

    Form f;
    private Resources theme;
    Form f2;
    SpanLabel lb;
    EncodedImage encoded;

    public AffichObjTrouv() {

        f = new Form("Les Objets Trouvés");
        Toolbar tb = f.getToolbar();

        tb.addMaterialCommandToSideMenu("Objets Perdus", FontImage.MATERIAL_HOME, e -> {
            AffichObjPerd a = new AffichObjPerd();
        });
        tb.addMaterialCommandToSideMenu("Objets Trouvés", FontImage.MATERIAL_WEB, e -> {
            AffichObjTrouv a = new AffichObjTrouv();
        });
        ObjetService objserv = new ObjetService();
        Map x = WebService.getResponse("objtrouv");
        System.out.println("retour objets trouv" + x);

        ArrayList<Objet> listobjet = objserv.findAllObjets(x);

        for (Objet o : listobjet) {

            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            try {
                encoded = EncodedImage.create("/images.png");
            } catch (IOException ex) {
            }
            img = URLImage.createToStorage(encoded, o.getPhoto(), "http://localhost/pidev2/web/" + o.getPhoto());
            imv = new ImageViewer(img);

            Container bouhom = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container sepa = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container title = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container ctnnb = new Container(new BoxLayout(BoxLayout.X_AXIS));

            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                sepa.add(sep);
            } catch (IOException ex) {
            }

            Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container ph = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label tit = new Label("Type : ");

            Label description = new Label("Description : ");
            tit.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            description.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            Label nb = new Label("Lieu : ");
            nb.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            Label l = new Label(o.getType());
            SpanLabel l2 = new SpanLabel(o.getDescription());
            Label lnb = new Label(o.getLieu());
            lnb.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
Label l4 = new Label("Posté Par : " + o.getNature());
            SpanLabel l6 = new SpanLabel(o.getLieu());
            ph.add(img);
            l.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                       l4.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            l2.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));

            title.add(tit);
            title.add(l);
            C2.add(description);
            C2.add(l2);
            ctnnb.add(nb);
            ctnnb.add(lnb);

            bouhom.add(ph);
            bouhom.add(title);

            bouhom.add(ctnnb);
            bouhom.add(l4);

            bouhom.add(sepa);

            bouhom.setLeadComponent(ph);

            f.add(bouhom);
            f.show();

            System.out.println("je suis là");

            ph.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    f2 = new Form(BoxLayout.y());

                    Toolbar tb = f2.getToolbar();

                    tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {

                        f.showBack();
                    });
                    ImageViewer imviewer = null;
                    Image im;
                    EncodedImage encod = null;
                    try {
                        encod = EncodedImage.create("/images.png");
                    } catch (IOException ex) {
                    }
                    im = URLImage.createToStorage(encod, o.getPhoto(), "http://localhost/pidev2/web/" + o.getPhoto());
                    imviewer = new ImageViewer(im);

                    Label lbtitle = new Label("Type : " + o.getType());
                    SpanLabel lbdescr = new SpanLabel("Description : " + o.getDescription());
                    Label lbdatedeb = new Label("Date : " + o.getDate());
                    SpanLabel lblieu = new SpanLabel("Lieu : " + o.getLieu());

                    lbtitle.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    lbdescr.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    lbdatedeb.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    lblieu.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    Button btnannuler = new Button("Partager");
                    Button btnparticiper = new Button("Réclamer");
                    Button btnsuppreclam = new Button("Annuler Reclamation");

                    f2.add(imviewer);

                    f2.add(lbdescr);
                    f2.add(btnparticiper);
                    f2.add(btnannuler);
                    f2.add(btnsuppreclam);

                    f2.show();
                    System.out.println("je suis maintenant là");

                    btnannuler.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {

                            String token = "";
                            FacebookClient fb = new DefaultFacebookClient(token);
                            FacebookType r = fb.publish("me/feed", FacebookType.class, Parameter.with("message", o.toString()));
                            System.out.println("fb.com" + r.getId());

                        }
                    });

                    btnparticiper.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            ObjetService os = new ObjetService();
                            os.ajouterReclamationObjTrouv(o);

                            btnparticiper.setEnabled(false);

                        }
                    });

                    btnsuppreclam.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            ObjetService os = new ObjetService();
                            os.SupprimerReclamation(o);

                            btnparticiper.setEnabled(false);

                        }
                    });

                }
            });

        }

    }

    public Form getForm() {
        return this.f;
    }

}
