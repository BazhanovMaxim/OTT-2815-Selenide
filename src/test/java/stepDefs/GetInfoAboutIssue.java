package stepDefs;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import restAPI.response.GetRequest;
import selenideElements.ReportedByMePage;

@RunWith(Cucumber.class)
public class GetInfoAboutIssue {

    private GetRequest getResponse;
    private ReportedByMePage reportedByMePage;

    @Тогда("отправляется запрос на получение информации о записи")
    public void ОтправляетсяЗапросНаПолучениеИнформацииОЗаписи(){
        getResponse = new GetRequest();
        getResponse.requestToGetIssueInfo();
    }

    @И("пользователь записывает информацию о записи в \"([^\"]*)\"$")
    public void ПользовательЗаписываетсИнформациюОЗаписиВ(String nameFile){
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.getIssueUI(nameFile);
    }
}
