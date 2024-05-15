/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;
import java.util.List;
/**
 *
 * @author eicha
 */
public class Coin  {
   
    private static int idCompteur=0;
    private int id;
    private double x;
    private double y;

    public Coin(double x, double y) {
        this.id = idCompteur++;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }
    
     public static int getIdCompteur() {
        return idCompteur;
    }
     
    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
   
   public boolean equals(Coin coin) {
        return ((Math.abs(coin.getX() - this.x) <= 0.5) && (Math.abs(coin.getY() - this.y) <= 0.5));
    }
   
   public static Coin findCoinAt(List<Coin> coins, double x, double y) {
        for (Coin coin : coins) {
            if (Math.abs(coin.getX() - x) <= 15 && Math.abs(coin.getY() - y) <= 15) {
                return coin;
            }
        }
        return null;
    }

    public static void setIdCompteur(int idCompteur) {
        Coin.idCompteur = idCompteur;
    }
   
   
   
}



    

