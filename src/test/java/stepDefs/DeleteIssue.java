package stepDefs;

import allure.AllureLogger;
import filesUtils.ReadFile;
import io.cucumber.java.ru.Когда;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import restAPI.request.DeleteRequest;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import selenideElements.DeleteIssueTab;
import selenideElements.ReportedByMePage;

import static com.codeborne.selenide.Condition.visible;

public class DeleteIssue extends AllureLogger {

    private ReportedByMePage reportedByMePage;
    private DeleteIssueTab deleteIssueTab;
    private DeleteRequest deleteIssueAPI;
    private ReadFile readFile;

    @Step("Отправляется запрос на удаление записи через API")
    @Тогда("отправляется запрос на удаление записи")
    public void requestToDeleteAnEntryIsSent() {
        deleteIssueAPI = new DeleteRequest();
        readFile = new ReadFile();
        String userLogin = readFile.returnUserLogin();
        String userPassword = readFile.returnUserPassword();
        String pathToDeleteRequest = "/rest/api/2/issue/{issueIdOrKey}";
        String pathIssueKeyAPI = readFile.readFile("target\\TestsFiles\\IssueKeyAPI.txt");
        Response response = deleteIssueAPI.deleteRequest(pathIssueKeyAPI, userLogin, userPassword, pathToDeleteRequest);
        equals("Проверка статуса кода", response.getStatusCode(), 204);
    }

    @Step("Пользователь выбирает созданную ранее запись")
    @Когда("пользователь выбирает созданную ранее запись")
    public void userSelectsAPreviouslyCreatedIssue() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToCreatedIssueById();
        attachScreenshot();
    }

    @Step("Пользователь нажимает на кнопку More")
    @И("пользователь нажимает на кнопку More")
    public void userClicksTheButtonMore(){
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToButtonMore();
        attachScreenshot();
    }

    @Step("Пользователь нажимает на кнопку Delete")
    @И("пользователь нажимает на кнопку Delete")
    public void userClicksTheButtonDelete() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToDeleteButton();
        attachScreenshot();
    }

    @Step("Появляется окно Delete Issue")
    @Тогда("появляется окно \"([^\"]*)\"$")
    public void windowsIsOpened(String deletePanelTitle){
        deleteIssueTab = new DeleteIssueTab();
        readFile = new ReadFile();
        Assert.assertEquals(deletePanelTitle + ": " + readFile.readFile("target/TestsFiles/IssueKey.txt"), deleteIssueTab.checkTitle());
        attachScreenshot();
    }

    @Step("Пользователь удаляет запись")
    @И("пользователь удаляет запись")
    public void userDeleteTheIssue(){
        deleteIssueTab = new DeleteIssueTab();
        deleteIssueTab.clickDeleteButton();
        attachScreenshot();
    }

    @Step("Проверяется удаление записи")
    @Тогда("проверяется удаление записи")
    public void checkedToDeletedTheIssue() {
        deleteIssueTab = new DeleteIssueTab();
        deleteIssueTab.checkDeletedIssue().waitUntil(visible, 10000).isEnabled();
        attachScreenshot();
    }
}
