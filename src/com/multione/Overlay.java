package com.multione;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class Overlay extends Multi1 {

    Pane myOverlay;
    //Creates an overlay of an image
    public Pane getOverlay(String src, int x, int y, int sizeWidth, int sizeHeight) {
        Image image = new Image(src);
        ImageView pImage = new ImageView(image);
        pImage.setFitWidth(sizeWidth);
        pImage.setFitHeight(sizeHeight);
        Pane Overlay = new Pane();
        Overlay.getChildren().addAll(new Rectangle(sizeWidth, sizeHeight), pImage);
        Overlay.setTranslateY(y);
        Overlay.setTranslateX(x);
        myOverlay = Overlay;
        return Overlay;
    }
}