/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.eich.projets2;
import java.io.*; 
import java.util.*;

/**
 *
 * @author bmerckling
 */

public class ProjetS2 {

    public static void main(String[] args) {
    System.out.println(" Regles pour la saisie : 1. Chaque piece fait 4 murs vous devez écrire les deux coins de chaque mur ");
    System.out.println(" 2. Meme si deux murs ont des coins communs, il faut reecrire les coordonnees ");
    System.out.println(" 3. Vous devez obligatoirement écrire les deux coins d'un mur à la suite ");
    System.out.println(" Ex : le mur 1 a deux coins, donc il faut écrire le coin 1 dans la première case et le coin 2 dans la deuxième case et ainsi de suite ");


    System.out.println(" Premierement, donnez les coordonées (x) des coins de vos murs puis, quand c'est fini ecrivez 000 " );
    double [][] Couple_coins  = new double [100][3];
    int n=0; 
   while (Lire.d() != 000) {
    Couple_coins [n][0] = Lire.d(); // On remplit a colonne correspondante aux x
    Couple_coins[n][2] = n+1; // On remplit la colonne avec le n° de coin (id)
    n=n+1;
   }
   System.out.println(" Maintenant, donnez les coordonées y associees aux x des coins de vos murs puis, quand c'est fini ecrivez fin " );
   System.out.println( " Indication : x ecrit en premier et y ecrit en premier = un coin ");
   while (Lire.d() != 000) {
    Couple_coins [n][1] =Lire.d(); // On remplit la colonne correspondante aux y 
    n=n+1;
   }
 System.out.println(Couple_coins); // On a maintenant tous les coins répértoriés dans un tableau 

 int i,j;                           // On va associer tous ces coins 2 par 2 pr faire un mur (case 1 du tableau de coins avec la case 2 du tableau de coins) 
 

     
                             
 double Murs [][] = new double [2][n/2]; // On fait un tableau dans lequel on mettra le n° du mur et la surface du mur                  
 for (j=0; j< ((n/2)-1); j++){  
    for (i=0 ; i<(n-1); i=i+2){
      Mur Mur_j = new Mur ((j+1),Couple_coins[i][0],Couple_coins[i+1][0],Couple_coins[i][1],Couple_coins[i+1][1],3); // on utilise la méthode public Mur
      Murs[0][j] = j+1;
      double Longueur;
        Longueur = Mur_j.longueur(Couple_coins[i][0],Couple_coins[i+1][0],Couple_coins[i][1],Couple_coins[i+1][1]);    // utilisation de la méthode longueur présente dans la classe Mur
        Murs[1][j]= Longueur * 3;   // pour avoir la surface on fait la longueur fois la hauteur qui est cte
    }
}       
  System.out.println(Murs);
 

}

    
}
