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
    private static int compteurId = 0;
    private int id;
    private Coin coinDebut;
    private Coin coinFin;
    private double hauteur;
    private double longueur;
    private int nbPortes;
    private int nbFenetres;
    private int IdRevetement;
   

    public Mur(Coin coinDebut, Coin coinFin, double hauteur) {
        compteurId++;
        this.id = compteurId;
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
        return "Mur{" + "id=" + id + ", coinDebut=" + coinDebut.getId() + ", coinFin=" + coinFin.getId() + ", nbPortes=" + nbPortes + ", nbFenetres=" + nbFenetres + ", hauteur=" + hauteur + ", IdRevetement=" + IdRevetement + '}';
    }

   

    
    
  public double calculSurface(){
        double surfaceTotale = this.longueur()*this.hauteur; // Assumons que cette méthode renvoie la surface totale de la pièce.
        double surfacePortes = this.nbPortes * 0.90 * 2.10; // Surface totale occupée par les portes.
        double surfaceFenetres = this.nbFenetres * 1.20 * 1.20; // Surface totale occupée par les fenêtres.
        return surfaceTotale - (surfacePortes + surfaceFenetres);
}
  
    public boolean isNear(double x, double y, double threshold) {
    Coin debut = this.getCoinDebut();
    Coin fin = this.getCoinFin();
    
    double dx = fin.getX() - debut.getX();
    double dy = fin.getY() - debut.getY();
    double lengthSquared = dx * dx + dy * dy;
    
    // Calculer la projection t du point (x, y) sur la ligne (debut -> fin)
    double t = ((x - debut.getX()) * dx + (y - debut.getY()) * dy) / lengthSquared;

    // Trouver le point le plus proche sur la ligne au point (x, y)
    double nearestX, nearestY;
    if (t < 0) {
        nearestX = debut.getX();
        nearestY = debut.getY();
    } else if (t > 1) {
        nearestX = fin.getX();
        nearestY = fin.getY();
    } else {
        nearestX = debut.getX() + t * dx;
        nearestY = debut.getY() + t * dy;
    }

    // Calculer la distance du point (x, y) au point le plus proche sur la ligne
    double distance = Math.sqrt((x - nearestX) * (x - nearestX) + (y - nearestY) * (y - nearestY));

    // Retourner si cette distance est inférieure au seuil
    return distance <= threshold;
}
    
    public int getIdRevetement() {
        return IdRevetement;
    }

    public void setIdRevetement(int IdRevetement) {
        this.IdRevetement = IdRevetement;
    }

    public int getNbPortes() {
        return nbPortes;
    }

    public void setNbPortes(int nbPortes) {
        this.nbPortes = nbPortes;
    }

    public int getNbFenetres() {
        return nbFenetres;
    }

    public void setNbFenetres(int nbFenetres) {
        this.nbFenetres = nbFenetres;
    }
    
    
    
    
}
