package stepDefs;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import filesUtils.CreateFile;
import org.junit.Assert;
import randonNameIssue.RandomName;
import restAPI.request.PostRequest;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import selenideElements.CreateIssuePage;
import selenideElements.NavigationPanel;
import selenideElements.ReportedByMePage;

@RunWith(Cucumber.class)
public class CreateIssue {

    private NavigationPanel navigationPanel;
    private ReportedByMePage reportedByMePage;
    private CreateIssuePage createIssuePage;
    private CreateFile createFile;
    private RandomName getRandomNameIssue;
    private PostRequest createIssueAPI;

    @Тогда("создаётся запись через API")
    public void issueIsCreatedViaTheAPI(){
        createIssueAPI = new PostRequest();
        Assert.assertEquals(201, createIssueAPI.requestToCreateIssue());
    }

    @Когда("пользователь нажимает на кнопку Create")
    public void userClicksTheCreateButton() {
        navigationPanel = new NavigationPanel();
        navigationPanel.clickCreateButton();
    }

    @Тогда("открывается вкладка \"([^\"]*)\"$")
    public void tabOpens(String nameTab) {
        createIssuePage = new CreateIssuePage();
        Assert.assertEquals(nameTab, createIssuePage.checkTitleOfIssue());
    }

    @Когда("пользователь в блоке Project печатает \"([^\"]*)\"$")
    public void userInTheProjectBlockPrints(String project_title) {
        createIssuePage = new CreateIssuePage();
        createIssuePage.enterProjectName(project_title);
    }

    @И("пользователь в блоке Issue Type печатает \"([^\"]*)\"$")
    public void userBlockIssieTypePrints(String type) {
        createIssuePage = new CreateIssuePage();
        createIssuePage.enterIssueType(type);
    }

    @И("пользователь в блоке Summary печатает название записи")
    public void userPrintsTheNameOfTheIssueInTheSummaryBlock() {
        getRandomNameIssue = new RandomName();
        createFile = new CreateFile();
        getRandomNameIssue.generateRandomSummary();
        createIssuePage.enterSummary(getRandomNameIssue.getNameIssue());
        createFile.checkFile(getRandomNameIssue.getNameIssue(), "IssueSummary.txt");
    }

    @И("пользователь нажимает на ссылку Assiqn to me")
    public void userClicksTheLinkAssiqnToMe() {
        createIssuePage = new CreateIssuePage();
        createIssuePage.setAssiqnToMeLink();
    }

    @И("пользователь нажимает на кнопку создания записи")
    public void userClicksOnTheButtonToCreateTheIssue() {
        createIssuePage = new CreateIssuePage();
        createIssuePage.clickCreateButton();
    }

    @Тогда("проверяется созданная запись")
    public void createdIssueIsChecked() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.checkIssueCreated();
    }
}
