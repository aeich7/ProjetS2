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
    private List<Piece> pieces = new ArrayList();

    
    public Niveau() {
        idCompteur++;
        this.id = idCompteur;
    }
    
      public List<Coin> getCoins() {
        return coins;
    }

    public List<Mur> getMurs() {
        return murs;
    }
    
    public List<Piece> getPieces(){
        return pieces;
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
        String appartementsIds = "ListeAppartements={";
        for (Appartement appartement : ListeAppartements) {
            appartementsIds += appartement.getId() + ", ";
        }
        if (!ListeAppartements.isEmpty()) {
            appartementsIds = appartementsIds.substring(0, appartementsIds.length() - 2); // Supprimer la virgule et l'espace en trop à la fin
        }
        appartementsIds += "}";

        return "Niveau{" + "id=" + id + ", " + appartementsIds + '}';
    }

    public void addCoin(Coin coin) {
        coins.add(coin);
    }

    public void addMur(Mur mur) {
        murs.add(mur);
    }
    
    public void addPiece(Piece piece){
        pieces.add(piece);
    }
    
    
}
