/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objet.entities;

import java.util.Date;







/**
 *
 * @author reaper
 */


public class FosUser{

    public static int currentUserId ;
    private Integer id;
    private String username;
    private String usernameCanonical;
    private String email;
    private String emailCanonical;
    private Integer enabled;
    private String salt;
    private String password;
    private String lastLogin;
    private String confirmationToken;
    private Date passwordRequestedAt;
    private String roles;
    private String nom;
    private String prenom;
    private Date date;
    private String sexe;
    private String cin;
    private String image;
    private String adresse;

    //Traiteur
    private int idPointFidelite;
    private int NbreMaxDemTraiteur;
    private float PrixTraiteur;
    private String DescriptionTraiteur;
    private int idSpecialiteTraiteur;
    private int idEvenementTraiteur;

    public FosUser(Integer id, String username, String roles, String nom, String prenom) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
    }

    public FosUser(int id, String cin, int enabled, String roles, String nom, String prenom) {
        this.id = id;
        this.enabled=enabled;
        this.cin = cin;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;    
    }

   
    public int getIdPointFidelite() {
        return idPointFidelite;
    }

    public void setIdPointFidelite(int idPointFidelite) {
        this.idPointFidelite = idPointFidelite;
    }

    public int getNbreMaxDemTraiteur() {
        return NbreMaxDemTraiteur;
    }

    public FosUser(String username, String usernameCanonical, String email, String emailCanonical, String password, String roles, String nom, String prenom, String sexe, String cin, String adresse, Date date) {
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.password = password;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.sexe = sexe;
        this.cin = cin;
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNbreMaxDemTraiteur(int NbreMaxDemTraiteur) {
        this.NbreMaxDemTraiteur = NbreMaxDemTraiteur;
    }

    public float getPrixTraiteur() {
        return PrixTraiteur;
    }

    public void setPrixTraiteur(float PrixTraiteur) {
        this.PrixTraiteur = PrixTraiteur;
    }

    public String getDescriptionTraiteur() {
        return DescriptionTraiteur;
    }

    public void setDescriptionTraiteur(String DescriptionTraiteur) {
        this.DescriptionTraiteur = DescriptionTraiteur;
    }

    public int getIdSpecialiteTraiteur() {
        return idSpecialiteTraiteur;
    }

    public void setIdSpecialiteTraiteur(int idSpecialiteTraiteur) {
        this.idSpecialiteTraiteur = idSpecialiteTraiteur;
    }

    public int getIdEvenementTraiteur() {
        return idEvenementTraiteur;
    }

    public void setIdEvenementTraiteur(int idEvenementTraiteur) {
        this.idEvenementTraiteur = idEvenementTraiteur;
    }
    
    
    
   
  
  
   

    public FosUser() {
    }

    public FosUser(Integer id) {
        this.id = id;
    }

    public FosUser(String email) {
        this.email = email;
    }
    
    
    public FosUser(Integer id, String username , String id_facebook){
    this.id = id;
    this.username = username;
  
    }
   
    public FosUser(String username, String email, String password, String roles, String nom, String prenom, Date date, int numTel, String url, int idPointFidelite, int NbreMaxDemTraiteur, float PrixTraiteur, String DescriptionTraiteur, int idSpecialiteTraiteur, int idEvenementTraiteur) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
       
        this.idPointFidelite = idPointFidelite;
        this.NbreMaxDemTraiteur = NbreMaxDemTraiteur;
        this.PrixTraiteur = PrixTraiteur;
        this.DescriptionTraiteur = DescriptionTraiteur;
        this.idSpecialiteTraiteur = idSpecialiteTraiteur;
        this.idEvenementTraiteur = idEvenementTraiteur;
    }
    
    

    public FosUser(Integer id, String username, String email, Integer enabled, String roles, String nom, String prenom, int Score_quiz) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
       
    }

    public FosUser(Integer id, String username, String usernameCanonical, String email, String emailCanonical, Integer enabled, String password, String roles, String nom, String prenom, Date date, int numTel, int numTelSecour, String url, String Titre) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        
    }
    
    
    public FosUser(String username, String email, String password, String nom, String prenom, int numTel, int numTelSecour,String roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        //this.date = date;
      
    }

    public FosUser(Integer id, String username, String usernameCanonical, String email, String emailCanonical, Integer enabled, String salt, String password, String lastLogin, String confirmationToken, Date passwordRequestedAt, String roles, String nom, String prenom, Date date, String sexe, String cin) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.sexe = sexe;
        this.cin = cin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
 
   
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
 

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    


 
    
    

    @Override
    public String toString() {
        return "Entity.FosUser[ id=" + id + " ]";
    }
    
}
