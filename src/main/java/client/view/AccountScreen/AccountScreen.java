package client.view.AccountScreen;

import client.View;
import client.view.RecipeScreen.RecipeScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AccountScreen extends BorderPane {

  private Header createHeader;
  private Header loginHeader;
  private Footer footer;

  private CreateAccount createAccount;
  private Login login;

  private Button createAccountButton;
  private Button loginButton;

  //private boolean validUsername;

  private EventHandler<ActionEvent> loginSwitchAction;
  private EventHandler<ActionEvent> loginAction;
  private EventHandler<ActionEvent> createSwitchAction;
  private EventHandler<ActionEvent> createAccountAction;

  public AccountScreen(String createAccountPrompt, String loginPrompt) {
    createHeader = new Header("Create Account");
    loginHeader = new Header("Login");
    footer = new Footer();

    createAccount = new CreateAccount(createAccountPrompt);
    login = new Login(loginPrompt);

    // Add header to the top of the BorderPane
    this.setTop(createHeader);
    // Add scroller to the centre of the BorderPane
    // if (loginPrompt.length() > 0){
    //   System.out.println("HERE");
    //   this.setCenter(login);
    // }else{
    //   this.setCenter(createAccount);
    // }
    // this.setCenter(login);
    this.setCenter(createAccount);
    // Add footer to the bottom of the BorderPane
    this.setBottom(footer);

    createAccountButton = footer.getCreateAccountButton();
    loginButton = footer.getLoginButton();
  }

  public Button getCreateButton() {
    return createAccountButton;
  }

  public void setCreateAccountAction(EventHandler<ActionEvent> e) {
    createAccountAction = e;
  }

  public void setCreateSwitchAction(EventHandler<ActionEvent> e) {
    createSwitchAction = e;
  }

  public Button getLoginButton() {
    return loginButton;
  }

  public void setLoginButtonAction(EventHandler<ActionEvent> e) {
    loginAction = e;
  }

  public void setLoginSwitchAction(EventHandler<ActionEvent> e) {
    loginSwitchAction = e;
  }

  public void switchToLogin() {
    login.clearLogin();
    this.setCenter(login);
    this.setTop(loginHeader);
    footer.switchToLogin();
    createAccountButton.setOnAction(createSwitchAction);
    loginButton.setOnAction(loginAction);
  }

  public void switchToCreate() {
    createAccount.clearCreateAccount();
    this.setCenter(createAccount);
    this.setTop(createHeader);
    footer.switchToCreate();
    createAccountButton.setOnAction(createAccountAction);
    loginButton.setOnAction(loginSwitchAction);
  }

  public Login getLogin() {
    return login;
  }

  public CreateAccount getCreateAccount() {
    return createAccount;
  }
  // public void setSuccess(boolean validUsername){
  //   this.validUsername = validUsername;
  // }
}
