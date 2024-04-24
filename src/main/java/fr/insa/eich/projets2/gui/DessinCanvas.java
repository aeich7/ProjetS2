/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.eich.projets2.gui;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import fr.insa.eich.projets2.*;
import java.util.*;
/**
 *
 * @author eicha
 */
public class DessinCanvas extends Pane{
    private MainPane main ;
    private Canvas realCanvas;
    private Map<Integer, Canvas> niveauxCanvas = new HashMap<>();
    
    
    
    public DessinCanvas(){
      
        this.realCanvas = new Canvas(this.getWidth(),this.getHeight());
        this.getChildren().add(this.realCanvas);
        this.niveauxCanvas.put(0, realCanvas);
        this.realCanvas.heightProperty().bind(this.heightProperty());
        this.realCanvas.heightProperty().addListener((o) -> {
            this.redrawAll();
        });
        this.realCanvas.widthProperty().bind(this.widthProperty());
        this.realCanvas.widthProperty().addListener((o) -> {
            this.redrawAll();
        });
        this.redrawAll();
    }
    
    
    
    public void redrawAll(){
        GraphicsContext context = this.realCanvas.getGraphicsContext2D();
        context.setFill(Color.WHITE);
        context.fillRect(0,0, this.getWidth(),this.getHeight());
        
       
    }
    
    public Canvas getRealCanvas() {
        return realCanvas;
    }
    
    public void selectNiveau(int niveau) { // Choisir le niveau sur lequel on travaille et s'il n'existe pas, le créer
        if (!niveauxCanvas.containsKey(niveau)) {
            Canvas newCanvas = new Canvas(this.getWidth(), this.getHeight()); 
            GraphicsContext gc = newCanvas.getGraphicsContext2D();
            gc.setFill(Color.WHITE);
            gc.fillRect(0,0, this.getWidth(),this.getHeight());
            niveauxCanvas.put(niveau, newCanvas);
            this.getChildren().add(newCanvas);
        }
        if (realCanvas != null) {
            realCanvas.setVisible(false);
        }
        realCanvas = niveauxCanvas.get(niveau);
        realCanvas.setVisible(true);
    }

    
}
