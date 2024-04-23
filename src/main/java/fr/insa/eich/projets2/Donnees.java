/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2;

/**
 *
 * @author eicha
 */

// Classe permettant l'échange de données entre les différentes actions du contrôleur
public class Donnees<T> {
    private T currentData;

    public T getCurrentData() {
        return currentData;
    }

    public void setCurrentData(T data) {
        this.currentData = data;
    }
    
}
