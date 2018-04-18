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

public class tutorialController {

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
        if (currentimage == 1) {
            imgView.setImage(new Image("resources/tutorial2.png"));
            currentimage = 2;
            Previous.setDisable(false);
        }
        else if (currentimage == 2) {
            imgView.setImage(new Image("resources/tutorial3.png"));
            currentimage = 3;
        }
        else if (currentimage == 3) {
            imgView.setImage(new Image("resources/tutorial4.png"));
            currentimage = 4;
        }
        else if (currentimage == 4) {
            imgView.setImage(new Image("resources/tutorial5.png"));
            currentimage = 5;
        }
        else if (currentimage == 5) {
            imgView.setImage(new Image("resources/tutorial6.png"));
            currentimage = 6;
        }
        else if (currentimage == 6) {
            imgView.setImage(new Image("resources/tutorial7.png"));
            currentimage = 7;
        }
        else if (currentimage == 7) {
            imgView.setImage(new Image("resources/tutorial8.png"));
            currentimage = 8;
            Next.setDisable(true);
        }
    }

    public void PreviousClicked() {
        if (currentimage == 2) {
            imgView.setImage(new Image("resources/tutorial1.png"));
            currentimage = 1;
            Previous.setDisable(true);
        }
        if (currentimage == 3) {
            imgView.setImage(new Image("resources/tutorial2.png"));
            currentimage = 2;
        }
        if (currentimage == 4) {
            imgView.setImage(new Image("resources/tutorial3.png"));
            currentimage = 3;
        }
        if (currentimage == 5) {
            imgView.setImage(new Image("resources/tutorial4.png"));
            currentimage = 4;
        }
        if (currentimage == 6) {
            imgView.setImage(new Image("resources/tutorial5.png"));
            currentimage = 5;
        }
        if (currentimage == 7) {
            imgView.setImage(new Image("resources/tutorial6.png"));
            currentimage = 6;
        }
        if (currentimage == 8) {
            imgView.setImage(new Image("resources/tutorial7.png"));
            currentimage = 7;
            Next.setDisable(false);
        }
    }

    public void EscapePressed(){
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
