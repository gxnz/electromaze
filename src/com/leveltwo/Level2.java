package com.leveltwo;

import com.Main;
import com.singleplayerController;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

import static javafx.application.Application.launch;


public class Level2 extends Main {

    boolean moveUp = false, moveDown = false, moveRight = false, moveLeft = false;
    boolean enemyMoveUp = false, enemyMoveDown = false, enemyMoveRight = false, enemyMoveLeft = false;
    boolean char2MoveUp = false, char2MoveDown = false, char2MoveRight = false, char2MoveLeft = false;
    int[][] map;
    Group root = new Group();
    boolean isTimerOn = false;
    int flagsCollected = 0;
    int totalFlags = 0;
    AnimationTimer timer;
    Timeline startTime;
    boolean isCountDownOver = false;
    boolean isGameOver = false;
    int points = 0;
    int numberOfCharactersDied = 0;
    boolean isCharacter1Alive = true;
    boolean isCharacter2Alive = true;
    int noOftimesPIsPressed = 0;
    int currentlevel = 1;
    Pane myCharacter;
    File Death = new File("src/com/leveltwo/sound/death.wav");
    int clipplaying = 0;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ELECTROMAZE");

        //Bulds map into current scene
        Maze maze = new Maze();
        map = maze.getMap1();
        maze.buildMaze(root, map);

        Overlay overlayGen = new Overlay();

        Pane myOverlay = overlayGen.getOverlay("com/leveltwo/Resource/map2new.png", 0, 0, 1024, 768);
        root.getChildren().addAll(myOverlay);

        setTimer();
        initializeScoreBoard();
        totalFlags = maze.getTotalFlags(map);

        //Generates character and enemies onto current scene
        Character characterGen = new Character();
        Pane myCharacter = characterGen.getCharacter("com/leveltwo/Resource/electrongreen.png", 60, 210, 25);
        root.getChildren().addAll(myCharacter);

        Enemy enemy1 = new Enemy();
        root.getChildren().addAll(enemy1.getEnemy("com/leveltwo/Resource/evilelectron.png", 900, 510, 25));

        Enemy enemy2 = new Enemy();
        root.getChildren().addAll(enemy2.getEnemy("com/leveltwo/Resource/evilelectron.png", 900, 510, 25));

        Enemy enemy3 = new Enemy();
        root.getChildren().addAll(enemy3.getEnemy("com/leveltwo/Resource/evilelectron.png", 900, 510, 25));

        Enemy enemy4 = new Enemy();
        root.getChildren().addAll(enemy4.getEnemy("com/leveltwo/Resource/evilelectron.png", 900, 510, 25));

        Scene scene = new Scene(root, 1024, 768);

