package com;

import com.levelone.Level1;
import com.leveltwo.Level2;
import com.levelthree.Level3;
import com.levelfour.Level4;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class singleplayerController extends IOException {

    private AnchorPane rootPane;

    @FXML public Button Return;

    public void LoadLevel1(ActionEvent event) {
        Level1 Level1 = new Level1();
        Level1.start(new Stage());
    }

    public void LoadLevel2(ActionEvent event) throws IOException {
        Level2 Level2 = new Level2();
        Level2.start(new Stage());
    }
    public void LoadLevel3(ActionEvent event) throws IOException {
        Level3 Level3 = new Level3();
        Level3.start(new Stage());
    }
    public void LoadLevel4(ActionEvent event) throws IOException {
        Level4 Level4 = new Level4();
        Level4.start(new Stage());
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