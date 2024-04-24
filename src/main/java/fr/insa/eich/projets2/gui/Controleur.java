/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2.gui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Slider;
import fr.insa.eich.projets2.*;
import javafx.stage.Modality;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import javafx.geometry.Point2D;

/**
 *
 * @author eicha
 */
public class Controleur {
;
    private MainPane vue;
    private int etat;
    private boolean drawMode = false;
    private boolean wallMode = false;
    private boolean PieceMode = false;
    private Donnees<Batiment> dBatiment;
    private List<Coin> CoinsPourMur = new ArrayList<>();
    private List<Coin> CoinsTot = new ArrayList();
    private List<Mur> listeDesMurs = new ArrayList<>();
    private List<Mur> mursSelectionnes = new ArrayList<>();
    
    public Controleur(MainPane vue){
        this.vue = vue;
        attachMouseEvents();
    }


    public void changeEtat(int NouvelEtat){
        if (NouvelEtat == 0){
            this.vue.getVbDroit().setManaged(false);
            this.vue.getVbDroit().setVisible(false);
        }
        if (NouvelEtat == 10){
            this.vue.getVbDroit().setManaged(true);
            this.vue.getVbDroit().setVisible(true);
        }
    }
    public void NouveauProjet() {
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
        
        // Bouton pour soumettre les informations et enregistrement du batiment créé
        Button submitButton = new Button("Soumettre");
        GridPane.setConstraints(submitButton, 1, 5);
        submitButton.setOnAction(e -> {
        String selectedType = buildingTypeInput.getValue();
        Batiment batiment = new Batiment(selectedType);
        int nbNiveaux = (int) levelsSlider.getValue();
        batiment.setNbNiveaux(nbNiveaux);
        int AppartParNiveau = 0; // Variable pour stocker le nombre d'appartements par niveau
            if ("Immeuble".equals(selectedType) && !apartmentsPerLevelInput.getText().isEmpty()) {
                AppartParNiveau = Integer.parseInt(apartmentsPerLevelInput.getText());
            }

            for (int j = 0; j < nbNiveaux; j++) { // Création des niveaux nécessaires suivant NbNiveaux
                Niveau niveau = new Niveau();
                batiment.AjouterNiveau(niveau);
                if ("Immeuble".equals(selectedType)) {
                    for (int k = 1; k<=AppartParNiveau; k++){
                        Appartement appartement = new Appartement(j);
                        batiment.getListeNiveaux().get(j).AjouterAppartement(appartement);
                    }   
                }
            }
           //System.out.println(batiment.toString()); Ligne de test
           this.dBatiment = new Donnees();
           this.dBatiment.setCurrentData(batiment);
           this.updateNiveaux();
           this.changeEtat(10);
           newWindow.close();
        });

        grid.getChildren().addAll(projectNameLabel, projectNameInput, buildingTypeLabel, buildingTypeInput,
                                  ceilingHeightLabel, ceilingHeightInput, levelsLabel, levelsSlider, levelsValue, submitButton);

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
        mursSelectionnes.clear(); // Réinitialiser les points à chaque activation du mode mur
    }

    public void DesactivPieceMode() {
        PieceMode = false;
    }

    public boolean isPieceModeActive() {
        return PieceMode;
    }
    
      
    private void attachMouseEvents() {
        Canvas canvas = vue.getcDessin().getRealCanvas();
        canvas.setOnMouseClicked(event -> {
            if (this.isCoinDrawModeActive()) {
                ClicCoin(event.getX(), event.getY());
            }
            else if (this.isWallModeActive()) {
                ClicMur(event.getX(), event.getY());
            }
            else if (this.isPieceModeActive()) {
                SelectionMur(event.getX(), event.getY());
            }
        });
    }
    
