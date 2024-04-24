/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2.gui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;
import javafx.scene.canvas.Canvas;
import javafx.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;

/**
 *
 * @author eicha
 */
public class MainPane extends BorderPane{
    
    private Controleur controleur;
    
    private ComboBox<String> cbNiveaux; // ComboBox pour les niveaux
    
    private VBox vbHaut;
    private VBox vbDroit;
    
    private MenuBar menubar;
    
  
    
    private Menu mFichier;
    private Menu mAide;
    private Menu mCredits;
    
    
    private MenuItem newItem;
    private MenuItem openItem;
    private MenuItem SaveItem;
    
    private RadioButton rbCoins;
    private RadioButton rbMurs;
    private RadioButton rbPieces;

    
    private DessinCanvas cDessin;
    
    public MainPane(){
        this.cDessin = new DessinCanvas();
        this.setCenter(this.cDessin);
        this.controleur = new Controleur(this);
        this.menubar = new MenuBar();
        
        this.mFichier = new Menu("Fichier");
        this.mAide = new Menu("Aide");
        this.mCredits = new Menu("Crédits");
        
        this.newItem = new MenuItem("Nouveau projet");
        this.openItem = new MenuItem("Ouvrir");
        this.SaveItem = new MenuItem("Sauvegarder");
        
        this.rbCoins = new RadioButton("Coins");
        rbCoins.setOnAction(e ->{
            this.controleur.ActivCoinDrawMode();
            this.controleur.DesactivWallMode();
            this.controleur.DesactivPieceMode();
        });
        this.rbMurs = new RadioButton("Murs");
        rbMurs.setOnAction(a ->{
            this.controleur.ActivWallMode();
            this.controleur.DesactivCoinDrawMode();
            this.controleur.DesactivPieceMode();
        });

        this.rbPieces= new RadioButton("Piece");
        rbPieces.setOnAction(a ->{
            this.controleur.DesactivCoinDrawMode();
            this.controleur.DesactivWallMode();
            this.controleur.ActivPieceMode();
        });
        
        this.cbNiveaux = new ComboBox<>(); // Initialisation de ComboBox
        this.cbNiveaux.setPromptText("Sélectionnez un niveau");

        
        menubar.getMenus().addAll(mFichier,mAide,mCredits);// Création de la barre de Menus
        mFichier.getItems().addAll(newItem,openItem,SaveItem);// Sous-menus de fichier
        newItem.setOnAction(t ->{
            this.controleur.NouveauProjet();
        });
        this.vbHaut = new VBox(menubar);
        this.setTop(vbHaut);
        
        ToggleGroup Options = new ToggleGroup(); // Faire en sorte que seulement un des boutons puisse être sélectionné à la fois (Coins OU Murs OU Pieces)
        
        rbCoins.setToggleGroup(Options);
        rbMurs.setToggleGroup(Options);
        rbPieces.setToggleGroup(Options);
        this.vbDroit = new VBox(cbNiveaux,rbCoins, rbMurs, rbPieces);
        this.setRight(vbDroit);
        this.controleur.changeEtat(0);
    }

    public MenuBar getMenubar() {
        return menubar;
    }

    public Menu getmFichier() {
        return mFichier;
    }

    public Menu getmAide() {
        return mAide;
    }

    public Menu getmCredits() {
        return mCredits;
    }

    public MenuItem getNewItem() {
        return newItem;
    }

    public MenuItem getOpenItem() {
        return openItem;
    }

    public MenuItem getSaveItem() {
        return SaveItem;
    }

    public DessinCanvas getcDessin() {
        return cDessin;
    }

    public VBox getVbHaut() {
        return vbHaut;
    }

    public VBox getVbDroit() {
        return vbDroit;
    }

    public Controleur getControleur() {
        return controleur;
    }

    public ComboBox<String> getCbNiveaux() {
        return cbNiveaux;
    }
    
   
    
    
}
