package stepDefs;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import restAPI.request.DeleteIssueCommentsAPI;
import selenideElements.DeleteCommentPanel;
import selenideElements.ReportedByMePage;

public class DeleteComments {

    private DeleteIssueCommentsAPI deleteIssueCommentsAPI;
    private ReportedByMePage reportedByMePage;
    private DeleteCommentPanel deleteCommentPanel;

    @Тогда("Удаляется комментарий через API")
    public void удаляетсяКомментарийЧерезAPI() {
        deleteIssueCommentsAPI = new DeleteIssueCommentsAPI();
        deleteIssueCommentsAPI.setDeleteIssueAPI();
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
