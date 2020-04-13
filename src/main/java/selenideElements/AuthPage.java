package selenideElements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class AuthPage {

    private SelenideElement loginField = $("#login-form-username");
    private SelenideElement passwordField = $("#login-form-password");
    private SelenideElement loginButton = $("#login");
    private SelenideElement titleDashboard = $(".aui-page-header-main > h1");

    public void openSite(){
        open("http://localhost:8080/");
    }

    public void inputUserData(String login, String password){
        loginField.setValue(login);
        passwordField.setValue(password);
        loginButton.click();
    }

    public String returnTitleDashboard(){
        return titleDashboard.waitUntil(Condition.visible, 10000).getText();
    }


}
