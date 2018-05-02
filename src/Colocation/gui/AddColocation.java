/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Colocation.gui;


import Colocation.entities.Colocation;
import Colocation.service.ColocationService;
import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.io.IOException;





/**
 *
 * @author douha
 */
public class AddColocation  {
    
     Form f;
    private String fileName= "No Picture";
    public AddColocation() {
        
      f = new Form("Ajout");
        
        
        

       
         f.getToolbar().addCommandToLeftBar("<- Retour", null, (ev) -> {
            ColocationMenu h = new ColocationMenu();
            h.getForm().show();

        });
                       
         
        
        
        TextField tfTitre = new TextField( "");
        TextField tfLoyer = new TextField( "");
        TextField tfDescription = new TextField("", "Description");
        
        
        String[] Villes = { "Tunis", "Ariana", "Bizerte", "Beja",
    "Sousse" ,"Jendouba", "Mounastir", "Tataouine" , "Kef"
};
        
        AutoCompleteTextField act = new AutoCompleteTextField(Villes);
        act.setEditable(false);
        
        String[] type = { "Studio", "Maison"};
        
        AutoCompleteTextField comboType = new AutoCompleteTextField(type);
        comboType.setEditable(false);
        
        String[] meuble = { "Meuble", "non meuble"};
        
        AutoCompleteTextField comboMeuble = new AutoCompleteTextField(meuble);
        comboMeuble.setEditable(false);
        
        

        
                        
                       Button btnOk = new Button("Ajouter");
                       btnOk.getStyle().setBgColor(22355663);
                               
        
        btnOk.addActionListener(new ActionListener() {
       
            

            @Override
            public void actionPerformed(ActionEvent evt) {

                Colocation c = new Colocation(tfTitre.getText(),Float.parseFloat(tfLoyer.getText()) , tfDescription.getText(), comboType.getText(), comboMeuble.getText(), act.getText());
               c.setPhoto(fileName);
                ColocationService ser = new ColocationService();
                ser.ajoutColocation(c);
                        
                Dialog.show("Confirmation", "ajout ok", "Ok", null);
                
                          if(tfTitre.getText().equals("")||tfLoyer.getText().equals("")){
         Dialog.show("Erreur", "Veuiller remplir tous les champs", "Ok", null);
     }
                
            }
            
            
        });
        
         Button AddPicture = new Button("Pick Picture From Gallery");
                AddPicture.getStyle().setBgColor(22355663);
        
        AddPicture.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                try {
                    
                    MultipartRequest cr = new MultipartRequest();
                    String  filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                    fileName= extractFileName(filePath) +"jpg"; 
                    cr.setUrl("http://localhost/inserImage.php");
                    cr.setPost(true);
                    
                    String mime="image/jpeg";
                    cr.addData("file", filePath, mime); 
                    cr.setFilename("file", fileName);
                    
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                    
                } catch (IOException ex) {
                    
                }
            }
        });
                        
         Container ctntitre =new Container(BoxLayout.y());
         ctntitre.add("titre");
         ctntitre.add(tfTitre);
         ctntitre.add("loyer");
         ctntitre.add(tfLoyer);
         ctntitre.add("Description");
         ctntitre.add(tfDescription);
         ctntitre.add("Ville");
         ctntitre.add(act);
         ctntitre.add("Type");
         ctntitre.add(comboType);
         ctntitre.add("Meuble");
         ctntitre.add(comboMeuble);
         ctntitre.add(AddPicture);
         ctntitre.add(btnOk);
         
         
                  
                 
                  f.add(ctntitre);
                  
                  
                  
                 f.show();
     
        
        
        
        
       
    }
    
   
    
   

  
    public static String extractFileName( String filePathName )
  {
    if ( filePathName == null )
      return null;

    int dotPos = filePathName.lastIndexOf( '.' );
    int slashPos = filePathName.lastIndexOf( '\\' );
    if ( slashPos == -1 )
      slashPos = filePathName.lastIndexOf( '/' );

    if ( dotPos > slashPos )
    {
      return filePathName.substring( slashPos > 0 ? slashPos + 1 : 0,
          dotPos );
    }

    return filePathName.substring( slashPos > 0 ? slashPos + 1 : 0 );
  }
    
    
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
        
}