package stepDefs;

import filesUtils.ReadFile;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import cucumber.api.junit.Cucumber;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.runner.RunWith;
import restAPI.response.GetRequest;
import selenideElements.ReportedByMePage;

@RunWith(Cucumber.class)
public class GetInfoAboutIssue {

    private GetRequest getIssueAPI;
    private ReportedByMePage reportedByMePage;
    private ReadFile readFile;

    @Step("Отправляется запрос на получение информации о записи через API")
    @Тогда("отправляется запрос на получение информации о записи")
    public void requestIsSentToGetInformationAboutTheIssue(){
        getIssueAPI = new GetRequest();
        readFile = new ReadFile();
        String userLogin = readFile.returnUserLogin();
        String userPassword = readFile.returnUserPassword();
        String issueKey = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        String pathToPostRequest = "http://localhost:8080/rest/api/2/issue/{key_value_issue}";
        Response response  = getIssueAPI.getRequest(issueKey, userLogin, userPassword, pathToPostRequest);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Step("Пользователь записывает информацию о записи в файл 'InfoAboutIssueUI.txt'")
    @И("пользователь записывает информацию о записи в \"([^\"]*)\"$")
    public void userWritesWithInformationAboutTheEntryIn(String nameFile){
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.getIssueUI(nameFile);
    }
}
