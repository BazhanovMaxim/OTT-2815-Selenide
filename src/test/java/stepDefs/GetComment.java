package stepDefs;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import restAPI.response.GetRequest;

public class GetComment {

    private GetRequest getCommentAPI;

    @Тогда("отправляется запрос на получение комментариев записи через API")
    public void отправляетсяЗапросНаПолучениеКомментариевЗаписиЧерезAPI(){
        getCommentAPI = new GetRequest();
        Assert.assertEquals(200, getCommentAPI.requestToGetComment());
    }

    @И("пользователь записывает информацию о комментариях в \"([^\"]*)\"$")
    public void пользовательЗаписываетИнформациюОКомментарияхВ(String arg0) {
    }
}
