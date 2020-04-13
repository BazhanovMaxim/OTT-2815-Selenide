package stepDefs;

import allure.AllureLogger;
import filesUtils.ReadFile;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import restAPI.response.GetRequest;

public class GetComment extends AllureLogger {

    private GetRequest getCommentAPI;
    private ReadFile readFile;

    @Step("Отправляется запрос на получение комментариев записи через API")
    @Тогда("отправляется запрос на получение комментариев записи через API")
    public void requestIsSentToGetCommentsOnTheEntryViaTheAPI(){
        getCommentAPI = new GetRequest();
        readFile = new ReadFile();
        String userLogin = readFile.returnUserLogin();
        String userPassword = readFile.returnUserPassword();
        String pathToPostRequest = "/rest/api/2/issue/{issueIdOrKey}/comment/";
        String issueKey = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        Response response = getCommentAPI.getRequest(issueKey, userLogin, userPassword, pathToPostRequest);
        equals("Проверка статуса кода", response.getStatusCode(), 200);
    }

    @Step("Пользователь записывает информацию о комментариях в файл 'InfoAboutIssueCommentsUI.txt'")
    @И("пользователь записывает информацию о комментариях в \"([^\"]*)\"$")
    public void userWritesInformationAboutCommentsIn(String arg0) {
    }
}
