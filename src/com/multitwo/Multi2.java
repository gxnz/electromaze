package com.multitwo;

import com.Main;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class Multi2 extends Main {

    boolean moveUp = false, moveDown = false, moveRight = false, moveLeft = false;
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
    File Death = new File("src/com/multitwo/sound/death.wav");
    int clipplaying = 0;

    @Override
    public void start(Stage primaryStage)  {
        primaryStage.setTitle("ELECTROMAZE");

        //Bulds map into current scene
        Maze maze = new Maze();
        map = maze.getMap1();
        maze.buildMaze(root, map);

        Overlay overlayGen = new Overlay();

        Pane myOverlay = overlayGen.getOverlay("com/multitwo/Resource/map4new.png", 0, 0, 1024, 768);
        root.getChildren().addAll(myOverlay);

        setTimer();
        initializeScoreBoard();
        totalFlags = maze.getTotalFlags(map);

//        ==================================================================================================================
//        MULTIPLAYER MODE2

        //Generate character and enemy onto current scene
        Character characterGen = new Character();
        Pane myCharacter = characterGen.getCharacter("com/multitwo/Resource/electrongreen.png", 60, 240, 25);
        root.getChildren().addAll(myCharacter);

        Character characterGen2 = new Character();
        Pane myCharacter2= characterGen2.getCharacter("com/multitwo/Resource/electronyellow.png", 60, 240, 25);
        root.getChildren().addAll(myCharacter2);

        Enemy enemy1 = new Enemy();
        root.getChildren().addAll(enemy1.getEnemy("com/multitwo/Resource/evilelectron.png", 180, 120, 25));

        Enemy enemy2 = new Enemy();
        root.getChildren().addAll(enemy2.getEnemy("com/multitwo/Resource/evilelectron.png", 180, 120, 25));

        Scene scene = new Scene(root, 1024, 768);

        //Performs character actions from the keys pressed
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
                  case W:
                        char2MoveUp = true;
                        break;
                    case S:
                        char2MoveDown = true;
                        break;
                    case D:
                        char2MoveRight = true;
                        break;
                    case A:
                        char2MoveLeft = true;
                        break;
                }
            }
        });

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
                    case W:
                        char2MoveUp = false;
                        break;
                    case S:
                        char2MoveDown = false;
                        break;
                    case D:
                        char2MoveRight = false;
                        break;
                    case A:
                        char2MoveLeft = false;
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
                  int edx = 0, edy = 0;
                  int ex = 0, ey = 0;

                   if (characterGen.hasCollectedFlag(root, maze, map) || characterGen2.hasCollectedFlag(root, maze, map)) {
                       flagsCollected += 1;
                       points += 1000;
                       updateScoreBoard(points);
                       if (flagsCollected == totalFlags && !isGameOver) {
                           points += getPointsByTimeLeft(timeSeconds);
                           updateScoreBoard(points);
                           isGameOver = false;
                           showAlertBox("Congrats! You won the game :"+ points);
                           startTime.stop();
                           stop();
                       }
                    }

                    //Checks character collision with enemy
                    if (((Math.abs(myCharacter.getTranslateX() - enemy1.enemy.getTranslateX()) < 30 && Math.abs(myCharacter.getTranslateY() - enemy1.enemy.getTranslateY()) < 30) ||
                            (Math.abs(myCharacter.getTranslateX() - enemy2.enemy.getTranslateX()) < 30 && Math.abs(myCharacter.getTranslateY() - enemy2.enemy.getTranslateY()) < 30))
                            && !isGameOver && isCharacter1Alive) {
                            numberOfCharactersDied++;
                            isCharacter1Alive = false;
                            //Sound.PlaySound(Death);

                        String musicFile = "sound/death.wav";
                        Media sound = new Media (new File(musicFile).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(sound);
                        mediaPlayer.play();

                            root.getChildren().remove(myCharacter);
                        if(numberOfCharactersDied == 2){
                            showAlertBox("Congrats! You won the Game");
                            isGameOver = true;
                            //Sound.PlaySound(Death);
                            startTime.stop();
                            stop();
                        }

                    }
                  //Checks final character remaining collision with enemy
                    if (((Math.abs(myCharacter2.getTranslateX() - enemy1.enemy.getTranslateX()) < 30 && Math.abs(myCharacter2.getTranslateY() - enemy1.enemy.getTranslateY()) < 30) ||
                            (Math.abs(myCharacter2.getTranslateX() - enemy2.enemy.getTranslateX()) < 30 && Math.abs(myCharacter2.getTranslateY() - enemy2.enemy.getTranslateY()) < 30))
                            && !isGameOver && isCharacter2Alive) {
                            numberOfCharactersDied++;
                            isCharacter2Alive = false;

                            //Sound.PlaySound(Death);
                        String musicFile = "sound/death.wav";
                        Media sound = new Media (new File(musicFile).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(sound);
                        mediaPlayer.play();

                            root.getChildren().remove(myCharacter2);
                        if(numberOfCharactersDied == 2){
                            isCharacter1Alive = false;

                            //Sound.PlaySound(Death);

                            isGameOver = true;
                            startTime.stop();
                            stop();
                            showAlertBox("GAME OVER!");
                        }

                    }
                    //Speed of character
                    if (moveUp && myCharacter.getTranslateY() > 0 && !characterGen.isColliding(map, KeyCode.UP, false)) {
                        if (characterGen.isMovingInResistance(map)) {
                            dy -= 2;
                        } else {
                            dy -= 5;
                        }
                    } else if (moveDown && myCharacter.getTranslateY() < 736 && !characterGen.isColliding(map, KeyCode.DOWN, false)) {
                        if (characterGen.isMovingInResistance(map)) {
                            dy += 2;
                        } else {
                            dy += 5;
                        }
                    } else if (moveRight && myCharacter.getTranslateX() < 992 && !characterGen.isColliding(map, KeyCode.RIGHT, false)) {
                        if (characterGen.isMovingInResistance(map)) {
                            dx += 2;
                        } else {
                            dx += 5;
                        }
                    } else if (moveLeft && myCharacter.getTranslateX() > 0 && !characterGen.isColliding(map, KeyCode.LEFT, false)) {
                        if (characterGen.isMovingInResistance(map)) {
                            dx -= 2;
                        } else {
                            dx -= 5;
                        }
                    }

                    if (char2MoveUp && myCharacter2.getTranslateY() > 0 && !characterGen2.isColliding(map, KeyCode.UP, false)) {
                        if (characterGen2.isMovingInResistance(map)) {
                            edy -= 2;
                        } else {
                            edy -= 5;
                        }
                    } else if (char2MoveDown && myCharacter2.getTranslateY() < 736 && !characterGen2.isColliding(map, KeyCode.DOWN, false)) {
                        if (characterGen2.isMovingInResistance(map)) {
                            edy += 2;
                        } else {
                            edy += 5;
                        }
                    } else if (char2MoveRight && myCharacter2.getTranslateX() < 992 && !characterGen2.isColliding(map, KeyCode.RIGHT, false)) {
                        if (characterGen2.isMovingInResistance(map)) {
                            edx += 2;
                        } else {
                            edx += 5;
                        }
                    } else if (char2MoveLeft && myCharacter2.getTranslateX() > 0 && !characterGen2.isColliding(map, KeyCode.LEFT, false)) {
                        if (characterGen2.isMovingInResistance(map)) {
                            edx -= 2;
                        } else {
                            edx -= 5;
                        }
                    }

                    y += dy + myCharacter.getTranslateY();
                    x += dx + myCharacter.getTranslateX();

                    ey += edy + myCharacter2.getTranslateY();
                    ex += edx + myCharacter2.getTranslateX();

                    myCharacter.setTranslateY(y);
                    myCharacter.setTranslateX(x);

                    myCharacter2.setTranslateX(ex);
                    myCharacter2.setTranslateY(ey);

                   //Starts AI enemy movement after a certain period of time in the game
                    enemy1.startEnemyMovement(map, 0, 120 - timeSeconds);
                    enemy2.startEnemyMovement(map, 0, 120 - timeSeconds);

                }
            }
        };
        timer.start();
    }

    int timeSeconds = 123;
    Label timerLabel = new Label();

    //Sets the timer for the game
    public void setTimer() {

        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setFont(new Font("Arial Bold", 30));
        timerLabel.setText("Game begins in : "+ (timeSeconds-120));
        timerLabel.setTranslateX(70);
        timerLabel.setTranslateY(723);

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

    Label score = new Label();

    //Displays scoreboard on the screen
    public void initializeScoreBoard() {
        score.setText("Score" + ": " + points);
        score.setTextFill(Color.WHITE);
        score.setFont(new Font("Arial Bold", 30));
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
