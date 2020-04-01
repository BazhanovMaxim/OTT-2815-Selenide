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

public class CreateIssueAPI {

    private CreateFile createFile;
    private ReadFile readFile;

    /*
    * Создание записи через API
    */

    public void requestToCreateIssue(){
        readFile = new ReadFile();
        String userLogin = readFile.returnLogin();
        String userPassword = readFile.returnPass();
        String pathToJsonFileForCreateWithAPI = "src/main/resources/response/createIssue.json";
        String pathToPostRequest = "/rest/api/2/issue/";
        int expectedStatusCode = 201;
        createIssue(userLogin, userPassword, pathToJsonFileForCreateWithAPI, pathToPostRequest, expectedStatusCode);
    }

    public void createIssue(String userLogin, String userPassword, String pathToJsonFileForCreateWithAPI,
                            String pathToPostRequest, int expectedStatusCode){
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
            // запрос
            Response response = request.post(pathToPostRequest);
            // ожидаем, что статус отправки запроса = 201 (Успешно)
            response.then().assertThat().statusCode(expectedStatusCode);
            // создаём файл с ключом созданной записи
            bufferedReader.close();
            // Отправляем
            setResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Создаём файл с ответом и ключом записи
    public void setResponse(Response response){
        createFile = new CreateFile();
        ResponseBody responseBody = response.getBody();
        createFile.checkFile((String)response.path("key"), "IssueKeyAPI.txt");
        createFile.writeToFileResponse(responseBody, "responseCreateIssue.json");
    }
}
