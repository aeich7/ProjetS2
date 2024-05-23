/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;
import java.util.*;
import static fr.insa.eich.projets2.Revetements.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.File;
/**
 *
 * @author arbre
 */
public class  Batiment {
    private static int compteurID = 0;
    private int id;
    private int nbNiveaux;
    private List<Niveau> ListeNiveaux= new ArrayList<>();
    private String type;
    private Map<Integer, Revetements> ListeRevets;
    private String nom;
    
    

     public Batiment(String type, String nom) {
        this.type = type;
        compteurID++;
        this.id=compteurID;
        this.nom = nom;
     }

     public String getType() {
       return type;
     }

     public void setType(String type) {
             this.type = type;
     }
     
    
    public void setListeRevets(Map<Integer, Revetements> ListeRevets) {
        this.ListeRevets = ListeRevets;
    }

    public Map<Integer, Revetements> getListeRevets() {
        return ListeRevets;
    }
    public void Afficher(){
       System.out.println("Type de batiment : " + type);
    }
    
    public void AjouterNiveau(Niveau Niveau1){
        ListeNiveaux.add(Niveau1);
    }
    
    public List<Niveau> getListeNiveaux() {
        return ListeNiveaux;
    }
       public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    
    
    @Override
    public String toString() {
        String niveauxIds = "ListeNiveaux={";
        for (Niveau niveau : ListeNiveaux) {
            niveauxIds += niveau.getId() + ", ";
        }
        if (!ListeNiveaux.isEmpty()) {
            niveauxIds = niveauxIds.substring(0, niveauxIds.length() - 2); // Supprimer la virgule et l'espace en trop à la fin
        }
        niveauxIds += "}";

        return "Batiment{" + "id=" + id + ", " + niveauxIds + ", type=" + type + ", nom="+ nom + '}';
    }

    public int getNbNiveaux() {
        return nbNiveaux;
    }

    public void setNbNiveaux(int nbNiveaux) {
        this.nbNiveaux = nbNiveaux;
    }
    
    public void CalculDevis(){
        for (Map.Entry<Integer, Revetements> Revets : ListeRevets.entrySet()){ //Réinitialiser le devis à chaque itération de la méthode Devis
            Revets.getValue().setSurfaceTot(0);
            Revets.getValue().setPrixTot(0);
        }
        if(this.type.equalsIgnoreCase("Immeuble")){
            for (Niveau niv : this.getListeNiveaux()){
                for (Appartement appart : niv.getListeAppartement()){
                    for (Piece piece : appart.getListePieces()){
                        for (Mur mur : piece.getListeMurs()){
                            ActualiserRevetMur(mur.getIdRevetement(),this.ListeRevets,mur.calculSurface());
                        }
                        ActualiserRevetSolPlafond(piece.getSol().getIdRevetement(),this.ListeRevets,piece.getSol().calculSurface());
                        ActualiserRevetSolPlafond(piece.getPlafond().getIdRevetement(),this.ListeRevets,piece.getPlafond().calculSurface());
                    }
                   
                }
                
            }
        }
        else{
            for (Niveau niv : this.getListeNiveaux()){
                for (Piece piece : niv.getListePieces()){
                        for (Mur mur : piece.getListeMurs()){
                            ActualiserRevetMur(mur.getIdRevetement(),this.ListeRevets,mur.calculSurface());
                        }
                        ActualiserRevetSolPlafond(piece.getSol().getIdRevetement(),this.ListeRevets,piece.getSol().calculSurface());
                        ActualiserRevetSolPlafond(piece.getPlafond().getIdRevetement(),this.ListeRevets,piece.getPlafond().calculSurface());
                    }
            }
        }
        double PrixBat = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter ("Devis.txt"))) {
        for (Map.Entry<Integer, Revetements> infos : this.getListeRevets().entrySet()){
            writer.newLine();
            writer.write("Revêtement numéro : "+infos.getValue().getId()+" .");
            writer.write("Désignation : "+infos.getValue().getDesignation()+" .");
            writer.write("Surface totale : "+infos.getValue().getSurfaceTot()+"m2.");
            writer.write("Prix total : "+infos.getValue().getPrixTot()+"€.");
            PrixBat = PrixBat + infos.getValue().getPrixTot();
            StringBuilder ligneDeTirets = new StringBuilder();
            for (int i = 0; i < 50; i++) {
                ligneDeTirets.append("-");
            }
            writer.newLine();
            writer.write(ligneDeTirets.toString());
        }
        writer.newLine(); 
        writer.newLine();
        writer.write("Prix total du batiment : "+PrixBat+"€.");
        }catch (IOException e){
                System.err.println("Une erreur est survenue lors de l'écriture dans le fichier ");
                }
        }
    
    
        public void sauvegarderBatiment() {
            File file = new File(this.getNom()+".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            int nbNiveaux = this.getNbNiveaux();
            // Sauvegarder les informations de chaque niveau
            writer.write(this.toString());
            writer.newLine();

        for (int i = 0; i < nbNiveaux; i++) {
            writer.write(this.getListeNiveaux().get(i).toString());
            writer.newLine();
            
            List<Mur> ListeMurs = this.getListeNiveaux().get(i).getMurs();
            List<Coin> ListeCoins = this.getListeNiveaux().get(i).getCoins();
            List<Piece> ListePieces = this.getListeNiveaux().get(i).getPieces();
            List<Appartement> ListeAppartements = this.getListeNiveaux().get(i).getListeAppartement();
            
           
            for (Coin coin : ListeCoins) {
                writer.write(coin.toString());
                writer.newLine();
            }
            
             for (Mur mur : ListeMurs) {
                writer.write(mur.toString());
                writer.newLine();
            }
            
            
            for (Piece piece : ListePieces) {
                writer.write(piece.toString());
                writer.newLine();
                writer.write(piece.getPlafond().toString());
                writer.newLine();
                writer.write(piece.getSol().toString());
                writer.newLine();
            }

            if (this.type.equals("Immeuble")){
                for (Appartement appart : ListeAppartements) {
                    writer.write(appart.toString());
                    writer.newLine();
            }
            }
        }
        
        for (Map.Entry<Integer, Revetements> Revets : ListeRevets.entrySet()){
            writer.write(Revets.getValue().toString());
            writer.newLine();
        }
        writer.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
 
        
    }

