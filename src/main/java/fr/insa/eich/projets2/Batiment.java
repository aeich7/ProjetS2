/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;
import java.util.*;

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
        return "Batiment{" + "id=" + id + ", ListeNiveaux=" + ListeNiveaux + ", type=" + type + '}';
    }

    public int getNbNiveaux() {
        return nbNiveaux;
    }

    public void setNbNiveaux(int nbNiveaux) {
        this.nbNiveaux = nbNiveaux;
    }
    
    
        
    }

