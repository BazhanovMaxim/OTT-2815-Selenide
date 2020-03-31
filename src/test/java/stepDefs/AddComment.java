package stepDefs;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import restAPI.request.AddCommentAPI;
import selenideElements.NavigationPanel;
import selenideElements.ReportedByMePage;

public class AddComment {

    private AddCommentAPI addCommentAPI;
    private ReportedByMePage reportedByMePage;
    private NavigationPanel navigationPanel;
    private String comment;

    @Тогда("отправляется запрос на добавления комментария")
    public void отправляетсяЗапросНаДобавленияКомментария(){
        addCommentAPI = new AddCommentAPI();
        addCommentAPI.requestToAddComment();
    }

    @Когда("пользователь нажимает на навигационную панель на Issue")
    public void пользовательНажимаетНаНавигационнуюПанельНаIssue() {
        navigationPanel = new NavigationPanel();
        navigationPanel.clickIssueNavigation();
    }

    @И("пользователь нажимает на Reported By Me")
    public void пользовательНажимаетНаReportedByMe() {
        navigationPanel = new NavigationPanel();
        navigationPanel.clickReporterLink();
    }

    @Тогда("пользователь на странице \"([^\"]*)\"$")
    public void пользовательНаСтранице(String title) {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.checkTitle(title);
    }

    @Когда("пользовать выбирает запись по ключу")
    public void пользоватьВыбираетЗаписьПоКлючу() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickToIssueById();
    }

    @И("пользователь нажимает на кнопку Comment")
    public void пользовательНажимаетНаКнопкуComment(){
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.clickButtonComment();
    }

    @Тогда("пользователь печатает комментарий \"([^\"]*)\"$")
    public void пользовательПечатаетКомментарий(String comment) {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.setIssueCommentField(comment);
        this.comment = comment;
    }

    @И("пользовать нажимает на кнопку Add")
    public void пользоватьНажимаетНаКнопкуAdd() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.addCommentButton();
    }

    @Тогда("проверяется добавленный комментарий")
    public void проверяетсяДобавленныйКомментарий() {
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.checkAddedComment(comment);
    }
}