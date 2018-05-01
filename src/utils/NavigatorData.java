/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import CoVoiturage.entities.User;




/**
 *
 * @author Mortadhafff
 */
public class NavigatorData {

    private final static NavigatorData instance = new NavigatorData();

    public static NavigatorData getInstance() {
        return instance;
    }
    

    public int idParapharmacie;
    public int idCategorie;
    public User connectedUser;
    public float montant;
}
