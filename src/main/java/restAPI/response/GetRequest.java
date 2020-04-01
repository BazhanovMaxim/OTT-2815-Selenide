package restAPI.response;

import filesUtils.CreateFile;
import filesUtils.ReadFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class GetRequest {

    private ReadFile readFile;
    private CreateFile createFile;

    // Get-запрос на получение комментария
    public void requestToGetComment(){
        readFile = new ReadFile();
        String userLogin = readFile.returnLogin();
        String userPassword = readFile.returnPass();
        String pathToPostRequest = "/rest/api/2/issue/{issueIdOrKey}/comment/";
        String issueKey = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        int expectedStatusCode = 200;
        getRequest(issueKey, userLogin, userPassword, pathToPostRequest, expectedStatusCode);
    }

    // Get-запрос на получение информации о записи
    public void requestToGetIssueInfo(){
        readFile = new ReadFile();
        String userLogin = readFile.returnLogin();
        String userPassword = readFile.returnPass();
        String issueKey = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        String pathToPostRequest = "http://localhost:8080/rest/api/2/issue/{key_value_issue}";
        int expectedStatusCode = 200;
        getRequest(issueKey, userLogin, userPassword, pathToPostRequest, expectedStatusCode);
    }

    // Запрос
    private void getRequest(String key_value, String Login, String Password,
                            String pathToPostRequest, int expectedStatusCode){
        // rest assured
        RestAssured.baseURI = "http://localhost:8080/";
        // авторизация = base64
        Response response = RestAssured.given().auth().preemptive().
                basic(Login, Password).
                when().
                get(pathToPostRequest, key_value);
        // ожидаем, что статус отправки запроса = 200 (Успешно)
        response.then().assertThat().statusCode(expectedStatusCode);
        createFileKeyIssueAPI(response);
    }

    // Создаётся файл с ключём для созданной записи через API
    private void createFileKeyIssueAPI(Response response){
        createFile = new CreateFile();
        ResponseBody responseBody = response.getBody();
        if ((String)response.path("id") != null) {
            createFile.checkFile(responseBody.asString(), "IssueInfoAPI.json");
        }
        else {
            createFile.checkFile(responseBody.asString(), "GetCommentsAPI.json");
        }
    }
}
