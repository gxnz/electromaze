package com.levelfour;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Towers extends Level4 {

    Pane myTowers;
    //Creates signal towers
    public Pane getTowers(String src, int x, int y, int sizeWidth, int sizeHeight) {
        Image image = new Image(src);
        ImageView pImage = new ImageView(image);
        pImage.setFitWidth(sizeWidth);
        pImage.setFitHeight(sizeHeight);
        Pane Towers = new Pane();
        Towers.getChildren().addAll(new Rectangle(sizeWidth, sizeHeight), pImage);
        Towers.setTranslateY(y);
        Towers.setTranslateX(x);
        myTowers = Towers;
        return Towers;
    }
}