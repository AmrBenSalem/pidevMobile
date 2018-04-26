/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.Service;

import EspaceEtude.entities.Documents;
import EspaceEtude.entities.Matiere;
import EspaceEtude.entities.Section;
import EspaceEtude.util.WebService;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author oussema
 */
public class EspaceEtudeService {

    public EspaceEtudeService() {
    
    
    
       
    }
    public static ArrayList<Section> getAllSection(String niv){
        ArrayList <Section> sectionList=new ArrayList<>();
        Map <String,Object> m=(Map <String,Object>) WebService.getResponse("etude/jsonSection/"+niv);
        ArrayList a=(ArrayList) m.get("sections");
        
        for(int i=0;i<a.size();i++){
           Map test =  (Map) a.get(i);
           Section s=new Section();
           Double ll = (Double) test.get("id");
           s.setId(ll.intValue());
           s.setLibelle((String) test.get("libelle"));
           s.setNiveau((String) test.get("niveau"));
           sectionList.add(s);
            
        }
        return sectionList;
    }
    public static ArrayList<Matiere> getAllMatiere(int id){
        ArrayList <Matiere> matiereList=new ArrayList<>();
        Map <String,Object> m=(Map <String,Object>) WebService.getResponse("etude/jsonMatiere/"+id);
        ArrayList a=(ArrayList) m.get("matieres");
        
        for(int i=0;i<a.size();i++){
           Map test =  (Map) a.get(i);
           Matiere s=new Matiere();
           Double ll = (Double) test.get("id");
           s.setId(ll.intValue());
           s.setLibelle((String) test.get("libelle"));
           s.setCoefficient((String) test.get("coefficient"));
           s.setType((String) test.get("type"));
           matiereList.add(s);
            
        }
        return matiereList;
    }
    public static ArrayList<Documents> getAllDocuments(int id){
        ArrayList <Documents> documentList=new ArrayList<>();
        Map <String,Object> m=(Map <String,Object>) WebService.getResponse("etude/jsonDocuments/"+id);
        ArrayList a=(ArrayList) m.get("documents");
        
        for(int i=0;i<a.size();i++){
           Map test =  (Map) a.get(i);
           Documents d=new Documents();
           Double ll = (Double) test.get("id");
           d.setId(ll.intValue());
           d.setLibelle((String) test.get("libelle"));
           d.setPath((String) test.get("path"));
           d.setDate((String) test.get("date"));
           d.setTypeDocument((String)test.get("type"));
           d.setSize((Double) test.get("size"));
           ll=(Double)test.get("matiere");
           d.setMatiere(ll.intValue());
           ll=(Double)test.get("user");
           d.setUser(ll.intValue());
           d.setImage((String) test.get("image"));
           documentList.add(d);
            
        }
        return documentList;
    }
}
