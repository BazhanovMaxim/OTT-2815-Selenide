package stepDefs;

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

public class DeleteIssue {

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
        Assert.assertEquals(204, response.getStatusCode());
    }

    @Step("Пользователь выбирает созданную ранее запись")
    @Когда("пользователь выбирает созданную ранее запись")
    public void userSelectsAPreviouslyCreatedIssue() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToCreatedIssueById();
    }

    @Step("Пользователь нажимает на кнопку More")
    @И("пользователь нажимает на кнопку More")
    public void userClicksTheButtonMore(){
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToButtonMore();
    }

    @Step("Пользователь нажимает на кнопку Delete")
    @И("пользователь нажимает на кнопку Delete")
    public void userClicksTheButtonDelete() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToDeleteButton();
    }

    @Step("Появляется окно Delete Issue")
    @Тогда("появляется окно \"([^\"]*)\"$")
    public void windowsIsOpened(String deletePanelTitle){
        deleteIssueTab = new DeleteIssueTab();
        readFile = new ReadFile();
        Assert.assertEquals(deletePanelTitle + ": " + readFile.readFile("target/TestsFiles/IssueKey.txt"), deleteIssueTab.checkTitle());
    }

    @Step("Пользователь удаляет запись")
    @И("пользователь удаляет запись")
    public void userDeleteTheIssue(){
        deleteIssueTab = new DeleteIssueTab();
        deleteIssueTab.clickDeleteButton();
    }

    @Step("Проверяется удаление записи")
    @Тогда("проверяется удаление записи")
    public void checkedToDeletedTheIssue() {
        deleteIssueTab = new DeleteIssueTab();
        deleteIssueTab.checkDeletedIssue().waitUntil(visible, 10000).shouldBe(visible);
    }
}
