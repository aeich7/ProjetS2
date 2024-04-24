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
    private static int idCompteur = 0;
    private int id;
    private int niveau;
    private List<Piece> ListePieces = new ArrayList<>();
    private double surfaceAppartement;
    
    public Appartement(int niveau){
        idCompteur++;
        this.id = idCompteur;
        this.niveau =niveau;
    }
    public double surfaceAppartementAuSol(){
        surfaceAppartement=0;
        for(Piece piece : ListePieces){
         surfaceAppartement+=piece.getSol().calculSurface();   
        }
        return surfaceAppartement;
            }
    
    public void AjouterPiece(Piece Piece1){
        ListePieces.add(Piece1);
    }

    public List<Piece> getListePieces() {
        return ListePieces;
    }
    public void ajouterPiece(Piece piece){
        ListePieces.add(piece);
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Appartement{" + "id=" + id + ", niveau=" + niveau + ", ListePieces=" + ListePieces + ", surfaceAppartement=" + surfaceAppartement + '}';
    }
    
    
    
}
