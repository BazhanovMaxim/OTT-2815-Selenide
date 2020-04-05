package stepDefs;

import cucumber.api.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import selenideElements.EditIssuePage;
import selenideElements.ReportedByMePage;
import restAPI.request.UpdateRequest;

@RunWith(Cucumber.class)
public class UpdateIssueInfo {

    private EditIssuePage editIssuePage;
    private ReportedByMePage reportedByMePage;
    private UpdateRequest updateIssueInfo;

    @Тогда("обновляется запись через API")
    public void IssueIsUpdatedViaTheAPI(){
        updateIssueInfo = new UpdateRequest();
        Assert.assertEquals(204, updateIssueInfo.updateInfo());
    }

    @Когда("пользователь выбирает свою запись через ключ")
    public void userSelectsTheirEntryUsingAKey() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToIssueById();
    }

    @И("пользователь нажимает на кнопку Edit")
    public void userInputInEditButton() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickEditButton();

    }

    @Тогда("открывается окно Edit Issue")
    public void editIssueWindowOpens() {
        editIssuePage = new EditIssuePage();
        editIssuePage.checkEditTitle();
    }

    @И("пользователь в блоке Summary печатает \"([^\"]*)\"$")
    public void userInTheSummaryBlockPrints(String summaryText) {
        editIssuePage = new EditIssuePage();
        editIssuePage.setSummaryField(summaryText);
    }

    @И("пользователь в блоке Priority печатает \"([^\"]*)\"$")
    public void userInThePriorityBlockPrints(String priorityText) {
        editIssuePage = new EditIssuePage();
        editIssuePage.setIssuePrioritySpan(priorityText);
    }

    @И("пользователь нажимает на кнопку Update")
    public void userClicksTheUpdateButton() {
        editIssuePage = new EditIssuePage();
        editIssuePage.clickUpdate();
    }

    @Тогда("проверяется изменение записи")
    public void editIssueTabCloses() {
        editIssuePage = new EditIssuePage();
        editIssuePage.checkIssueEditWasSuccess();
    }
}