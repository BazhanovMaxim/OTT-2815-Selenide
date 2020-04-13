package filesUtils;

import com.google.gson.Gson;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class ReadFile {

    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

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

    public String userParameters(String parameter){

        FileInputStream fileInputStream;
        //инициализируем специальный объект Properties
        //типа Hashtable для удобной работы с данными
        Properties prop = new Properties();

        try {
            //обращаемся к файлу и получаем данные
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);
            return prop.getProperty(parameter);
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружено");
            e.printStackTrace();
        }

        return null;
    }

    // Возвращаем id-комментария записи
    public String returnIdComment(){
        String pathToReadFile = "target/TestsFiles/responseAddComment.json";
        Map map = keyValue(pathToReadFile);
        if (map != null) {
            return (String) map.get("id");
        }
        return null;
    }

    // Возвращаем логин пользователья для входа
    public String returnUserLogin(){
        return userParameters("login");
    }

    // Возвращаем пароль пользователья для входа
    public String returnUserPassword(){
        return userParameters("password");
    }

}
