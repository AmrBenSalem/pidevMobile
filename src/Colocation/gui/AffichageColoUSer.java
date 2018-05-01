
package Colocation.gui;

import Colocation.entities.Colocation;
import Colocation.service.ColocationService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.sun.mail.smtp.SMTPTransport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;







/**
 *
 * @author douha
 */
public class AffichageColoUSer {
 private Resources theme;
    Form f;
   
    
    
    public AffichageColoUSer() throws IOException { 
    f = new Form();
       SpanLabel lb = new SpanLabel("");

       
        Container ctn2=new Container(BoxLayout.y());
        ctn2.setScrollableY(true);
        f.add(lb);
        ColocationService rs = new ColocationService ();
        ArrayList<Colocation> lis = rs.getList3();
            
        
        for (Colocation col : lis) 
        {
                    Container ctn = new Container(BoxLayout.y());
        Container ctn1 =new Container(BoxLayout.x());
        Container ctn3 = new Container(BoxLayout.y());
        ctn3.getStyle().setBorder(Border.createLineBorder(1));
            ImageViewer imv= null ;
            Image img ;
            EncodedImage encoded = EncodedImage.createFromImage(Image.createImage(180, 150), true);
            img = URLImage.createToStorage(encoded, col.getPhoto(), "http://localhost/pidev2/web/uploads/photos/"+col.getPhoto(), URLImage.RESIZE_SCALE) ;
           
            imv = new ImageViewer(img) ;
           
            
            Label l = new Label(col.getTitre());
            l.getUnselectedStyle().setBgColor(22355663);

            Button detail = new Button("Details");
            ctn3.add(l);
            
            detail.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            
                              Form  f2 = new Form();
       SpanLabel lb6 = new SpanLabel("");

        Container ctnDetail=new Container(BoxLayout.y());
        ctn2.setScrollableY(true);
        f2.add(lb6);
                            ImageViewer imvDetail= null ;
            Image imgDetail ;
            EncodedImage encoded1 = EncodedImage.createFromImage(Image.createImage(260, 280), true);
            imgDetail = URLImage.createToStorage(encoded1, col.getPhoto(), "http://localhost/pidev2/web/uploads/photos/"+col.getPhoto(), URLImage.RESIZE_SCALE) ;
           
            imvDetail = new ImageViewer(imgDetail) ;
            
            Label lb1 = new Label("Meuble: "+col.getMeuble());
             Label lb2 = new Label("Type: "+col.getType());
              Label lb3 = new Label("Ville: "+col.getVille());
               Label lb4 = new Label("Prix: "+String.valueOf(col.getLoyer()));
               Label lb5 = new Label("               "+col.getTitre());
               
               lb1.getStyle().setBorder(Border.createLineBorder(1));
               lb2.getStyle().setBorder(Border.createLineBorder(1));
               lb3.getStyle().setBorder(Border.createLineBorder(1));
               lb4.getStyle().setBorder(Border.createLineBorder(1));
               lb5.getStyle().setBorder(Border.createLineBorder(2));
               
                           
               
              
               
            ctnDetail.add(lb5);
            ctnDetail.add(imvDetail);
          ctnDetail.add(lb1);
          ctnDetail.add(lb2);
            ctnDetail.add(lb3);
            ctnDetail.add(lb4);
            TextArea txcar = new TextArea("Description: "+col.getDescription());
            ctnDetail.add(txcar);
            
            
            
           
            
            f2.add(ctnDetail);
            f2.show();    
                        
                        }
                    });
            
            Button edit =  new Button("edit");
       edit.setIcon(FontImage.createMaterial(FontImage.MATERIAL_EDIT, edit.getUnselectedStyle()));
       Button delete =  new Button("delete");
       delete.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, delete.getUnselectedStyle()));
               
       Container ctnCrud=new Container(BoxLayout.x());
              ctnCrud.add(edit);
              ctnCrud.add(delete);
          //  ctn.add(col.getDescription());
          //  ctn.add(col.getType());
        //    ctn.add(col.getMeuble());
            ctn.add("Ville: "+col.getVille());
            ctn.add("Prix: "+String.valueOf(col.getLoyer()));
            
            ctn.add(detail);
            ctn.add(ctnCrud);
                       ctn1.add(imv);

            ctn1.add(ctn);
            ctn3.add(ctn1);
            ctn2.add(ctn3);
            
             delete.addActionListener(new ActionListener() {

                                  @Override
                                  public void actionPerformed(ActionEvent evt) {
                                     rs.delete(col.getId());
                                    
                                  }
                              });
           
        }

        //lb.setText(lis.toString());
        /*  f.getToolbar().addCommandToRightBar("back", null, ()->{HomePage h=new HomePage();
          h.getF().show();
          });*/
        f.add(ctn2) ;
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
           
}
