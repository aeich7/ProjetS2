/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2.gui;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

/**
 *
 * @author eicha
 */
public class MainPane extends BorderPane{
    
    private Controleur controleur;
    
    private ComboBox<String> cbNiveaux; // ComboBox pour les niveaux
    private ComboBox<HBox> choixAppart; // Choix de l'appart pour ajouter les pièces dedans
    
    private VBox vbHaut;
    private VBox vbDroit;
    
    private MenuBar menubar;
    
  
    
    private Menu mFichier;
    private Menu mAide;
    private Menu mCredits;
    
    
    private MenuItem newItem;
    private MenuItem SaveItem;
    private MenuItem CalculItem;
    private MenuItem DevisItem;
    private MenuItem AutreItem;
    private MenuItem EcoleItem; 
    private MenuItem ElevesItem; 
    
    private RadioButton rbCoins;
    private RadioButton rbMurs;
    private RadioButton rbPieces;
    private RadioButton rbAppartement;

    
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
        this.SaveItem = new MenuItem("Sauvegarder");
        this.CalculItem = new MenuItem("Devis");
        this.DevisItem = new MenuItem("Devis");
        this.AutreItem = new MenuItem("Autre");
        this.ElevesItem = new MenuItem("Eleves");
        this.EcoleItem = new MenuItem("Ecole");
        
        
        
        this.rbCoins = new RadioButton("Coins");
        rbCoins.setOnAction(e ->{
            this.controleur.ActivCoinDrawMode();
            this.controleur.DesactivWallMode();
            this.controleur.DesactivPieceMode();
            this.controleur.DesactivAppartementMode();
            this.choixAppart.setDisable(true);
        });
        this.rbMurs = new RadioButton("Murs");
        rbMurs.setOnAction(a ->{
            this.controleur.ActivWallMode();
            this.controleur.DesactivCoinDrawMode();
            this.controleur.DesactivPieceMode();
            this.controleur.DesactivAppartementMode();
            this.choixAppart.setDisable(true);
        });

        this.rbPieces= new RadioButton("Piece");
        rbPieces.setOnAction(a ->{
            this.controleur.DesactivCoinDrawMode();
            this.controleur.DesactivWallMode();
            this.controleur.ActivPieceMode();
            this.controleur.DesactivAppartementMode();
            this.choixAppart.setDisable(true);
        });
        
        this.rbAppartement = new RadioButton("Appartement");
        rbAppartement.setOnAction(a -> {
            this.controleur.DesactivCoinDrawMode();
            this.controleur.DesactivWallMode();
            this.controleur.DesactivPieceMode();
            this.controleur.ActiveAppartementMode();
            this.choixAppart.setDisable(false);
        });
        
        this.cbNiveaux = new ComboBox<>(); // Initialisation de ComboBox des niveaux
        this.cbNiveaux.setPromptText("Sélectionnez un niveau");

        this.choixAppart = new ComboBox<>();
        this.choixAppart.setPromptText("Sélectionnez un appartement");
        
        menubar.getMenus().addAll(mFichier,mAide,mCredits);// Création de la barre de Menus
        mFichier.getItems().addAll(newItem,CalculItem,SaveItem);// Sous-menus de fichier
        mAide.getItems().addAll(DevisItem, AutreItem);
        mCredits.getItems().addAll(EcoleItem,ElevesItem);
        newItem.setOnAction(t ->{
            this.controleur.NouveauProjet();
        });
        CalculItem.setOnAction(t -> {
            this.controleur.getdBatiment().getCurrentData().CalculDevis();
        });
        DevisItem.setOnAction(t ->{
            this.controleur.Aide_devis();
        });
        AutreItem.setOnAction(t ->{
            this.controleur.Aide_autre();
        });
        EcoleItem.setOnAction(t -> {
            this.controleur.Creditsecole(); 
        });
        ElevesItem.setOnAction(t -> {
            this.controleur.Creditseleves();
        });
        
        this.vbHaut = new VBox(menubar);
        this.setTop(vbHaut);
        
        ToggleGroup Options = new ToggleGroup(); // Faire en sorte que seulement un des boutons puisse être sélectionné à la fois (Coins OU Murs OU Pieces)
        
        rbCoins.setToggleGroup(Options);
        rbMurs.setToggleGroup(Options);
        rbPieces.setToggleGroup(Options);
        rbAppartement.setToggleGroup(Options);
        
        this.rbAppartement.setVisible(false);
        this.choixAppart.setVisible(false);
        this.choixAppart.setDisable(true);
        
        this.vbDroit = new VBox(cbNiveaux,rbCoins, rbMurs, rbPieces,rbAppartement, choixAppart);
        vbDroit.setStyle("-fx-background-color: lightblue;");
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

    public ComboBox<HBox> getChoixAppart() {
        return choixAppart;
    }

    public RadioButton getRbAppartement() {
        return rbAppartement;
    }

    public MenuItem getCalculItem() {
        return CalculItem;
    }
    
    
   
    
    
}
