/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2.gui;
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
import javafx.application.Platform;

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
    private Donnees<Batiment> dBatiment;
    private List<Coin> CoinsPourMur = new ArrayList<>();
    private List<Mur> mursSelectionnes = new ArrayList<>();
    private List<Mur> mursProches = new ArrayList<>();
    
    
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
        String hauteur = ceilingHeightInput.getText();
        this.hauteur = Double.parseDouble(hauteur); 
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
        mursSelectionnes.clear(); // Réinitialiser les murs à chaque activation du mode piece
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
                CreerPiece(event.getX(), event.getY());
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
        boolean test = true;
        for (Coin CoinTest :dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getCoins()){
            if (CoinTest.equals(Coin))
                test = false;
        }
        if (test == false){
           System.out.println("Ce coin existe déjà !");
        }
        else{
            niveauActuel.addCoin(Coin);
            GraphicsContext gc = vue.getcDessin().getRealCanvas().getGraphicsContext2D();
            gc.setFill(Color.BLACK); // Définit la couleur du point
            gc.fillOval(alignedX - 2, alignedY - 2, 4, 4); // Dessine un petit cercle pour représenter le coin aligné
        }
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
        int gridSize = 30;  // Taille de la grille pour alignement
        double alignedX = Math.round(x / gridSize) * gridSize;
        double alignedY = Math.round(y / gridSize) * gridSize;
        
        List<Mur> choixMur = TrouverMur(dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getMurs(),alignedX, alignedY);
        Mur selectedMur = null;
        if (choixMur.size() != 1) {
            // Plusieurs murs proches ou aucun mur trouvé, nécessite une gestion supplémentaire
            if (choixMur.size() > 1) {
                // Implémenter ici un choix utilisateur (par exemple via une boîte de dialogue)
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

        if (mursSelectionnes.size() == 4) {
            if (verifierClos(mursSelectionnes)) {
            Piece piece = new Piece();// Créer une pièce après la sélection de 4 murs
            for (Mur mur : mursSelectionnes){
                piece.AjouterMur(mur);
            }
            Sol Sol = new Sol(); //Création du Sol
            Plafond Plafond = new Plafond(); //Création du Plafond
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
            piece.setCentreX(sommeX/(double)Sol.getCoins().size());
            piece.setCentreY(sommeY/(double)Sol.getCoins().size());
            piece.setPlafond(Plafond);
            piece.setSol(Sol);
            niveauActuel.addPiece(piece);
            mursSelectionnes.clear();// Nettoyer la liste après la création de la pièce
            System.out.println(piece.toString()); 
            redessiner(); // Redessine les murs sans surlignage 
        } else {
                System.out.println("Les murs sélectionnés ne forment pas un espace clos.");
                mursSelectionnes.clear();
                redessiner(); 
            }
    } 
    }
        else {
        System.out.println(selectedMur == null ? "Aucun mur trouvé à cette position." : "Mur déjà sélectionné.");
}
    }
    private boolean verifierClos(List<Mur> murs) {
    // Assurez-vous que les murs sont connectés de manière séquentielle pour former un cycle fermé
    if (murs.size() < 4) return false;  // Assure qu'il y a assez de murs pour former un cycle fermé.

    for (int i = 0; i < murs.size(); i++) {
        Mur current = murs.get(i);
        Mur next = murs.get((i + 1) % murs.size());

        // Vérifie toutes les connexions possibles pour un cycle fermé.
        boolean isCorrectlyConnected =
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
        List<Mur> ListeMursSelectionnes = mursSelectionnes;
        List<Piece> ListePieces = dBatiment.getCurrentData().getListeNiveaux().get(niveauIndex).getPieces();
        for (Mur mur : ListeMurs) {
            gc.setStroke(Color.RED);
            gc.setLineWidth(2);
            gc.strokeLine(mur.getCoinDebut().getX(), mur.getCoinDebut().getY(), mur.getCoinFin().getX(), mur.getCoinFin().getY());
        }
        for (Coin coin : ListeCoins) { // Redessine les coins
            gc.setFill(Color.BLACK);
            gc.fillOval(coin.getX() - 2, coin.getY() - 2, 4, 4);
        }
        for (Mur mur : ListeMursSelectionnes){
            gc.setStroke(Color.YELLOW);
            gc.setLineWidth(2);
            gc.strokeLine(mur.getCoinDebut().getX(), mur.getCoinDebut().getY(), mur.getCoinFin().getX(), mur.getCoinFin().getY());
        }
        // Afficher le numéro de la pièce au centre de chaque pièce
        gc.setFill(Color.BLUE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
            for (Piece piece : ListePieces) {
              gc.fillText("Pièce numéro " + piece.getId(), piece.getCentreX(), piece.getCentreY());
    }
    }
    
    
}


    
  