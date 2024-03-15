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
    private int nombre_fenetres;
    private int nombre_portes; 
    private double x; 
    private double y; 
    private double aire_ouvertures; 
    
     public Ouvertures(int id, int nombre_fenetres, int nombre_portes, double aire_ouverture) {
        this.id = id;
        this.nombre_fenetres = nombre_fenetres;
        this.nombre_portes = nombre_portes;
        this.aire_ouvertures = aire_ouverture;
    }
     public Ouvertures aire (int id, int nombre_portes, int nombre_fenetres, double x, double y){
         if (x==1.20) {
            this.aire_ouvertures = x*y*nombre_fenetres;
         }
         else if ((x==0.90)||(x==2.10)){
             this.aire_ouvertures = x*y*nombre_portes;
         }
         return this;
     }
         
}
