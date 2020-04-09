package stepDefs;

import com.codeborne.selenide.Condition;
import cucumber.api.junit.Cucumber;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.runner.RunWith;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import selenideElements.EditIssuePage;
import selenideElements.ReportedByMePage;
import restAPI.request.UpdateRequest;

import static com.codeborne.selenide.Condition.visible;

@RunWith(Cucumber.class)
public class UpdateIssueInfo {

    private EditIssuePage editIssuePage;
    private ReportedByMePage reportedByMePage;
    private UpdateRequest updateIssueInfo;

    @Step("Отправляется запрос на обновление записи через API")
    @Тогда("обновляется запись через API")
    public void IssueIsUpdatedViaTheAPI(){
        updateIssueInfo = new UpdateRequest();
        Assert.assertEquals(204, updateIssueInfo.updateInfo());
    }

    @Step("Пользователь выбирает свою запись через ключ")
    @Когда("пользователь выбирает свою запись через ключ")
    public void userSelectsTheirEntryUsingAKey() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToIssueById();
    }

    @Step("Пользователь нажимает на кнопку Edit")
    @И("пользователь нажимает на кнопку Edit")
    public void userInputInEditButton() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickEditButton();

    }

    @Step("открывается окно Edit Issue")
    @Тогда("открывается окно Edit Issue")
    public void editIssueWindowOpens() {
        editIssuePage = new EditIssuePage();
        editIssuePage.checkEditTitle().shouldHave(Condition.text("Edit Issue"));
    }

    @Step("Пользователь в блоке Summary печатает текст")
    @И("пользователь в блоке Summary печатает \"([^\"]*)\"$")
    public void userInTheSummaryBlockPrints(String summaryText) {
        editIssuePage = new EditIssuePage();
        editIssuePage.setSummaryField(summaryText);
    }

    @Step("Пользователь в блоке Priority печатает текст")
    @И("пользователь в блоке Priority печатает \"([^\"]*)\"$")
    public void userInThePriorityBlockPrints(String priorityText) {
        editIssuePage = new EditIssuePage();
        editIssuePage.setIssuePrioritySpan(priorityText);
    }

    @Step("Пользователь нажимает на кнопку Update")
    @И("пользователь нажимает на кнопку Update")
    public void userClicksTheUpdateButton() {
        editIssuePage = new EditIssuePage();
        editIssuePage.clickUpdate();
    }

    @Step("Проверяется изменение записи")
    @Тогда("проверяется изменение записи")
    public void editIssueTabCloses() {
        editIssuePage = new EditIssuePage();
        editIssuePage.checkIssueEditWasSuccess().shouldBe(visible);
    }
}