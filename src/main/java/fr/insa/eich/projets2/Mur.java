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
    private Coin CoinDebut;
    private Coin CoinFin;
    private double longueur;
    private double hauteur;
    private int nbreFenetres;
    private int nbrePortes;
    
    public Mur longueur(double x1, double x2, double y1, double y2){
        if (x1 == x2){
            this.longueur = y2 - y1;
        }
        else if (y1 == y2){
            this.longueur = x2 - x1;
        }
        return this;
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
    
}
