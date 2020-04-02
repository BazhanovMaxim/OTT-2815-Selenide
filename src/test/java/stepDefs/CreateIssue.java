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
    public void создаётсяЗаписьЧерезAPI(){
        createIssueAPI = new PostRequest();
        Assert.assertEquals(201, createIssueAPI.requestToCreateIssue());
    }

    @Когда("пользователь нажимает на кнопку Create")
    public void пользовательНажимаетНаКнопкуCreate() {
        navigationPanel = new NavigationPanel();
        navigationPanel.clickCreateButton();
    }

    @Тогда("открывается вкладка \"([^\"]*)\"$")
    public void открываетсяВкладка(String nameTab) {
        createIssuePage = new CreateIssuePage();
        createIssuePage.checkTitleOfIssue(nameTab);
    }

    @Когда("пользователь в блоке Project печатает \"([^\"]*)\"$")
    public void пользовательВБлокеProjectПечатает(String project_title) {
        createIssuePage = new CreateIssuePage();
        createIssuePage.enterProjectName(project_title);
    }

    @И("пользователь в блоке Issue Type печатает \"([^\"]*)\"$")
    public void пользовательВБлокеIssueTypeПечатает(String type) {
        createIssuePage = new CreateIssuePage();
        createIssuePage.enterIssueType(type);
    }

    @И("пользователь в блоке Summary печатает название записи")
    public void пользовательВБлокеSummaryПечатаетНазваниеЗаписи() {
        getRandomNameIssue = new RandomName();
        createFile = new CreateFile();
        getRandomNameIssue.generateRandomSummary();
        createIssuePage.enterSummary(getRandomNameIssue.getNameIssue());
        createFile.checkFile(getRandomNameIssue.getNameIssue(), "IssueSummary.txt");
    }

    @И("пользователь нажимает на ссылку Assiqn to me")
    public void пользовательНажимаетНаСсылкуAssiqnToMe() {
        createIssuePage = new CreateIssuePage();
        createIssuePage.setAssiqnToMeLink();
    }

    @И("пользователь нажимает на кнопку создания записи")
    public void пользовательНажимаетНаКнопкуСозданияЗаписи() {
        createIssuePage = new CreateIssuePage();
        createIssuePage.clickCreateButton();
    }

    @Тогда("проверяется созданная запись")
    public void проверяетсяСозданнаяЗапись() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.checkIssueCreated();
    }
}
