package com.multitwo;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.File;

public class Character extends Multi2 {

    Pane myCharacter;
    //Creates character

    File Resistor = new File("src/com/multitwo/sound/resistor.wav");
    int clipplaying = 0;

    public Pane getCharacter(String src, int x, int y, int size) {
        Image image = new Image(src);
        ImageView pImage = new ImageView(image);
        pImage.setFitWidth(size);
        pImage.setFitHeight(size);
        Pane character = new Pane();
        character.getChildren().addAll(new Rectangle(size, size), pImage);
        character.setTranslateY(y);
        character.setTranslateX(x);
        myCharacter = character;
        return character;
    }

    //Collision detection of character inside maze and map components
    public boolean isColliding(int[][] map, KeyCode keyCode,boolean isEnemy) {

        double x = myCharacter.getTranslateX();
        double y = myCharacter.getTranslateY();

        double topLeftCornerX = x;
        double topLeftCornerY = y;

        double topRightCornerX = x + myCharacter.getWidth();
        double topRightCornerY = y;

        double bottomLeftCornerX = x;
        double bottomLeftCornerY = y + myCharacter.getHeight();

        double bottomRightCornerX = x + myCharacter.getWidth();
        double bottomRightCornerY = y + myCharacter.getHeight();

        int passThroughComponent;

        if(isEnemy){
            passThroughComponent = 2;
        }
        else{
            passThroughComponent = 3;
        }

        switch (keyCode) {
            case UP:
                if ((map[(int) ((topLeftCornerY - 5) / 30)][(int) (topLeftCornerX / 30)] == 0 && map[(int) ((topRightCornerY - 5) / 30)][(int) ((topRightCornerX - 1) / 30)] == 0) ||
                        (map[(int) ((topLeftCornerY - 5) / 30)][(int) (topLeftCornerX / 30)] == passThroughComponent && map[(int) ((topRightCornerY - 5) / 30)][(int) ((topRightCornerX - 1) / 30)] == passThroughComponent) ||
                        (map[(int) ((topLeftCornerY - 5) / 30)][(int) (topLeftCornerX / 30)] == 4 && map[(int) ((topRightCornerY - 5) / 30)][(int) ((topRightCornerX - 1) / 30)] == 4) ||
                        (map[(int) ((topLeftCornerY - 5) / 30)][(int) (topLeftCornerX / 30)] == 5 && map[(int) ((topRightCornerY - 5) / 30)][(int) ((topRightCornerX - 1) / 30)] == 5)) {
                    return false;
                } else {
                    return true;
                }
            case DOWN:
                if ((map[(int) ((bottomLeftCornerY + 5) / 30)][(int) (bottomLeftCornerX / 30)] == 0 && map[(int) ((bottomRightCornerY + 5) / 30)][(int) ((bottomRightCornerX - 1) / 30)] == 0) ||
                        (map[(int) ((bottomLeftCornerY + 5) / 30)][(int) (bottomLeftCornerX / 30)] == passThroughComponent && map[(int) ((bottomRightCornerY + 5) / 30)][(int) ((bottomRightCornerX - 1) / 30)] == passThroughComponent) ||
                        (map[(int) ((bottomLeftCornerY + 5) / 30)][(int) (bottomLeftCornerX / 30)] == 4 && map[(int) ((bottomRightCornerY + 5) / 30)][(int) ((bottomRightCornerX - 1) / 30)] == 4) ||
                        (map[(int) ((bottomLeftCornerY + 5) / 30)][(int) (bottomLeftCornerX / 30)] == 5 && map[(int) ((bottomRightCornerY + 5) / 30)][(int) ((bottomRightCornerX - 1) / 30)] == 5)
                        ) {
                    return false;
                } else {
                    return true;
                }
            case RIGHT:
                if ((map[(int) (topRightCornerY / 30)][(int) ((topRightCornerX + 5) / 30)] == 0 && map[(int) ((bottomRightCornerY - 1) / 30)][(int) ((bottomRightCornerX + 5) / 30)] == 0) ||
                        (map[(int) (topRightCornerY / 30)][(int) ((topRightCornerX + 5) / 30)] == passThroughComponent && map[(int) ((bottomRightCornerY - 1) / 30)][(int) ((bottomRightCornerX + 5) / 30)] == passThroughComponent) ||
                        (map[(int) (topRightCornerY / 30)][(int) ((topRightCornerX + 5) / 30)] == 4 && map[(int) ((bottomRightCornerY - 1) / 30)][(int) ((bottomRightCornerX + 5) / 30)] == 4) ||
                        (map[(int) (topRightCornerY / 30)][(int) ((topRightCornerX + 5) / 30)] == 5 && map[(int) ((bottomRightCornerY - 1) / 30)][(int) ((bottomRightCornerX + 5) / 30)] == 5)
                        ) {
                    return false;
                } else {
                    return true;
                }
            case LEFT:
                if ((map[(int) (topLeftCornerY / 30)][(int) ((topLeftCornerX - 5) / 30)] == 0 && map[(int) ((bottomLeftCornerY - 1) / 30)][(int) ((bottomLeftCornerX - 5) / 30)] == 0) ||
                        (map[(int) (topLeftCornerY / 30)][(int) ((topLeftCornerX - 5) / 30)] == passThroughComponent && map[(int) ((bottomLeftCornerY - 1) / 30)][(int) ((bottomLeftCornerX - 5) / 30)] == passThroughComponent) ||
                        (map[(int) (topLeftCornerY / 30)][(int) ((topLeftCornerX - 5) / 30)] == 4 && map[(int) ((bottomLeftCornerY - 1) / 30)][(int) ((bottomLeftCornerX - 5) / 30)] == 4) ||
                        (map[(int) (topLeftCornerY / 30)][(int) ((topLeftCornerX - 5) / 30)] == 5 && map[(int) ((bottomLeftCornerY - 1) / 30)][(int) ((bottomLeftCornerX - 5) / 30)] == 5)
                        ) {
                    return false;
                } else {
                    return true;
                }
        }
        return false;
    }

    //Checks the position of the character inside the resistance
    public boolean isMovingInResistance(int[][] map) {
        double x = myCharacter.getTranslateX();
        double y = myCharacter.getTranslateY();
        double centerX = x + (myCharacter.getWidth()/2);
        double centerY = y + (myCharacter.getHeight()/2);

        int row = (int) centerY / 30;
        int col = (int) centerX / 30;

        if (map[row][col] == 4) {
            if (clipplaying == 0){
                Sound.PlaySound(Resistor);
            }
            clipplaying = 1;
            return true;
        } else {
            clipplaying = 0;
            return false;
        }
    }

    //Checks character position at signal tower
    public boolean hasCollectedFlag(Group root, Maze maze, int[][] map) {
        double x = myCharacter.getTranslateX();
        double y = myCharacter.getTranslateY();
        double centerX = x + 15;
        double centerY = y + 15;

        int row = (int) centerY / 30;
        int col = (int) centerX / 30;

        if (map[row][col] == 5) {
            maze.removeFlag(root,row,col);

            Towers towersGen = new Towers();
            //Pane myTowers = towersGen.getTowers("File:src/sample/resource/greensignalmap.png",col*30,row*30,30,30);
            //root.getChildren().addAll(myTowers);

            return true;
        } else {
            return false;
        }
    }


}