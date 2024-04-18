/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;
import java.io.*;

/**
 *
 * @author Bruno
 */
public class Revetements {
    private int id; 
    private String designation; 
    private boolean pourMur;
    private boolean pourSol;
    private boolean pourPlafond; 
    private double Prixunitaire; 

    public Revetements(int id, String designation, boolean pourMur, boolean pourSol, boolean pourPlafond, double Prixunitaire) {
        this.id = id;
        this.designation = designation;
        this.pourMur = pourMur;
        this.pourSol = pourSol;
        this.pourPlafond = pourPlafond;
        this.Prixunitaire = Prixunitaire;
    }
   public static int Comptageligne(String Fichier){ // compter le nombre de lignes du fichier à lire
		int compteur=0;
		try{
			BufferedReader txt = new BufferedReader(new FileReader("Fichier.txt"));
			String line;
			while ((line=txt.readLine())!=null){
				compteur=compteur+1;     
			}
		}
		catch(IOException e){
			e.printStackTrace();	// Renvoie le chemin où se trouve l'erreur et la nature de l'erreur
		}
		return compteur;
	}
   public static String [][]Tableau (String Fichier, String Contenu [][]) {
      int line=0; 
     try
       {
           BufferedReader in = new BufferedReader (new FileReader("Tableau.txt"));
           String ligne, mot; 
           while ((ligne = in.readLine())!= null){
               int curseur =0;
               int colonne = 0; 
               while (curseur<ligne.length()){
                   if (ligne.charAt(curseur)==';'){
                       curseur = curseur +1;
                   }
                   else { 
                       mot = "";
                            
               while (ligne.charAt(curseur ) != ';'){
                   if ((int)ligne.charAt(curseur)==233){ // pour gérer les é
                       mot= mot+'e';
                   }
                   else {
                   if ((int)ligne.charAt(curseur)==232){ // pour gérer les è
                       mot=mot +'e';
                   }
                   else {
                   mot= mot+ligne.charAt(curseur); 
               }
            }
                   curseur = curseur +1; 
                   }
               Contenu[line][colonne] = mot; 
               colonne = colonne +1;
                   }  
       }
               line =line+1;
           }
       }
       catch (FileNotFoundException err){
           System.out.println("Erreur, le fichier n'existe pas ! /n" + err);}
       catch (IOException err){
           System.out.println("Erreur : /n "+err);}
     return Contenu; 
       }
           }
   

