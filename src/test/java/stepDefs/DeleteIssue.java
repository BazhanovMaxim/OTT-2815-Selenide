package stepDefs;

import io.cucumber.java.ru.Когда;
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
    public void отправляетсяЗапросНаУдалениеЗаписи() {
        deleteIssueAPI = new DeleteRequest();
        deleteIssueAPI.setDeleteIssueAPI();
    }

    @Когда("пользователь выбирает созданную ранее запись")
    public void пользовательВыбираетСозданнуюРанееЗапись() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToCreatedIssueById();
    }

    @И("пользователь нажимает на кнопку More")
    public void пользоватьНажимаетНаКнопку(){
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToButtonMore();
    }

    @И("пользователь нажимает на кнопку Delete")
    public void пользовательНажимаетНаКнопкуDelete() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToDeleteButton();
    }

    @Тогда("появляется окно \"([^\"]*)\"$")
    public void появляетсяОкно(String tabName){
        deleteIssueTab = new DeleteIssueTab();
        deleteIssueTab.checkTitle(tabName);
    }

    @И("пользователь удаляет запись")
    public void пользовательУдаляетЗапись(){
        deleteIssueTab = new DeleteIssueTab();
        deleteIssueTab.clickDeleteButton();
    }

    @Тогда("проверяется удаление записи")
    public void проверяетсяУдалениеЗаписи() {
        deleteIssueTab = new DeleteIssueTab();
        deleteIssueTab.checkDeletedIssue();
    }
}