    public void ClicCoin(double x, double y) {
        int gridSize = 30; // La taille de la grille pour l'alignement
        // Récupérer la chaîne sélectionnée dans la ComboBox
        String selectedNiveau = this.vue.getCbNiveaux().getValue();
        // Extraire l'index du niveau à partir de la chaîne (par exemple, "Niveau 0" devient 0)
        int niveauIndex = Integer.parseInt(selectedNiveau.replace("Niveau ", ""));
        Niveau niveauActuel = dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex);
        double alignedX = Math.round(x / gridSize) * gridSize;
        double alignedY = Math.round(y / gridSize) * gridSize;
        Coin Coin = new Coin(alignedX,alignedY);
        niveauActuel.addCoin(Coin);
        GraphicsContext gc = vue.getcDessin().getRealCanvas().getGraphicsContext2D();
        gc.setFill(Color.BLACK); // Définit la couleur du point
        gc.fillOval(alignedX - 2, alignedY - 2, 4, 4); // Dessine un petit cercle pour représenter le coin aligné
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
    public void setupNiveauSelection() { // Permet d'effectuer les différentes actions sur le niveau sélectionné
        vue.getCbNiveaux().setOnAction(event -> {
        String selectedNiveau = vue.getCbNiveaux().getValue();
        int niveauIndex = Integer.parseInt(selectedNiveau.replace("Niveau ", ""));
        vue.getcDessin().selectNiveau(niveauIndex);
        attachMouseEvents(); 
    });
}
    public void ClicMur(double x, double y) {
        // Récupérer la chaîne sélectionnée dans la ComboBox
        String selectedNiveau = this.vue.getCbNiveaux().getValue();
        // Extraire l'index du niveau à partir de la chaîne (par exemple, "Niveau 0" devient 0)
        int niveauIndex = Integer.parseInt(selectedNiveau.replace("Niveau ", ""));
        int gridSize = 30;
        double alignedX = Math.round(x / gridSize) * gridSize;
        double alignedY = Math.round(y / gridSize) * gridSize;

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
        // Récupérer la chaîne sélectionnée dans la ComboBox
        String selectedNiveau = this.vue.getCbNiveaux().getValue();
        // Extraire l'index du niveau à partir de la chaîne (par exemple, "Niveau 0" devient 0)
        int niveauIndex = Integer.parseInt(selectedNiveau.replace("Niveau ", ""));
        Niveau niveauActuel = dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex);
        GraphicsContext gc = vue.getcDessin().getRealCanvas().getGraphicsContext2D();
        gc.setStroke(Color.RED); // Choisissez la couleur pour le mur
        gc.setLineWidth(2); // Choisissez l'épaisseur du trait
        gc.strokeLine(deb.getX(), deb.getY(), fin.getX(), fin.getY());
        // Création d'un nouvel objet Mur et ajout à la liste
        Mur nouveauMur = new Mur(deb, fin);
        niveauActuel.addMur(nouveauMur);
    }
    
    public void SelectionMur(double x, double y) {
        // Récupérer la chaîne sélectionnée dans la ComboBox
        String selectedNiveau = this.vue.getCbNiveaux().getValue();
        // Extraire l'index du niveau à partir de la chaîne (par exemple, "Niveau 0" devient 0)
        int niveauIndex = Integer.parseInt(selectedNiveau.replace("Niveau ", ""));
        int gridSize = 30;  // Taille de la grille pour alignement
        double alignedX = Math.round(x / gridSize) * gridSize;
        double alignedY = Math.round(y / gridSize) * gridSize;

        Mur selectedMur = findMurAt(dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getMurs(),alignedX, alignedY);
        if (selectedMur != null && !mursSelectionnes.contains(selectedMur)) {
            mursSelectionnes.add(selectedMur);
            highlightMur(selectedMur);  // Méthode pour visualiser la sélection
            System.out.println("Mur sélectionné ajouté.");

        if (mursSelectionnes.size() == 4) {
            Piece piece = new Piece();// Créer une pièce après la sélection de 4 murs
            for (Mur mur : mursSelectionnes){
                piece.AjouterMur(mur);
            }
            mursSelectionnes.clear();// Nettoyer la liste après la création de la pièce
            //System.out.println(piece.toString()); Ligne de test !
            redessiner(); // Redessine les murs sans surlignage 
        }
    } else {
        System.out.println(selectedMur == null ? "Aucun mur trouvé à cette position." : "Mur déjà sélectionné.");
    }
}
    private Mur findMurAt(List<Mur> Mur,double x, double y) {
    final double PROXIMITY_THRESHOLD = 5.0; // Seuil de proximité en pixels
    for (Mur mur : Mur) {
        if (mur.isNear(x, y, PROXIMITY_THRESHOLD)) {
            return mur;
        }
    }
    return null;
}
    public void highlightMur(Mur mur) { // Pour afficher la sélection
        GraphicsContext gc = vue.getcDessin().getRealCanvas().getGraphicsContext2D();
        gc.setStroke(Color.BLUE);  // Couleur pour le mur sélectionné
        gc.setLineWidth(3);
        gc.strokeLine(mur.getCoinDebut().getX(), mur.getCoinDebut().getY(), mur.getCoinFin().getX(), mur.getCoinFin().getY());
    }
    
    public void redessiner() {
        // Récupérer la chaîne sélectionnée dans la ComboBox
        String selectedNiveau = this.vue.getCbNiveaux().getValue();
        // Extraire l'index du niveau à partir de la chaîne (par exemple, "Niveau 0" devient 0)
        int niveauIndex = Integer.parseInt(selectedNiveau.replace("Niveau ", ""));
        GraphicsContext gc = vue.getcDessin().getRealCanvas().getGraphicsContext2D();
        gc.clearRect(0, 0, vue.getcDessin().getRealCanvas().getWidth(), vue.getcDessin().getRealCanvas().getHeight());  // Efface le contenu précédent
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, vue.getcDessin().getRealCanvas().getWidth(), vue.getcDessin().getRealCanvas().getHeight()); // Applique un fond blanc
        List<Mur> ListeMurs = dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getMurs();
        List<Coin> ListeCoins = dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getCoins();
        for (Mur mur : ListeMurs) {
            gc.setStroke(Color.RED);
            gc.setLineWidth(2);
            gc.strokeLine(mur.getCoinDebut().getX(), mur.getCoinDebut().getY(), mur.getCoinFin().getX(), mur.getCoinFin().getY());
        }
        for (Coin coin : ListeCoins) { // Redessine les coins
            gc.setFill(Color.BLACK);
            gc.fillOval(coin.getX() - 2, coin.getY() - 2, 4, 4);
        }
    }
    
    
}


    
  