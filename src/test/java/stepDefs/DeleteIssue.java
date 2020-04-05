package stepDefs;

import io.cucumber.java.ru.Когда;
import org.junit.Assert;
import restAPI.request.DeleteRequest;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import selenideElements.DeleteIssueTab;
import selenideElements.ReportedByMePage;

public class DeleteIssue {

    private ReportedByMePage reportedByMePage;
    private DeleteIssueTab deleteIssueTab;
    private DeleteRequest deleteIssueAPI;

    @Тогда("отправляется запрос на удаление записи")
    public void requestToDeleteAnEntryIsSent() {
        deleteIssueAPI = new DeleteRequest();
        Assert.assertEquals(204, deleteIssueAPI.setDeleteIssueAPI());
    }

    @Когда("пользователь выбирает созданную ранее запись")
    public void userSelectsAPreviouslyCreatedIssue() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToCreatedIssueById();
    }

    @И("пользователь нажимает на кнопку More")
    public void userClicksTheButtonMore(){
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToButtonMore();
    }

    @И("пользователь нажимает на кнопку Delete")
    public void userClicksTheButtonDelete() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToDeleteButton();
    }

    @Тогда("появляется окно \"([^\"]*)\"$")
    public void windowsIsOpened(String deletePanelTitle){
        deleteIssueTab = new DeleteIssueTab();
        Assert.assertEquals(deletePanelTitle, deleteIssueTab.checkTitle());
    }

    @И("пользователь удаляет запись")
    public void userDeleteTheIssue(){
        deleteIssueTab = new DeleteIssueTab();
        deleteIssueTab.clickDeleteButton();
    }

    @Тогда("проверяется удаление записи")
    public void checkedToDeletedTheIssue() {
        deleteIssueTab = new DeleteIssueTab();
        deleteIssueTab.checkDeletedIssue();
    }
}
