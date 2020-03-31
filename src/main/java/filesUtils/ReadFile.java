package filesUtils;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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

    public Map keyValue (){
        try {
            // create Gson instance
            Gson gson = new Gson();
            Map map_login = new HashMap<String, String>();
            // create a reader
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/response/data.json"));
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

    public String returnLogin(){
        Map map = keyValue();
        String user_login = (String) map.get("login");
        return new String(Base64.decodeBase64(user_login.getBytes()));

    }

    public String returnPass(){
        Map map = keyValue();
        String user_password = (String) map.get("password");
        return new String(Base64.decodeBase64(user_password.getBytes()));
    }

}
