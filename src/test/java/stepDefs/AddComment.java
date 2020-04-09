package stepDefs;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import org.junit.Assert;
import restAPI.request.PostRequest;
import selenideElements.NavigationPanel;
import selenideElements.ReportedByMePage;

public class AddComment {

    private PostRequest postRequest;
    private ReportedByMePage reportedByMePage;
    private NavigationPanel navigationPanel;
    private String comment;

    @Step("Отправка запроса на добавления комментария")
    @Тогда("отправляется запрос на добавления комментария")
    public void requestIsSentToAddAComment(){
        postRequest = new PostRequest();
        Assert.assertEquals(201,postRequest.requestToAddComment());
    }

    @Step("Пользователь нажимает на навигационную панель на Issue")
    @Когда("пользователь нажимает на навигационную панель на Issue")
    public void userClicksOnTheNavigationBarOnIssue() {
        navigationPanel = new NavigationPanel();
        navigationPanel.clickIssueNavigation();
    }

    @Step("Пользователь нажимает на Reported By Me")
    @И("пользователь нажимает на Reported By Me")
    public void userClicksOnTheReportedByMe() {
        navigationPanel = new NavigationPanel();
        navigationPanel.clickReporterLink();
    }

    @Step("Пользователь на странице Reported By Me / Проверка заголовка")
    @Тогда("пользователь на странице \"([^\"]*)\"$")
    public void userOnThePage(String title) {
        reportedByMePage = new ReportedByMePage();
        Assert.assertEquals(title, reportedByMePage.checkTitle());
    }

    @Step("Выбор записи по ключу")
    @Когда("пользовать выбирает запись по ключу")
    public void userSelectsAnEntryByKey() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToIssueById();
    }

    @Step("Пользователь нажимает на кнопку Comment")
    @И("пользователь нажимает на кнопку Comment")
    public void userClicksTheCommentButton(){
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickButtonComment();
    }

    @Step("Пользователь печатает комментарий")
    @Тогда("пользователь печатает комментарий \"([^\"]*)\"$")
    public void userPrintsAComment(String comment) {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.setIssueCommentField(comment);
        this.comment = comment;
    }

    @Step("Пользователь нажимает на кнопку Add")
    @И("пользовать нажимает на кнопку Add")
    public void userClicksTheAddButton() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.addCommentButton();
    }

    @Step("Проверяется добавленный комментарий")
    @Тогда("проверяется добавленный комментарий")
    public void addedCommentIsChecked() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.checkAddedComment(comment);
    }
}