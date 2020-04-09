package restAPI.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UpdateRequest {

    public Response requestToUpdate(String key_issue, String userLogin, String userPassword,
                                 String pathToDeleteRequest){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/response/editIssue.json"));
            JsonObject jb = new JsonParser().parse(bufferedReader).getAsJsonObject();
            RestAssured.baseURI = "http://localhost:8080/";
            RequestSpecification request = RestAssured.given().auth().preemptive().basic(userLogin, userPassword);
            // запрос
            request.header("Content-Type", "application/json");
            request.filter(new AllureRestAssured());
            request.body(jb.toString());
            Response response = request.put(pathToDeleteRequest, key_issue);
            bufferedReader.close();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
