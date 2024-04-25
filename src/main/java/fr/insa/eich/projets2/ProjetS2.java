/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.eich.projets2;
import static fr.insa.eich.projets2.Revetements.*;
import static fr.insa.eich.projets2.Ouvertures.*;
import static fr.insa.eich.projets2.Mur.*;

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
        
        Scanner scanner = new Scanner(System.in);
        double hauteur; 
        System.out.println("Quelle est la hauteur sous plafond de l'immeuble ? ");
        hauteur = scanner.nextDouble(); 
        scanner.nextLine(); 
        
        String[][] contenu = new String[19][6];  // initialisation d'une matrice pour y mettre les données récupérées dans la méthode Tableau
        String[][] resultat = Tableau("Tableau.txt", contenu); // création d'une autre matrice pour y mettre celle créé dans la méthode Tableau pour pouvoir l'afficher
        
        
        System.out.println(" Voici les revêtements possibles : ");
        afficherMatrice(resultat);
        
        String [][]results = mur(resultat); //création d'une matrice pour y mettre les revêtements qui peuvent aller avec mur
        // results prend la matrice resultat et n'écrit que les rvet utiles pour un mur 
        System.out.println();
        
        // création d'un mur avec les coins ... (partie déjà faite par Orély1 dans sa branche github) 
        
        
        // Tout d'abord, on va calculer surface totale du mur sans les ouvertures 
        // Dans la classe mur, il y a une methode pour calculer longueur du mur. Vu qu'on a deja la hauteur, calcul est rapide 
        // Je reprends les notations d'Auré, comme ça adaptations sont plus faciles 
        
        
        
        // La partie avec l'attribution des valeurs est juste ici pour le test
        double debutX, finX, finY, debutY; 
        debutX = 1.20; 
        finX= 5.60; 
        debutY = 0;
        finY=0;
        // fin de la partie spécifique au test
        
        
        
        Double dim_inconnue; 
        dim_inconnue = longueur(debutX, finX, debutY, finY); 
        System.out.println(" La longueur est de : "+ dim_inconnue);
        double surface_mur_nu =dim_inconnue * hauteur; 
        System.out.println(" La surface du mur sans revetement est de : "+surface_mur_nu); 
        System.out.println();
        
        
        System.out.println(" Donnez le nombre de portes dans ce mur ");
        int nbre_portes; 
        nbre_portes = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        
        System.out.println(" Donnez le nombre de fenêtres sur ce mur " );
        int nbre_fenetres; 
        nbre_fenetres = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        
        double dimension = surface(nbre_fenetres, nbre_portes);
        System.out.println(" Ceci est la surface totale de toutes les ouvertures : " + dimension);
                
        Double dim_tot = surface_mur_nu - dimension; 
        System.out.println("La surface totale pour y mettre du revêtements est : "+dim_tot);
        System.out.println();
        
        // Tout ce qui est avant sert au calcul de la surface du mur où on applique du revêtement
        // Cela sert aussi pour le devis (final et par revetements) evidemment 
        
        // On doit maintenant choisir un revetement à mettre sur notre beau mur
        
        System.out.println(" Voici les revêtements que vous pouvez utiliser pour les murs : ");
        afficherMatrice(results);
        System.out.println();
        System.out.println(" Donnez l'identifiant de celui que vous voulez pour votre mur ");
        int l=0;
        int valeur = Lire.i();
        while (Integer.parseInt(results[l][0])!=valeur){  // On lit l'id rentré par user et on cherche le prix à l'unite
                l=l+1;     // pour cela, on convertit tout en int et on regarde ligne par ligne la 1e colonne (celle des id)
                 
        }
        double prix_unitaire = Double.parseDouble(results[l][2]);
        double prix_mur = dim_tot * prix_unitaire ; 
        System.out.println(" Le prix du mur est de "+ prix_mur + "€");
        
        String [][]results1 = sol(resultat); //création d'une matrice pour y mettre les revêtements qui peuvent aller avec sol
        // results prend la matrice resultat et n'écrit que les rvet utiles pour un sol 
        System.out.println();
         
        /* System.out.println(" Voici les revêtements que vous pouvez utiliser pour les sols : ");
        afficherMatrice(results1);
        
         String [][]results2 = plafond(resultat); //création d'une matrice pour y mettre les revêtements qui peuvent aller avec plafond
        // results prend la matrice resultat et n'écrit que les rvet utiles pour un plafond
        System.out.println();
         
        System.out.println(" Voici les revêtements que vous pouvez utiliser pour les plafonds : ");
        afficherMatrice(results2);
        */
        
     
      
     }
}
   
