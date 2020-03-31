package stepDefs;

import filesUtils.ReadFile;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import cucumber.api.junit.Cucumber;
import io.cucumber.java.ru.И;
import org.junit.runner.RunWith;
import selenideElements.AuthPage;


@RunWith(Cucumber.class)
public class Auth {

    private AuthPage authPage;
    private ReadFile readFile;

    @Дано("открыть сайт Jira")
    public void открытьСайтJira() {
        authPage = new AuthPage();
        authPage.openSite();
    }

    @И("пользователь входит в систему")
    public void пользовательВходитВСистему() {
        readFile = new ReadFile();
        authPage.inputUserData(readFile.returnLogin(), readFile.returnPass());
    }

    @Тогда("открывается страница \"([^\"]*)\"$")
    public void открываетсяСтраница(String titleDashboard) {
        authPage = new AuthPage();
        authPage.checkTitleDashboard(titleDashboard);
    }
}
