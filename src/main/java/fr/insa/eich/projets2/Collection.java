/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arbre
 */
public class Collection {
    private final List murs;
    private final List pieces;
    private final List appartements;
    private final List niveaux;
     public Collection() {
             this.murs = new ArrayList<>();
             this.pieces = new ArrayList<>();
             this.appartements = new ArrayList<>();
             this.niveaux = new ArrayList<>();
     }

     public void ajouterMur(Mur mur) {
             this.murs.add(mur);
     }

     public void supprimerMur(Mur mur) {
             this.murs.remove(mur);
     }

     public List getMurs() {
             return this.murs;
     }
     public void ajouterPiece(Piece piece) {
             this.piece.add(piece);
     }

     public void supprimerPiece(Piece piece) {
             this.pieces.remove(piece);
     }

     public List getPieces() {
             return this.pieces;
     }
      public void ajouterAppartement(Appartement appartement) {
             this.appartements.add(appartement);
     }

     public void supprimerAppartement(Appartement Appartement) {
             this.appartements.remove(appartements);
     }

     public List getAppartements() {
             return this.appartements;
     }
      public void ajouterNiveau(Niveau niveau) {
             this.niveaux.add(niveaux);
     }

     public void supprimerNiveau(Niveau niveau) {
             this.niveaux.remove(niveau);
     }

     public List getNiveaux() {
             return this.niveaux;
     }


}
