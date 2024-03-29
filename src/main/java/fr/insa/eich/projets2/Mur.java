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
    private double xDeb;
    private double xFin;
    private double yDeb;
    private double yFin;
    private double hauteur;
    private double longueur;
    
    public Mur(int id, double xDeb, double xFin, double yDeb, double yFin, int hauteur) {
        this.id = id;
        this.xDeb = xDeb;
        this.xFin = xFin;
        this.yDeb = yDeb;
        this.yFin = yFin;
        this.hauteur = hauteur;
    }
           
    public double longueur(double xDeb,double xFin, double yDeb, double yFin){
        if (xDeb == xFin){
            longueur = yFin - yDeb;
        }
        else if (yDeb == yFin){
            longueur = xFin - xDeb;
        }
        return longueur;
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
