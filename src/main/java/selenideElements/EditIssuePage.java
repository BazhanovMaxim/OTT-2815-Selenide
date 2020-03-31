package selenideElements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class EditIssuePage {

    // Elements
    private SelenideElement summaryField = $("#summary");
    private SelenideElement issuePrioritySpan = $("#priority-field");
    private SelenideElement updateButton = $("#edit-issue-submit");
    private SelenideElement issueTitle = $(By.xpath("//*[@id=\"edit-issue-dialog\"]/div[1]/h2"));
    private SelenideElement issueEditWasSuccess = $(".aui-message");

    // Methods
    public void setSummaryField(String issueSummary){
        summaryField.setValue(issueSummary);
    }
    public void setIssuePrioritySpan(String issuePriority){
        issuePrioritySpan.click();
        issuePrioritySpan.clear();
        issuePrioritySpan.setValue(issuePriority);
    }
    public void clickUpdate(){
        updateButton.click();
    }
    public void checkEditTitle(){
        issueTitle.shouldHave(Condition.text("Edit Issue"));
    }
    public void checkIssueEditWasSuccess(){
        issueEditWasSuccess.shouldBe(visible);
    }
}
