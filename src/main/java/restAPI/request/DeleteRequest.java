package restAPI.request;

import filesUtils.ReadFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest {

    private ReadFile readFile;

    private void deleteRequest(String key_issue, String userLogin, String userPassword, String pathToDeleteRequest){
        RestAssured.baseURI = "http://localhost:8080/";
        // авторизация = base64
        RequestSpecification request = RestAssured.given().auth().preemptive().basic(userLogin, userPassword);
        request.header("Content-Type", "application/json");
        // запрос
        Response response = request.delete(pathToDeleteRequest, key_issue);
        // ожидаем, что статус отправки запроса = 204 (Успешно)
        response.then().assertThat().statusCode(204);
    }

    public void setDeleteIssueAPI(){
        readFile = new ReadFile();
        String userLogin = readFile.returnLogin();
        String userPassword = readFile.returnPass();
        String pathToDeleteRequest = "/rest/api/2/issue/{issueIdOrKey}";
        String pathIssueKeyAPI = readFile.readFile("target\\TestsFiles\\IssueKeyAPI.txt");
        deleteRequest(pathIssueKeyAPI, userLogin, userPassword, pathToDeleteRequest);
    }

    public void setDeleteCommentAPI(){
        readFile = new ReadFile();
        String userLogin = readFile.returnLogin();
        String userPassword = readFile.returnPass();
        String idComment = readFile.returnIdComment();
        String pathToDeleteRequest = "rest/api/2/issue/{IssueKey}/comment/" + idComment;
        String pathIssueKeyAPI = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        deleteRequest(pathIssueKeyAPI, userLogin, userPassword, pathToDeleteRequest);
    }

}
