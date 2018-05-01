/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objet.entities;


/**
 *
 * @author bader
 */
public class Interaction {
    
    private int id;
    private int user;
    private int objet;
    private String statut;
    private String telephone;
    private String nomuser;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }
    
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getObjet() {
        return objet;
    }

    public void setObjet(int objet) {
        this.objet = objet;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Interaction() {
    }

    public Interaction(int id, int user, int objet, String statut) {
        this.id = id;
        this.user = user;
        this.objet = objet;
        this.statut = statut;
    }
    

    public Interaction(int user, int objet, String statut) {
        this.user = user;
        this.objet = objet;
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Interaction{" + "statut=" + statut + ", telephone=" + telephone + ", nomuser=" + nomuser + '}';
    }

  
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Interaction other = (Interaction) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
       
    
}

