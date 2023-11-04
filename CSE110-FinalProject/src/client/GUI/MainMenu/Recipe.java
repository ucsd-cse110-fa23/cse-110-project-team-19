package client.GUI.MainMenu;

import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Insets;

public class Recipe extends HBox {

    private Label index;
    private Button recipeName;
    private Button selectButton;
    private boolean markedDone;

    Recipe() {
        this.setPrefSize(500, 20); // sets size of task
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background
                                                                                                     // color of task

        index = new Label();
        index.setText(""); // create index label
        index.setPrefSize(40, 20); // set size of Index label
        index.setTextAlignment(TextAlignment.LEFT); // Set alignment of index label
        index.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the task
        this.getChildren().add(index); // add index label to task

        recipeName = new Button(); // create task name text field
        recipeName.setPrefSize(380, 20); // set size of text field
        recipeName.setStyle("-fx-font-style: italic; -fx-background-color: #FFFFFF; -fx-font-weight: bold; -fx-font: 11 arial;"); // set background color of texfield
        index.setTextAlignment(TextAlignment.CENTER); // set alignment of text field
        recipeName.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
        this.getChildren().add(recipeName); // add textlabel to task


        selectButton = new Button("Select");
        selectButton.setPrefSize(80, 20);
        selectButton.setPrefHeight(Double.MAX_VALUE);
        selectButton.setStyle(
                "-fx-font-style: italic; -fx-background-color: #FFFFFF; -fx-font-weight: bold; -fx-font: 11 arial;");

        this.getChildren().addAll(selectButton, recipeName);
    }

    public void setTaskIndex(int num) {
        this.index.setText(num+""); // num to String
    }

    public void setRecipeName(String recipeName) {
        this.recipeName.setText(recipeName);
    }

    public Button getRecipeName() {
        return this.recipeName;
    }

    public Button getselectButton() {
        return this.selectButton;
    }

    public boolean isMarkedDone() {
        return this.markedDone;
    }

    public void toggleDone() {
        if(markedDone ==false){
            markedDone = true;
            this.setStyle("-fx-border-color: #000000; -fx-border-width: 0; -fx-font-weight: bold;");
            for (int i = 0; i < this.getChildren().size(); i++) {
                this.getChildren().get(i).setStyle("-fx-background-color: #BCE29E; -fx-border-width: 0;");
            }
        } else{
            markedDone = false;
            this.setStyle("-fx-border-color: #000000; -fx-border-width: 0; -fx-font-weight: bold;");
            for (int i = 0; i < this.getChildren().size(); i++) {
                this.getChildren().get(i).setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
            }
        }
    }
}