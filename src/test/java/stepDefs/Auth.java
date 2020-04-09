package stepDefs;

import filesUtils.ReadFile;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import cucumber.api.junit.Cucumber;
import io.cucumber.java.ru.И;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.runner.RunWith;
import selenideElements.AuthPage;


@RunWith(Cucumber.class)
public class Auth {

    private AuthPage authPage;
    private ReadFile readFile;

    @Step("Открыт сайт Jira")
    @Дано("открыть сайт Jira")
    public void openSiteJira() {
        authPage = new AuthPage();
        authPage.openSite();
    }

    @Step("Авторизация пользователя")
    @И("пользователь входит в систему")
    public void userEnterToSystem() {
        readFile = new ReadFile();
        authPage.inputUserData(readFile.returnUserLogin(), readFile.returnUserPassword());
    }

    @Step("Пользователь на странице System Dashboard / Проверка заголовка")
    @Тогда("открывается страница \"([^\"]*)\"$")
    public void pageOpens(String titleDashboard) {
        authPage = new AuthPage();
        Assert.assertEquals(titleDashboard, authPage.returnTitleDashboard());
    }
}
