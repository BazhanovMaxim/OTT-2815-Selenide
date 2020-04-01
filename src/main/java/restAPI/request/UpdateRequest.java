package restAPI.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import filesUtils.ReadFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UpdateRequest {

    private ReadFile readFile;

    public void updateInfo(){
        readFile = new ReadFile();
        String userLogin = readFile.returnLogin();
        String userPassword = readFile.returnPass();
        String pathToPutRequest = "/rest/api/2/issue/{key_issue}";
        String pathIssueKeyAPI = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        int expectedStatusCode = 204;
        requestToUpdate(pathIssueKeyAPI, userLogin, userPassword, pathToPutRequest, expectedStatusCode);
    }

    private void requestToUpdate(String key_issue, String userLogin, String userPassword,
                                 String pathToDeleteRequest, int expectedStatusCode){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/response/editIssue.json"));
            JsonObject jb = new JsonParser().parse(bufferedReader).getAsJsonObject();
            RestAssured.baseURI = "http://localhost:8080/";
            RequestSpecification request = RestAssured.given().auth().preemptive().basic(userLogin, userPassword);
            // запрос
            request.header("Content-Type", "application/json");
            request.body(jb.toString());
            Response response = request.put(pathToDeleteRequest, key_issue);
            // ожидаем, что статус отправки запроса = 204 (Успешно)
            response.then().assertThat().statusCode(expectedStatusCode);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
