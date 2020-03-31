package selenideElements;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DeleteIssueTab {

    private SelenideElement title = $(".jira-dialog-heading h2");
    private SelenideElement deleteIssueButton = $("#delete-issue-submit");
    private SelenideElement deleteTitle = $(".aui-message");

    public void checkTitle(String nameTab){
        title.shouldHave(text(nameTab));
    }

    public void clickDeleteButton(){
        deleteIssueButton.click();
    }

    public void checkDeletedIssue(){
        deleteTitle.waitUntil(visible, 10000).shouldBe(visible);
    }
}
