package restAPI.response;

import filesUtils.CreateFile;
import filesUtils.ReadFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetCommentAPI {

    private ReadFile readFile;
    private CreateFile createFile;

    public void requestToGetComment(){
        readFile = new ReadFile();
        String issueKey = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        getCommentRequest(issueKey, readFile.returnLogin(), readFile.returnPass());
    }

    private void getCommentRequest(String key_value, String Login, String Password){
        readFile = new ReadFile();

        //rest assured
        RestAssured.baseURI = "http://localhost:8080/";
        // авторизация = base64
        Response response = RestAssured.given().auth().preemptive().
                basic(Login, Password).
                when().
                get("/rest/api/2/issue/{issueIdOrKey}/comment/", key_value);
        // ожидаем, что статус отправки запроса = 200 (Успешно)
        response.then().assertThat().statusCode(200);
        createFileKeyIssueAPI(response.getBody().asString());
    }

    // Создаётся файл с ключём для созданной записи через API
    private void createFileKeyIssueAPI(String response){
        createFile = new CreateFile();
        createFile.checkFile(response, "GetCommentsAPI.json");
    }
}
