/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;

import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class Ouvertures {
    private int id; 
    private double dim_x;  // pour connaitre longueur d'un côté 
    private double dim_y;   //pour connaitre longueur de l'autre côté 

    public Ouvertures(int id, double dim_x, double dim_y) {
        this.id = id;
        this.dim_x = dim_x;
        this.dim_y = dim_y;
    }
    
     public static double surface (int nombre_fenetres, int nombre_portes){
         double aire_ouvertures=0; 
         aire_ouvertures = aire_ouvertures + 1.20*1.20*nombre_fenetres;
         aire_ouvertures = aire_ouvertures + 0.90*2.10*nombre_portes;
         return aire_ouvertures;
     }
     
     public String toString (double dim_x, double dim_y){
    if (dim_x ==1.20){
        return "fenêtre";
    }
    if ((dim_x==0.90)||(dim_x==2.10)){
        return "porte";
    }
    else {
        return "tremis";
    }
}

    public int getId() {
        return id;
    }
     
      public void afficher() {
    System.out.println("L'ouverture est un(e) "+this.toString());
    System.out.println("Id du (de la) " +this.toString()+ " est " +this.getId()+"Dim_x : "+this.dim_x + "Dim_y : "+this.dim_y); 
}   
}
