package restAPI.request;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest {

    // Запрос на удаление
    public Response deleteRequest(String key_issue, String userLogin, String userPassword,
                               String pathToDeleteRequest){
        RestAssured.baseURI = "http://localhost:8080/";
        // авторизация = base64
        RequestSpecification request = RestAssured.given().auth().preemptive().basic(userLogin, userPassword);
        request.header("Content-Type", "application/json");
        request.filter(new AllureRestAssured());
        // запрос
        Response response = request.delete(pathToDeleteRequest, key_issue);
        return response;
    }
}
