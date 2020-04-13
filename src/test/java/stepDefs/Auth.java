package stepDefs;

import allure.AllureLogger;
import com.codeborne.selenide.Condition;
import filesUtils.ReadFile;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import cucumber.api.junit.Cucumber;
import io.cucumber.java.ru.И;
import io.qameta.allure.Step;
import org.junit.runner.RunWith;
import selenideElements.AuthPage;

@RunWith(Cucumber.class)
public class Auth extends AllureLogger {

    private AuthPage authPage;
    private ReadFile readFile;

    @Step("Открыт сайт Jira")
    @Дано("открыть сайт Jira")
    public void openSiteJira() {
        authPage = new AuthPage();
        authPage.openSite();
        attachScreenshot();
    }

    @Step("Авторизация пользователя")
    @И("пользователь входит в систему")
    public void userEnterToSystem() {
        readFile = new ReadFile();
        authPage.inputUserData(readFile.returnUserLogin(), readFile.returnUserPassword());
        attachScreenshot();
    }

    @Step("Пользователь на странице System Dashboard / Проверка заголовка")
    @Тогда("открывается страница \"([^\"]*)\"$")
    public void pageOpens(String titleDashboard) {
        authPage = new AuthPage();
        equals("Проверка заголовка Title Dashboard", authPage.returnTitleDashboard(), titleDashboard);
        attachScreenshot();
    }
}
