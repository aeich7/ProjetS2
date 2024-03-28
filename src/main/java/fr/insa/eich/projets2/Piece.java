/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;
import java.util.*; 

/**
 *
 * @author eicha
 */
public class Piece {
    private int id;
    private int sol;
    private int plafond;
    private List<Mur> ListeMurs = new ArrayList<>();

    public Piece(int id, int plafond) {
        this.id = id;
        this.plafond = plafond;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSol() {
        return sol;
    }

    public void setSol(int sol) {
        this.sol = sol;
    }

    public int getPlafond() {
        return plafond;
    }

    public void setPlafond(int plafond) {
        this.plafond = plafond;
    }
    
    public void AjouterMur(Mur Mur1){
        ListeMurs.add(Mur1);
    }

    public List<Mur> getListeMurs() {
        return ListeMurs;
    }
       
        
    }
    
