package restAPI.request;

import filesUtils.ReadFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteIssueCommentsAPI {

    private ReadFile readFile;

    private void deleteIssueComment(String key_issue, String userLogin, String userPassword, String idComment){
        readFile = new ReadFile();
        RestAssured.baseURI = "http://localhost:8080/";
        // авторизация = base64
        RequestSpecification request = RestAssured.given().auth().preemptive().basic(userLogin, userPassword);
        request.header("Content-Type", "application/json");
        // запрос
        Response response = request.delete("rest/api/2/issue/{IssueKey}/comment/{idComment}", key_issue, idComment);
        // ожидаем, что статус отправки запроса = 204 (Успешно)
        response.then().assertThat().statusCode(204);
    }

    public void setDeleteIssueAPI(){
        readFile = new ReadFile();
        String userLogin = readFile.returnLogin();
        String userPassword = readFile.returnPass();
        String pathIssueKeyAPI = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        String idComment = readFile.returnIdComment();
        deleteIssueComment(pathIssueKeyAPI, userLogin, userPassword, idComment);
    }

}
