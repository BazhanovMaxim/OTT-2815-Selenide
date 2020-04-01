package selenideElements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DeleteCommentPanel {

    private SelenideElement issueTitleDeleteComment = $(".jira-dialog-heading > h2");

    public void checkIssueTitleDeleteComment(String nameTab){
        issueTitleDeleteComment.waitWhile(enabled, 10000).shouldHave(exactText(nameTab));
    }

    public void clickIssueDeleteButton(String buttonToDelete){
        $(By.name(buttonToDelete)).click();
    }

}
