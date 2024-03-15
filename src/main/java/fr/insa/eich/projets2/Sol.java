package fr.insa.eich.projets2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author arbre
 */
public class Sol {
   
   private double longueur;
   private double largeur;
   private int id;
   
   
   
   public double surface() {
   return (double) (this.longueur * this.largeur);
 }
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
    
   
       
}
