/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;

/**
 *
 * @author arbre
 */
public class Niveau {
    private int id;
    private double hauteurSousPlafond;
    private List<Appartement> ListeAppartement = new ArrayList<>();
    
    
    public void AjouterAppartement(Appartement Appartement1){
        ListeAppartement.add(Appartement1);
    }

    public List<Appartement> getListeAppartement() {
        return ListeAppartement;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
