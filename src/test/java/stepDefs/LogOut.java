package stepDefs;

import allure.AllureLogger;
import io.cucumber.java.ru.И;
import cucumber.api.junit.Cucumber;
import io.qameta.allure.Step;
import org.junit.runner.RunWith;
import selenideElements.NavigationPanel;

import static com.codeborne.selenide.Selenide.close;

@RunWith(Cucumber.class)
public class LogOut extends AllureLogger {

    private NavigationPanel navigationPanel;

    @Step("Пользователь выходит из системы")
    @И("пользователь выходит из системы")
    public void userLogsOutOfTheSystem() {
        navigationPanel = new NavigationPanel();
        navigationPanel.clickProfileLink();
        navigationPanel.clickLogOutButton();
        attachScreenshot();
        close();
    }
}
