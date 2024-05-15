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
    private double surfaceAppartement;
    private Color color;

    
    public Appartement(int niveau){
        idCompteur++;
        this.id = idCompteur;
        this.niveau =niveau;
        this.color = generateRandomColor();
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
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
        return "Appartement{" + "id=" + id + ", niveau=" + niveau + ", ListePieces=" + ListePieces + ", surfaceAppartement=" + surfaceAppartement + '}';
    }
    
    public String toComboBox(){ // Pour l'affichage dans le ComboBox
        return "Appartement "+id;
    }
    
}
