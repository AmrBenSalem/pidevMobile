/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.Entities;

import java.util.Date;


/**
 *
 * @author Liwa
 */
public class Event {
    private int id;
    private int iduser;
    private String titre;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private String lieu;
    private String photo;
    private int nb_max;
    private int enable;
    private String categorie;
    private Date createdAt;
    private double x;
    private double y;
   

    public Event() {
    }

    public Event(int id, int iduser, String titre, String description, Date dateDebut, Date dateFin, String lieu, String photo, int nb_max, int enable, String categorie, Date createdAt, double x, double y) {
        this.id = id;
        this.iduser = iduser;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.photo = photo;
        this.nb_max = nb_max;
        this.enable = enable;
        this.categorie = categorie;
        this.createdAt = createdAt;
        this.x = x;
        this.y = y;
    }

    public Event(int iduser, String titre, String description, Date dateDebut, Date dateFin, String lieu, String photo, int nb_max, int enable, String categorie, Date createdAt, double x, double y) {
        this.iduser = iduser;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.photo = photo;
        this.nb_max = nb_max;
        this.enable = enable;
        this.categorie = categorie;
        this.createdAt = createdAt;
        this.x = x;
        this.y = y;
    }

    public Event(String titre, String description){
        this.titre = titre;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getNb_max() {
        return nb_max;
    }

    public void setNb_max(int nb_max) {
        this.nb_max = nb_max;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", iduser=" + iduser + ", titre=" + titre + ", description=" + description + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", lieu=" + lieu + ", photo=" + photo + ", nb_max=" + nb_max + ", enable=" + enable + ", categorie=" + categorie + ", createdAt=" + createdAt + ", x=" + x + ", y=" + y + '}';
    }



    
    

 

 
    

    }
