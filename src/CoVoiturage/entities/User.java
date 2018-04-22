/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoVoiturage.entities;


/**
 *
 * @author bader
 */
public class User {

    private int id;
    private String userName;
    private String username_canonical;
    private String dateNaissance;
    private String sexe;
    private String Classe;
    private String Telephone;
    private String email;
    private String password;
    private int enabled;
    private String roles;
    private String nom;
    private String prenom;

    public User(String userName, String email, String password/*, int enabled, String roles*/, String nom, String prenom) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        //this.enabled = enabled;
        // this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
    }

    public User(int id, String userName, String nom, String prenom) {
        this.id = id;
        this.userName = userName;
        this.nom = nom;
        this.prenom = prenom;
    }

    public User(String userName, String dateNaissance, String sexe, String Classe, String Telephone, String email, String password, String nom, String prenom) {
        this.userName = userName;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.Classe = Classe;
        this.Telephone = Telephone;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
    }

    public User(int id, String userName, String email, String password, int enabled, String nom, String prenom) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.nom = nom;
        this.prenom = prenom;
    }

 

    public String getStringNaissance() {
        return dateNaissance;
    }

    public void setStringNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getClasse() {
        return Classe;
    }

    public void setClasse(String Classe) {
        this.Classe = Classe;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }
    
    

    public User(String userName, String email, String password/*, int enabled, String roles*/, String nom, String prenom, int enabled, int id) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        // this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
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
        return "User{" + "userName=" + userName + ", dateNaissance=" + dateNaissance + ", sexe=" + sexe + ", Classe=" + Classe + ", Telephone=" + Telephone + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + '}';
    }

 

}
