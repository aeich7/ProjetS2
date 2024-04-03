/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.eich.projets2;
import java.util.Scanner;
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
            Piece piece = new Piece(pieceId, 3);          
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
                Coin debut = new Coin(pointDebutId, debutX, debutY);

                System.out.println("Entrer les coordonnées du point de fin du mur " + murId);
                System.out.print("x: ");
                double finX = scanner.nextDouble();
                System.out.print("y: ");
                double finY = scanner.nextDouble();
                scanner.nextLine(); 

                int pointFinId = ++pointIdCompteur;
                Coin fin = new Coin(pointFinId, finX, finY);

                Mur mur = new Mur(murId, debut, fin, 3.0);
                piece.AjouterMur(mur);
            }
            piece.afficher();
        }

        scanner.close();
    }
}
