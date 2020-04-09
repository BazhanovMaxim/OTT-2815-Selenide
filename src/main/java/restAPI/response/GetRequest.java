package restAPI.response;

import filesUtils.CreateFile;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class GetRequest {

    private CreateFile createFile;

    // Запрос
    public Response getRequest(String key_value, String userLogin, String userPassword,
                            String pathToPostRequest){
        // rest assured
        RestAssured.baseURI = "http://localhost:8080/";
        Response response = RestAssured.given().auth().preemptive().
                basic(userLogin, userPassword).
                filter(new AllureRestAssured()).
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
