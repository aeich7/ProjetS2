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
    private int niveau ;
    private int numero;
    private List<Piece> ListePieces = new ArrayList<>();
    private double surfaceAppartement;
    
    public Appartement( int niveau ,int numero){
        this.niveau =niveau;
        this.numero=numero;
    }
    
    public void afficher(){
        System.out.println("Etage:"+niveau+", numero:"+numero);
    }
    public double surfaceAppartement(){
        surfaceAppartement=0;
        for(Piece piece : ListePieces){
         surfaceAppartement+=piece.getSol().getSurface();   
        }
        return surfaceAppartement;
            }
    
    public void AjouterPiece(Piece Piece1){
        ListePieces.add(Piece1);
    }

    public List<Piece> getListePieces() {
        return ListePieces;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
