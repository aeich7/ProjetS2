/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2.gui;
import java.util.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import fr.insa.eich.projets2.*;
import java.io.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.VPos;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import static fr.insa.eich.projets2.Revetements.*; //Importer les méthodes de revêtements
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
/**
 *
 * @author eicha
 */
public class Controleur {
;
    private MainPane vue;
    private int etat;
    private double hauteur;
    private boolean drawMode = false;
    private boolean wallMode = false;
    private boolean PieceMode = false;
    private boolean AppartementMode = false;
    private Donnees<Batiment> dBatiment;
    private List<Coin> CoinsPourMur = new ArrayList<>();
    private List<Mur> mursSelectionnes = new ArrayList<>();
    private List<Mur> mursProches = new ArrayList<>();
    private int gridSize = 30;
    private String[][] revetements;
    private String [][] revetsPourMur;
    private String [][] revetsPourSol;
    private String [][] revetsPourPlafond;
    
    
    public Controleur(MainPane vue){
        this.vue = vue;
        attachMouseEvents();
    }

    public Donnees<Batiment> getdBatiment() {
        return dBatiment;
    }
    
    public MainPane getVue() {
        return vue;
    }
     public void Aide_devis(){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.getDialogPane().setMinWidth(400); // Configuration de la taille de la fenêtre d'alerte 
            alert1.getDialogPane().setMinHeight(150);
            alert1.setTitle("Aide pour le devis");
            alert1.setHeaderText(null);
            alert1.setContentText("Pour calculer le devis de toutes les pièces que vous avez construites ou allez construire, il faudra aller dans le menu 'Fichier' puis appuyer sur le bouton 'Devis', sinon votre devis ne sera pas calculé ! ");
            alert1.showAndWait();
            }
     public void Aide_autre(){
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.getDialogPane().setMinWidth(300); 
            alert2.getDialogPane().setMinHeight(100);
            alert2.setTitle("Aide autre");
            alert2.setHeaderText(null);
            alert2.setContentText("Si rien ne se passe quand vous cliquez sur un bouton, regardez la console, les erreurs y sont notées  !! ");
            alert2.showAndWait();
            }
     public void Creditsecole(){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().setMinWidth(300); 
            alert.getDialogPane().setMinHeight(100);
            alert.setTitle("Crédits école");
            alert.setHeaderText(null);
            alert.setContentText("Copyrights INSA Strasbourg, tous droits réservés");
            alert.showAndWait();
     }
    public void Creditseleves(){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().setMinWidth(300); 
            alert.getDialogPane().setMinHeight(100);
            alert.setTitle("Crédits élèves");
            alert.setHeaderText(null);
            alert.setContentText("Créé par Eich Aurélien , Merckling Bruno et Kerloc'h Hotaru");
            alert.showAndWait();
    }
    public void Aide_échelle(){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().setMinWidth(200); 
            alert.getDialogPane().setMinHeight(100);
            alert.setTitle("Aide pour l'échelle");
            alert.setHeaderText(null);
            alert.setContentText("La distance entre deux points sur le plan représente un mètre ! ");
            alert.showAndWait();
    }
    public void changeEtat(int NouvelEtat){
        if (NouvelEtat == 0){
            this.vue.getVbDroit().setManaged(false);
            this.vue.getVbDroit().setVisible(false);
            this.vue.getSaveItem().setDisable(true);
            this.vue.getCalculItem().setDisable(true);
        }
        if (NouvelEtat == 10){
            this.vue.getVbDroit().setManaged(true);
            this.vue.getVbDroit().setVisible(true);
            this.vue.getSaveItem().setDisable(false);
            this.vue.getCalculItem().setDisable(false);
        }
    }
    
