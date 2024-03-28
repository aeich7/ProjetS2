/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;
import java.util.*;

/**
 *
 * @author arbre
 */
public class Niveau {
    private int id;
    private double hauteurSousPlafond;
    private List<Appartement> ListeAppartements = new ArrayList<>();
    
    
    public void AjouterAppartement(Appartement Appartement1){
        ListeAppartements.add(Appartement1);
    }

    public List<Appartement> getListeAppartement() {
        return ListeAppartements;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
