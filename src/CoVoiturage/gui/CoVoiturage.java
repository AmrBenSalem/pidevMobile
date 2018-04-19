/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoVoiturage.gui;

import com.codename1.ui.Form;
import com.codename1.ui.Label;

/**
 *
 * @author Justpro
 */
public class CoVoiturage {
    
    Form f;
    
    public CoVoiturage(){
        this.f = new Form("aa");
        this.f.add(new Label("ss"));
    }
    
    public Form getForm(){
        return this.f;
    }
}
