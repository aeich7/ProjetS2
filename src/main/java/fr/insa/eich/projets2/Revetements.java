/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;

/**
 *
 * @author bruno
 */
public class Revetements {
    private int id; 
    private String designation; 
    private boolean pourMur;
    private boolean pourSol;
    private boolean pourPlafond; 
    private double Prixunitaire; 

    public Revetements(int id, String designation, boolean pourMur, boolean pourSol, boolean pourPlafond, double Prixunitaire) {
        this.id = id;
        this.designation = designation;
        this.pourMur = pourMur;
        this.pourSol = pourSol;
        this.pourPlafond = pourPlafond;
        this.Prixunitaire = Prixunitaire;
    }
    
    
    
}
