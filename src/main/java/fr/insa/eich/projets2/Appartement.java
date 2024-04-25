/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;
import java.util.*;

/**
 *
 * @author arbre
 */
public class Appartement {
    private int id;
    private int etage ;
    private int numero;
    private List<Piece> ListePieces = new ArrayList<>();
    private double surfaceappartement;
    public double surfaceTotaleRevetement;
    
    public Appartement( int etage,int numero){
        this.etage=etage;
        this.numero=numero;
    }
    
    public void afficher(){
        System.out.println("Etage:"+etage+", numero:"+numero);
    }
    public double surfaceappartement(){
        surfaceappartement=0;
        for(Piece Piece : Piece){
         surfaceappartement+=piece.getSurface();   
        }
        return surfaceappartement;
            }
     public double surfaceTotaleRevetement(){
        surfaceTotaleRevetement=0;
        for(Piece piece : ListePieces){
         surfaceTotaleRevetement+=piece.SurfaceRevetement();   
        }
        return surfaceTotaleRevetement;
            }

    
    public void AjouterPiece(Piece Piece1){
        ListePieces.add(Piece1);
    }

    public List<Piece> getListePiece() {
        return ListePieces;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
