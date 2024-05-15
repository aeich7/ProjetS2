package fr.insa.eich.projets2;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author arbre
 */
public class Plafond  {
    private static int idCompteur=0;
    private int id ;
    private HashSet<Coin> Coins = new HashSet<>();
     private Revetements revetement;

    public Plafond() {
        idCompteur++;
        this.id = idCompteur;
        
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
    
    public double calculSurface() {
        if (Coins == null || Coins.size() != 4) {
            throw new IllegalArgumentException("Il doit y avoir exactement quatre coins.");
        }
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        for (Coin coin : Coins) {
            if (coin.getX() < minX) {
                minX = coin.getX();
            }
            if (coin.getX() > maxX) {
                maxX = coin.getX();
            }
            if (coin.getY() < minY) {
                minY = coin.getY();
            }
            if (coin.getY() > maxY) {
                maxY = coin.getY();
            }
          
        }
        double longueur = maxX - minX;
        double largeur = maxY - minY;
        double surface = longueur * largeur;
        return surface;
    }

    public HashSet<Coin> getCoins() {
        return Coins;
    }
    
    
    public boolean equals(Plafond plafond) {
        return (this.getCoins()== plafond.getCoins());
    }
   
     }
