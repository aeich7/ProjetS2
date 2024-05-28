/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2.gui;
import javafx.scene.layout.HBox;
import fr.insa.eich.projets2.*;
import javafx.stage.FileChooser;
import java.io.File;
import static fr.insa.eich.projets2.Revetements.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


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
    private MenuItem Charger;
    private MenuItem CalculItem;
     private MenuItem DevisItem;
    private MenuItem AutreItem;
    private MenuItem EchelleItem; 
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
        this.Charger = new MenuItem("Charger");
        
        this.DevisItem = new MenuItem("Devis");
        this.AutreItem = new MenuItem("Autre");
        this.EchelleItem = new MenuItem("Echelle");
        this.ElevesItem = new MenuItem("Eleves");
        this.EcoleItem = new MenuItem("Ecole");
        
        DevisItem.setOnAction(t ->{
            this.controleur.Aide_devis();
        });
        EchelleItem.setOnAction(t ->{
           this.controleur.Aide_échelle(); 
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
        
        Charger.setOnAction(e -> {
        // Créer une nouvelle fenêtre
        Stage selectionWindow = new Stage();
        selectionWindow.setTitle("Sélectionner les fichiers");

        // Bouton pour sélectionner le fichier du bâtiment
        Button selectBuildingFileButton = new Button("Sélectionner le fichier du bâtiment");
        Button selectRevetementFileButton = new Button("Sélectionner le fichier des revêtements");
        selectBuildingFileButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sélectionner le fichier du bâtiment");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Texte", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(selectionWindow);
            if (selectedFile != null) {
                Donnees<Batiment> dbatiment = new Donnees<Batiment>();
                dbatiment.setCurrentData(this.controleur.chargerBatiment(selectedFile));
                this.controleur.setdBatiment(dbatiment);

                if (this.controleur.getdBatiment().getCurrentData().getType().equals("Immeuble")) {
                    this.rbAppartement.setVisible(true);
                    this.rbAppartement.setDisable(false);
                    this.choixAppart.setVisible(true);
                    this.choixAppart.setDisable(false);
                }
                selectBuildingFileButton.setDisable(true);
                selectBuildingFileButton.setText("Fichier chargé");
                this.controleur.updateNiveaux();
                this.controleur.updateChoixAppart(0);
                this.controleur.changeEtat(10);
                this.controleur.redessiner();
                this.controleur.getVue().getcDessin().setControleur(this.controleur.getVue().getControleur());
            }
             if (selectBuildingFileButton.isDisable()&& selectRevetementFileButton.isDisable()){
                selectionWindow.close();
            }
            
        });

        // Action du bouton pour sélectionner le fichier des revêtements
        
        selectRevetementFileButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sélectionner le fichier des revêtements");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Texte", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(selectionWindow);
            if (selectedFile != null) {
                int lignes = Comptageligne(selectedFile.getAbsolutePath());
                String[][] contenu = new String[lignes][6];
                contenu = Tableau(selectedFile.getAbsolutePath(), contenu);
                String [][] revetements = contenu;
                this.controleur.setRevetements(revetements);    
                this.controleur.setRevetsPourMur(mur(revetements));
                this.controleur.setRevetsPourPlafond(plafond(revetements));
                this.controleur.setRevetsPourSol(sol(revetements));
                selectRevetementFileButton.setDisable(true);
                selectRevetementFileButton.setText("Fichier chargé");
            }
            if (selectBuildingFileButton.isDisable()&& selectRevetementFileButton.isDisable()){
                selectionWindow.close();
            }
        });
        // Disposition de la nouvelle fenêtre
        VBox layout = new VBox(10, selectBuildingFileButton, selectRevetementFileButton);
        layout.setPadding(new Insets(10));
        Scene scene = new Scene(layout, 400, 200);
        selectionWindow.setScene(scene);

        // Afficher la nouvelle fenêtre
        selectionWindow.initModality(Modality.APPLICATION_MODAL);
        selectionWindow.showAndWait();
        });
    
        
        
            
        EcoleItem.setOnAction(t -> {
            this.controleur.Creditsecole(); 
        });
        ElevesItem.setOnAction(t -> {
            this.controleur.Creditseleves();
        });
        
        this.rbCoins = createStyledRadioButton("Coins");
        rbCoins.setOnAction(e ->{
            this.controleur.ActivCoinDrawMode();
            this.controleur.DesactivWallMode();
            this.controleur.DesactivPieceMode();
            this.controleur.DesactivAppartementMode();
            this.choixAppart.setDisable(true);
        });
        this.rbMurs = createStyledRadioButton("Murs");
        rbMurs.setOnAction(a ->{
            this.controleur.ActivWallMode();
            this.controleur.DesactivCoinDrawMode();
            this.controleur.DesactivPieceMode();
            this.controleur.DesactivAppartementMode();
            this.choixAppart.setDisable(true);
        });

        this.rbPieces= createStyledRadioButton("Piece");
        rbPieces.setOnAction(a ->{
            this.controleur.DesactivCoinDrawMode();
            this.controleur.DesactivWallMode();
            this.controleur.ActivPieceMode();
            this.controleur.DesactivAppartementMode();
            this.choixAppart.setDisable(true);
        });
        
        this.rbAppartement = createStyledRadioButton("Appartement");
        rbAppartement.setOnAction(a -> {
            this.controleur.DesactivCoinDrawMode();
            this.controleur.DesactivWallMode();
            this.controleur.DesactivPieceMode();
            this.controleur.ActiveAppartementMode();
            this.choixAppart.setDisable(false);
        });
        
        this.cbNiveaux = new ComboBox<>(); // Initialisation de ComboBox des niveaux
        this.cbNiveaux.setPromptText("Sélectionnez un niveau");
        this.cbNiveaux.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px;");
        this.choixAppart = new ComboBox<>();
        this.choixAppart.setPromptText("Sélectionnez un appartement");
        this.choixAppart.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px;");
        
        
        menubar.getMenus().addAll(mFichier,mAide,mCredits);// Création de la barre de Menus
        mFichier.getItems().addAll(newItem,CalculItem,SaveItem,Charger);// Sous-menus de fichier
        newItem.setOnAction(t ->{
            this.controleur.NouveauProjet();
            this.Charger.setDisable(true);
        });
        CalculItem.setOnAction(t -> {
            this.controleur.getdBatiment().getCurrentData().CalculDevis();
        });
        SaveItem.setOnAction(t ->{
            this.controleur.getdBatiment().getCurrentData().sauvegarderBatiment();
        });
        
        mAide.getItems().addAll(DevisItem,EchelleItem ,AutreItem);
        mCredits.getItems().addAll(EcoleItem,ElevesItem);
        this.vbHaut = new VBox(menubar);
        this.setTop(vbHaut);
        
        ToggleGroup Options = new ToggleGroup(); // Faire en sorte que seulement un des boutons puisse être sélectionné à la fois (Coins OU Murs OU Pieces etc)
        
        rbCoins.setToggleGroup(Options);
        rbMurs.setToggleGroup(Options);
        rbPieces.setToggleGroup(Options);
        rbAppartement.setToggleGroup(Options);
        
        this.rbAppartement.setVisible(false);
        this.choixAppart.setVisible(false);
        this.choixAppart.setDisable(true);
        
        this.vbDroit = new VBox(cbNiveaux,rbCoins, rbMurs, rbPieces,rbAppartement, choixAppart);
        vbDroit.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-border-width: 1px;");
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
    
     private RadioButton createStyledRadioButton(String text) {
        RadioButton rb = new RadioButton(text);
        rb.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
        return rb;
    }
   
    
    
}
