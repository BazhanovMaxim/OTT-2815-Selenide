package restAPI.response;

import filesUtils.CreateFile;
import filesUtils.ReadFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import static io.restassured.RestAssured.given;

public class GetIssueInfo {

    private CreateFile createFile;
    private ReadFile readFile;

    /*
    * Получаем информацию о записи. Параметры: key_value_issue - ключ записи,
    * nameFile - название файла, в который будем записывать информацию о записи
     */

    public void responseToGetIssueInfo(){
        readFile = new ReadFile();
        //String pathToWriteResponse = "target\\TestsFiles\\IssueInfoAPI.json";
        String issueKey = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        getIssueInfo(issueKey, readFile.returnLogin(), readFile.returnPass());
    }

    private void getIssueInfo(String key_value_issue, String Login, String Password){
        Response response = RestAssured.given().auth().preemptive()
                .basic(Login, Password)
                .when()
                .get("http://localhost:8080/rest/api/2/issue/{key_value_issue}", key_value_issue);
        ResponseBody responseBody = response.getBody();
        createFileKeyIssueAPI(response.getBody().asString());
    }

    // Создаётся файл с ключём для созданной записи через API
    private void createFileKeyIssueAPI(String response){
        createFile = new CreateFile();
        createFile.checkFile(response, "IssueInfoAPI.json");
    }
}