package client.GUI.RecordScreen;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Header extends HBox {
    private Button backButton;

    Header() {

        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #F0F8FF;");
        backButton = new Button("back");
        backButton.setPrefSize(50, 20);
        // backButton.setPrefHeight(Double.MAX_VALUE);
        backButton.setStyle(
                "-fx-font-style: italic; -fx-background-color: #FFFFFF; -fx-font-weight: bold; -fx-font: 11 arial;");

        Text titleText = new Text("Record Screen"); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        
        this.getChildren().add(backButton);
        this.setAlignment(Pos.CENTER_LEFT); // Align the text to the Center
        
        this.getChildren().add(titleText);
        this.setSpacing(136);
        
    }
        

    public Button getBackButton() {
        return this.backButton;
    }

}
