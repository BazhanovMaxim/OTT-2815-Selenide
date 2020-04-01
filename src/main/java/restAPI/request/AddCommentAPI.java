package restAPI.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import filesUtils.CreateFile;
import filesUtils.ReadFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AddCommentAPI {

    private ReadFile readFile;
    private CreateFile createFile;

    public void requestToAddComment(){
        readFile = new ReadFile();
        String userLogin = readFile.returnLogin();
        String userPassword = readFile.returnPass();
        String issueKey = readFile.readFile("src/main/resources/response/keyIssueAPI.txt");
        setComment(issueKey, userLogin, userPassword);
    }

    public void setComment(String key_value, String userLogin, String userPassword){

        try{
            // Считываем файл и добавляем в JsonObject
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/response/addComment.json"));
            JsonObject jb = new JsonParser().parse(bufferedReader).getAsJsonObject();
            //rest assured
            RestAssured.baseURI = "http://localhost:8080/";
            // авторизация = base64
            RequestSpecification request = RestAssured.given().auth().preemptive().basic(userLogin, userPassword);
            request.header("Content-Type", "application/json");
            request.body(jb.toString());
            // запрос
            Response response = request.post("/rest/api/2/issue/{IssueKey}/comment", key_value);
            // ожидаем, что статус отправки запроса = 201 (Успешно)
            response.then().assertThat().statusCode(201);
            // Получем тело ответа
            ResponseBody responseBody = response.getBody();
            // Закрываем считываение файла
            bufferedReader.close();
            setResponse(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Создаём файл ответа
    public void setResponse(ResponseBody responseBody){
        createFile = new CreateFile();
        createFile.writeToFileResponse(responseBody, "responseAddComment.json");
    }
}