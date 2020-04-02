package stepDefs;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import restAPI.request.DeleteRequest;
import selenideElements.DeleteCommentPanel;
import selenideElements.ReportedByMePage;

public class DeleteComments {

    private ReportedByMePage reportedByMePage;
    private DeleteCommentPanel deleteCommentPanel;
    private DeleteRequest deleteRequest;

    @Тогда("Удаляется комментарий через API")
    public void удаляетсяКомментарийЧерезAPI() {
        deleteRequest = new DeleteRequest();
        Assert.assertEquals(204, deleteRequest.setDeleteCommentAPI());
    }

    @И("пользователь нажимает на кнопку Comments")
    public void пользовательНажимаетНаКнопкуComments() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickIssueAllCommentButton();
    }

    @Тогда("пользователь нажимает на кнопку удаления комментария")
    public void пользовательНажимаетНаКнопкуУдаленияКомментария() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickIssueTrashButtonToDeleteComment();
    }

    @И("открывается окно \"([^\"]*)\"$")
    public void открываетсяОкно(String DeletePanel) {
        deleteCommentPanel = new DeleteCommentPanel();
        deleteCommentPanel.checkIssueTitleDeleteComment(DeletePanel);
    }

    @Тогда("пользователь нажимает на кнопку \"([^\"]*)\"$")
    public void пользовательНажимаетНаКнопку(String buttonToDelete) {
        deleteCommentPanel = new DeleteCommentPanel();
        deleteCommentPanel.clickIssueDeleteButton(buttonToDelete);
    }

    @Тогда("проверяется удаление комментария")
    public void проверяетсяУдалениеКомментария() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.checkIssueDeletePanel();
    }
}
