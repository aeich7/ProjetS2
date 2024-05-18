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
			BufferedReader txt = new BufferedReader(new FileReader(Fichier));
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
    public static String[][] Tableau(String fichier, String Contenu[][]) {
        int line = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(fichier));
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

  /*public static void main (){
      String[][] contenu = new String[19][6];  // initialisation d'une matrice pour y mettre les données récupérées dans la méthode Tableau
      String[][] resultat = Tableau("Tableau.txt", contenu); // création d'une autre matrice pour y mettre celle créé dans la méthode Tableau pour pouvoir l'afficher   
  }
    */
  public static String[][]mur(String Contents [][]) {
        int i,j, k=0,l=0 ;
        
        for(i=0;i<18;i++) {                 // comptage du nombre de lignes nécessaires au tableau de revet de mur
            if (Contents[i][2].equalsIgnoreCase("1")){
               k=k+1; 
            }
        }
        String [][]associemur= new String [k][3]; // on affiche les id, les désignations et le prix à l'unité pour permettre au client de choisir le revêtement qu'il veut 
        // On affiche tout ça dans un tableau (aka revet mur)
        // On pourra s'en servir dès que user créé un mur en lui demandant direct le revêtement qu'il veut utiliser dessus
        for(i=0;i<18;i++){     
                if (Contents[i][2].equalsIgnoreCase("1")){
                    for (j=0;j<2;j++){
                        associemur[l][j]= Contents [i][j] ;
                        associemur[l][2]= Contents [i][5];
                    }
                 l=l+1;
                }
}
        return associemur  ;
  }
  
  public static String[][]sol(String Contents [][]) {
        int i,j, k=0,l=0 ;
        
        for(i=0;i<18;i++) {                 // comptage du nombre de lignes nécessaires au tableau de revet de sol
            if (Contents[i][3].equalsIgnoreCase("1")){
               k=k+1; 
            }
        }
        String [][]associemur= new String [k][3]; // on affiche les id, les désignations et le prix à l'unité pour permettre au client de choisir le revêtement qu'il veut 
        // On affiche tout ça dans un tableau (aka revet sol)
        // On pourra s'en servir dès que user créé une pièce en lui demandant direct le revêtement qu'il veut utiliser sur le sol
        for(i=0;i<18;i++){     
                if (Contents[i][3].equalsIgnoreCase("1")){
                    for (j=0;j<2;j++){
                        associemur[l][j]= Contents [i][j] ;
                        associemur[l][2]= Contents [i][5];
                    }
                 l=l+1;
                }
}
        return associemur  ;
  }
  
  public static String[][]plafond(String Contents [][]) {
        int i,j, k=0,l=0 ;
        
        for(i=0;i<18;i++) {                 // comptage du nombre de lignes nécessaires au tableau de revet de plafond
            if (Contents[i][4].equalsIgnoreCase("1")){
               k=k+1; 
            }
        }
        String [][]associemur= new String [k][3]; // on affiche les id, les désignations et le prix à l'unité pour permettre au client de choisir le revêtement qu'il veut 
        // On affiche tout ça dans un tableau (aka revet plafond)
        // On pourra s'en servir dès que user créé une pièce en lui demandant direct le revêtement qu'il veut utiliser au plafond
        for(i=0;i<18;i++){     
                if (Contents[i][4].equalsIgnoreCase("1")){
                    for (j=0;j<2;j++){
                        associemur[l][j]= Contents [i][j] ;
                        associemur[l][2]= Contents [i][5];
                    }
                 l=l+1;
                }
}
        return associemur  ;
  }

    @Override
    public String toString() {
        return "Revetements{" + "id=" + id + ", designation=" + designation + ", pourMur=" + pourMur + ", pourSol=" + pourSol + ", pourPlafond=" + pourPlafond + ", Prixunitaire=" + Prixunitaire + '}';
    }
  

 /*int ide = Integer.parseInt(resultat[i][0]); // manipulation pour passer de string à int 
            double prx = Double.parseDouble(resultat[i][5]); // manipulation pour passer de string à double 
     */ 


}