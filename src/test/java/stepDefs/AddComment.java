package stepDefs;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import restAPI.request.PostRequest;
import selenideElements.NavigationPanel;
import selenideElements.ReportedByMePage;

public class AddComment {

    private PostRequest postRequest;
    private ReportedByMePage reportedByMePage;
    private NavigationPanel navigationPanel;
    private String comment;

    @Тогда("отправляется запрос на добавления комментария")
    public void requestIsSentToAddAComment(){
        postRequest = new PostRequest();
        Assert.assertEquals(201,postRequest.requestToAddComment());
    }

    @Когда("пользователь нажимает на навигационную панель на Issue")
    public void userClicksOnTheNavigationBarOnIssue() {
        navigationPanel = new NavigationPanel();
        navigationPanel.clickIssueNavigation();
    }

    @И("пользователь нажимает на Reported By Me")
    public void userClicksOnTheReportedByMe() {
        navigationPanel = new NavigationPanel();
        navigationPanel.clickReporterLink();
    }

    @Тогда("пользователь на странице \"([^\"]*)\"$")
    public void userOnThePage(String title) {
        reportedByMePage = new ReportedByMePage();
        Assert.assertEquals(title, reportedByMePage.checkTitle());
    }

    @Когда("пользовать выбирает запись по ключу")
    public void userSelectsAnEntryByKey() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToIssueById();
    }

    @И("пользователь нажимает на кнопку Comment")
    public void userClicksTheCommentButton(){
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickButtonComment();
    }

    @Тогда("пользователь печатает комментарий \"([^\"]*)\"$")
    public void userPrintsAComment(String comment) {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.setIssueCommentField(comment);
        this.comment = comment;
    }

    @И("пользовать нажимает на кнопку Add")
    public void userClicksTheAddButton() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.addCommentButton();
    }

    @Тогда("проверяется добавленный комментарий")
    public void addedCommentIsChecked() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.checkAddedComment(comment);
    }
}