package com;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class storyController {

    private AnchorPane rootPane;

    @FXML public ImageView imgView;

    @FXML public Button Next;
    @FXML public Button Previous;
    @FXML public Button ReturnSecond;

    @FXML public ImageView Paused;
    @FXML public Button Pause;
    @FXML public Button Resume;
    @FXML public Button Return;
    @FXML public Button Exit;

    int currentimage = 1;

    public void NextClicked(){
        //System.out.println("Click!");

        if (currentimage == 1) {
            imgView.setImage(new Image("resources/story2.png"));
            currentimage = 2;
            Previous.setDisable(false);
        }
        else if (currentimage == 2) {
            imgView.setImage(new Image("resources/story3.png"));
            currentimage = 3;
        }
        else if (currentimage == 3) {
            imgView.setImage(new Image("resources/story4.png"));
            currentimage = 4;
            Next.setDisable(true);
        }
    }

    public void PreviousClicked(){
        if (currentimage == 2) {
            imgView.setImage(new Image("resources/story1.png"));
            currentimage = 1;
            Previous.setDisable(true);
        }
        if (currentimage == 3){
            imgView.setImage(new Image("resources/story2.png"));
            currentimage = 2;
        }
        if (currentimage == 4){
            imgView.setImage(new Image("resources/story3.png"));
            Next.setDisable(false);
            currentimage = 3;
        }
    }

    public void EscapePressed(){
        //System.out.println("Click!");
        Paused.setVisible(true);
        Pause.setDisable(true);
        Resume.setDisable(false);
        Return.setDisable(false);
        Exit.setDisable(false);

        ReturnSecond.setDisable(true);
        Next.setDisable(true);
        Previous.setDisable(true);
    }

    public void ResumePressed(){
        //System.out.println("Click!");
        Paused.setVisible(false);
        Pause.setDisable(false);
        Resume.setDisable(true);
        Return.setDisable(true);
        Exit.setDisable(true);

        ReturnSecond.setDisable(false);
        Next.setDisable(false);
        Previous.setDisable(false);
    }

    public void LoadMenu(ActionEvent event) throws IOException {
        Parent menuParent = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene menuScene = new Scene(menuParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.show();
    }

    public void ExitPressed(){
        System.exit(0);
    }

}
