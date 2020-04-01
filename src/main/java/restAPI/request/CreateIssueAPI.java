package restAPI.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import filesUtils.CreateFile;
import filesUtils.ReadFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;
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
        createIssue(userLogin, userPassword, pathToJsonFileForCreateWithAPI);
    }

    public void createIssue(String userLogin, String userPassword, String pathToJsonFileForCreateWithAPI){

        readFile = new ReadFile();

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
            Response response = request.post("/rest/api/2/issue/");
            // ожидаем, что статус отправки запроса = 201 (Успешно)
            response.then().assertThat().statusCode(201);
            // создаём файл с ключом созданной записи
            createFileKeyIssueAPI((String)response.path("key"));
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Создаётся файл с ключём для созданной записи через API
    private void createFileKeyIssueAPI(String key){
        createFile = new CreateFile();
        createFile.checkFile(key, "IssueKeyAPI.txt");
    }
}
