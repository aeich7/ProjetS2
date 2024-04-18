/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;

/**
 *
 * @author eicha
 */
public class Mur {
    private int id;
    private Coin coinDebut;
    private Coin coinFin;
    private double hauteur;
    private double longueur;
   

    public Mur(int id, Coin coinDebut, Coin coinFin, double hauteur) {
        this.id = id;
        this.coinDebut = coinDebut;
        this.coinFin = coinFin;
        this.hauteur = hauteur;
    }
           
    public  double longueur(){
        if (this.coinDebut.getX() == this.coinFin.getX()){
            this.longueur = this.coinFin.getY() - this.coinDebut.getY();
        }
        else if (this.coinDebut.getY() == this.coinFin.getY()){
            this.longueur = this.coinFin.getX()- this.coinDebut.getX();
        }
        return this.longueur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    public Coin getCoinDebut() {
        return coinDebut;
    }

    public void setCoinDebut(Coin coinDebut) {
        this.coinDebut = coinDebut;
    }

    public Coin getCoinFin() {
        return coinFin;
    }

    public void setCoinFin(Coin coinFin) {
        this.coinFin = coinFin;
    }
    
    
    public double getLongueur() {
        return longueur;
    }
    
  
    public void afficher(){
        System.out.println("Id du mur :"+this.getId());
        System.out.print("Coin début :"+this.getCoinDebut()+" Coin Fin : "+this.getCoinFin());
    }

    @Override
    public String toString() {
        return "Mur{" + "id=" + id + ", coinDebut=" + coinDebut + ", coinFin=" + coinFin + ", hauteur=" + hauteur + ", longueur=" + longueur + '}';
    }
    
  public double calculSurface(int nbPortes, int nbFenetres){
        double surfaceTotale = this.longueur()*hauteur; // Assumons que cette méthode renvoie la surface totale de la pièce.
        double surfacePortes = nbPortes * 0.90 * 2.10; // Surface totale occupée par les portes.
        double surfaceFenetres = nbFenetres * 1.20 * 1.20; // Surface totale occupée par les fenêtres.
        return surfaceTotale - (surfacePortes + surfaceFenetres);
}
    
}
