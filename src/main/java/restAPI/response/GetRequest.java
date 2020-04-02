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
    public int requestToGetComment(){
        readFile = new ReadFile();
        String userLogin = readFile.returnLogin();
        String userPassword = readFile.returnPass();
        String pathToPostRequest = "/rest/api/2/issue/{issueIdOrKey}/comment/";
        String issueKey = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        Response response = getRequest(issueKey, userLogin, userPassword, pathToPostRequest);
        return response.statusCode();
    }

    // Get-запрос на получение информации о записи
    public int requestToGetIssueInfo(){
        readFile = new ReadFile();
        String userLogin = readFile.returnLogin();
        String userPassword = readFile.returnPass();
        String issueKey = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        String pathToPostRequest = "http://localhost:8080/rest/api/2/issue/{key_value_issue}";
        Response response  = getRequest(issueKey, userLogin, userPassword, pathToPostRequest);
        return response.statusCode();
    }

    // Запрос
    private Response getRequest(String key_value, String Login, String Password,
                            String pathToPostRequest){
        // rest assured
        RestAssured.baseURI = "http://localhost:8080/";
        // авторизация = base64
        Response response = RestAssured.given().auth().preemptive().
                basic(Login, Password).
                when().
                get(pathToPostRequest, key_value);
        createFileKeyIssueAPI(response);
        return response;
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
