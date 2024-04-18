/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.eich.projets2;
import static fr.insa.eich.projets2.Revetements.Tableau;
import java.io.*; 
import java.util.*;

/**
 *
 * @author bmerckling
 */

public class ProjetS2 {
    

    public static void afficherMatrice(String[][] matrice) { // procédure qui sert juste à afficher la matrice pour vérifier si c'est okkkk
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println(); // Nouvelle ligne après chaque ligne de la matrice
        }
    }

    public static void main(String[] args) {
        // Exemple d'utilisation de la méthode
        String[][] contenu = new String[19][6];  // initialisation d'une matrice pour y mettre les données récupérées dans la méthode Tableau
        String[][] resultat = Tableau("Tableau.txt", contenu); // création d'une autre matrice pour y mettre celle créé dans la méthode Tableau pour pouvoir l'afficher
         afficherMatrice(resultat);
     }
}
   