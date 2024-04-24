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
    private static int idCompteur =0;
    private double hauteurSousPlafond;
    private double hauteurBatiment;
    private double nbrniveau;
    private List<Coin> coins = new ArrayList<>();
    private List<Mur> murs = new ArrayList<>();
    private List<Appartement> ListeAppartements = new ArrayList<>();
    
    public List<Coin> getCoins() {
        return coins;
    }

    public List<Mur> getMurs() {
        return murs;
    }
    
    public void afficher (int etage, double hauteurSousPlafond){
        hauteurBatiment=etage*hauteurSousPlafond;
        System.out.println("la hauteur du batiment est:"+hauteurBatiment);
    }

    public Niveau() {
        idCompteur++;
        this.id = idCompteur;
    }
    
    
    
    
    public void afficher(){
        System.out.println("le niveau du batiment est "+nbrniveau);
    }
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

    @Override
    public String toString() {
        return "Niveau{" + "id=" + id + ", hauteurSousPlafond=" + hauteurSousPlafond + ", hauteurBatiment=" + hauteurBatiment + ", nbrniveau=" + nbrniveau + ", ListeAppartements=" + ListeAppartements + '}';
    }
    

    public void addCoin(Coin coin) {
        coins.add(coin);
    }

    public void addMur(Mur mur) {
        murs.add(mur);
    }

    
    
}