        //Performs character action from the keys pressed
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                isTimerOn = true;
                switch (event.getCode()) {
                    case UP:
                        moveUp = true;
                        break;
                    case DOWN:
                        moveDown = true;
                        break;
                    case RIGHT:
                        moveRight = true;
                        break;
                    case LEFT:
                        moveLeft = true;
                        break;
                }
            }
        });
        //Stops character action after key released
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        moveUp = false;
                        break;
                    case DOWN:
                        moveDown = false;
                        break;
                    case RIGHT:
                        moveRight = false;
                        break;
                    case LEFT:
                        moveLeft = false;
                        break;
                    case P:
                        noOftimesPIsPressed++;
                        if(noOftimesPIsPressed%2 == 1){
                            timer.stop();
                            startTime.stop();
                        }
                        else {
                            timer.start();
                            startTime.play();
                        }
                        break;
                    case ESCAPE:
                        primaryStage.close();
                        break;
                    case PAGE_DOWN:
                        timeSeconds=0;
                        timerLabel.setText("Timer :" + timeSeconds);
                        break;
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

        //Controls movement of character inside maze
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isTimerOn && isCountDownOver) {
                    int dx = 0, dy = 0;
                    int x = 0, y = 0;
                    //Checks if character has passed through all signal towers
                    if (characterGen.hasCollectedFlag(root, maze, map)) {
                        flagsCollected += 1;
                        points += 1000;
                        updateScoreBoard(points);
                        if (flagsCollected == totalFlags && !isGameOver) {
                            points += getPointsByTimeLeft(timeSeconds);
                            updateScoreBoard(points);
                            isGameOver = false;
                            showAlertBox("Congrats! You passed the Level :"+ points);
                            startTime.stop();
                            stop();
                        }
                    }
                    //Checks character collision with enemy
                    if (((Math.abs(myCharacter.getTranslateX() - enemy1.enemy.getTranslateX()) < 30 && Math.abs(myCharacter.getTranslateY() - enemy1.enemy.getTranslateY()) < 30) ||
                            (Math.abs(myCharacter.getTranslateX() - enemy2.enemy.getTranslateX()) < 30 && Math.abs(myCharacter.getTranslateY() - enemy2.enemy.getTranslateY()) < 30) ||
                            (Math.abs(myCharacter.getTranslateX() - enemy3.enemy.getTranslateX()) < 30 && Math.abs(myCharacter.getTranslateY() - enemy3.enemy.getTranslateY()) < 30) ||
                            (Math.abs(myCharacter.getTranslateX() - enemy4.enemy.getTranslateX()) < 30 && Math.abs(myCharacter.getTranslateY() - enemy4.enemy.getTranslateY()) < 30))
                            && !isGameOver && isCharacter1Alive) {
                        isCharacter1Alive = false;
                        isGameOver = true;
                        if (clipplaying == 0){
                            Sound.PlaySound(Death);
                        }
                        clipplaying = 1;
                        startTime.stop();
                        stop();
                        showAlertBox("GAME OVER! YOUR SCORE :"+points);

                    }
                    //Speed of the character
                    if (moveUp && myCharacter.getTranslateY() > 0 && !characterGen.isColliding(map, KeyCode.UP,false)) {
                        if (characterGen.isMovingInResistance(map)) {
                            dy -= 2;
                        } else {
                            dy -= 5;
                        }
                    } else if (moveDown && myCharacter.getTranslateY() < 736 && !characterGen.isColliding(map, KeyCode.DOWN,false)) {
                        if (characterGen.isMovingInResistance(map)) {
                            dy += 2;
                        } else {
                            dy += 5;
                        }
                    } else if (moveRight && myCharacter.getTranslateX() < 992 && !characterGen.isColliding(map, KeyCode.RIGHT,false)) {
                        if (characterGen.isMovingInResistance(map)) {
                            dx += 2;
                        } else {
                            dx += 5;
                        }
                    } else if (moveLeft && myCharacter.getTranslateX() > 0 && !characterGen.isColliding(map, KeyCode.LEFT,false)) {
                        if (characterGen.isMovingInResistance(map)) {
                            dx -= 2;
                        } else {
                            dx -= 5;
                        }
                    }

                    y += dy + myCharacter.getTranslateY();
                    x += dx + myCharacter.getTranslateX();

                    myCharacter.setTranslateY(y);
                    myCharacter.setTranslateX(x);

                    //Starts AI enemy movement after a certain period of time in the game
                    enemy1.startEnemyMovement(map, 0, 120 - timeSeconds);
                    enemy2.startEnemyMovement(map, 3, 120 - timeSeconds);
                    enemy3.startEnemyMovement(map, 6, 120 - timeSeconds);
                    enemy4.startEnemyMovement(map, 9, 120 - timeSeconds);
                }

            }
        };
        timer.start();
    }

    int timeSeconds = 123;
    Label timerLabel = new Label();

    //Sets the timer for the game
    public void setTimer() {

        timerLabel.setTextFill(Color.YELLOW);
        timerLabel.setFont(new Font("Arial", 30));
        timerLabel.setText("Game begins in : "+ (timeSeconds-120));

        startTime = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(timeSeconds<=123 && timeSeconds > 120 && isTimerOn){
                    timeSeconds = timeSeconds - 1;
                    timerLabel.setText("Game begins in : "+ (timeSeconds-120));
                }

                if (timeSeconds <= 120 && timeSeconds > 0 && isTimerOn) {
                    isCountDownOver = true;
                    timeSeconds = timeSeconds - 1;
                    timerLabel.setText("Timer : " + timeSeconds);
                }

                if (timeSeconds == 0 && !isGameOver) {
                    isTimerOn = false;
                    isGameOver = true;
                    try {
                        stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    timer.stop();
                    showAlertBox("GAME OVER! YOUR SCORE :" + points);
                }
            }
        }));

        startTime.setCycleCount(Animation.INDEFINITE);
        startTime.play();

        root.getChildren().add(timerLabel);
    }

    //Displays message in the game
    public void showAlertBox(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ELECTROMAZE");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    //Create a new label for score
    Label score = new Label();

    //Displays scoreboard on the screen
    public void initializeScoreBoard() {
        score.setText("Score" + ": " + points);
        score.setTextFill(Color.YELLOW);
        score.setFont(new Font("Arial", 30));
        score.setPrefSize(200, 100);
        score.setTranslateX(800);
        score.setTranslateY(690);
        root.getChildren().add(score);
    }

    //Updates score on the screen
    public void updateScoreBoard(int points) {
        score.setText("Score" + ": " + points);
    }

    //Score allocated for completion of level
    public int getPointsByTimeLeft(int timeLeft) {
        if (timeLeft < 120 && timeLeft >= 100) {
            return 1000;
        } else if (timeLeft < 100 && timeLeft >= 80) {
            return 900;
        } else if (timeLeft < 80 && timeLeft >= 70) {
            return 800;
        } else if (timeLeft < 70 && timeLeft >= 60) {
            return 700;
        } else if (timeLeft < 60 && timeLeft >= 50) {
            return 600;
        } else if (timeLeft < 50 && timeLeft >= 40) {
            return 500;
        } else if (timeLeft < 40 && timeLeft >= 30) {
            return 400;
        } else if (timeLeft < 30 && timeLeft >= 20) {
            return 300;
        } else if (timeLeft < 20 && timeLeft >= 10) {
            return 200;
        } else {
            return 100;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
