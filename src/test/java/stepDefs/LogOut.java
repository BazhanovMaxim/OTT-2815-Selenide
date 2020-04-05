package stepDefs;

import io.cucumber.java.ru.И;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import selenideElements.NavigationPanel;

import static com.codeborne.selenide.Selenide.close;

@RunWith(Cucumber.class)
public class LogOut {

    private NavigationPanel navigationPanel;

    @И("пользователь выходит из системы")
    public void userLogsOutOfTheSystem() {
        navigationPanel = new NavigationPanel();
        navigationPanel.clickProfileLink();
        navigationPanel.clickLogOutButton();
        close();
    }
}
