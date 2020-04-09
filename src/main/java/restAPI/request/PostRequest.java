package restAPI.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import filesUtils.CreateFile;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PostRequest {

    private CreateFile createFile;

    // Запрос
    public Response requestToPost(String userLogin, String userPassword, String pathToJsonFileForCreateWithAPI,
                              String pathToPostRequest){
        try{
            // Считываем файл и добавляем в JsonObject
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToJsonFileForCreateWithAPI));
            JsonObject jb = new JsonParser().parse(bufferedReader).getAsJsonObject();
            //rest assured
            RestAssured.baseURI = "http://localhost:8080/";
            // авторизация = base64
            RequestSpecification request = RestAssured.given().auth().preemptive().basic(userLogin, userPassword);
            request.header("Content-Type", "application/json");
            request.body(jb.toString());
            request.filter(new AllureRestAssured());
            // запрос
            Response response = request.post(pathToPostRequest);
            // создаём файл с ключом созданной записи
            bufferedReader.close();
            // Отправляем
            setResponse(response);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * Создаём файл с ответом и ключом записи
    * Если найден путь "key" - то это ответ с получением создание записи
    * иначе - создание комментария
     */
    public void setResponse(Response response){
        createFile = new CreateFile();
        ResponseBody responseBody = response.getBody();
        if ((String)response.path("key") != null){
            createFile.checkFile((String)response.path("key"), "IssueKeyAPI.txt");
            createFile.writeToFileResponse(responseBody, "responseCreateIssue.json");
        }
        else{
            createFile.writeToFileResponse(responseBody, "responseAddComment.json");
        }

    }
}
