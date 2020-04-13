package selenideElements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DeleteCommentPanel {

    private SelenideElement issueTitleDeleteComment = $(".jira-dialog-heading > h2");

    public String checkIssueTitleDeleteComment(){
        return issueTitleDeleteComment.getText();
    }

    public void clickIssueDeleteButton(String buttonToDelete){
        $(By.name(buttonToDelete)).click();
    }

}
