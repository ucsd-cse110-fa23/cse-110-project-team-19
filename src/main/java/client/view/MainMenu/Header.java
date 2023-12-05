package client.view.MainMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class Header extends HBox {
  private Button filterButton;
  private Button sortButton;
  public Label label;

  Header() {
    this.setPrefSize(500, 60); // Size of the header
    this.setStyle("-fx-background-color: #F0F8FF;");
    label = new Label("          ");
    label.setStyle(
      "-fx-border-width: 0; -fx-font-weight: bold; -fx-font: 11 arial"
    );

    String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

    filterButton = new Button("Filter by: ");
    filterButton.setStyle(defaultButtonStyle);

    sortButton = new Button("Sort by: ");
    sortButton.setStyle(defaultButtonStyle);

    Text titleText = new Text("Main Menu"); // Text of the Header
    titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
    sortButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
    filterButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
    Pane spacer1 = new Pane();
    Pane spacer2 = new Pane();
    Pane spacer3 = new Pane();
    Pane spacer4 = new Pane();
    Header.setHgrow(spacer1, Priority.ALWAYS);
    Header.setHgrow(spacer2, Priority.ALWAYS);
    Header.setHgrow(spacer3, Priority.ALWAYS);
    Header.setHgrow(spacer4, Priority.ALWAYS);
    spacer1.setMaxWidth(10);
    spacer2.setMaxWidth(115);
    spacer3.setMaxWidth(50);
    spacer4.setMaxWidth(10);
    this.getChildren().addAll(spacer1, label, spacer2, titleText, spacer3 , sortButton, spacer4, filterButton);
    this.setAlignment(Pos.CENTER); // Align the text to the Center
  }

  public Button getfilterButton() {
    return filterButton;
  }

  public Button getsortButton() {
    return sortButton;
  }

  public void switchToAlphaAZ(){
    this.getChildren().remove(label);
    label = new Label("Sorted A-Z");
    this.getChildren().add(1, label);
  }

  public void switchToAlphaZA(){
    this.getChildren().remove(label);
    label = new Label("Sorted Z-A");
    this.getChildren().add(1, label);
  }

  public void switchToChronoOldNew(){
    this.getChildren().remove(label);
    label = new Label("From Old-New");
    this.getChildren().add(1, label);
  }

  public void switchToChronoNewOld(){
    this.getChildren().remove(label);
    label = new Label("From New-Old");
    this.getChildren().add(1, label);
  }

  public void switchToClear(){
    this.getChildren().remove(label);
    label = new Label("          ");
    this.getChildren().add(1, label);
  }

  public void switchToBreakfast(){
    this.getChildren().remove(label);
    label = new Label("Breakfast");
    this.getChildren().add(1, label);
  }
  public void switchToLunch(){
    this.getChildren().remove(label);
    label = new Label("Lunch");
    this.getChildren().add(1, label);
  }

  public void switchToDinner(){
    this.getChildren().remove(label);
    label = new Label("Dinner");
    this.getChildren().add(1, label);
  }
  
}

