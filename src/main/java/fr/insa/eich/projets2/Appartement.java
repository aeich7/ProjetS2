/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;
import java.util.*;
import javafx.scene.paint.Color;

/**
 *
 * @author arbre
 */
public class Appartement {
    private static int idCompteur = 0;
    private int id;
    private int niveau;
    private List<Piece> ListePieces = new ArrayList<>();
    private Color color;

    
    public Appartement(int niveau){
        idCompteur++;
        this.id = idCompteur;
        this.niveau =niveau;
        this.color = generateRandomColor();
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

    public int getNiveau() {
        return niveau;
    }
    
    
    // Méthode pour générer une couleur aléatoire associée à l'appartement
    private Color generateRandomColor() {
        Random rand = new Random();
        return Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    public Color getColor() {
        return color;
    }
    
    

    @Override
    public String toString() {
        String PieceIds = "Pieces={";
        for (Piece piece : ListePieces) {
            PieceIds += piece.getId() + ",";
        }
        if (!ListePieces.isEmpty()) {
            PieceIds = PieceIds.substring(0, PieceIds.length() - 1); // Supprimer la virgule et l'espace en trop à la fin
        }
        PieceIds += "}";
        
        return "Appartement{" + "id=" + id + ", niveau=" + niveau + ", " + PieceIds + '}';
    }
    
    public String toComboBox(){ // Pour l'affichage dans le ComboBox
        return "Appartement "+id;
    }
    
}
