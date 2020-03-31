package filesUtils;

import java.io.File;

public class DeleteFile {

    // Удаление файла
    public void deleteFile(String nameFile) {
        File myObj = new File("target\\TestsFiles\\" + nameFile);
        myObj.delete();
    }
}
