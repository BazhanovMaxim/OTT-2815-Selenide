package selenideElements;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class CreateIssuePage {

    // Elements
    private SelenideElement projectInput = $("#project-field");
    private SelenideElement issueType = $("#issuetype-field");
    private SelenideElement summaryProjectInput = $("#summary");
    private SelenideElement titleOfIssue = $(".jira-dialog-heading > h2");
    private SelenideElement assiqneeToMeLink = $("#assign-to-me-trigger");

    //Buttons
    private SelenideElement createButton = $("#create-issue-submit");

    // Methods
    public void enterProjectName(String projectName) {
        projectInput.append(projectName);
    }

    public void enterIssueType(String type) {
        issueType.append(type);
    }

    public void setAssiqnToMeLink() {
        assiqneeToMeLink.click();
    }

    public void enterSummary(String summary) {
        summaryProjectInput.setValue(summary);
    }

    public void clickCreateButton() {
        createButton.click();
    }

    public void checkTitleOfIssue(String nameTab) {
        titleOfIssue.waitWhile(enabled, 10000).shouldHave(exactText(nameTab));
    }
}
