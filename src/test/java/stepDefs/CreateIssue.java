package stepDefs;

import allure.AllureLogger;
import filesUtils.ReadFile;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import filesUtils.CreateFile;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import randonNameIssue.RandomName;
import restAPI.request.PostRequest;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import selenideElements.CreateIssuePage;
import selenideElements.NavigationPanel;
import selenideElements.ReportedByMePage;

@RunWith(Cucumber.class)
public class CreateIssue extends AllureLogger {

    private NavigationPanel navigationPanel;
    private ReportedByMePage reportedByMePage;
    private CreateIssuePage createIssuePage;
    private CreateFile createFile;
    private ReadFile readFile;
    private PostRequest postRequest;
    private RandomName getRandomNameIssue;
    private PostRequest createIssueAPI;

    @Step("Отправка запроса на создание записи через API")
    @Тогда("создаётся запись через API")
    public void issueIsCreatedViaTheAPI(){
        createIssueAPI = new PostRequest();
        readFile = new ReadFile();
        postRequest = new PostRequest();
        String userLogin = readFile.returnUserLogin();
        String userPassword = readFile.returnUserPassword();
        String pathToJsonFileForCreateWithAPI = "src/main/resources/response/createIssue.json";
        String pathToPostRequest = "/rest/api/2/issue/";
        Response response = postRequest.requestToPost(userLogin, userPassword, pathToJsonFileForCreateWithAPI, pathToPostRequest);
        equals("Проверка статуса кода", response.getStatusCode(), 201);
    }

    @Step("Пользователь нажимает на кнопку Create")
    @Когда("пользователь нажимает на кнопку Create")
    public void userClicksTheCreateButton() {
        navigationPanel = new NavigationPanel();
        navigationPanel.clickCreateButton();
        attachScreenshot();
    }

    @Step("Открывается вкладка Create Issue")
    @Тогда("открывается вкладка \"([^\"]*)\"$")
    public void tabOpens(String titleCreateIssue) {
        createIssuePage = new CreateIssuePage();
        equals("Проверка открытой страницы по заголовку Create Issue", createIssuePage.checkTitleOfIssue(),titleCreateIssue);
        attachScreenshot();
    }

    @Step("Пользователь в блоке Project печатает текст")
    @Когда("пользователь в блоке Project печатает \"([^\"]*)\"$")
    public void userInTheProjectBlockPrints(String project_title) {
        createIssuePage = new CreateIssuePage();
        createIssuePage.enterProjectName(project_title);
        attachScreenshot();
    }

    @Step("Пользователь в блоке Issue Type печатает текст")
    @И("пользователь в блоке Issue Type печатает \"([^\"]*)\"$")
    public void userBlockIssieTypePrints(String type) {
        createIssuePage = new CreateIssuePage();
        createIssuePage.enterIssueType(type);
        attachScreenshot();
    }

    @Step("Пользователь в блоке Summary печатает название записи")
    @И("пользователь в блоке Summary печатает название записи")
    public void userPrintsTheNameOfTheIssueInTheSummaryBlock() {
        getRandomNameIssue = new RandomName();
        createFile = new CreateFile();
        getRandomNameIssue.generateRandomSummary();
        createIssuePage.enterSummary(getRandomNameIssue.getNameIssue());
        createFile.checkFile(getRandomNameIssue.getNameIssue(), "IssueSummary.txt");
        attachScreenshot();
    }

    @Step("Пользователь нажимает на ссылку 'Assiqn to me'")
    @И("пользователь нажимает на ссылку Assiqn to me")
    public void userClicksTheLinkAssiqnToMe() {
        createIssuePage = new CreateIssuePage();
        createIssuePage.setAssiqnToMeLink();
        attachScreenshot();
    }

    @Step("Пользователь нажимает на кнопку создания записи")
    @И("пользователь нажимает на кнопку создания записи")
    public void userClicksOnTheButtonToCreateTheIssue() {
        createIssuePage = new CreateIssuePage();
        createIssuePage.clickCreateButton();
        attachScreenshot();
    }

    @Step("Проверка созданной записи")
    @Тогда("проверяется созданная запись")
    public void createdIssueIsChecked() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.checkIssueCreated();
        attachScreenshot();
    }
}
