package stepDefs;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import restAPI.response.GetCommentAPI;

public class GetComment {

    private GetCommentAPI getCommentAPI;

    @Тогда("отправляется запрос на получение комментариев записи через API")
    public void отправляетсяЗапросНаПолучениеКомментариевЗаписиЧерезAPI(){
        getCommentAPI = new GetCommentAPI();
        getCommentAPI.requestToGetComment();
    }

    @И("пользователь записывает информацию о комментариях в \"([^\"]*)\"$")
    public void пользовательЗаписываетИнформациюОКомментарияхВ(String arg0) {
    }
}
