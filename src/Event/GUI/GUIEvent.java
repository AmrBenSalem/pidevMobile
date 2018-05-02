/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.GUI;

import CoVoiturage.entities.User;
import CoVoiturage.services.CoVoiturageParser;
import CoVoiturage.util.Db;
import Event.Entities.Avis;
import Event.Entities.Event;
import Event.Services.ServiceEvent;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import util.WebService;
import com.codename1.components.SliderBridge;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.Animation;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.StyleListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import java.util.Date;

/**
 *
 * @author Liwa
 */
public class GUIEvent extends SliderBridge implements Animation, StyleListener {

    Form f;
    Form f2;
    SpanLabel lb;
    EncodedImage encoded;
    public static User currentUser;

    public GUIEvent() {

        f = new Form();

        ServiceEvent serviceevent = new ServiceEvent();
        Map x = WebService.getResponse("event/ej/");
        //Map x2 = WebService.getResponse("event/ej/16/6/4");
        //Map x4 = WebService.getResponse("event/ej/16/6/4");
        
        //Map x3 = WebService.getResponse("event/ej/avis/event/2/");
        //System.out.println("--------------x--------------" + x);
        //System.out.println("--------------x22222222222--------------" + x4);

        //Event e = serviceevent.getEvent(x2);
        //int idevent = e.getId();
        //System.out.println(e.toString());
        ArrayList<Event> listevents = serviceevent.getListEvents(x);

        for (Event ev : listevents) {
            //System.out.println("eveent   ::::::"+ev.toString());
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            try {
                encoded = EncodedImage.create("/images.png");
            } catch (IOException ex) {
                //Logger.getLogger(GUIEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println("http://localhost/pidev2/web/" + ev.getPhoto());    
            img = URLImage.createToStorage(encoded, ev.getPhoto(), "http://localhost/pidev2/web/" + ev.getPhoto());
            //img.scaled(50, 50);
            //System.out.println("http://localhost/pidev2/web/" + ev.getPhoto());
            imv = new ImageViewer(img);

            Container bouhom = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container sepa = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container title = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container ctnnb = new Container(new BoxLayout(BoxLayout.X_AXIS));

            //Image i = URLImage.createToStorage(encoded, ev.getPhoto(), "http://localhost/mobile/images/"+e.getPhoto());
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                sepa.add(sep);
            } catch (IOException ex) {
                //Logger.getLogger(CoVoiturageView.class.getName()).log(Level.SEVERE, null, ex);
            }

            Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container ph = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label tit = new Label("Titre : ");
            Label description = new Label("Description : ");
            tit.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            description.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            Label nb = new Label("Nombre max : ");
            nb.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            Label l = new Label(ev.getTitre());
            SpanLabel l2 = new SpanLabel(ev.getDescription());
            Label lnb = new Label(ev.getNb_max() + "");
            lnb.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            //Label l3 = new Label(ev.getDateDebut().toString());
            //Label l4 = new Label(ev.getDateFin().toString());

            SpanLabel l6 = new SpanLabel(ev.getLieu());
            ph.add(img);
            l.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            l2.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            //title.add(ph);
            title.add(tit);
            title.add(l);
            C2.add(description);
            C2.add(l2);
            ctnnb.add(nb);
            ctnnb.add(lnb);
            //C2.add(l2);
            //C2.add(l3);
            //C2.add(l4);
            //C2.add(l5);
            //C2.add(l6);

            //C1.add(i);
            bouhom.add(ph);
            bouhom.add(title);
            bouhom.add(C2);
            bouhom.add(ctnnb);

            bouhom.add(sepa);

            bouhom.setLeadComponent(ph);

            f.add(bouhom);
            f.show();

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
                        //Logger.getLogger(GUIEvent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("http://localhost/pidev2/web/" + ev.getPhoto());    
                    im = URLImage.createToStorage(encod, ev.getPhoto(), "http://localhost/pidev2/web/" + ev.getPhoto());
                    imviewer = new ImageViewer(im);

                    Label lbtitle = new Label("Titre : " + ev.getTitre());
                    SpanLabel lbdescr = new SpanLabel("Description : " + ev.getDescription());
                    Label lbdatedeb = new Label("Date début : " + ev.getDateDebut());
                    Label lbdatefin = new Label("Date fin : " + ev.getDateFin());
                    SpanLabel lblieu = new SpanLabel("Lieu : " + ev.getLieu());
                    Label lbcateg = new Label("Catégorie : " + ev.getCategorie());

                    lbtitle.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    lbdescr.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    lbdatedeb.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    lbdatefin.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    lblieu.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    lbcateg.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    f2.add(imviewer);
                    f2.add(lbtitle);
                    f2.add(lbcateg);
                    f2.add(lbdescr);
                    f2.add(lbdatedeb);
                    f2.add(lbdatefin);
                    f2.add(lblieu);
                    Slider rate = createStarRankSlider();

                    Button btnpRate = new Button("Donner Avis");
                    Button btnannuler = new Button("Annuler Participation");
                    Button btnparticiper = new Button("Participer");

                    Db d = Db.getInstance();
                    Date thisDate = new Date();

                    if (d.getUser().getId() != ev.getIduser()     ) {
                        
                        Map ifParticipate = WebService.getResponse("event/ej/if/participate/"+d.getUser().getId()+"/"+ev.getId());
                        System.out.println("If Participated ----------------" + ifParticipate);
                        String s = ifParticipate.toString();
                        System.out.println("ssssssssssssssssss" + s);
                        Map ifRate = WebService.getResponse("event/ej/if/rate/"+d.getUser().getId()+"/"+ev.getId());
                        String s2 = ifRate.toString();
                        System.out.println("s2222222222222"+s2);
                        if (s.equals("{}")  &&   before(thisDate, ev.getDateDebut()) ) {
                            f2.add(btnparticiper);
                        } else if (before(thisDate, ev.getDateFin())) {

                            f2.add(btnannuler);
                            
                            

                        }
                        
                        if (!(s2.equals("{}")) && after(thisDate, ev.getDateFin())){
                            
                            f2.add(FlowLayout.encloseCenter(rate));
                            f2.add(btnpRate);
                        
                        }
                        
                        

                        btnparticiper.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {

                                Map par = WebService.getResponse("event/ej/" + ev.getId() + "/" + d.getUser().getId() + "/participer");
                                System.out.println("paaaaaaaaar" + par);

                                System.out.println(ev.getId());
                                GUIEvent gui = new GUIEvent();
                                gui.getForm().show();

                            }
                        });
                        
                        
                        
                        
                         
                        
                        
                        
                        
                        
                        
                        
                    } 
                        btnannuler.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {

                                Map par = WebService.getResponse("event/ej/" + ev.getId() + "/" + d.getUser().getId() + "/annuler");
                                System.out.println("annuler----------" + par);
                                Map annuler = WebService.getResponse("event/ej/" + ev.getId() + "/" + d.getUser().getId() + "/annuler");
                                GUIEvent gui = new GUIEvent();
                                gui.getForm().show();
                            }
                        });

                        btnpRate.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                
                                
                                int avis = rate.getProgress() / 2;
                                System.out.println("rate-----------------" + avis);
                                Map r = WebService.getResponse("event/ej/"+ev.getId()+"/"+d.getUser().getId()+"/"+avis);
                                Map r2 = WebService.getResponse("event/ej/"+ev.getId()+"/"+d.getUser().getId()+"/"+avis);
                                System.out.println("rrrrrrrrrr:::::::::" + r2);
                                GUIEvent gui = new GUIEvent();
                                gui.getForm().show();

                            }
                        });

                    

                    f2.show();

                }
            });

        }

    }

    public Form getForm() {
        return this.f;
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

    boolean before(Date d1, Date d2) {
        return d1.getTime() < d2.getTime();
    }
    
    
    boolean after(Date d1, Date d2) {
        return d1.getTime() > d2.getTime();
    }

}
