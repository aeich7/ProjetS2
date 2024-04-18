package fr.insa.eich.projets2;

import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author arbre
 */
public class Plafond  {
    private int id ;
    private double surface;
    private List<Coin> Coins = new ArrayList<>();
    
    public double getsurface() {
        return surface;
    }
     public void setsurface(double surface) {
        this.surface = surface;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     public void AjouterCoin(Coin Coin){
        Coins.add(Coin);
    }

    @Override
    public String toString() {
        return "Plafond{" + "id=" + id + ", surface=" + surface + ", Coins=" + Coins + '}';
    }
   
     }
