package com;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class singleplayerController {

    private AnchorPane rootPane;
    @FXML public ImageView Paused;
    @FXML public Button Pause;
    @FXML public Button Resume;
    @FXML public Button Return;
    @FXML public Button Exit;

    public void EscapePressed(){
        //System.out.println("Click!");
        Paused.setVisible(true);
        Pause.setDisable(true);
        Resume.setDisable(false);
        Return.setDisable(false);
        Exit.setDisable(false);
    }

    public void ResumePressed(){
        //System.out.println("Click!");
        Paused.setVisible(false);
        Pause.setDisable(false);
        Resume.setDisable(true);
        Return.setDisable(true);
        Exit.setDisable(true);
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