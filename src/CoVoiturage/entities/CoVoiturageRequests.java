/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoVoiturage.entities;


/**
 *
 * @author Justpro
 */
public class CoVoiturageRequests {
    
    private int id;
    private CoVoiturage idc;
    private User user;
    private String etat;
    private String created;

    public CoVoiturageRequests() {
    }

    
    
    public CoVoiturageRequests(CoVoiturage idc, User user, String etat, String created) {
        this.idc = idc;
        this.user = user;
        this.etat = etat;
        this.created = created;
    }
    
    public CoVoiturageRequests(int id,CoVoiturage idc, User user, String etat, String created) {
        this.id=id;
        this.idc = idc;
        this.user = user;
        this.etat = etat;
        this.created = created;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CoVoiturage getIdc() {
        return idc;
    }

    public void setIdc(CoVoiturage idc) {
        this.idc = idc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
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
        final CoVoiturageRequests other = (CoVoiturageRequests) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CoVoiturageRequests{" + "id=" + id + ", idc=" + idc + ", user=" + user + ", etat=" + etat + ", created=" + created + '}';
    }
    
    
    
}
