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
    public static String[][] Tableau(String Fichier, String Contenu[][]) {
        int line = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader("Tableau.txt"));
            String ligne;
            while ((ligne = in.readLine()) != null) {
                int curseur = 0;
                int colonne = 0;
                ligne = ligne + " ";
                while (curseur < ligne.length()) {
                    if (ligne.charAt(curseur) == ';') {
                        curseur = curseur + 1;
                    } else {
                        StringBuilder mot = new StringBuilder();
                        while (curseur < ligne.length() && ligne.charAt(curseur) != ';') {
                            char currentChar = ligne.charAt(curseur);
                            if (currentChar == 'é' || currentChar == 'è') {
                                mot.append('e');
                            } else {
                                mot.append(currentChar);
                            }
                            curseur = curseur + 1;
                        }
                        Contenu[line][colonne] = mot.toString();
                        colonne = colonne + 1;
                    }
                }
                line = line + 1;
            }
            in.close();
        } catch (FileNotFoundException err) {
            System.out.println("Erreur, le fichier n'existe pas ! /n" + err);
        } catch (IOException err) {
            System.out.println("Erreur : /n " + err);
        }
        return Contenu;
    }

  public static void main (){
      String[][] contenu = new String[19][6];  // initialisation d'une matrice pour y mettre les données récupérées dans la méthode Tableau
      String[][] resultat = Tableau("Tableau.txt", contenu); // création d'une autre matrice pour y mettre celle créé dans la méthode Tableau pour pouvoir l'afficher
  }  
}


