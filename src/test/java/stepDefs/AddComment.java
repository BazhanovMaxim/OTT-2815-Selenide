package stepDefs;

import allure.AllureLogger;
import filesUtils.ReadFile;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import restAPI.request.PostRequest;
import selenideElements.NavigationPanel;
import selenideElements.ReportedByMePage;

import static com.codeborne.selenide.Condition.exactText;

public class AddComment extends AllureLogger {

    private PostRequest postRequest;
    private ReportedByMePage reportedByMePage;
    private NavigationPanel navigationPanel;
    private String comment;
    private ReadFile readFile;

    @Step("Отправка запроса на добавления комментария")
    @Тогда("отправляется запрос на добавления комментария")
    public void requestIsSentToAddAComment(){
        postRequest = new PostRequest();
        readFile = new ReadFile();
        // Отправка запроса
        String userLogin = readFile.returnUserLogin();
        String userPassword = readFile.returnUserPassword();
        String issueKey = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        String pathToJsonFileForCreateWithAPI = "src/main/resources/response/addComment.json";
        String pathToPostRequest = "/rest/api/2/issue/" + issueKey + "/comment";
        Response response = postRequest.requestToPost(userLogin, userPassword, pathToJsonFileForCreateWithAPI, pathToPostRequest);
        equals("Проверка статуса кода", response.getStatusCode(), 201);
    }

    @Step("Пользователь нажимает на навигационную панель на Issue")
    @Когда("пользователь нажимает на навигационную панель на Issue")
    public void userClicksOnTheNavigationBarOnIssue() {
        navigationPanel = new NavigationPanel();
        navigationPanel.clickIssueNavigation();
        attachScreenshot();
    }

    @Step("Пользователь нажимает на Reported By Me")
    @И("пользователь нажимает на Reported By Me")
    public void userClicksOnTheReportedByMe() {
        navigationPanel = new NavigationPanel();
        navigationPanel.clickReporterLink();
        attachScreenshot();
    }

    @Step("Пользователь на странице Reported By Me / Проверка заголовка")
    @Тогда("пользователь на странице \"([^\"]*)\"$")
    public void userOnThePage(String titleReportedByMe) {
        reportedByMePage = new ReportedByMePage();
        equals("Проверяем, что на странице Reported By Me", reportedByMePage.checkTitle(), titleReportedByMe);
        attachScreenshot();
    }

    @Step("Выбор записи по ключу")
    @Когда("пользовать выбирает запись по ключу")
    public void userSelectsAnEntryByKey() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToIssueById();
        attachScreenshot();
    }

    @Step("Пользователь нажимает на кнопку Comment")
    @И("пользователь нажимает на кнопку Comment")
    public void userClicksTheCommentButton(){
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickButtonComment();
        attachScreenshot();
    }

    @Step("Пользователь печатает комментарий")
    @Тогда("пользователь печатает комментарий \"([^\"]*)\"$")
    public void userPrintsAComment(String comment) {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.setIssueCommentField(comment);
        this.comment = comment;
        attachScreenshot();
    }

    @Step("Пользователь нажимает на кнопку Add")
    @И("пользовать нажимает на кнопку Add")
    public void userClicksTheAddButton() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.addCommentButton();
        attachScreenshot();
    }

    @Step("Проверяется добавленный комментарий")
    @Тогда("проверяется добавленный комментарий")
    public void addedCommentIsChecked() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.checkAddedComment().shouldBe(exactText(comment));
        attachScreenshot();
    }

}