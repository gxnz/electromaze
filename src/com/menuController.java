package com;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class menuController {

    private AnchorPane rootPane;
    @FXML public ImageView HighScores;
    @FXML public Button StoryButton;
    @FXML public Button TutorialButton;
    @FXML public Button SinglePlayerButton;
    @FXML public Button MultiplayerButton;
    @FXML public Button HighScoresButton;

    public void ButtonClicked(){
        System.out.println("Button Clicked");
    }


    public void LoadStory(ActionEvent event) throws IOException {
        Parent storyParent = FXMLLoader.load(getClass().getResource("story.fxml"));
        Scene storyScene = new Scene(storyParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(storyScene);
        window.show();
    }

    public void LoadTutorial(ActionEvent event) throws IOException {
        Parent tutorialParent = FXMLLoader.load(getClass().getResource("tutorial.fxml"));
        Scene tutorialScene = new Scene(tutorialParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tutorialScene);
        window.show();
    }

    public void LoadSinglePlayer(ActionEvent event) throws IOException {
        Parent singleplayerParent = FXMLLoader.load(getClass().getResource("singleplayer.fxml"));
        Scene singleplayerScene = new Scene(singleplayerParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(singleplayerScene);
        window.show();
    }

    public void LoadMultiPlayer(ActionEvent event) throws IOException {
    //movement.start();
    }

    public void ShowHighScores (ActionEvent event){
       HighScores.setVisible(true);
       StoryButton.setDisable(true);
       TutorialButton.setDisable(true);
       SinglePlayerButton.setDisable(true);
       MultiplayerButton.setDisable(true);
       HighScoresButton.setDisable(true);
    }

    public void Return (ActionEvent event){
        HighScores.setVisible(false);
        StoryButton.setDisable(false);
        TutorialButton.setDisable(false);
        SinglePlayerButton.setDisable(false);
        MultiplayerButton.setDisable(false);
        HighScoresButton.setDisable(false);
    }

    public void ExitPressed(){
        System.exit(0);
    }

}
