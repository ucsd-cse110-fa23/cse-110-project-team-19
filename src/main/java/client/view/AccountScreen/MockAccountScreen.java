package client.view.AccountScreen;

public class MockAccountScreen{

  String inputtedUsername;
  String inputtedPassword;

  boolean automaticLogin;

  public MockAccountScreen() {
    inputtedUsername = "";
    inputtedPassword = "";
    
    automaticLogin = false;
  }

  public MockAccountScreen(String[] automaticLoginTxt){
    if(automaticLoginTxt[0].equals("true")){
      inputtedUsername = automaticLoginTxt[1];
      inputtedPassword = automaticLoginTxt[2];

      automaticLogin = true;
    } else{
      inputtedUsername = "";
      inputtedPassword = "";
    
      automaticLogin = false;
    }
  }

  public void inputUsername(String username){
    inputtedUsername = username;
  }

  public void inputtedPassword(String password){
    inputtedPassword = password;
  }

  public void toggleAutomaticLogin(){
    automaticLogin = !automaticLogin;
  }

  public String getUsername(){
    return inputtedUsername;
  }

  public String getPasswword(){
    return inputtedPassword;
  }

  public String[] automaticLoginTxt(){
    return new String[]{(automaticLogin?"true":"false"), inputtedUsername, inputtedPassword};
  }
}
