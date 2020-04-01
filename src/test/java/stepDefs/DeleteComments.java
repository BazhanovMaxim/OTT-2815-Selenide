package stepDefs;

import io.cucumber.java.ru.Тогда;
import restAPI.request.DeleteIssueCommentsAPI;

public class DeleteComments {
    private DeleteIssueCommentsAPI deleteIssueCommentsAPI;

    @Тогда("Удаляется комментарий через API")
    public void удаляетсяКомментарийЧерезAPI() {
        deleteIssueCommentsAPI = new DeleteIssueCommentsAPI();
        deleteIssueCommentsAPI.setDeleteIssueAPI();
    }
}
