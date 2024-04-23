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
  
    
    public Controleur(MainPane vue){
        this.vue = vue;  
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
        GridPane.setConstraints(submitButton, 1, 4);
        submitButton.setOnAction(e -> {
           String selectedType = buildingTypeInput.getValue();
           Batiment batiment = new Batiment(selectedType);
           int nbNiveaux = (int) levelsSlider.getValue();
           batiment.setNbNiveaux(nbNiveaux);
           for (int j = 1; j<=nbNiveaux; j++){ // Création des niveaux nécessaires
               Niveau Niveau = new Niveau();
               batiment.AjouterNiveau(Niveau);
           }
           batiment.Afficher();
           Donnees<Batiment> dBatiment = new Donnees();
           dBatiment.setCurrentData(batiment);
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
    
    public void toggleCoinDrawMode() {
        drawMode = !drawMode;
    }

    public boolean isCoinDrawModeActive() {
        return drawMode;
    }
    
    public void ClicCoin(){
        
    }
    
    
    
}
    
  