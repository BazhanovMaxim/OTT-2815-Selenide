package stepDefs;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import cucumber.api.junit.Cucumber;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.runner.RunWith;
import restAPI.response.GetRequest;
import selenideElements.ReportedByMePage;

@RunWith(Cucumber.class)
public class GetInfoAboutIssue {

    private GetRequest getResponse;
    private ReportedByMePage reportedByMePage;

    @Step("Отправляется запрос на получение информации о записи через API")
    @Тогда("отправляется запрос на получение информации о записи")
    public void requestIsSentToGetInformationAboutTheIssue(){
        getResponse = new GetRequest();
        Assert.assertEquals(200, getResponse.requestToGetIssueInfo());
    }

    @Step("Пользователь записывает информацию о записи в файл 'InfoAboutIssueUI.txt'")
    @И("пользователь записывает информацию о записи в \"([^\"]*)\"$")
    public void userWritesWithInformationAboutTheEntryIn(String nameFile){
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.getIssueUI(nameFile);
    }
}
