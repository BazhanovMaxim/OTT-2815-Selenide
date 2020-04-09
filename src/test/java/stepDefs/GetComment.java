package stepDefs;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import org.junit.Assert;
import restAPI.response.GetRequest;

public class GetComment {

    private GetRequest getCommentAPI;

    @Step("Отправляется запрос на получение комментариев записи через API")
    @Тогда("отправляется запрос на получение комментариев записи через API")
    public void requestIsSentToGetCommentsOnTheEntryViaTheAPI(){
        getCommentAPI = new GetRequest();
        Assert.assertEquals(200, getCommentAPI.requestToGetComment());
    }

    @Step("Пользователь записывает информацию о комментариях в файл 'InfoAboutIssueCommentsUI.txt'")
    @И("пользователь записывает информацию о комментариях в \"([^\"]*)\"$")
    public void userWritesInformationAboutCommentsIn(String arg0) {
    }
}
