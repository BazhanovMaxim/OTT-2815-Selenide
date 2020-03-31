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

public class AddCommentAPI {

    private ReadFile readFile;

    public void setComment(String key_value){

        readFile = new ReadFile();

        try{
            // Считываем файл и добавляем в JsonObject
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/response/addComment.json"));
            JsonObject jb = new JsonParser().parse(bufferedReader).getAsJsonObject();
            //rest assured
            RestAssured.baseURI = "http://localhost:8080/";
            // авторизация = base64
            RequestSpecification request = RestAssured.given().auth().preemptive().basic(readFile.returnLogin(), readFile.returnPass());
            request.header("Content-Type", "application/json");
            request.body(jb.toString());
            // запрос
            Response response = request.post("/rest/api/2/issue/{IssueKey}/comment", key_value);
            // ожидаем, что статус отправки запроса = 201 (Успешно)
            response.then().assertThat().statusCode(201);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void requestToAddComment(){
        readFile = new ReadFile();
        setComment(readFile.readFile("src/main/resources/response/keyIssueAPI.txt"));
    }

}
