package randonNameIssue;

import java.util.Random;

public class RandomName {

    /*
    * Рандомное название нужно для названия в записи.
    * Далее это название записывается в файл в тесте (KeyIssueAPI.txt)
     */

    private final Random random = new Random();
    private String nameIssue = "TEST_";

    public String getNameIssue() {
        return nameIssue;
    }

    private void setNameIssue(String nameIssue) {
        this.nameIssue = this.nameIssue + nameIssue;
    }

    public void generateRandomSummary(){
        String result =  String.valueOf(random.nextInt(1000));
        setNameIssue(result);
    }
}
