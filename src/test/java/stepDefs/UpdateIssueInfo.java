package stepDefs;

import allure.AllureLogger;
import cucumber.api.junit.Cucumber;
import filesUtils.ReadFile;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.runner.RunWith;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import selenideElements.EditIssuePage;
import selenideElements.ReportedByMePage;
import restAPI.request.UpdateRequest;

import static com.codeborne.selenide.Condition.visible;

@RunWith(Cucumber.class)
public class UpdateIssueInfo extends AllureLogger {

    private EditIssuePage editIssuePage;
    private ReportedByMePage reportedByMePage;
    private UpdateRequest updateIssueInfo;
    private ReadFile readFile;

    @Step("Отправляется запрос на обновление записи через API")
    @Тогда("обновляется запись через API")
    public void IssueIsUpdatedViaTheAPI(){
        updateIssueInfo = new UpdateRequest();
        readFile = new ReadFile();
        String userLogin = readFile.returnUserLogin();
        String userPassword = readFile.returnUserPassword();
        String pathToPutRequest = "/rest/api/2/issue/{key_issue}";
        String pathIssueKeyAPI = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        Response response = updateIssueInfo.requestToUpdate(pathIssueKeyAPI, userLogin, userPassword, pathToPutRequest);
        equals("Проверка статуса кода", response.getStatusCode(), 204);
    }

    @Step("Пользователь выбирает свою запись через ключ")
    @Когда("пользователь выбирает свою запись через ключ")
    public void userSelectsTheirEntryUsingAKey() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToIssueById();
        attachScreenshot();
    }

    @Step("Пользователь нажимает на кнопку Edit")
    @И("пользователь нажимает на кнопку Edit")
    public void userInputInEditButton() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickEditButton();
        attachScreenshot();
    }

    @Step("открывается окно Edit Issue")
    @Тогда("открывается окно Edit Issue")
    public void editIssueWindowOpens() {
        editIssuePage = new EditIssuePage();
        readFile = new ReadFile();
        String expectedValue = "Edit Issue : MAX-2";
        equals("Проверка открытого окна по заголовку", editIssuePage.checkEditTitle(), expectedValue);
        attachScreenshot();
    }

    @Step("Пользователь в блоке Summary печатает текст")
    @И("пользователь в блоке Summary печатает \"([^\"]*)\"$")
    public void userInTheSummaryBlockPrints(String summaryText) {
        editIssuePage = new EditIssuePage();
        editIssuePage.setSummaryField(summaryText);
        attachScreenshot();
    }

    @Step("Пользователь в блоке Priority печатает текст")
    @И("пользователь в блоке Priority печатает \"([^\"]*)\"$")
    public void userInThePriorityBlockPrints(String priorityText) {
        editIssuePage = new EditIssuePage();
        editIssuePage.setIssuePrioritySpan(priorityText);
        attachScreenshot();
    }

    @Step("Пользователь нажимает на кнопку Update")
    @И("пользователь нажимает на кнопку Update")
    public void userClicksTheUpdateButton() {
        editIssuePage = new EditIssuePage();
        editIssuePage.clickUpdate();
        attachScreenshot();
    }

    @Step("Проверяется изменение записи")
    @Тогда("проверяется изменение записи")
    public void editIssueTabCloses() {
        editIssuePage = new EditIssuePage();
        editIssuePage.checkIssueEditWasSuccess().shouldBe(visible);
        attachScreenshot();
    }
}