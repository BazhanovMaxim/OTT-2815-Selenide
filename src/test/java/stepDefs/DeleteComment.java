package stepDefs;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import restAPI.request.DeleteRequest;
import selenideElements.DeleteCommentPanel;
import selenideElements.ReportedByMePage;

public class DeleteComment {

    private ReportedByMePage reportedByMePage;
    private DeleteCommentPanel deleteCommentPanel;
    private DeleteRequest deleteRequest;

    @Тогда("Удаляется комментарий через API")
    public void commentsAreDeletedViaTheAPI() {
        deleteRequest = new DeleteRequest();
        Assert.assertEquals(204, deleteRequest.setDeleteCommentAPI());
    }

    @И("пользователь нажимает на кнопку Comments")
    public void userClicksTheCommentsButton() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickIssueAllCommentButton();
    }

    @Тогда("пользователь нажимает на кнопку удаления комментария")
    public void userClicksTheDeleteCommentButton() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickIssueTrashButtonToDeleteComment();
    }

    @И("открывается окно \"([^\"]*)\"$")
    public void windowOpens(String DeletePanelTitle) {
        deleteCommentPanel = new DeleteCommentPanel();
        Assert.assertEquals(DeletePanelTitle, deleteCommentPanel.checkIssueTitleDeleteComment());
    }

    @Тогда("пользователь нажимает на кнопку \"([^\"]*)\"$")
    public void userClicksTheButton(String buttonToDelete) {
        deleteCommentPanel = new DeleteCommentPanel();
        deleteCommentPanel.clickIssueDeleteButton(buttonToDelete);
    }

    @Тогда("проверяется удаление комментария")
    public void checksWhetherACommentIsDeleted() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.checkIssueDeletePanel();
    }
}
