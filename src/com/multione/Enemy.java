package com.multione;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Enemy {

    Pane enemy;
    //Creates Enemy
    public Pane getEnemy(String src, int x, int y, int size) {
        Image image = new Image(src);
        ImageView pImage = new ImageView(image);
        pImage.setFitWidth(size);
        pImage.setFitHeight(size);
        Pane character = new Pane();
        character.getChildren().addAll(new Rectangle(size, size), pImage);
        character.setTranslateY(y);
        character.setTranslateX(x);
        this.enemy = character;
        return character;
    }
    //Collision detection of enemy inside maze
    public boolean isEnemyColliding(int[][] map, KeyCode keyCode) {

        double x = enemy.getTranslateX();
        double y = enemy.getTranslateY();

        double topLeftCornerX = x;
        double topLeftCornerY = y;

        double topRightCornerX = x + 30;
        double topRightCornerY = y;

        double bottomLeftCornerX = x;
        double bottomLeftCornerY = y + 30;

        double bottomRightCornerX = x + 30;
        double bottomRightCornerY = y + 30;

        switch (keyCode) {
            case UP:
                if ((map[(int) ((topLeftCornerY - 5) / 30)][(int) (topLeftCornerX / 30)] == 0 && map[(int) ((topRightCornerY - 5) / 30)][(int) ((topRightCornerX - 1) / 30)] == 0) ||
                        (map[(int) ((topLeftCornerY - 5) / 30)][(int) (topLeftCornerX / 30)] == 2 && map[(int) ((topRightCornerY - 5) / 30)][(int) ((topRightCornerX - 1) / 30)] == 2) ||
                        (map[(int) ((topLeftCornerY - 5) / 30)][(int) (topLeftCornerX / 30)] == 4 && map[(int) ((topRightCornerY - 5) / 30)][(int) ((topRightCornerX - 1) / 30)] == 4) ||
                        (map[(int) ((topLeftCornerY - 5) / 30)][(int) (topLeftCornerX / 30)] == 5 && map[(int) ((topRightCornerY - 5) / 30)][(int) ((topRightCornerX - 1) / 30)] == 5)) {
                    return false;
                } else {
                    return true;
                }
            case DOWN:
                if ((map[(int) ((bottomLeftCornerY) / 30)][(int) (bottomLeftCornerX / 30)] == 0 && map[(int) ((bottomRightCornerY) / 30)][(int) ((bottomRightCornerX - 1) / 30)] == 0) ||
                        (map[(int) ((bottomLeftCornerY) / 30)][(int) (bottomLeftCornerX / 30)] == 2 && map[(int) ((bottomRightCornerY) / 30)][(int) ((bottomRightCornerX - 1) / 30)] == 2) ||
                        (map[(int) ((bottomLeftCornerY) / 30)][(int) (bottomLeftCornerX / 30)] == 4 && map[(int) ((bottomRightCornerY) / 30)][(int) ((bottomRightCornerX - 1) / 30)] == 4) ||
                        (map[(int) ((bottomLeftCornerY) / 30)][(int) (bottomLeftCornerX / 30)] == 5 && map[(int) ((bottomRightCornerY) / 30)][(int) ((bottomRightCornerX - 1) / 30)] == 5)
                        ) {
                    return false;
                } else {
                    return true;
                }
            case RIGHT:
                if ((map[(int) (topRightCornerY / 30)][(int) (topRightCornerX / 30)] == 0 && map[(int) ((bottomRightCornerY - 1) / 30)][(int) (bottomRightCornerX / 30)] == 0) ||
                        (map[(int) (topRightCornerY / 30)][(int) (topRightCornerX / 30)] == 2 && map[(int) ((bottomRightCornerY - 1) / 30)][(int) (bottomRightCornerX / 30)] == 2) ||
                        (map[(int) (topRightCornerY / 30)][(int) (topRightCornerX / 30)] == 4 && map[(int) ((bottomRightCornerY - 1) / 30)][(int) (bottomRightCornerX / 30)] == 4) ||
                        (map[(int) (topRightCornerY / 30)][(int) (topRightCornerX / 30)] == 5 && map[(int) ((bottomRightCornerY - 1) / 30)][(int) (bottomRightCornerX / 30)] == 5)
                        ) {
                    return false;
                } else {
                    return true;
                }
            case LEFT:
                if ((map[(int) (topLeftCornerY / 30)][(int) ((topLeftCornerX - 5) / 30)] == 0 && map[(int) ((bottomLeftCornerY - 1) / 30)][(int) ((bottomLeftCornerX - 5) / 30)] == 0) ||
                        (map[(int) (topLeftCornerY / 30)][(int) ((topLeftCornerX - 5) / 30)] == 2 && map[(int) ((bottomLeftCornerY - 1) / 30)][(int) ((bottomLeftCornerX - 5) / 30)] == 2) ||
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

    double adx = 0, ady = 0;
    boolean aiUp, aiDown, aiRight, aiLeft;
    boolean isDecisionMade = false;

    //Returns the direction of the enemy movement provided enemy can move in multiple directions
    public void chooseDirection(){
        Random random = new Random();
        int decisionHelper = random.nextInt(10);
        isDecisionMade = true;

        switch (decisionHelper % 4) {
            case 0:
                aiUp = true;
                aiDown = false;
                aiRight = false;
                aiLeft = false;
                break;
            case 1:
                aiUp = false;
                aiDown = true;
                aiRight = false;
                aiLeft = false;
                break;
            case 2:
                aiUp = false;
                aiDown = false;
                aiRight = true;
                aiLeft = false;
                break;
            case 3:
                aiUp = false;
                aiDown = false;
                aiRight = false;
                aiLeft = true;
                break;
        }
    }

    //Returns number of directions the enemy can move at a given point in time
    public int getNoOfchoicesToMove(int[][] map){
        int noOfChoices = 0;
        if(!isEnemyColliding(map,KeyCode.UP)){
            noOfChoices++;
        }
        if(!isEnemyColliding(map,KeyCode.DOWN)){
            noOfChoices++;
        }
        if(!isEnemyColliding(map,KeyCode.RIGHT)){
            noOfChoices++;
        }
        if(!isEnemyColliding(map,KeyCode.LEFT)){
            noOfChoices++;
        }
        return noOfChoices;
    }

    //Begins enemy movement
    public void startEnemyMovement(int[][] map, int startInterval,int currentGameTime) {
        if(currentGameTime>startInterval){
            if (aiUp && enemy.getTranslateY() > 0 && !isEnemyColliding(map, KeyCode.UP) && (getNoOfchoicesToMove(map) <=2 || (getNoOfchoicesToMove(map) > 2 && isDecisionMade))) {
                ady = -5;
                isDecisionMade = false;
            } else if (aiDown && enemy.getTranslateY() < 736 && !isEnemyColliding(map, KeyCode.DOWN) && (getNoOfchoicesToMove(map) <=2 || (getNoOfchoicesToMove(map) > 2 && isDecisionMade)) ) {
                ady = 5;
                isDecisionMade = false;
            } else if (aiRight && enemy.getTranslateX() < 992 && !isEnemyColliding(map, KeyCode.RIGHT) && (getNoOfchoicesToMove(map) <=2 || (getNoOfchoicesToMove(map) > 2 && isDecisionMade))) {
                adx = 5;
                isDecisionMade = false;
            } else if (aiLeft && enemy.getTranslateX() > 0 && !isEnemyColliding(map, KeyCode.LEFT) && (getNoOfchoicesToMove(map) <=2 || (getNoOfchoicesToMove(map) > 2 && isDecisionMade)) ) {
                adx = -5;
                isDecisionMade = false;
            } else {
                adx = 0;
                ady = 0;
                chooseDirection();
            }

            enemy.setTranslateY(ady + enemy.getTranslateY());
            enemy.setTranslateX(adx + enemy.getTranslateX());
        }
    }
}
