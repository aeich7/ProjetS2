package fr.insa.eich.projets2;

import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author arbre
 */
public class Sol{
   private double surface;
   private double longueur;
   private double largeur;
   private int id;
   private List<Coin> Coins = new ArrayList<>();
   
   
   
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public double getlongueur() {
        return longueur;
    }
   
    public void setlongueur(double longueur) {
        this.longueur = longueur;
    }
     public double getlargeur() {
        return largeur;
    }
    public void setlargeur(double largeur){
        this.largeur = largeur;
    }
     public void AjouterCoin(Coin Coin){
        Coins.add(Coin);
    }

    public double getSurface() {
        return surface;
    }

  
    

     
    @Override
    public String toString() {
        return "Sol{" + "longueur=" + longueur + ", largeur=" + largeur + ", id=" + id + ", Coins=" + Coins + '}';
    }
  
     
       
}
