package com;

import com.multione.Multi1;
import com.multitwo.Multi2;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class multiplayerController {

    public void LoadMulti1(ActionEvent event) throws IOException {
        Multi1 Multi1 = new Multi1();
        Multi1.start(new Stage());
    }

    public void LoadMulti2(ActionEvent event) throws IOException {
        Multi2 Multi2 = new Multi2();
        Multi2.start(new Stage());
    }

    public void LoadMenu(ActionEvent event) throws IOException {
        Parent menuParent = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene menuScene = new Scene(menuParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.show();
    }
}
