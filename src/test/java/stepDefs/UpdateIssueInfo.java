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
    public void обновляетсяЗаписьЧерезAPI(){
        updateIssueInfo = new UpdateRequest();
        Assert.assertEquals(204, updateIssueInfo.updateInfo());
    }

    @Когда("пользователь выбирает свою запись через ключ")
    public void пользователь_выбирает_свою_запись_через_ключ() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToIssueById();
    }

    @И("пользователь нажимает на кнопку Edit")
    public void пользователь_нажимает_на_кнопку_Edit() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickEditButton();

    }

    @Тогда("открывается окно Edit Issue")
    public void открывается_окно_Edit_Issue() {
        editIssuePage = new EditIssuePage();
        editIssuePage.checkEditTitle();
    }

    @И("пользователь в блоке Summary печатает \"([^\"]*)\"$")
    public void пользователь_в_блоке_Summary_печатает(String summaryText) {
        editIssuePage = new EditIssuePage();
        editIssuePage.setSummaryField(summaryText);
    }

    @И("пользователь в блоке Priority печатает \"([^\"]*)\"$")
    public void пользователь_в_блоке_Priority_печатает(String priorityText) {
        editIssuePage = new EditIssuePage();
        editIssuePage.setIssuePrioritySpan(priorityText);
    }

    @И("пользовать нажимает на кнопку Update")
    public void пользовать_нажимает_на_кнопку_Update() {
        editIssuePage = new EditIssuePage();
        editIssuePage.clickUpdate();
    }

    @Тогда("проверяется изменение записи")
    public void ВкладкаEditIssueЗакрывается() {
        editIssuePage = new EditIssuePage();
        editIssuePage.checkIssueEditWasSuccess();
    }
}