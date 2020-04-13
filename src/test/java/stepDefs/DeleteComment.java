package stepDefs;

import allure.AllureLogger;
import filesUtils.ReadFile;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import restAPI.request.DeleteRequest;
import selenideElements.DeleteCommentPanel;
import selenideElements.ReportedByMePage;

public class DeleteComment extends AllureLogger {

    private ReportedByMePage reportedByMePage;
    private DeleteCommentPanel deleteCommentPanel;
    private DeleteRequest deleteRequest;
    private ReadFile readFile;

    @Step("Отправляется запрос на удаление записи через API")
    @Тогда("Удаляется комментарий через API")
    public void commentsAreDeletedViaTheAPI() {
        deleteRequest = new DeleteRequest();
        readFile = new ReadFile();
        String userLogin = readFile.returnUserLogin();
        String userPassword = readFile.returnUserPassword();
        String idComment = readFile.returnIdComment();
        String pathToDeleteRequest = "rest/api/2/issue/{IssueKey}/comment/" + idComment;
        String pathIssueKeyAPI = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        Response response = deleteRequest.deleteRequest(pathIssueKeyAPI, userLogin, userPassword, pathToDeleteRequest);
        equals("Проверка статуса кода", response.getStatusCode(), 204);
    }

    @Step("Пользователь нажимает на кнопку Comments")
    @И("пользователь нажимает на кнопку Comments")
    public void userClicksTheCommentsButton() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickIssueAllCommentButton();
        attachScreenshot();
    }

    @Step("Пользователь нажимает на кнопку удаления комментария")
    @Тогда("пользователь нажимает на кнопку удаления комментария")
    public void userClicksTheDeleteCommentButton() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickIssueTrashButtonToDeleteComment();
        attachScreenshot();
    }

    @Step("Открывается окно Delete Comment / Проверка заголовка")
    @И("открывается окно \"([^\"]*)\"$")
    public void windowOpens(String DeletePanelTitle) {
        deleteCommentPanel = new DeleteCommentPanel();
        equals("Проверка открытого окна Delete Comment по заголовку", deleteCommentPanel.checkIssueTitleDeleteComment(), DeletePanelTitle);
        attachScreenshot();
    }

    @Step("Пользователь нажимает на кнопку Delete")
    @Тогда("пользователь нажимает на кнопку \"([^\"]*)\"$")
    public void userClicksTheButton(String buttonToDelete) {
        deleteCommentPanel = new DeleteCommentPanel();
        deleteCommentPanel.clickIssueDeleteButton(buttonToDelete);
        attachScreenshot();
    }

    @Step("Проверяется удалённый комментарий")
    @Тогда("проверяется удаление комментария")
    public void checksWhetherACommentIsDeleted() {
        reportedByMePage = new ReportedByMePage();
        String expectedValue = "MAX-2 has been updated.";
        equals("Проверка удалённого комментария", reportedByMePage.checkIssueDeletePanel(), expectedValue);
    }
}
