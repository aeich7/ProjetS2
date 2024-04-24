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

/**
 *
 * @author eicha
 */
public class Controleur {
;
    private MainPane vue;
    private int etat;
    private boolean drawMode = false;
    private Donnees<Batiment> dBatiment;
    
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
    
      
    private void attachMouseEvents() {
        Canvas canvas = vue.getcDessin().getRealCanvas();
        canvas.setOnMouseClicked(event -> {
            if (this.isCoinDrawModeActive()) {
                ClicCoin(event.getX(), event.getY());
            }
        });
    }
    
    public void ClicCoin(double x, double y) {
        int gridSize = 30; // La taille de la grille pour l'alignement
        double alignedX = Math.round(x / gridSize) * gridSize;
        double alignedY = Math.round(y / gridSize) * gridSize;

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
    
    

}
    
  