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

public class UpdateInfoIssue {

    private ReadFile readFile;

    private void requestToUpdate(String key_issue){

        readFile = new ReadFile();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/response/editIssue.json"));
            JsonObject jb = new JsonParser().parse(bufferedReader).getAsJsonObject();
            RestAssured.baseURI = "http://localhost:8080/";
            RequestSpecification request = RestAssured.given().auth().preemptive().basic(readFile.returnLogin(), readFile.returnPass());

            request.header("Content-Type", "application/json");
            request.body(jb.toString());
            Response response = request.put("/rest/api/2/issue/{key_issue}", key_issue);
            // ожидаем, что статус отправки запроса = 204 (Успешно)
            response.then().assertThat().statusCode(204);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateInfo(){
        readFile = new ReadFile();
        requestToUpdate(readFile.readFile("src/main/resources/response/keyIssueAPI.txt"));
    }

}
