package stepDefs;

import filesUtils.ReadFile;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import cucumber.api.junit.Cucumber;
import io.cucumber.java.ru.И;
import org.junit.Assert;
import org.junit.runner.RunWith;
import selenideElements.AuthPage;


@RunWith(Cucumber.class)
public class Auth {

    private AuthPage authPage;
    private ReadFile readFile;
    //путь к нашему файлу конфигураций
    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

    @Дано("открыть сайт Jira")
    public void openSiteJira() {
        authPage = new AuthPage();
        authPage.openSite();
    }

    @И("пользователь входит в систему")
    public void userEnterToSystem() {
        readFile = new ReadFile();
        authPage.inputUserData(readFile.returnUserLogin(), readFile.returnUserPassword());
    }

    @Тогда("открывается страница \"([^\"]*)\"$")
    public void pageOpens(String titleDashboard) {
        authPage = new AuthPage();
        Assert.assertEquals(titleDashboard, authPage.returnTitleDashboard());
    }
}
