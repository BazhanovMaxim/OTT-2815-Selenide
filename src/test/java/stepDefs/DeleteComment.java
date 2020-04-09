package stepDefs;

import com.codeborne.selenide.Condition;
import filesUtils.ReadFile;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import restAPI.request.DeleteRequest;
import selenideElements.DeleteCommentPanel;
import selenideElements.ReportedByMePage;

import static com.codeborne.selenide.Condition.exactText;

public class DeleteComment {

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
        Assert.assertEquals(204, response.getStatusCode());
    }

    @Step("Пользователь нажимает на кнопку Comments")
    @И("пользователь нажимает на кнопку Comments")
    public void userClicksTheCommentsButton() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickIssueAllCommentButton();
    }

    @Step("Пользователь нажимает на кнопку удаления комментария")
    @Тогда("пользователь нажимает на кнопку удаления комментария")
    public void userClicksTheDeleteCommentButton() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickIssueTrashButtonToDeleteComment();
    }

    @Step("Открывается окно Delete Comment / Проверка заголовка")
    @И("открывается окно \"([^\"]*)\"$")
    public void windowOpens(String DeletePanelTitle) {
        deleteCommentPanel = new DeleteCommentPanel();
        deleteCommentPanel.checkIssueTitleDeleteComment().waitWhile(Condition.enabled, 10000).shouldHave(exactText(DeletePanelTitle));
    }

    @Step("Пользователь нажимает на кнопку Delete")
    @Тогда("пользователь нажимает на кнопку \"([^\"]*)\"$")
    public void userClicksTheButton(String buttonToDelete) {
        deleteCommentPanel = new DeleteCommentPanel();
        deleteCommentPanel.clickIssueDeleteButton(buttonToDelete);
    }

    @Step("Проверяется удалённый комментарий")
    @Тогда("проверяется удаление комментария")
    public void checksWhetherACommentIsDeleted() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.checkIssueDeletePanel().waitWhile(Condition.enabled, 10000);
    }
}
