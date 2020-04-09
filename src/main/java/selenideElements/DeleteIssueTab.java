package selenideElements;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class DeleteIssueTab {

    private SelenideElement title = $(".jira-dialog-heading h2");
    private SelenideElement deleteIssueButton = $("#delete-issue-submit");
    private SelenideElement deleteTitle = $(".aui-message");

    public String checkTitle(){
        return title.getText();
    }

    public void clickDeleteButton(){
        deleteIssueButton.click();
    }

    public SelenideElement checkDeletedIssue(){
        return deleteTitle;
    }
}
