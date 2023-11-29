package client.view.AccountScreen;

public class MockAccountScreen{

  String inputtedUsername;
  String inputtedPassword;

  public MockAccountScreen() {
    inputtedUsername = "";
    inputtedPassword = "";
  }

  public void inputUsername(String username){
    inputtedUsername = username;
  }

  public void inputtedPassword(String password){
    inputtedPassword = password;
  }

  public String getUsername(){
    return inputtedUsername;
  }

  public String getPasswword(){
    return inputtedPassword;
  }
}
