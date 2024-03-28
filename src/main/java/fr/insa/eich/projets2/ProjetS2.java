/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.eich.projets2;
import java.io.*; 
import java.util.*;

/**
 *
 * @author eicha
 */
public class ProjetS2 {

    public static void main(String[] args) {
    System.out.println(" Regles pour la saisie : 1. Chaque piece fait 4 murs vous devez écrire les deux coins de chaque mur ");
    System.out.println(" 2. Meme si deux murs ont des coins communs, il faut reecrire les coordonnees ");
    System.out.println(" Premierement, donnez les coordonées (x) des coins de vos murs puis, quand c'est fini ecrivez fin " );
    String [][] Couple_coins  = new String [100][2];
    int n=0;
   String tabulation = (Lire.S()); 
   while (tabulation != "fin") {
    Couple_coins [n][1] = tabulation;
    n=n+1;
   }
   System.out.println(" Maintenant, donnez les coordonées y associees aux x des coins de vos murs puis, quand c'est fini ecrivez fin " );
   System.out.println( " Indication : x ecrit en premier et y ecrit en premier = un coin ");
   while (tabulation != "fin") {
    Couple_coins [n][2] = tabulation;
    n=n+1;
   }
 System.out.println(Couple_coins);
   
   
    }
}
