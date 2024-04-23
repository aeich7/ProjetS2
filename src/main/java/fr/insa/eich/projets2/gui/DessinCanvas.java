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
/**
 *
 * @author eicha
 */
public class DessinCanvas extends Pane{
    private Canvas realCanvas;
    
    public DessinCanvas(){
        
        this.realCanvas = new Canvas(this.getWidth(),this.getHeight());
        this.getChildren().add(this.realCanvas);
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
    
    public void DessinerCoin(Coin coin){
        GraphicsContext context = this.realCanvas.getGraphicsContext2D();
        context.setFill(Color.BLACK);
        context.fillOval(coin.getX() - 2.5, coin.getY() - 2.5, 5, 5);
    }
    
}
