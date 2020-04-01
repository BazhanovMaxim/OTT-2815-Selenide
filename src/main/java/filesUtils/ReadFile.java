package filesUtils;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class ReadFile {

    // Чтение файла
    public String readFile(String pathFile){
        String issueName = "";
        try {
            File myObj = new File(pathFile);
            BufferedReader myReader = new BufferedReader(new FileReader(myObj));
            issueName = myReader.readLine();
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return issueName;
    }

    private Map keyValue (String pathToReadFile){
        try {
            // create Gson instance
            Gson gson = new Gson();
            Map map_login;
            // create a reader
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToReadFile));
            // convert JSON file to map
            map_login = gson.fromJson(bufferedReader, Map.class);
            // close reader
            bufferedReader.close();
            return map_login;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }



    // Возвращаем id-комментария записи
    public String returnIdComment(){
        String pathToReadFile = "target/TestsFiles/responseAddComment.json";
        Map map = keyValue(pathToReadFile);
        String idComment = (String) map.get("id");
        return idComment;
    }

    // Возвращаем логин пользователья для входа
    public String returnLogin(){
        String pathToReadFile = "src/main/resources/response/userData.json";
        Map map = keyValue(pathToReadFile);
        String user_login = (String) map.get("login");
        return new String(Base64.decodeBase64(user_login.getBytes()));
    }

    // Возвращаем пароль пользователья для входа
    public String returnPass(){
        String pathToReadFile = "src/main/resources/response/userData.json";
        Map map = keyValue(pathToReadFile);
        String user_password = (String) map.get("password");
        return new String(Base64.decodeBase64(user_password.getBytes()));
    }

}
