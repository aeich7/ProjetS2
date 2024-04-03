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
    private int id;
    private int sol;
    private int plafond;
    private List<Mur> Murs = new ArrayList<>();

    public Piece(int id, int plafond) {
        this.id = id;
        this.plafond = plafond;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSol() {
        return sol;
    }

    public void setSol(int sol) {
        this.sol = sol;
    }

    public int getPlafond() {
        return plafond;
    }

    public void setPlafond(int plafond) {
        this.plafond = plafond;
    }
    
    public List<Mur> getListeMurs() {
        return Murs;
    }
    
    public void AjouterMur(Mur Mur){
        Murs.add(Mur);
    }
    
    public void afficher(){
        System.out.println("Piece numéro : "+this.getId());
        for (Mur mur : Murs ){
            System.out.println("Id du mur : "+mur.getId()+" Début | x : "+mur.getCoinDebut().getX()+" y: "+mur.getCoinDebut().getY()+"| Fin | x : "+mur.getCoinFin().getX()+" y : "+mur.getCoinFin().getY()+" |");
        }
        
    }
}  
    
