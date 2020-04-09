package stepDefs;

import com.codeborne.selenide.Condition;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import org.junit.Assert;
import restAPI.request.DeleteRequest;
import selenideElements.DeleteCommentPanel;
import selenideElements.ReportedByMePage;

import static com.codeborne.selenide.Condition.exactText;

public class DeleteComment {

    private ReportedByMePage reportedByMePage;
    private DeleteCommentPanel deleteCommentPanel;
    private DeleteRequest deleteRequest;

    @Step("Отправляется запрос на удаление записи через API")
    @Тогда("Удаляется комментарий через API")
    public void commentsAreDeletedViaTheAPI() {
        deleteRequest = new DeleteRequest();
        Assert.assertEquals(204, deleteRequest.setDeleteCommentAPI());
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
