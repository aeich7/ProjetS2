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
    private int idétage ;
    private List<Piece> ListePieces = new ArrayList<>();
    
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
