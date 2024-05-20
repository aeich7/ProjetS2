/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;
import java.util.*;
import static fr.insa.eich.projets2.Revetements.*;
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
    

     public Batiment(String type) {
        this.type = type;
        compteurID++;
        this.id=compteurID;
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

        return "Batiment{" + "id=" + id + ", " + niveauxIds + ", type=" + type + '}';
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
        for (Map.Entry<Integer, Revetements> infos : this.getListeRevets().entrySet()){
            System.out.println();
            System.out.println("Revêtement numéro : "+infos.getValue().getId()+" .");
            System.out.println("Désignation : "+infos.getValue().getDesignation()+" .");
            System.out.println("Surface totale : "+infos.getValue().getSurfaceTot()+"m2.");
            System.out.println("Prix total : "+infos.getValue().getPrixTot()+"€.");
            PrixBat = PrixBat + infos.getValue().getPrixTot();
            StringBuilder ligneDeTirets = new StringBuilder();
            for (int i = 0; i < 50; i++) {
                ligneDeTirets.append("-");
            }
            System.out.println();
            System.out.println(ligneDeTirets.toString());
        }
        System.out.println("Prix total du batiment : "+PrixBat+"€.");
        }
    
    
 
        
    }

