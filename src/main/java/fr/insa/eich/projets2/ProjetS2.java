/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.eich.projets2;
import java.util.Scanner;
import java.util.*;

/**
 *
 * @author eicha
 */
public class ProjetS2 {
    private static int pointIdCompteur = 0;
    private static int murIdCompteur = 0;
    private static int pieceIdCompteur = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
         
        System.out.println("Combien de pièces voulez-vous créer ?");
        int nombreDePieces = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < nombreDePieces; i++) {
            int pieceId = ++pieceIdCompteur;
            Piece piece = new Piece();          
            System.out.println("Combien de murs pour la pièce " + pieceId + " ?");
            int nombreDeMurs = scanner.nextInt();
            scanner.nextLine(); 

            for (int j = 0; j < nombreDeMurs; j++) {
                int murId = ++murIdCompteur;

                System.out.println("Entrer les coordonnées du point de début du mur " + murId);
                System.out.print("x: ");
                double debutX = scanner.nextDouble();
                System.out.print("y: ");
                double debutY = scanner.nextDouble();
                scanner.nextLine(); 

                int pointDebutId = ++pointIdCompteur;
                Coin debut = new Coin(debutX, debutY);
                System.out.println("Entrer les coordonnées du point de fin du mur " + murId);
                System.out.print("x: ");
                double finX = scanner.nextDouble();
                System.out.print("y: ");
                double finY = scanner.nextDouble();
                scanner.nextLine(); 
                int pointFinId = ++pointIdCompteur;
                Coin fin = new Coin(finX, finY);
                if (piece.getListeMurs() != null){
                    for (Mur mur : piece.getListeMurs()){
                        if ((mur.getCoinDebut().equals(debut))) {
                            debut = mur.getCoinDebut();
                            pointIdCompteur = pointIdCompteur -1;
                        }
                        else if(mur.getCoinFin().equals(debut)){
                            debut = mur.getCoinFin();
                            pointIdCompteur = pointIdCompteur -1;
                        }
                        else if ((mur.getCoinDebut().equals(fin))) {
                            fin = mur.getCoinDebut();
                            pointIdCompteur = pointIdCompteur -1;
                        }
                        else if(mur.getCoinFin().equals(fin)){
                            fin = mur.getCoinFin();
                            pointIdCompteur = pointIdCompteur -1;        
                    } 
                }                   
                
            }
                Mur mur = new Mur(debut, fin, 3.0);
                piece.AjouterMur(mur);
        }
       piece.afficher();
      
        
    }
    scanner.close();
    }
    
}


