package restAPI.request;

import filesUtils.ReadFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest {

    private ReadFile readFile;

    /*
    *  Delete-запрос на удаление записи
     */
    public int setDeleteIssueAPI(){
        readFile = new ReadFile();
        String userLogin = readFile.returnLogin();
        String userPassword = readFile.returnPass();
        String pathToDeleteRequest = "/rest/api/2/issue/{issueIdOrKey}";
        String pathIssueKeyAPI = readFile.readFile("target\\TestsFiles\\IssueKeyAPI.txt");
        Response response = deleteRequest(pathIssueKeyAPI, userLogin, userPassword, pathToDeleteRequest);
        return response.getStatusCode();
    }

    /*
    *  Delete-запрос на удаления комментария
     */
    public int setDeleteCommentAPI(){
        readFile = new ReadFile();
        String userLogin = readFile.returnLogin();
        String userPassword = readFile.returnPass();
        String idComment = readFile.returnIdComment();
        String pathToDeleteRequest = "rest/api/2/issue/{IssueKey}/comment/" + idComment;
        String pathIssueKeyAPI = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        Response response = deleteRequest(pathIssueKeyAPI, userLogin, userPassword, pathToDeleteRequest);
        return response.getStatusCode();
    }

    // Запрос на удаление
    private Response deleteRequest(String key_issue, String userLogin, String userPassword,
                               String pathToDeleteRequest){
        RestAssured.baseURI = "http://localhost:8080/";
        // авторизация = base64
        RequestSpecification request = RestAssured.given().auth().preemptive().basic(userLogin, userPassword);
        request.header("Content-Type", "application/json");
        // запрос
        Response response = request.delete(pathToDeleteRequest, key_issue);
        return response;
    }
}