    public void NouveauProjet() {
        if (!(this.dBatiment == null)){
            dBatiment.setCurrentData(null);
        }
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Champ pour le nom du projet
        Label projectNameLabel = new Label("Nom du projet:");
        GridPane.setConstraints(projectNameLabel, 0, 0);
        TextField projectNameInput = new TextField();
        projectNameInput.setPromptText("Entrez le nom du projet");
        GridPane.setConstraints(projectNameInput, 1, 0);

        // ComboBox pour le type de bâtiment
        Label buildingTypeLabel = new Label("Type de bâtiment:");
        GridPane.setConstraints(buildingTypeLabel, 0, 1);
        ComboBox<String> buildingTypeInput = new ComboBox<>();
        buildingTypeInput.getItems().addAll("Maison", "Immeuble");
        buildingTypeInput.setPromptText("Sélectionnez le type de bâtiment");
        GridPane.setConstraints(buildingTypeInput, 1, 1);
        
        // Champ pour le nombre d'appartements par niveau, initialisé comme caché
        Label apartmentsPerLevelLabel = new Label("Nombre d'appartements par niveau:");
        GridPane.setConstraints(apartmentsPerLevelLabel, 0, 4);
        TextField apartmentsPerLevelInput = new TextField();
        apartmentsPerLevelInput.setPromptText("Entrez le nombre d'appartements par niveau");
        GridPane.setConstraints(apartmentsPerLevelInput, 1, 4);

        // Affichage conditionnel du champ Nombre d'appartements
        buildingTypeInput.valueProperty().addListener((obs, oldVal, newVal) -> {
            if ("Immeuble".equals(newVal)) {
                grid.getChildren().addAll(apartmentsPerLevelLabel, apartmentsPerLevelInput);
            } else {
                grid.getChildren().removeAll(apartmentsPerLevelLabel, apartmentsPerLevelInput);
            }
    });

        // Champ pour la hauteur sous plafond
        Label ceilingHeightLabel = new Label("Hauteur sous plafond:");
        GridPane.setConstraints(ceilingHeightLabel, 0, 2);
        TextField ceilingHeightInput = new TextField();
        ceilingHeightInput.setPromptText("Entrez la hauteur sous plafond");
        GridPane.setConstraints(ceilingHeightInput, 1, 2);


        // Slider pour le nombre de niveaux
        Label levelsLabel = new Label("Nombre de niveaux:");
        GridPane.setConstraints(levelsLabel, 0, 3);
        Slider levelsSlider = new Slider(1, 20, 1);  // Min, Max, Valeur initiale
        levelsSlider.setShowTickLabels(true);
        levelsSlider.setShowTickMarks(true);
        levelsSlider.setMajorTickUnit(2);
        levelsSlider.setSnapToTicks(true);
        GridPane.setConstraints(levelsSlider, 1, 3);
        Text levelsValue = new Text("1");
        GridPane.setConstraints(levelsValue, 2, 3);
        levelsSlider.valueProperty().addListener((obs, oldval, newVal) ->
                levelsValue.setText(String.format("%.0f", newVal)));
        
        // Bouton pour sélectionner le fichier de revêtements
        Button selectFileButton = new Button("Sélectionner Fichier de Revêtements");
        GridPane.setConstraints(selectFileButton, 1, 5);

        selectFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sélectionner le fichier de revêtements");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Texte", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(newWindow);
            if (selectedFile != null) {
                int lignes = Comptageligne(selectedFile.getAbsolutePath());
                String[][] contenu = new String[lignes][6]; 
                contenu = Tableau(selectedFile.getAbsolutePath(), contenu);
                revetements = contenu; 
                revetsPourPlafond = plafond(revetements);
                revetsPourMur = mur(revetements);
                revetsPourSol = sol(revetements);  
                selectFileButton.setDisable(true);
                selectFileButton.setText("Fichier chargé");
            }
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fichier chargé");
            alert.setHeaderText(null);
            alert.setContentText("Le fichier de revêtements a été chargé avec succès.");
     
            alert.showAndWait();
            }
        );

        // Bouton pour soumettre les informations et enregistrement du batiment créé
        Button submitButton = new Button("Soumettre");
        GridPane.setConstraints(submitButton, 1, 6);
        submitButton.setOnAction(e -> {

        String hauteur = ceilingHeightInput.getText();
        this.hauteur = Double.parseDouble(hauteur); 
        String nom = projectNameInput.getText();
        String selectedType = buildingTypeInput.getValue();
        Batiment batiment = new Batiment(selectedType, nom);
        int nbNiveaux = (int) levelsSlider.getValue();
        batiment.setNbNiveaux(nbNiveaux);
        int AppartParNiveau = 0; // Variable pour stocker le nombre d'appartements par niveau
            if ("Immeuble".equals(selectedType) && !apartmentsPerLevelInput.getText().isEmpty()) {
                AppartParNiveau = Integer.parseInt(apartmentsPerLevelInput.getText());
                this.vue.getRbAppartement().setVisible(true); //Affiche les boutons pour les Appartements
                this.vue.getChoixAppart().setVisible(true);
            }

            for (int j = 0; j < nbNiveaux; j++) { // Création des niveaux nécessaires suivant NbNiveaux
                Niveau niveau = new Niveau();
                batiment.AjouterNiveau(niveau);
                if ("Immeuble".equals(selectedType)) {
                    for (int k = 1; k<=AppartParNiveau; k++){
                        Appartement appartement = new Appartement(j+1);
                        batiment.getListeNiveaux().get(j).AjouterAppartement(appartement);
                    }
                }
            }
            Platform.runLater(() -> {
            updateChoixAppart(0);
            });
           //System.out.println(batiment.toString()); Ligne de test
           this.dBatiment = new Donnees();
           this.dBatiment.setCurrentData(batiment);
           this.updateNiveaux();
           this.changeEtat(10);
           this.dBatiment.getCurrentData().setListeRevets(creerRevetements(revetements, gridSize)); //enregistrer tous les revêtements sur le batiment
           this.vue.getcDessin().setControleur(this.vue.getControleur());
           newWindow.close();
           
        });

        grid.getChildren().addAll(projectNameLabel, projectNameInput, buildingTypeLabel, buildingTypeInput,
                                  ceilingHeightLabel, ceilingHeightInput, levelsLabel, levelsSlider, levelsValue, selectFileButton, submitButton);

        Scene secondScene = new Scene(grid, 500, 250);
        newWindow.setTitle("Nouveau Projet");
        newWindow.setScene(secondScene);
        newWindow.show();
        
    }
    
    public void ActivCoinDrawMode() { // Activer le mode de Dessin des coins
        drawMode = true;
    }
    
    public void DesactivCoinDrawMode() { // Désactiver le mode de Dessin des coins
        drawMode = false;
    }

    public boolean isCoinDrawModeActive() { // Retourner si le mode de Dessin des coins est actif 
        return drawMode;
    }
    
    public void ActivWallMode() {
        wallMode = true;
        CoinsPourMur.clear(); // Réinitialiser les points à chaque activation du mode mur
    }

    public void DesactivWallMode() {
        wallMode = false;
    }

    public boolean isWallModeActive() {
        return wallMode;
    }
    
    public void ActivPieceMode() {
        PieceMode = true;
        mursSelectionnes.clear(); // Réinitialiser les murs à chaque activation du mode piece
    }

    public void DesactivPieceMode() {
        PieceMode = false;
    }

    public boolean isPieceModeActive() {
        return PieceMode;
    }
    
    public boolean isAppartementModeActive(){
        return AppartementMode;
    }
    
    public void ActiveAppartementMode(){
        AppartementMode = true;
    }
    
    public void DesactivAppartementMode(){
        AppartementMode = false;
    }
    
    
      
    private void attachMouseEvents() {
        Canvas canvas = vue.getcDessin().getRealCanvas();
        canvas.setOnMouseClicked(event -> {
            if (this.isCoinDrawModeActive()) {
                ClicCoin(event.getX(), event.getY());
            }
            else if (this.isWallModeActive()) {
                ClicMur(event.getX(), event.getY());
                redessiner(); // Afficher un coin sélectionné en rouge
            }
            else if (this.isPieceModeActive()) {
                CreerPiece(event.getX(), event.getY());
            }
            else if(this.isAppartementModeActive()){
                AjoutAppartement(event.getX(),event.getY());
            }
        });
    }
    
    public void ClicCoin(double x, double y) {
        // Récupérer la chaîne sélectionnée dans la ComboBox
        String selectedNiveau = this.vue.getCbNiveaux().getValue();
        // Extraire l'index du niveau à partir de la chaîne (par exemple, "Niveau 0" devient 0)
        int niveauIndex = Integer.parseInt(selectedNiveau.replace("Niveau ", ""));
        Niveau niveauActuel = dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex);
        double alignedX = Math.round(x / this.gridSize) * this.gridSize;
        double alignedY = Math.round(y / this.gridSize) * this.gridSize;
        Coin Coin = new Coin(alignedX,alignedY);
        boolean test = true;
        for (Coin CoinTest :dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getCoins()){
            if (CoinTest.equals(Coin))
                test = false;
        }
        if (test == false){
           System.out.println("Ce coin existe déjà !");
           int nvCompt = Coin.getIdCompteur() -1;
           Coin.setIdCompteur(nvCompt);
           
        }
        else{
            niveauActuel.addCoin(Coin);
            GraphicsContext gc = vue.getcDessin().getRealCanvas().getGraphicsContext2D();
            gc.setFill(Color.BLACK); // Définit la couleur du point
            gc.fillOval(alignedX - 2, alignedY - 2, 4, 4); // Dessine un petit cercle pour représenter le coin aligné
        }
        redessiner();
        }
    
    public void updateNiveaux() { // Permet d'obtenir le nombre de niveau et ainsi créer le nombre de "plans" de niveau 
        this.vue.getCbNiveaux().getItems().clear();
        int nbNiveaux = this.dBatiment.getCurrentData().getNbNiveaux();
            for (int i = 0; i < nbNiveaux; i++) {
                this.vue.getCbNiveaux().getItems().add("Niveau " + i);
            }
            if (!this.vue.getCbNiveaux().getItems().isEmpty()) {
                this.vue.getCbNiveaux().setValue(this.vue.getCbNiveaux().getItems().get(0));
                setupNiveauSelection();
            }
    }
    
    public void updateChoixAppart(int indexNiveau) { // Pouvoir choisir la Liste dans le ComboBox des appartements
        this.vue.getChoixAppart().getItems().clear();
        List<Appartement> appartements = this.dBatiment.getCurrentData().getListeNiveaux().get(indexNiveau).getListeAppartement();
        for (Appartement appartement : appartements){
            HBox hBox = new HBox(5); // 5 est l'espacement
            Rectangle rect = new Rectangle(10, 10); //Taille du rectangle
            rect.setFill(appartement.getColor()); // Couleur du rectangle
            Label label = new Label(appartement.toComboBox());
            hBox.getChildren().addAll(rect, label);
            //this.vue.getChoixAppart().getItems().add(appartement.toComboBox());
            this.vue.getChoixAppart().getItems().add(hBox);
        }
        
}
    public void setupNiveauSelection() { // Permet d'effectuer les différentes actions sur le niveau sélectionné
        vue.getCbNiveaux().setOnAction(event -> {
        String selectedNiveau = vue.getCbNiveaux().getValue();
        int niveauIndex = Integer.parseInt(selectedNiveau.replace("Niveau ", ""));
        vue.getcDessin().selectNiveau(niveauIndex);
        attachMouseEvents(); 
        
        // Vérifie si le type de bâtiment actuel est un immeuble
        boolean isImmeuble = this.dBatiment.getCurrentData().getType().equals("Immeuble");
        if (isImmeuble) {
            // Mettre à jour et afficher la ComboBox des appartements si c'est un immeuble
            updateChoixAppart(niveauIndex);
            this.vue.getChoixAppart().setVisible(true); // Rendre visible la ComboBox
        } else {
            // Sinon, masquer la ComboBox des appartements
            this.vue.getChoixAppart().setVisible(false);
            this.vue.getRbAppartement().setVisible(false);
        }
        });
    }

    public void ClicMur(double x, double y) {
        // Récupérer la chaîne sélectionnée dans la ComboBox
        String selectedNiveau = this.vue.getCbNiveaux().getValue();
        // Extraire l'index du niveau à partir de la chaîne (par exemple, "Niveau 0" devient 0)
        int niveauIndex = Integer.parseInt(selectedNiveau.replace("Niveau ", ""));
        double alignedX = Math.round(x / this.gridSize) * gridSize;
        double alignedY = Math.round(y / this.gridSize) * gridSize;

        Coin clickedCoin = Coin.findCoinAt(dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getCoins(), alignedX, alignedY);
            if (clickedCoin != null) {
            
                boolean EstDejaPris = CoinsPourMur.stream().anyMatch(c -> c.equals(clickedCoin));// Vérifier si le coin est déjà ajouté à la liste pour construire un mur

                if (!EstDejaPris) {
                    CoinsPourMur.add(clickedCoin);
                    if (CoinsPourMur.size() == 2) {
                        TracerMur(CoinsPourMur.get(0), CoinsPourMur.get(1));
                        CoinsPourMur.clear(); // Réinitialisation après la construction du mur
                }
                } else {
                    System.out.println("Ce Coin est déjà sélectionné.");
                }
                } else {
                    System.out.println("Il n'y a pas de Coin à cet emplacement ! Veuillez le créer ou en sélectionnez un autre.");
                }
    }
    
    public void TracerMur(Coin deb, Coin fin) {
        String selectedNiveau = this.vue.getCbNiveaux().getValue();
        int niveauIndex = Integer.parseInt(selectedNiveau.replace("Niveau ", ""));
        Niveau niveauActuel = dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex);

        // Vérifier si le mur existe déjà
        boolean murExiste = niveauActuel.getMurs().stream().anyMatch(mur -> 
        (mur.getCoinDebut().equals(deb) && mur.getCoinFin().equals(fin)) ||
        (mur.getCoinDebut().equals(fin) && mur.getCoinFin().equals(deb)));

        if (!murExiste) {
            GraphicsContext gc = vue.getcDessin().getRealCanvas().getGraphicsContext2D();
            gc.setStroke(Color.RED);
            gc.setLineWidth(2);
            gc.strokeLine(deb.getX(), deb.getY(), fin.getX(), fin.getY());

            Mur nouveauMur = new Mur(deb, fin, this.hauteur);
            nouveauMur.setIdNiveau(niveauIndex+1);
            niveauActuel.addMur(nouveauMur);
        } else {
        System.out.println("Un mur entre ces deux coins existe déjà.");
        }
    }
    
    public void CreerPiece(double x, double y) { //Créer des pièces.
        // Récupérer la chaîne sélectionnée dans la ComboBox
        String selectedNiveau = this.vue.getCbNiveaux().getValue();
        // Extraire l'index du niveau à partir de la chaîne (par exemple, "Niveau 0" devient 0)
        int niveauIndex = Integer.parseInt(selectedNiveau.replace("Niveau ", ""));
        Niveau niveauActuel = dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex);  
        double alignedX = Math.round(x / this.gridSize) * this.gridSize;
        double alignedY = Math.round(y / this.gridSize) * this.gridSize;
        
        List<Mur> choixMur = TrouverMur(dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getMurs(),alignedX, alignedY);
        Mur selectedMur = null;
        if (choixMur.size() != 1) {
            // Plusieurs murs proches ou aucun mur trouvé, nécessite une gestion supplémentaire
            if (choixMur.size() > 1) {
                
                // Créer une alerte avec un type de confirmation
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sélection du mur");
                alert.setHeaderText("Plusieurs murs détectés");
                alert.setContentText("Choisissez un mur:");

                // Créer une ComboBox pour lister les murs
                ComboBox<Mur> comboBox = new ComboBox<>();
                comboBox.getItems().addAll(choixMur);
                comboBox.setPrefWidth(250);  // Configure la largeur préférée
                comboBox.setCellFactory(lv -> new ListCell<Mur>() {
                @Override
                protected void updateItem(Mur mur, boolean empty) {
                    super.updateItem(mur, empty);
                    if (empty || mur == null) {
                        setText(null);
                    } else {
                        setText("Mur numéro : "+String.valueOf(mur.getId())); // Afficher seulement l'ID du mur
                    }
                }
                });

                comboBox.setButtonCell(new ListCell<Mur>() {
                @Override
                protected void updateItem(Mur mur, boolean empty) {
                    super.updateItem(mur, empty);
                    if (empty || mur == null) {
                        setText(null);
                    } else {
                        setText("Mur numéro : "+String.valueOf(mur.getId())); // Afficher seulement l'ID du mur dans le bouton de la ComboBox
                    }
                }
                });
                
                // Ajout d'un ChangeListener pour changer la couleur du mur sélectionné
                comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mur>() {
                @Override
                public void changed(ObservableValue<? extends Mur> observable, Mur oldValue, Mur newValue) {
                    if (newValue != null) {
                        choixMultipleMurs(newValue); // Méthode pour mettre en évidence le mur actuellement sélectionné
                    }
                    redessiner(); // Méthode pour redessiner les murs
                }
            });
                
                // Définir le ComboBox comme contenu supplémentaire de l'alerte
                alert.getDialogPane().setContent(comboBox);

                // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
                alert.showAndWait();

                // Retourner le mur sélectionné, ou null si aucun n'est choisi
                   selectedMur = comboBox.getSelectionModel().getSelectedItem();
            }
        } else {
            selectedMur = choixMur.get(0); // Un seul mur trouvé, sélection automatique
        }
        
        //Mur selectedMur = TrouverMur(dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getMurs(),alignedX, alignedY);
        if (selectedMur != null && !mursSelectionnes.contains(selectedMur)) {
            mursSelectionnes.add(selectedMur);
            selectionMur(selectedMur);  // Méthode pour visualiser la sélection
            System.out.println("Mur sélectionné ajouté.");

        if (mursSelectionnes.size() == 4) { //Vérifie si quatre murs ont bien été sélectionnés
            if (verifierClos(mursSelectionnes)) { //Appel de la méthode qui permet de vérifier si l'espace est clos.
                if (isPieceUnique(niveauActuel.getPieces(), mursSelectionnes)) {
                    Piece piece = new Piece();// Créer une pièce après la sélection de 4 murs
                    for (Mur mur : mursSelectionnes){
                        SelectionRevêtementMur(revetsPourMur, mur);
                        piece.AjouterMur(mur);
                    }
                    Sol Sol = new Sol(); //Création du Sol
                    Sol.setIdPiece(piece.getId());
                    SelectionRevêtementSol(revetsPourSol, Sol); //Méthode de sélection du revêtement
                    Plafond Plafond = new Plafond(); //Création du Plafond
                    Plafond.setIdPiece(piece.getId());
                    SelectionRevêtementPlafond(revetsPourPlafond, Plafond); //Méthode de sélection du revêtement
                    for (Mur mur : mursSelectionnes){ // Ajout des coins dans le HashSet de chacun (garantissant l'unicité)
                        Sol.AjouterCoin(mur.getCoinDebut());
                        Sol.AjouterCoin(mur.getCoinFin());
                        Plafond.AjouterCoin(mur.getCoinDebut());
                        Plafond.AjouterCoin(mur.getCoinFin());
                    }
                    double sommeX = 0;
                    double sommeY = 0;
                    for (Coin Coin : Sol.getCoins()){
                        sommeX = sommeX + Coin.getX();
                        sommeY = sommeY + Coin.getY();
                    }
                    piece.setCentreX(sommeX/(double)Sol.getCoins().size()); //Calcul du centre de la pièce en x
                    piece.setCentreY(sommeY/(double)Sol.getCoins().size()); //Calcul du centre de la pièce en y
                    piece.setPlafond(Plafond);
                    piece.setSol(Sol);
                    niveauActuel.addPiece(piece);
                    mursSelectionnes.clear();// Nettoyer la liste après la création de la pièce
                    System.out.println(piece.toString()); 
                    redessiner(); // Redessine les murs sans surlignage 
                } else {
                    System.out.println("Une pièce identique existe déjà.");
                    mursSelectionnes.clear();
                    redessiner();
            }
        } else {
                System.out.println("Les murs sélectionnés ne forment pas un espace clos.");
                mursSelectionnes.clear();
                redessiner(); 
            }
        } 
        }
        else{
            if (selectedMur == null){
                System.out.println("Pas de mur existant à cet endroit !");
            }
            else {
                System.out.println("Mur déjà sélectionné !");
            } 
        }
    }
    
    public boolean isPieceUnique(List<Piece> piecesExistantes, List<Mur> mursNouvellePiece) { // Verifier si La pièce existe déjà dans une liste
    for (Piece pieceExistante : piecesExistantes) {
        List<Mur> mursExistants = pieceExistante.getListeMurs();
        if (mursExistants.containsAll(mursNouvellePiece) && mursNouvellePiece.containsAll(mursExistants)) {
            return false; // La pièce existe déjà
        }
    }
    return true; // Aucune pièce correspondante trouvée, donc elle est unique
}
    private boolean verifierClos(List<Mur> murs) {
    // Permet d'assurer que les murs sont connectés de manière à former un espace clos
    if (murs.size() < 4) return false;  // Assure qu'il y a assez de murs pour former un cycle fermé.

    for (int i = 0; i < murs.size(); i++) { //Initialisation de la comparaison
        Mur current = murs.get(i); 
        Mur next = murs.get((i + 1) % murs.size());

        // Vérifie toutes les connexions possibles pour un cycle fermé.
        boolean isCorrectlyConnected = //Méthode pour vérifier si un CoinFin ou CoinDébut est bien le même que le CoinFin ou CoinDébut du Mur d'après en testant toutes les posibilités.
            current.getCoinFin().equals(next.getCoinDebut()) ||
            current.getCoinFin().equals(next.getCoinFin()) ||
            current.getCoinDebut().equals(next.getCoinDebut()) ||
            current.getCoinDebut().equals(next.getCoinFin());

        if (!isCorrectlyConnected) {
            System.out.println("Echec de connexion entre : " + current + " et " + next);
            return false;  // Si une connexion n'est pas correcte, retourne faux.
        }
    }
    return true;  // Si toutes les connexions sont valides, retourne vrai.
}
    
    
    private List TrouverMur(List<Mur> Mur,double x, double y) { // Savoir si un ou plusieurs murs est à proximité du clic (pour la sélection)
    final double PROXIMITY_THRESHOLD = 1.0; // Seuil de proximité en pixels
    mursProches.clear();
    for (Mur mur : Mur) {
        if (mur.isNear(x, y, PROXIMITY_THRESHOLD)) {
            mursProches.add(mur);
        }
    }
    return mursProches;
    }

    
    public void selectionMur(Mur mur) { // Pour afficher la sélection
        GraphicsContext gc = vue.getcDessin().getRealCanvas().getGraphicsContext2D();
        gc.setStroke(Color.YELLOW);  // Couleur pour le mur sélectionné
        gc.setLineWidth(3);
        gc.strokeLine(mur.getCoinDebut().getX(), mur.getCoinDebut().getY(), mur.getCoinFin().getX(), mur.getCoinFin().getY());
    }
    
    public void choixMultipleMurs(Mur mur) { // Pour afficher la sélection
        Platform.runLater(() -> {
        GraphicsContext gc = vue.getcDessin().getRealCanvas().getGraphicsContext2D();
        // Effacer les dessins précédents ou redessiner l'arrière-plan si nécessaire
        gc.clearRect(0, 0, vue.getcDessin().getRealCanvas().getWidth(), vue.getcDessin().getRealCanvas().getHeight());
        redessiner();  // Redessiner tous les murs avant de surligner
        gc.setStroke(Color.GREEN);  // Couleur pour le mur sélectionné
        gc.setLineWidth(3);
        gc.strokeLine(mur.getCoinDebut().getX(), mur.getCoinDebut().getY(), mur.getCoinFin().getX(), mur.getCoinFin().getY());
    });
    }
    
    public void AjoutAppartement(double x, double y){
        Piece PieceProche = null;
        double minDistance = Double.MAX_VALUE; // Intialisation d'un max qui sera mis à jour à chaque fois
        
        // Récupérer l'élément sélectionné dans le ComboBox
        HBox selectedHBox = this.vue.getChoixAppart().getValue();
        
        if (selectedHBox == null) {
            System.out.println("Pas d'appartement sélectionné !");
            return;
        }

        // Extraire le label de l'HBox pour obtenir le texte
        Label label = null;
        for (Node node : selectedHBox.getChildren()) {
            if (node instanceof Label) {
                label = (Label) node;
                break;
            }
        }
    
        String selectedAppart = label.getText();
        
        int NumAppart = Integer.parseInt(selectedAppart.replace("Appartement ", ""));
        int PosAppartDansListe = 0;
        // Récupérer la chaîne sélectionnée dans la ComboBox
        String selectedNiveau = this.vue.getCbNiveaux().getValue();
        // Extraire l'index du niveau à partir de la chaîne (par exemple, "Niveau 0" devient 0)
        int niveauIndex = Integer.parseInt(selectedNiveau.replace("Niveau ", ""));
        if (niveauIndex == 0){ // Si on se situe au niveau 0, il suffit de faire NumAppart - 1 pour avoir sa position dans la liste d'appartement/niveau
            PosAppartDansListe = NumAppart - 1;
        }
        else{ //Sinon, pour tous les autres niveaux, il faudra soustraire l'idMax des appartements du niveau précédent puis soustraire 1 our retrouver sa position dans la liste.
            int idMax = 0;
            for (Appartement Appart : this.dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex - 1).getListeAppartement()){
                if (Appart.getId()> idMax){
                    idMax = Appart.getId();
                }
            }
            PosAppartDansListe = NumAppart - idMax - 1;
        }
        for (Piece piece : this.dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getPieces()) {
            double distance = Math.sqrt(Math.pow(x - piece.getCentreX(), 2) + Math.pow(y - piece.getCentreY(), 2)); // Distance du clic par rapport au centre de la pièce
            if (distance < minDistance) {  
                minDistance = distance;
                PieceProche = piece;
            }
        }
        if (minDistance < 100) { // Définir un seuil approprié pour le "clic proche"
            PieceProche.setIdAppartement(NumAppart); //Associe l'id de l'appart à la pièce
            List<Piece> Compar = this.dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getListeAppartement().get(PosAppartDansListe).getListePieces();
            boolean estDejaDansAppart = false;
            for (Piece piece : Compar){
                if (PieceProche == piece){
                    estDejaDansAppart= true;
                }
            }
            List<Appartement> Compar2 = this.dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getListeAppartement();
            boolean estDansAutreAppart = false;
            for (Appartement appart : Compar2){
                List<Piece> AutresApparts = appart.getListePieces();
                for (Piece piece2 : AutresApparts){
                    if (PieceProche == piece2){
                    estDansAutreAppart = true;
                    }
                }
            }
            if (estDejaDansAppart == true){
                System.out.println("Cette pièce est déjà dans l'appartement !");
            }
            else if(estDansAutreAppart == true){
                System.out.println("Cette pièce est déjà dans un autre appartement !");
            }
            else {
                this.dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getListeAppartement().get(PosAppartDansListe).AjouterPiece(PieceProche); //Ajoute la pièce dans l'appartement
                System.out.println("Pièce numéro "+ PieceProche.getId()+" ajoutée à l'appartement numéro : "+ this.dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getListeAppartement().get(PosAppartDansListe).getId()+" !" );
                System.out.println(this.dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getListeAppartement().get(PosAppartDansListe).toString()); //Ligne de test
            }
            redessiner();
        }
    }
    

    public static void SelectionRevêtementMur(String[][] revêtementsMur, Mur mur) {
        // Créer une nouvelle fenêtre modale
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Entrez les détails pour le mur");

        // Créer un groupe de boutons radio pour les revêtements muraux
        ToggleGroup group = new ToggleGroup();
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        // Ajouter un texte pour indiquer le mur
        Text text = new Text("Choisir le revêtement pour le mur numéro " + mur.getId());
        vbox.getChildren().add(text);

        for (String[] revêtement : revêtementsMur) {
            RadioButton radioButton = new RadioButton("Désignation: " + revêtement[1] + ", Prix: " + revêtement[2] + "€/m2");
            radioButton.setUserData(Integer.parseInt(revêtement[0])); // Stocker l'ID 
            radioButton.setToggleGroup(group);
            vbox.getChildren().add(radioButton);
        }

        // Ajouter des champs de saisie pour le nombre de fenêtres et de portes
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(20));

        Label fenetresLabel = new Label("Nombre de fenêtres:");
        TextField fenetresTextField = new TextField();

        Label portesLabel = new Label("Nombre de portes:");
        TextField portesTextField = new TextField();

        gridPane.add(fenetresLabel, 0, 0);
        gridPane.add(fenetresTextField, 1, 0);
        gridPane.add(portesLabel, 0, 1);
        gridPane.add(portesTextField, 1, 1);

        // Ajouter un bouton pour confirmer la sélection
        Button confirmButton = new Button("Confirmer");
        confirmButton.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            if (selectedRadioButton != null) {
                Integer idRevêtementSélectionné = (Integer) selectedRadioButton.getUserData();
                int nombreFenetres = Integer.parseInt(fenetresTextField.getText());
                int nombrePortes = Integer.parseInt(portesTextField.getText());
                
                mur.setIdRevetement(idRevêtementSélectionné);
                mur.setNbPortes(nombrePortes);
                mur.setNbFenetres(nombreFenetres);
                window.close();
                
            }
        });

        vbox.getChildren().addAll(gridPane, confirmButton);

        // Afficher la fenêtre
        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
        
    }

    public static void SelectionRevêtementSol(String[][] revêtementsSol, Sol sol){
        // Créer une nouvelle fenêtre modale
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Sélectionner le revêtement pour le sol");

        // Créer un groupe de boutons radio pour les revêtements de sol
        ToggleGroup group = new ToggleGroup();
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        // Ajouter un texte pour indiquer le sol
        Text text = new Text("Choisir le revêtement pour le sol");
        vbox.getChildren().add(text);

        for (String[] revêtement : revêtementsSol) {
            RadioButton radioButton = new RadioButton("Désignation: " + revêtement[1] + ", Prix: " + revêtement[2] + "€/m2");
            radioButton.setUserData(Integer.parseInt(revêtement[0])); // Stocker l'ID en tant que donnée utilisateur
            radioButton.setToggleGroup(group);
            vbox.getChildren().add(radioButton);
        }

        // Ajouter un bouton pour confirmer la sélection
        Button confirmButton = new Button("Confirmer");
        confirmButton.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            if (selectedRadioButton != null) {
                Integer idRevêtementSélectionné = (Integer) selectedRadioButton.getUserData();
                sol.setIdRevetement(idRevêtementSélectionné);
                window.close();
            }
        });

        vbox.getChildren().addAll(confirmButton);

        // Afficher la fenêtre
        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
     }
    
    public static void SelectionRevêtementPlafond(String[][] revêtementsPlafond, Plafond plafond){
        // Créer une nouvelle fenêtre modale
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Sélectionner le revêtement pour le plafond");

        // Créer un groupe de boutons radio pour les revêtements de plafond
        ToggleGroup group = new ToggleGroup();
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        // Ajouter un texte pour indiquer le plafond
        Text text = new Text("Choisir le revêtement pour le plafond");
        vbox.getChildren().add(text);

        for (String[] revêtement : revêtementsPlafond) {
            RadioButton radioButton = new RadioButton("Désignation: " + revêtement[1] + ", Prix: " + revêtement[2] + "€/m2");
            radioButton.setUserData(Integer.parseInt(revêtement[0])); // Stocker l'ID en tant que donnée utilisateur
            radioButton.setToggleGroup(group);
            vbox.getChildren().add(radioButton);
        }

        // Ajouter un bouton pour confirmer la sélection
        Button confirmButton = new Button("Confirmer");
        confirmButton.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            if (selectedRadioButton != null) {
                Integer idRevêtementSélectionné = (Integer) selectedRadioButton.getUserData();
                plafond.setIdRevetement(idRevêtementSélectionné);
                window.close();
            }
        });

        vbox.getChildren().addAll(confirmButton);

        // Afficher la fenêtre
        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
        
    }
    
    public void redessiner() {
        // Récupérer la chaîne sélectionnée dans la ComboBox
        String selectedNiveau = this.vue.getCbNiveaux().getValue();
        int niveauIndex = 0;
        if (selectedNiveau != null){
            // Extraire l'index du niveau à partir de la chaîne (par exemple, "Niveau 0" devient 0)
            niveauIndex = Integer.parseInt(selectedNiveau.replace("Niveau ", ""));
        }
        else {
            niveauIndex = 0;
        }
        
        GraphicsContext gc = vue.getcDessin().getRealCanvas().getGraphicsContext2D();
        gc.clearRect(0, 0, vue.getcDessin().getRealCanvas().getWidth(), vue.getcDessin().getRealCanvas().getHeight());  // Efface le contenu précédent
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, vue.getcDessin().getRealCanvas().getWidth(), vue.getcDessin().getRealCanvas().getHeight()); // Applique un fond blanc
        int i = 0;
        for (i = 0; i<=vue.getcDessin().getRealCanvas().getWidth(); i=i+gridSize){ //Création de la grille à l'échelle (distance entre deux intersections = 1 m)
            int j = 0;
            for (j = 0; j<=vue.getcDessin().getRealCanvas().getHeight(); j=j+gridSize){
                gc.setStroke(Color.LIGHTGRAY);
                gc.strokeLine(i, j+1, i, j-1);
                gc.strokeLine(i+1, j, i-1, j);
            }
        }
        List<Mur> ListeMurs = dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getMurs();
        List<Coin> ListeCoins = dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getCoins();
        List<Mur> ListeMursSelectionnes = mursSelectionnes;
        List<Piece> ListePieces = dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getPieces();
        List<Coin> ListeCoinsPourMur = CoinsPourMur;
        List<Appartement> ListeAppartements = dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getListeAppartement();
        for (Mur mur : ListeMurs) {
            gc.setStroke(Color.RED);
            gc.setLineWidth(2);
            gc.strokeLine(mur.getCoinDebut().getX(), mur.getCoinDebut().getY(), mur.getCoinFin().getX(), mur.getCoinFin().getY());
        }
        for (Coin coin : ListeCoins) { // Redessine les coins
            gc.setFill(Color.BLACK);
            gc.fillOval(coin.getX() - 2, coin.getY() - 2, 4, 4);
        }
        for (Coin coin : ListeCoinsPourMur){
            gc.setFill(Color.RED);
            gc.fillOval(coin.getX() - 2, coin.getY() - 2, 4, 4);
        }
        for (Mur mur : ListeMursSelectionnes){
            gc.setStroke(Color.YELLOW);
            gc.setLineWidth(2);
            gc.strokeLine(mur.getCoinDebut().getX(), mur.getCoinDebut().getY(), mur.getCoinFin().getX(), mur.getCoinFin().getY());
        }
        // Afficher le numéro de la pièce au centre de chaque pièce
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        for (Piece piece : ListePieces) {
            if (piece.getIdAppartement() == 0){ // Si la pièce n'a pas encore été attribuée à un appart (idAppart = 0) alors afficher le nom de la pièce en noir
                gc.setFill(Color.BLACK);
                gc.fillText("Pièce numéro " + piece.getId(), piece.getCentreX(), piece.getCentreY());
                
          } else { //Sinon récupérer la couleur de l'appart pour l'affichage
            for (Appartement appart : ListeAppartements) {
                for (Piece pieceAppart : appart.getListePieces()) {
                    gc.setFill(appart.getColor());
                    gc.fillText("Pièce numéro " + pieceAppart.getId(), pieceAppart.getCentreX(), pieceAppart.getCentreY());
                }
            }

        }
   }
        
    
        }
    
    public static Batiment chargerBatiment(File fichier) { //Cette méthode se base sur le découpage du fichier txt par lignes, en regardant le type de l'objet au début de la ligne.
        //Chaque ligne est ensuite traitée par les différentes méthodes ci-dessous, se basant sur le ", " pour découper la ligne et ensuite récupérer les informations derrière chaque "=" (.split("="))
        Batiment batiment = null;
        Map<Integer, Niveau> niveaux = new HashMap<>();
        Map<Integer, Coin> coins = new HashMap<>();
        Map<Integer, Piece> pieces = new HashMap<>();
        Map<Integer, Appartement> appartements = new HashMap<>();
        Map<Integer, Revetements> revetements = new HashMap<>();
        Map<Integer, Mur> murs = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Batiment")) {
                    batiment = parseBatiment(line);
                } else if (line.startsWith("Niveau")) {
                    Niveau niveau = parseNiveau(line);
                    niveaux.put(niveau.getId(), niveau);
                } else if (line.startsWith("Mur")) {
                    Mur mur = parseMur(line, coins);
                    murs.put(mur.getId(), mur);
                } else if (line.startsWith("Coin")) {
                    Coin coin = parseCoin(line);
                    coins.put(coin.getId(), coin);
                } else if (line.startsWith("Piece")) {
                    Piece piece = parsePiece(line, coins, murs);
                    pieces.put(piece.getId(), piece);
                } else if (line.startsWith("Plafond")) {
                    Plafond plafond = parsePlafond(line, coins);
                    pieces.get(plafond.getIdPiece()).setPlafond(plafond);
                } else if (line.startsWith("Sol")) {
                    Sol sol = parseSol(line, coins);
                    pieces.get(sol.getIdPiece()).setSol(sol);
                } else if (line.startsWith("Appartement")) {
                    Appartement appartement = parseAppartement(line);
                    appartements.put(appartement.getId(), appartement);
                } else if (line.startsWith("Revetements")) {
                    Revetements revetement = parseRevetement(line);
                    revetements.put(revetement.getId(), revetement);
                }
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(niveaux.get(0));
        for(Mur murs2 : murs.values()){
            int idNiveau = murs2.getIdNiveau();
            niveaux.get(idNiveau).addMur(murs2);
            Coin Coin1 = murs2.getCoinDebut();
            Coin Coin2 = murs2.getCoinFin();
            niveaux.get(idNiveau).addCoin(Coin2);
            niveaux.get(idNiveau).addCoin(Coin1);
            }
        
        for (Piece piece : pieces.values()){
            System.out.println(piece);
            if (batiment.getType().equals("Immeuble")){
                int idAppart = piece.getIdAppartement();
                appartements.get(idAppart).AjouterPiece(piece);
            }
            double sommeX = 0;
            double sommeY = 0;
            for (Coin Coin : piece.getSol().getCoins()){
                sommeX = sommeX + Coin.getX();
                sommeY = sommeY + Coin.getY();
            }
            piece.setCentreX(sommeX/(double)piece.getSol().getCoins().size()); //Calcul du centre de la pièce en x
            piece.setCentreY(sommeY/(double)piece.getSol().getCoins().size()); //Calcul du centre de la pièce en y
            int idNiveau = piece.getListeMurs().getFirst().getIdNiveau();
            niveaux.get(idNiveau).addPiece(piece);
        }
        if (batiment.getType().equals("Immeuble")){   
            for (Appartement appart : appartements.values()){
                int idNiveau = appart.getNiveau();
                niveaux.get(idNiveau).AjouterAppartement(appart);
            }
        }
        // Ajouter les niveaux au bâtiment
        if (batiment != null) {
            for (Niveau niveau : niveaux.values()) {
                batiment.AjouterNiveau(niveau);
                batiment.setNbNiveaux(niveaux.values().size());
            }
            batiment.setListeRevets(revetements);
            
        }
        return batiment;
    }

    private static Batiment parseBatiment(String line) { //Méthode permettant de récupérer les informations enregistrées pour un Batiment
        // Supprimer la partie "ListeNiveaux={...}" de la ligne
        int startIndexOfLevels = line.indexOf("ListeNiveaux={");
        int endIndexOfLevels = line.indexOf("}", startIndexOfLevels);
        String lineWithoutLevels = line.substring(0, startIndexOfLevels) + line.substring(endIndexOfLevels + 1);

        // Extraire les informations restantes de la ligne
        String[] parts = lineWithoutLevels.split(", ");
        int id = Integer.parseInt(parts[0].split("=")[1]);
        String type = parts[2].split("=")[1];
        if (parts[3].endsWith("}")) {
            parts[3] = parts[3].substring(0, parts[3].length() - 1);
        }
        String nom = parts[3].split("=")[1];

        // Créer et retourner l'objet Batiment
        Batiment bat = new Batiment(type, nom);
        bat.setId(id);
        System.out.print(bat);
        return bat;
    }

    private static Niveau parseNiveau(String line) { //Méthode permettant de récupérer les informations enregistrées pour un Niveau
        // Example: Niveau{id=1, ListeAppartements={1, 2}}
        String[] parts = line.split(", ");
        int id = Integer.parseInt(parts[0].split("=")[1]);
        Niveau niv = new Niveau();
        niv.setId(id);
        System.out.println(niv);
        return niv;
    }

    private static Mur parseMur(String line, Map<Integer, Coin> coins) { //Méthode permettant de récupérer les informations enregistrées pour un Mur
        // Example: Mur{id=1, coinDebut=1, coinFin=2, nbPortes=2, nbFenetres=1, hauteur=2.0, IdRevetement=2}
        String[] parts = line.split(", ");
        int id = Integer.parseInt(parts[0].split("=")[1]);
        int idCoinDeb = Integer.parseInt(parts[1].split("=")[1]);
        int idCoinFin = Integer.parseInt(parts[2].split("=")[1]);
        int nbPortes = Integer.parseInt(parts[3].split("=")[1]);
        int nbFenetres = Integer.parseInt(parts[4].split("=")[1]);
        double hauteur = Double.parseDouble(parts[5].split("=")[1]);
        int idRevetement = Integer.parseInt(parts[6].split("=")[1]);
        // Supprimer les accolades à la fin de la chaîne si elles existent
        if (parts[7].endsWith("}")) {
            parts[7] = parts[7].substring(0, parts[7].length() - 1);
        }
        int idNiveau = Integer.parseInt(parts[7].split("=")[1]);
        Coin coinDebut = null;
        Coin coinFin = null;
        for (Coin coin : coins.values()){
            if (coin.getId()==idCoinDeb){
                coinDebut = coin;
            }
            if (coin.getId()==idCoinFin){
                coinFin = coin;
            }
        }
        Mur mur = new Mur(coinDebut,coinFin,hauteur);
        mur.setId(id);
        mur.setIdRevetement(idRevetement);
        mur.setNbFenetres(nbFenetres);
        mur.setNbPortes(nbPortes);
        mur.setIdNiveau(idNiveau);
        System.out.println(mur);
        return mur;
    }

    private static Coin parseCoin(String line) { //Méthode permettant de récupérer les informations enregistrées pour un Coin
        // Example: Coin{id=1, x=60.0, y=60.0}
        String[] parts = line.split(", ");
        int id = Integer.parseInt(parts[0].split("=")[1]);
        double x = Double.parseDouble(parts[1].split("=")[1]);
        // Supprimer les accolades à la fin de la chaîne si elles existent
    if (parts[2].endsWith("}")) {
        parts[2] = parts[2].substring(0, parts[2].length() - 1);
        }
        double y = Double.parseDouble(parts[2].split("=")[1]);
        Coin coin =new Coin(x, y);
        coin.setId(id);
        return coin;
    }

    private static Piece parsePiece(String line, Map<Integer, Coin> coins, Map<Integer, Mur> murs) { //Méthode permettant de récupérer les informations enregistrées pour une pièce
        
        String[] parts = line.split(", ");
        int id = Integer.parseInt(parts[0].split("=")[1]);
        int idAppartement = Integer.parseInt(parts[1].split("=")[1]);
        List<Integer> ListeMurs = parseMurIds(parts[4].split("=")[1]);
        System.out.println("Test : "+ListeMurs);
        Piece piece = new Piece();
        for (Map.Entry<Integer, Mur> Mur : murs.entrySet()){
            for (int MurId : ListeMurs){
                if (MurId == Mur.getValue().getId()){
                    piece.AjouterMur(Mur.getValue());
                }
            }
            
        }    
        piece.setIdAppartement(idAppartement);
        piece.setId(id);
        return piece;
    }

    private static Plafond parsePlafond(String line, Map<Integer, Coin> coins) { //Méthode permettant de récupérer les informations enregistrées pour un plafond
        // Example: Plafond{id=1, Coins={1,4,2,3}, IdRevetement=1}
        String[] parts = line.split(", ");
        int id = Integer.parseInt(parts[0].split("=")[1]);
        List<Integer> coinList = parseCoinIds(parts[1].split("=")[1]);
        int idRevetement = Integer.parseInt(parts[2].split("=")[1]);
        if (parts[3].endsWith("}")) {
        parts[3] = parts[3].substring(0, parts[3].length() - 1);
        }
        int idPiece = Integer.parseInt(parts[3].split("=")[1]);
        Plafond plafond = new Plafond();
        for (Map.Entry<Integer, Coin> coinsTest : coins.entrySet()){
            for (int coinsId : coinList){
                if (coinsId == coinsTest.getValue().getId()){
                    plafond.AjouterCoin(coinsTest.getValue());
                }
            }
            
        }
        plafond.setIdRevetement(idRevetement);
        plafond.setId(id);
        plafond.setIdPiece(idPiece);
        System.out.println(plafond);
        return plafond;
    }

    private static Sol parseSol(String line, Map<Integer, Coin> coins) { //Méthode permettant de récupérer les informations enregistrées pour un sol
        // Example: Sol{id=1, Coins={1,4,2,3}, IdRevetement=2}
        String[] parts = line.split(", ");
        System.out.println(parts.length);
        int id = Integer.parseInt(parts[0].split("=")[1]);
        List<Integer> coinList = parseCoinIds(parts[1].split("=")[1]);
        int idRevetement = Integer.parseInt(parts[2].split("=")[1]);
        if (parts[3].endsWith("}")) {
        parts[3] = parts[3].substring(0, parts[3].length() - 1);
        }
        int idPiece = Integer.parseInt(parts[3].split("=")[1]);
        Sol sol = new Sol();
        for (Map.Entry<Integer, Coin> coinsTest : coins.entrySet()){
            for (int coinsId : coinList){
                if (coinsId == coinsTest.getValue().getId()){
                    sol.AjouterCoin(coinsTest.getValue());
                }
            } 
        }
        sol.setIdPiece(idPiece);
        sol.setIdRevetement(idRevetement);
        sol.setId(id);
        System.out.println(sol);
        return sol;
    }

    

    private static Appartement parseAppartement(String line) {//Méthode permettant de récupérer les informations enregistrées pour un appartement
        
        String[] parts = line.split(", ");
        int id = Integer.parseInt(parts[0].split("=")[1]);
        // Supprimer les accolades à la fin de la chaîne si elles existent
        if (parts[1].endsWith("}")) {
            parts[1] = parts[1].substring(0, parts[1].length() - 1);
        }
        int niveau = Integer.parseInt(parts[1].split("=")[1]); 
        if (parts[2].endsWith("}")) {
        parts[2] = parts[2].substring(0, parts[2].length() - 1);
        }
        List<Integer> PieceList = parsePiecesIds(parts[2].split("=")[1]);;
        Appartement appart = new Appartement(niveau);         
        appart.setId(id);
        System.out.println(appart);
        return appart;
    }

    private static Revetements parseRevetement(String line) { //Méthode permettant de récupérer les informations enregistrées pour un revêtement
        // Example: Revetements{id=1, designation=Peinture, pourMur=true, pourSol=false, pourPlafond=true, Prixunitaire=10.95}
        String[] parts = line.split(", ");
        int id = Integer.parseInt(parts[0].split("=")[1]);
        String designation = parts[1].split("=")[1];
        boolean pourMur = Boolean.parseBoolean(parts[2].split("=")[1]);
        boolean pourSol = Boolean.parseBoolean(parts[3].split("=")[1]);
        boolean pourPlafond = Boolean.parseBoolean(parts[4].split("=")[1]);
        double prixUnitaire = Double.parseDouble(parts[5].split("=")[1]);
        // Supprimer les accolades à la fin de la chaîne si elles existent
        if (parts[6].endsWith("}")) {
            parts[6] = parts[6].substring(0, parts[6].length() - 1);
        }
        int echelle = Integer.parseInt(parts[6].split("=")[1]);
        
        return new Revetements(id, designation, pourMur, pourSol, pourPlafond, prixUnitaire, echelle);
    }
    
    private static List<Integer> parseCoinIds(String coinStr) { //Méthode permettant de récupérer les ids de Coins contenu dans les Sols et Plafonds
    coinStr = coinStr.replace("{", "").replace("}", ""); // Supprimer les accolades de la chaîne
    String[] coinIds = coinStr.replace("Coins={", "").replace("}", "").split(",");
    List<Integer> coinIdList = new ArrayList<>();
    for (String coinId : coinIds) {
        coinIdList.add(Integer.parseInt(coinId)); // Assure-toi de supprimer les espaces en trop
    }
    //System.out.print(coinIdList);
    return coinIdList;
}
    
    private static List<Integer> parseMurIds(String murStr) { //Méthode permettant de récupérer les ids de Mur contenu dans les pièces
    // Example: Murs={1,2,3,4}
    //System.out.print(murStr);
    murStr = murStr.replace("{", "").replace("}", ""); // Supprimer les accolades de la chaîne
    String[] murIds = murStr.replace("Murs={", "").replace("}", "").split(",");
    //System.out.println(Arrays.toString(murIds));
    List<Integer> murIdList = new ArrayList<>();
    for (String murId : murIds) {
        murIdList.add(Integer.parseInt(murId));
    }
    System.out.println(murIdList);
    return murIdList;
}
     private static List<Integer> parsePiecesIds(String pieceStr) { //Méthode permettant de récupérer les ids de Mur contenu dans les pièces
    // Example: Murs={1,2,3,4}
    //System.out.print(murStr);
    pieceStr = pieceStr.replace("{", "").replace("}", ""); // Supprimer les accolades de la chaîne
    String[] pieceIds = pieceStr.replace("Pieces={", "").replace("}", "").split(",");
    //System.out.println(Arrays.toString(murIds));
    List<Integer> pieceIdList = new ArrayList<>();
    if(pieceIds[0] == ""){
        return pieceIdList;
    }
    for (String pieceId : pieceIds) {
        pieceIdList.add(Integer.parseInt(pieceId));
    }
    return pieceIdList;
}

    public void setdBatiment(Donnees<Batiment> dBatiment) {
        this.dBatiment = dBatiment;
    }

    public void setRevetements(String[][] revetements) {
        this.revetements = revetements;
    }

    public void setRevetsPourMur(String[][] revetsPourMur) {
        this.revetsPourMur = revetsPourMur;
    }

    public void setRevetsPourSol(String[][] revetsPourSol) {
        this.revetsPourSol = revetsPourSol;
    }

    public void setRevetsPourPlafond(String[][] revetsPourPlafond) {
        this.revetsPourPlafond = revetsPourPlafond;
    }

    
    
    
}


    

    
                
               
                
    
    
    



    
  