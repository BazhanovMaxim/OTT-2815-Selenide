package restAPI.request;

import filesUtils.ReadFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteIssueAPI {

    private ReadFile readFile;

    private void deleteIssue(String key_issue){

        readFile = new ReadFile();

        RestAssured.baseURI = "http://localhost:8080/";
        // авторизация = base64
        RequestSpecification request = RestAssured.given().auth().preemptive().basic(readFile.returnLogin(), readFile.returnPass());
        request.header("Content-Type", "application/json");
        // запрос
        Response response = request.delete("/rest/api/2/issue/{issueIdOrKey}", key_issue);
        // ожидаем, что статус отправки запроса = 204 (Успешно)
        response.then().assertThat().statusCode(204);

    }

    public void setDeleteIssueAPI(){
        readFile = new ReadFile();
        deleteIssue(readFile.readFile("target\\TestsFiles\\IssueKeyAPI.txt"));
    }

}
