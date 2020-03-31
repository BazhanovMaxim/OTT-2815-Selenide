package stepDefs;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import restAPI.response.GetIssueInfo;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import selenideElements.ReportedByMePage;

@RunWith(Cucumber.class)
public class GetInfoAboutIssue {

    private GetIssueInfo getResponse;
    private ReportedByMePage reportedByMePage;

    @Тогда("отправляется запрос на получение информации о записи")
    public void ОтправляетсяЗапросНаПолучениеИнформацииОЗаписи(){
        getResponse = new GetIssueInfo();
        getResponse.responseToGetIssueInfo();
    }

    @И("пользователь записывает информацию о записи в \"([^\"]*)\"$")
    public void ПользовательЗаписываетсИнформациюОЗаписиВ(String nameFile){
        reportedByMePage = new ReportedByMePage();
        reportedByMePage.getIssueUI(nameFile);
    }
}
