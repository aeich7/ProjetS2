/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;
import java.util.*; 

/**
 *
 * @author eicha
 */
public class Piece {
    private static int idCompteur = 0;
    private int id;
    private Sol sol;
    private Plafond plafond;
    private List<Mur> Murs = new ArrayList<>();
    private double centreX;
    private double centreY;

    public Piece() {
        idCompteur++;
        this.id = idCompteur;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sol getSol() {
        return sol;
    }

    public void setSol(Sol sol) {
        this.sol = sol;
    }

    public Plafond getPlafond() {
        return plafond;
    }

    public void setPlafond(Plafond plafond) {
        this.plafond = plafond;
    }

   
    
    public List<Mur> getListeMurs() {
        return Murs;
    }
    
    public void AjouterMur(Mur Mur){
        Murs.add(Mur);
    }

    public double getCentreX() {
        return centreX;
    }

    public void setCentreX(double centreX) {
        this.centreX = centreX;
    }

    public double getCentreY() {
        return centreY;
    }

    public void setCentreY(double centreY) {
        this.centreY = centreY;
    }
    
    

    @Override
    public String toString() {
        return "Piece{" + "id=" + id + ", sol=" + sol + ", plafond=" + plafond + ", Murs=" + Murs + '}';
    }
   
    public void afficher(){
        System.out.println("Piece numéro : "+this.getId());
        for (Mur mur : Murs ){
            System.out.println("Id du mur : "+mur.getId()+" Début | x : "+mur.getCoinDebut().getX()+" y: "+mur.getCoinDebut().getY()+"| Fin | x : "+mur.getCoinFin().getX()+" y : "+mur.getCoinFin().getY()+" |");
        }
    
    }
}  
    
